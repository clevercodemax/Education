package edu.javacourse.first.db;

import edu.javacourse.first.domain.*;

import javax.xml.bind.*;
import java.io.*;


public class XmlJaxbExample {
    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("student_orders.xml");
            // Пример чтения файла
            StudentOrders sos = readXML(is);

            writeXML(sos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    private static StudentOrders readXML(InputStream is) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(StudentOrders.class);
        Unmarshaller u = jc.createUnmarshaller();

        // Считываем файл в иерархию объектов
        StudentOrders po = (StudentOrders) u.unmarshal(is);
//
        for (StudentOrder so : po.getStudentOrders()) {
            System.out.println(so.getStudentOrderId());
            System.out.println(so.getHusband().getSurName());
            System.out.println(so.getHusband().getPassportSeria());
            System.out.println(so.getWife().getGivenName());
            System.out.println(so.getWife().getPassportDateIssue());
            System.out.println(so.getWife().getPatronymic());
            for (PersonChild pc : so.getChildren()) {
                System.out.println(pc.getGivenName());
                System.out.println(pc.getDateOfBirth());
            }
            System.out.println("==============");
        }
        return po;
    }

    private static void writeXML(StudentOrders sos) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(StudentOrders.class);
        Marshaller m = jc.createMarshaller();

        for (StudentOrder so : sos.getStudentOrders()) {
            so.setStudentOrderId(so.getStudentOrderId() + 10);
        }

        try (FileOutputStream fos = new FileOutputStream("result.xml")) {
            m.marshal(sos, fos);
        } catch (IOException ex) {
            throw ex;
        }

    }
}