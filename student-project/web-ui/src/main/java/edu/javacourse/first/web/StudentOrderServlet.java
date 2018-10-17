package edu.javacourse.first.web;

import edu.javacourse.first.ProcessStarter;
import edu.javacourse.first.domain.PersonAdult;
import edu.javacourse.first.domain.StudentOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "StudentOrderServlet", urlPatterns = {"/add"})
public class StudentOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateStr = req.getParameter("dateSO");
        String hSurName = req.getParameter("h_surName");
        String hGivenName = req.getParameter("h_givenName");
        String hPatronymic = req.getParameter("h_patronymic");

        PersonAdult h = new PersonAdult();
        h.setSurName(hSurName);
        h.setGivenName(hGivenName);
        h.setPatronymic(hPatronymic);

        PersonAdult w = new PersonAdult();

        StudentOrder so = new StudentOrder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            so.setStudentOrderDate(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        so.setHusband(h);
        so.setWife(w);

        ProcessStarter ps = new ProcessStarter();
        ps.addStudentOrder(so);

        System.out.println("URL for StudentOrder is working");

        resp.getWriter().append("OK");
    }

}
