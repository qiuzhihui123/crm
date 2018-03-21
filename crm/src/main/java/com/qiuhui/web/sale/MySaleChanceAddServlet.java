package com.qiuhui.web.sale;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Customer;
import com.qiuhui.entity.SaleChance;
import com.qiuhui.service.SaleService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/sale/add")
public class MySaleChanceAddServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int staffId = getCurrStaff(req).getId();
		
		List<String> process = saleService.findAllProcess();
		List<Customer> customers = saleService.findAllCustomersByStaffId(staffId);
		req.setAttribute("process", process);
		req.setAttribute("customerList", customers);
		
		forward("sale/add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saleName = req.getParameter("salename");
		String custId = req.getParameter("custId");
		String worth = req.getParameter("worth");
		String process = req.getParameter("process");
		String content = req.getParameter("content");
		
		int staffId = getCurrStaff(req).getId();
		
		
		SaleChance saleChance = new SaleChance(saleName, Integer.parseInt(custId), Float.parseFloat(worth), process, content, staffId);
		saleService.saveSaleChane(saleChance);
		AjaxResult result = AjaxResult.success();
		sendJson(result,resp);
	}
}
