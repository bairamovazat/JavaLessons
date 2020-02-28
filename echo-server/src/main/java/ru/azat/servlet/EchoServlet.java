package ru.azat.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EchoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String env = System.getProperty("NODE_NAME");

        response.setContentType("text/html");//setting the content type
        PrintWriter printWriter = response.getWriter();
        printWriter.write(env == null ? "Undefined node name" : ("Node name:" + env));
        printWriter.close();//closing the stream

        super.doGet(request, response);
    }
}
