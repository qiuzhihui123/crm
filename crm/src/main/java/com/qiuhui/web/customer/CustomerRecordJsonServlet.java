package com.qiuhui.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.SaleChance;
import com.qiuhui.entity.Staff;
import com.qiuhui.service.SaleService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.util.Page;
import com.qiuhui.web.BasicServlet;

@WebServlet("/customer/record.json")
public class CustomerRecordJsonServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		System.out.println("custId--"+custId );
		
		String p = req.getParameter("p");
		
		int pageNo = 1;
		
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		Page<SaleChance> pages = saleService.findCustomerRecordByPage(custId, pageNo);
		AjaxResult result = AjaxResult.success(pages);
		sendJson(result,resp);
		
		
		
		List<SaleChance> chanceList = saleService.findSaleChanceBYCustId(custId);
	}
}
