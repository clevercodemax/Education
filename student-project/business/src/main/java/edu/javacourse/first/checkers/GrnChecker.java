package edu.javacourse.first.checkers;

import edu.javacourse.first.answer.CheckerAnswer;
import edu.javacourse.first.checkers.answer.BasicCheckerAnswer;
import edu.javacourse.first.domain.Person;
import edu.javacourse.first.exception.SendGetDataException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

public class GrnChecker extends BasicChecker implements Callable<CheckerAnswer> {

    //    использование Map
    private static Map<String, String> settings = new HashMap<>();

    static {
        PropertyResourceBundle pt = (PropertyResourceBundle)
                PropertyResourceBundle.getBundle("grn_checker", new Locale("en"));
        settings.put("host", pt.getString("grn.host"));
        settings.put("port", pt.getString("grn.port"));
        settings.put("login", pt.getString("grn.login"));
        settings.put("password", pt.getString("grn.password"));

        String test = pt.getString("grn.test");
        System.out.println(test);
    }

    private Person person;

    public GrnChecker(Person person) {
        super(settings.get("host"), Integer.parseInt(settings.get("port")),
                settings.get("login"), settings.get("password"));
        this.person = person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public CheckerAnswer call() throws Exception {
        System.out.println(new Date());
        CheckerAnswer c = check();
        System.out.println(new Date());
        return c;
    }

    protected CheckerAnswer sendAndGetData() throws SendGetDataException {
        try {
            OutputStream os = socket.getOutputStream();
            StringBuilder sb = new StringBuilder(buildXmlForPerson());
            os.write(sb.toString().getBytes());
            os.flush();

            StringBuilder ans = new StringBuilder();
            Reader br = new InputStreamReader(socket.getInputStream());
            char[] request = new char[256];
            int count = br.read(request);
            while (count != -1) {
                ans.append(new String(request, 0, count));

                if (ans.toString().endsWith("</answer>")) {
                    break;
                }
                count = br.read(request);
            }

            CheckerAnswer answer = buildAnswer(ans.toString());
            return answer;

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
            throw new SendGetDataException(e.getMessage());
        }
    }

    private String buildXmlForPerson() throws IOException, XMLStreamException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        XMLStreamWriter xml = factory.createXMLStreamWriter(bos);

        xml.writeStartDocument();
        xml.writeStartElement("person");

        xml.writeStartElement("surName");
        xml.writeCharacters(person.getSurName());
        xml.writeEndElement();
        xml.writeStartElement("givenName");
        xml.writeCharacters(person.getGivenName());
        xml.writeEndElement();
        xml.writeStartElement("patronymic");
        xml.writeCharacters(person.getPatronymic());
        xml.writeEndElement();
        xml.writeStartElement("dateOfBirth");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        xml.writeCharacters(sdf.format(new Date()));
        xml.writeEndElement();

        xml.writeEndElement();
        xml.writeEndDocument();

        String answer = new String(bos.toByteArray(), 0, bos.size(), "utf-8");
        bos.close();
        System.out.println(answer);

        return answer;
    }

    private CheckerAnswer buildAnswer(String s) {
        System.out.println(s);
        final String RES_B = "<result>";
        final String RES_E = "</result>";
        final String MES_B = "<message>";
        final String MES_E = "</message>";

        int r1 = s.indexOf(RES_B);
        int r2 = s.indexOf(RES_E);
        int m1 = s.indexOf(MES_B);
        int m2 = s.indexOf(MES_E);
        boolean result = Boolean.parseBoolean(s.substring(r1 + RES_B.length(), r2));
        String message = s.substring(m1 + MES_B.length(), m2);

        BasicCheckerAnswer answer = new BasicCheckerAnswer(result, message + ": " + person.getGivenName());
        return answer;
    }

}
