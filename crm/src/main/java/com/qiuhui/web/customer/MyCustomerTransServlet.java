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

@WebServlet("/customer/my/trans")
public class MyCustomerTransServlet extends BasicServlet{

	CustomerService customerService = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		String toStaffId = req.getParameter("toStaffId");
		int currStaffId = getCurrStaff(req).getId();
		
		try {
			customerService.transByCustId(custId, currStaffId, toStaffId);
			resp.sendRedirect("/customer/my/list");
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		} catch (ForbiddenException e) {
			resp.sendError(403, e.getMessage());
		}
		
		
	}
}
