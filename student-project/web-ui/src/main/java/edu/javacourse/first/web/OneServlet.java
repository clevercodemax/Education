package edu.javacourse.first.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OneServlet", urlPatterns = "/one")
public class OneServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("Name");
        HttpSession session = req.getSession();
        session.setAttribute("NAME", name);
        resp.getWriter().append("OK");

    }

}
