package com.wellsfargo.fsd.cpk.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;
import com.wellsfargo.fsd.cpk.service.ProductService;
import com.wellsfargo.fsd.cpk.service.ProductServiceImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet({ "/login", "/logout", "/list", "/newItem", "/addItem", "/deleteItem", "/editItem", "/saveItem" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService;

	@Override
	public void init() throws ServletException {
		productService = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getServletPath();
		String viewName = "";

		switch (url) {
		case "/login":
			viewName = doLogin(request, response);
			break;
		case "/list":
			viewName = doList(request, response);
			break;
		case "/newItem":
			viewName = doNewItem(request, response);
			break;
		case "/addItem":
			viewName = doAddItem(request, response);
			break;
		case "/deleteItem":
			viewName = doDeleteItem(request, response);
			break;
		case "/editItem":
			viewName = doEditItem(request, response);
			break;
		case "/saveItem":
			viewName = doSaveItem(request, response);
			break;
		case "/logout":
			viewName = doLogout(request, response);
			break;
		default : viewName = "notfound.jsp"; break;	
		}

		request.getRequestDispatcher(viewName).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("msg", "Admin logged in successfully");
		HttpSession session = request.getSession();
		String uName = request.getParameter("loginid");
		session.setAttribute("loginid", uName);
		String view = "adminprintmessage.jsp";

		return view;
	}

	private String doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = "index.jsp";
		request.setAttribute("msg", "Admin logged out successfully");

		return view;
	}

	private String doList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = "";

		try {
			List<Product> items = productService.getAllItems();
			request.setAttribute("items", items);
			view = "listProducts.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "adminerrpage.jsp";
		}

		return view;
	}

	private String doNewItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Product item = new Product();
		request.setAttribute("item", item);

		return "productForm.jsp";
	}

	private String doAddItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Product item = new Product();

		item.setId(Integer.parseInt(request.getParameter("id")));
		item.setProductName(request.getParameter("pname"));
		item.setCost(Integer.parseInt(request.getParameter("cost")));
		item.setProductDescription(request.getParameter("pdesc"));

		String view = "";

		try {
			productService.validateAndAdd(item);
			request.setAttribute("msg", "Item Got Added!");
			view = "adminprintmessage.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "adminerrpage.jsp";
		}
		return view;
	}

	private String doDeleteItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String view = "";

		try {
			productService.deleteItem(id);
			request.setAttribute("msg", "Item Got Deleted!");
			view = "adminprintmessage.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "adminerrpage.jsp";
		}
		return view;
	}

	private String doEditItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String view = "";

		try {
			Product item = productService.getItemById(id);
			request.setAttribute("item", item);
			request.setAttribute("msg", "Product is ready for editing!");
			view = "productForm.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "adminerrpage.jsp";
		}
		return view;
	}

	private String doSaveItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product item = new Product();

		item.setId(Integer.parseInt(request.getParameter("id")));
		item.setProductName(request.getParameter("pname"));
		item.setCost(Integer.parseInt(request.getParameter("cost")));
		item.setProductDescription(request.getParameter("pdesc"));

		String view = "";

		try {
			productService.validateAndSave(item);
			request.setAttribute("msg", "Product Got Saved!");
			view = "adminprintmessage.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "adminerrpage.jsp";
		}
		return view;
	}
}