package com.qiuhui.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Staff;
import com.qiuhui.service.CustomerService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/public/my")
public class PublicCustomerToMyServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		
		Staff staff = getCurrStaff(req);
		
		customerService.pubToMyCustomer(custId,staff);
		resp.sendRedirect("/customer/my/list");
	}

}
