package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FlowerDAO;
import model.Flower;

public class FlowerServlet extends HttpServlet {
    private FlowerDAO flowerDAO;

    @Override
    public void init() {
	flowerDAO = new FlowerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String action = req.getServletPath();

	try {
    		switch (action) {
		case "/new" :
		    showNewForm(req, resp);
		    break;
		case "/insert" :
		    break;
		case "/update" :
		    break;
		case "/showEditForm" :
		    break;
		case "/delete" :
		    break;
		default:
		    listFlower(req, resp);
		    break;
		}
	} catch (SQLException e) {
	    throw new ServletException(e);
	}
    }

    private void listFlower(HttpServletRequest req, HttpServletResponse resp)
		    throws SQLException, ServletException, IOException {
	List<Flower> flowers = flowerDAO.getAll();
	req.setAttribute("flowers", flowers);
	RequestDispatcher dispatcher = req.getRequestDispatcher("flower-list.jsp");
	dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher dispatcher = req.getRequestDispatcher("flower-form.jsp");
	dispatcher.forward(req, resp);
    }
}
