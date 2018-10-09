package edu.javacourse.first.db;

import edu.javacourse.first.domain.PersonAdult;
import edu.javacourse.first.domain.PersonChild;
import edu.javacourse.first.domain.StudentOrder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlDataSource implements StudentOrderDataSource {

    @Override
    public Long addStudentOrder(StudentOrder so) {
        return null;
    }

    @Override
    public List<StudentOrder> getStudentOrders() {
        return null;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        List<StudentOrder> soList = new ArrayList<>();

        try {
            //Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("student_orders.xml");

            Node root = document.getFirstChild();
//            System.out.println(root.getNodeName());
            NodeList ordNodeList = root.getChildNodes();
            for (int i = 0; i < ordNodeList.getLength(); i++) {
                Node item = ordNodeList.item(i);
                if (item instanceof Element) {
                    System.out.println(((Element) item).getAttribute("so-id"));
                    System.out.println(item.getTextContent());
                    NodeList persons = item.getChildNodes();
                    PersonAdult husband = new PersonAdult();
                    PersonAdult wife = new PersonAdult();
                    List<PersonChild> children = new ArrayList<>();
//                    System.out.println(item.getNodeName());
                    for (int j = 0; j < persons.getLength(); j++) {
                        Node person = persons.item(j);
                        if (person instanceof Element) {
                            if ("husband".equals(person.getNodeName())) {
                                NodeList names = person.getChildNodes();
                                for (int k = 0; k < names.getLength(); k++) {
                                    Node name = names.item(k);
                                    if ("surName".equals(name.getNodeName())) {
                                        husband.setSurName(name.getTextContent());
                                    }
                                    if ("givenName".equals(name.getNodeName())) {
                                        husband.setGivenName(name.getTextContent());
                                    }
                                    if ("patronymic".equals(name.getNodeName())) {
                                        husband.setPatronymic(name.getTextContent());
                                    }
                                    if ("dateOfBirth".equals(name.getNodeName())) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                        Date dob = sdf.parse(name.getTextContent());
//                                        String s = sdf.format(dob);
                                        husband.setDateOfBirth(dob);
//                                        System.out.println(s);
                                    }
                                }
                            }
                            if ("wife".equals(person.getNodeName())) {
//                                System.out.println("wife");
                            }
                            if ("children".equals(person.getNodeName())) {
//                                System.out.println("children");
                            }
                        }
                    }
                    StudentOrder so = new StudentOrder(1L, new Date(), husband, wife, children);
                    soList.add(so);
                }

            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (StudentOrder so : soList) {
            System.out.println();
            System.out.println(so.getHusband().getSurName());
            System.out.println(so.getHusband().getDateOfBirth());
        }
    }

}
