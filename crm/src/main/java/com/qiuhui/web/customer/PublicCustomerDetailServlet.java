package com.qiuhui.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Customer;
import com.qiuhui.entity.Staff;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.CustomerService;
import com.qiuhui.service.StaffService;
import com.qiuhui.util.Config;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/public/detail")
public class PublicCustomerDetailServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService = new CustomerService();
	StaffService staffService = new StaffService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		System.out.println(custId);
		try {
			Customer cust = customerService.findCustomerById(custId,Config.PUBLIC_ID);
			List<Staff> staffList = staffService.findAllStaffs();
			
						
			req.setAttribute("cust", cust);
			req.setAttribute("staffList", staffList);
			
			forward("customer/publicdetail", req, resp);
		} catch(ServiceException e) {
			resp.sendError(404,e.getMessage());
		} catch(ForbiddenException e) {
			resp.sendError(403,e.getMessage());
		}
	}

}
