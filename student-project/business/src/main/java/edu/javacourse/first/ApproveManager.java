package edu.javacourse.first;

import edu.javacourse.first.answer.CheckerAnswer;
import edu.javacourse.first.domain.StudentOrder;

import java.io.*;
import java.util.List;

public class ApproveManager {

    public void approveOrder(StudentOrder so, List<CheckerAnswer> answers) {

        System.out.println("ApproveManager.approveOrder started");

        try (FileWriter writer = new FileWriter("result.txt", false)) {
            writer.append("<< -- StudentOrder -- >>" + System.lineSeparator());
            for (CheckerAnswer ca : answers) {
                writer.write(ca.getMessage().toCharArray());
//                writer.append(ca.getMessage());
                writer.append(System.lineSeparator());
            }
            writer.append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        начинаем читать из файла
        try {
//            файл в поток байтов
            FileInputStream fis = new FileInputStream("result.txt");
//            байты в поток символов
            InputStreamReader isr = new InputStreamReader(fis);
//            символы в строки
            BufferedReader br = new BufferedReader(isr);
            String line;
//            System.out.println("<> ---- ReadFile ---- <>");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
//            закрываем все открытые потоки
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

////      запишем объект в файл
//        try {
//            FileOutputStream fos = new FileOutputStream("student.dat");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(so);
//            oos.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////      прочитаем объект из файла
//        try {
//            FileInputStream fis = new FileInputStream("student.dat");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            StudentOrder son = (StudentOrder) ois.readObject();
//            System.out.println("ReadFromFile: ");
//            System.out.print("son: " + son.hashCode() + " ");
//            System.out.println(son.getHusband().getGivenName());
//            System.out.print("so: " + so.hashCode() + " ");
//            System.out.println(so.getHusband().getGivenName());
//            System.out.println("son (" + son.equals(so) + ") equals so ");
//            ois.close();
//            fis.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

    }

    public void denyOrder(StudentOrder so, List<CheckerAnswer> answers) {
        System.out.println("ApproveManager.denyOrder");
        System.out.println();
    }
}
