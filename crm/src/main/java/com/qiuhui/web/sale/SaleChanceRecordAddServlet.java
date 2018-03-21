package com.qiuhui.web.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.SaleChanceRecord;
import com.qiuhui.service.SaleService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/sale/add/record")
public class SaleChanceRecordAddServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SaleService saleService = new SaleService();
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saleId = req.getParameter("saleId");
		String content = req.getParameter("content");
		
		SaleChanceRecord record = new SaleChanceRecord();
		record.setContent(content);
		record.setSaleId(Integer.parseInt(saleId));
		
		
		saleService.addRecord(record);
		AjaxResult result = AjaxResult.success();
		sendJson(result,resp);
	}
}
