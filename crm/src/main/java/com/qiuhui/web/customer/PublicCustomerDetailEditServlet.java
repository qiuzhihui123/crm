package com.qiuhui.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Customer;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.CustomerService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.util.Config;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/public/edit")
public class PublicCustomerDetailEditServlet extends BasicServlet{

	CustomerService customerService = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String custId = req.getParameter("custId");
		int staffId = Config.PUBLIC_ID;
		
		try {
			Customer cust = customerService.findCustomerById(custId, staffId);
			List<String> sources = customerService.findAllSources();
			List<String> trades = customerService.findAllTrades();
			
			req.setAttribute("cust", cust);
			req.setAttribute("sources",sources);
			req.setAttribute("trades", trades);
			
			forward("customer/publicedit",req,resp);
		} catch (ForbiddenException e) {
			resp.sendError(403,e.getMessage());
		} catch(ServiceException e){
			resp.sendError(404,e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		String custname = req.getParameter("custname");
		String sex = req.getParameter("sex");
		String jobtitle = req.getParameter("jobtitle");
		String address = req.getParameter("address");
		String mobile = req.getParameter("mobile");
		String source = req.getParameter("source");
		String trade = req.getParameter("trade");
		String level = req.getParameter("level");
		String mark = req.getParameter("mark");
		
		
		customerService.edit(custId,custname,sex,jobtitle,address,mobile,source,trade,level,mark);
		
		AjaxResult result = AjaxResult.success();
		sendJson(result, resp);
	}
}
