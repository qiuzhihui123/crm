package com.qiuhui.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Customer;
import com.qiuhui.entity.SaleChance;
import com.qiuhui.entity.Staff;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.CustomerService;
import com.qiuhui.service.SaleService;
import com.qiuhui.service.StaffService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/my/detail")
public class MyCustomerDetailServlet extends BasicServlet{
	
	StaffService staffService = new StaffService();
	CustomerService customerService = new CustomerService();
	SaleService saleService = new SaleService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		int staffId = getCurrStaff(req).getId();
		
		try {
			Customer cust = customerService.findCustomerById(custId,staffId);
			List<Staff> staffList = staffService.findAllStaffs();
			
			List<SaleChance> chanceList = saleService.findSaleChanceBYCustId(custId);
			
			req.setAttribute("cust", cust);
			req.setAttribute("staffList", staffList);
			req.setAttribute("chanceList", chanceList);
			
			forward("customer/mydetail",req,resp);
		} catch (ForbiddenException e) {
			resp.sendError(403,e.getMessage());
		} catch(ServiceException e){
			resp.sendError(404,e.getMessage());
			
		}
	}
}
