package com.qiuhui.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.CustomerService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/my/del")
public class MyCustomerDelServlet extends BasicServlet{
	
	CustomerService customerService = new CustomerService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		int staffId = getCurrStaff(req).getId();
		try {
			customerService.delByCustId(custId, staffId);
			resp.sendRedirect("/customer/my/list");
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		} catch (ForbiddenException e) {
			resp.sendError(403, e.getMessage());
		}
	}
}
