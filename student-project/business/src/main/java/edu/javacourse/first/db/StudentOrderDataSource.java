package edu.javacourse.first.db;

import edu.javacourse.first.domain.StudentOrder;

import java.util.List;

public interface StudentOrderDataSource {
    Long addStudentOrder(StudentOrder so);
    List<StudentOrder> getStudentOrders();
}
