package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlowerServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	System.out.println("Servlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter writer = resp.getWriter();
	writer.println("Servlet work with method DO");
	System.out.println("Servlet work with method DO");
	// проверить жизненные циклы сервлетов и сколько инстансов запускается
    }
}
