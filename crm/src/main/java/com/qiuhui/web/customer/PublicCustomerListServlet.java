package com.qiuhui.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.Customer;
import com.qiuhui.service.CustomerService;
import com.qiuhui.util.Config;
import com.qiuhui.util.Page;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/public/list")
public class PublicCustomerListServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
	
		Page<Customer> page = customerService.findCustomerByPage(pageNo,Config.PUBLIC_ID);
		
		req.setAttribute("page", page);
		
		forward("customer/publiccustomers", req, resp);
	}

}
