package com.sarthak.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sarthak on 13-09-2016.
 */
public class HelloWorld extends HttpServlet {
    String message;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.println(message);
    }

    @Override
    public void init() throws ServletException {
        message="Hello World";
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
