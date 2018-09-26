package edu.javacourse.first.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student-orders")
public class StudentOrders {

    @XmlElement(name = "student-order", required = true)
    private List<StudentOrder> studentOrders;

    public List<StudentOrder> getStudentOrders() {
        return studentOrders;
    }

    public void setStudentOrders(List<StudentOrder> studentOrders) {
        this.studentOrders = studentOrders;
    }
}
