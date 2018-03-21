package com.qiuhui.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.service.CustomerService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.util.Config;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/add")
public class CustomerAddServlet extends BasicServlet {

	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String isPublic = req.getParameter("isPublic");
		System.out.println(isPublic);
		int staffId;
		
		if(StringUtils.isNotEmpty(isPublic) && isPublic.equals("true")){
			staffId = Config.PUBLIC_ID;
		} else {
			staffId = getCurrStaff(req).getId();
		}
		
		
		List<String> sources = customerService.findAllSources(); 
		List<String> trades = customerService.findAllTrades();
		
		req.setAttribute("staffId", staffId);
		req.setAttribute("sources", sources);
		req.setAttribute("trades", trades);
		
		forward("customer/add",req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custname = req.getParameter("custname");
		String sex = req.getParameter("sex");
		String jobtitle = req.getParameter("jobtitle");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		String source = req.getParameter("source");
		String trade = req.getParameter("trade");
		String level = req.getParameter("level");
		String mark = req.getParameter("mark");
		
		String staffId = req.getParameter("staffId");
		 
		
		customerService.addMyCustomer(custname,sex,jobtitle,mobile,address,source,trade,level,mark,Integer.parseInt(staffId));
	
		AjaxResult result = AjaxResult.success(staffId);
		sendJson(result, resp);
	}
	
}
