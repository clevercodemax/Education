package edu.javacourse.first.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "OtherServlet", urlPatterns = "/other")
public class OtherServlet extends HttpServlet {
}
