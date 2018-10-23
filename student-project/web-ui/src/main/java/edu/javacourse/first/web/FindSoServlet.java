package edu.javacourse.first.web;

import edu.javacourse.first.ProcessStarter;
import edu.javacourse.first.domain.StudentOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindSoServlet", urlPatterns = "/find")
public class FindSoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProcessStarter ps = new ProcessStarter();
        List<StudentOrder> sos = ps.findStudentOrders(null);

        req.setAttribute("ORDER_LIST", sos);

//        getServletContext().getRequestDispatcher("/find.jsp").forward(req, resp);
        getServletContext().getRequestDispatcher("/find_jstl.jsp").forward(req, resp);

    }
}
