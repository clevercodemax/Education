package edu.javacourse.grn;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class RequestHandler implements Runnable {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            processRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequest() throws IOException {
        StringBuilder sb = new StringBuilder();
        Reader br = new InputStreamReader(socket.getInputStream());

        char[] request = new char[256];
        int count = br.read(request);
        while (count != -1) {
            sb.append(new String(request, 0, count));

            if (sb.toString().endsWith("</person>")) {
                break;
            }
            count = br.read(request);
        }

        System.out.println(sb.toString());

        boolean result = true;
        String message = "";
        try {
            GrnPerson person = buildPerson(sb.toString());
            System.out.println(person);

            result = checkPerson(person);
            message = "Job Done";

        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
            message = "GRN-system ERROR";
        }


        OutputStream os = socket.getOutputStream();
        os.write(("<?xml version=\"1.0\" ?><answer>" +
                "<result>" + result + "</result>" +
                "<message>" + message + "</message>" +
                "</answer>").getBytes());

        socket.close();
    }

    private boolean checkPerson(GrnPerson person) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private GrnPerson buildPerson(String s) throws Exception {
        byte[] buffer = s.getBytes();
        ByteInputStream bis = new ByteInputStream(buffer, buffer.length);
        GrnPerson person = new GrnPerson();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(bis);
        NodeList childNodes = doc.getFirstChild().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                if ("surName".equals(node.getNodeName())) {
                    person.setSurName(node.getTextContent().trim());
                }
                if ("givenName".equals(node.getNodeName())) {
                    person.setGivenName(node.getTextContent().trim());
                }
                if ("patronymic".equals(node.getNodeName())) {
                    person.setPatronymic(node.getTextContent().trim());
                }
                if ("dateOfBirth".equals(node.getNodeName())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    person.setDateOfBirth(sdf.parse(node.getTextContent()));
                }
            }
        }
        return person;
    }
}
