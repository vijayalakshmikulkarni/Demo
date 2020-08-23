package com.wellsfargo.fsd.cpk.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminpageFilter
 */
@WebFilter("/login")
public class AdminpageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminpageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String uName=request.getParameter("loginid");
		String pwd = request.getParameter("password");
		if (uName.equals("admin")&& pwd.equals("admin"))
		chain.doFilter(request, response);
		else
			request.setAttribute("msg", "Login Failed, please enter valid admin credentials");
			request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
