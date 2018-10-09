package edu.javacourse.first.db;

import edu.javacourse.first.domain.PersonAdult;
import edu.javacourse.first.domain.PersonChild;
import edu.javacourse.first.domain.StudentOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeDataSource implements StudentOrderDataSource {

    private static final int CHILD_COUNT = 2;

    @Override
    public Long addStudentOrder(StudentOrder so) {
        return null;
    }

    @Override
    public List<StudentOrder> getStudentOrders() {
        List<StudentOrder> result = new ArrayList<StudentOrder>(10);
        for (int i = 0; i < 1; i++) {
            result.add(getStudentOrder("Husband " + i, "Wife " + i, "Child #" + i));
        }

        return result;
    }

    private static StudentOrder getStudentOrder(String hName, String wName, String cName) {

        PersonAdult h = new PersonAdult();
        h.setGivenName(hName);
        PersonAdult w = new PersonAdult();
        w.setGivenName(wName);
        List<PersonChild> children = new ArrayList<>();
        for (int i = 0; i < CHILD_COUNT; i++) {
            PersonChild c = new PersonChild();
            c.setGivenName(cName + " " + i);
            children.add(c);
        }
        StudentOrder so = new StudentOrder(1L, new Date(), h, w, children);
        return so;
    }

}
