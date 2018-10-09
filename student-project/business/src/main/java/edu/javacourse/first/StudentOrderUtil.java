package edu.javacourse.first;

import edu.javacourse.first.domain.Person;
import edu.javacourse.first.domain.PersonAdult;
import edu.javacourse.first.domain.PersonChild;
import edu.javacourse.first.domain.StudentOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentOrderUtil {

    public static StudentOrder createStudentOrder() {

        StudentOrder so = new StudentOrder();

        so.setStudentOrderDate(new Date());

        PersonAdult h = adult("H");
        PersonAdult w = adult("W");

        so.setHusband(h);
        so.setWife(w);

        List<PersonChild> children = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            children.add(child(i));
        }
        so.setChildren(children);

        return so;
    }

    private static PersonAdult adult(String prefix) {
        PersonAdult p = new PersonAdult();
        p.setSurName(prefix + "_SurName");
        p.setGivenName(prefix + "_GivenName");
        p.setPatronymic(prefix + "_Patronymic");
        p.setDateOfBirth(new Date());
        p.setPassportSeria(prefix + "_Seria");
        p.setPassportNumber("123456");
        p.setPassportDateIssue(new Date());
        p.setPassportDateExpire(new Date());
        return p;
    }

    private static PersonChild child(int counter) {
        PersonChild c = new PersonChild();
        c.setSurName("Child_" + counter + "_SurName");
        c.setGivenName("Child_" + counter + "_GivenName");
        c.setPatronymic("Child_" + counter + "_Patronymic");
        c.setDateOfBirth(new Date());
        c.setBirthDocument("Child_Birth_" + counter);
        return c;
    }

}
