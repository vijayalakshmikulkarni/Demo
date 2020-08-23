package com.wellsfargo.fsd.cpk.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellsfargo.fsd.cpk.dao.KitDetailDao;
import com.wellsfargo.fsd.cpk.entity.CoronaKit;
import com.wellsfargo.fsd.cpk.entity.KitDetail;
import com.wellsfargo.fsd.cpk.entity.OrderSummary;
import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;
import com.wellsfargo.fsd.cpk.service.ProductService;
import com.wellsfargo.fsd.cpk.service.ProductServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet({ "/newuser","/insertuser","/showproducts","/newitemkit","/additemkit","/deleteitemkit","/showkit","/placeorder","/saveorder","/ordersummary" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int kitId;
	private ProductService productService;
	private KitDetailDao kitDao;
	private CoronaKit ck;
	private OrderSummary orderSummary;

	@Override
	public void init() throws ServletException {
		productService = new ProductServiceImpl();
		kitDao= new KitDetailDao();
		ck= new CoronaKit();
		orderSummary = new OrderSummary();
		
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getServletPath();
		String viewName="";
		
		switch(url) {
		
		case "/newuser":
			viewName = showNewUserForm(request, response);
			break;
		case "/insertuser":
			viewName = insertNewUser(request, response);
			break;
		case "/showproducts":
			viewName = showAllProducts(request, response);
			break;	
		case "/newitemkit":viewName=doNewItemToKit(request, response);break;
		case "/additemkit":viewName=addNewItemToKit(request, response); break;
		
		case "/deleteitemkit":
			viewName = deleteItemFromKit(request, response);
			break;
		case "/showkit":
			viewName = showKitDetails(request, response);
			break;
		case "/placeorder":
			viewName = showPlaceOrderForm(request, response);
			break;
		case "/saveorder":
			viewName = saveOrderForDelivery(request, response);
			break;	
		case "/ordersummary":
			viewName = showOrderSummary(request, response);
			break;	
		default : viewName = "notfound.jsp"; break;	
		
		}
		
		request.getRequestDispatcher(viewName).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		return "newuser.jsp";		
		
	}
	
	private String insertNewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String view="";
				
		ck.setId(1);
		ck.setPersonName(request.getParameter("pname"));
		ck.setEmail(request.getParameter("email"));
		ck.setContactNumber(request.getParameter("pcontact"));
		
		//below code will delete previously stored kitDetails order items for new user
		try {
			kitDao.deleteAll();
			
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}	
		
		view="userprintMessage.jsp";
		
		request.setAttribute("msg","User details entered successfully");
		return view;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String view="";
		
		try {
			List<Product> items = productService.getAllItems();
			request.setAttribute("items", items);
			view="showproductstoadd.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		return view;
	}
	private String doNewItemToKit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				
		return "additemkit.jsp";
	}
	
	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String view="";
		KitDetail kit = new KitDetail();
		
		
		int productId= Integer.parseInt(request.getParameter("productId"));
		int quantity= Integer.parseInt(request.getParameter("quantity"));
		
		kitId+=1;
		kit.setId(kitId);
		kit.setCoronaKitId(1);
		kit.setProductId(productId);
		kit.setQuantity(quantity);
		
		Product p=null;
		
		try {
			p = productService.getItemById(productId);
			request.setAttribute("msg", "Item Got added To Kit!");
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		int cost=p.getCost();
		int amount =cost*quantity;
		
		kit.setAmount(amount);	
		try {
			kitDao.add(kit);
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		view="userprintMessage.jsp";
				
		return view;
	}
	
	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String view="";
		int id = Integer.parseInt(request.getParameter("productId"));
		
				
		try {
			kitDao.deleteById(id);
			request.setAttribute("msg", "Item Got Removed From The Kit!");
			
			view="userprintMessage.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}		
						
		return view;
	}
	
	private String showKitDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
String view="";

		
		try {
			List<KitDetail> items = kitDao.getAll();
			request.setAttribute("items", items);
			view="showkit.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
					
				
		return view;
	}
	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String view="placeorder.jsp";
		
						
				
		return view;
	}
	
	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String view="";
		int totalAmount=0;
		String address= request.getParameter("paddress");
		ck.setDeliveryAddress(address);
		
		String order =request.getParameter("orderfinal");
		boolean orderFinal=order.equals("yes")?true:false;
		
		try {
			totalAmount = kitDao.getSumAmount();
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		ck.setOrderFinalized(orderFinal);
		ck.setOrderDate(LocalDate.now());
		ck.setTotalAmount(totalAmount);
		
						
		if (!orderFinal) {
			request.setAttribute("msg", "You cannot place the order without finalizing the order");
			
		}else 
			request.setAttribute("msg", "order got placed successfully");
		
		view="userprintMessage.jsp";
		
		return view;
	}
	
	
	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String view="";
		
		request.setAttribute("msg", ck);
		try {
			List<KitDetail> items = kitDao.getAll();
			request.setAttribute("items", items);
			view="ordersummary.jsp";
		} catch (CpkException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}			
		
		
		return view;
	}	
}	