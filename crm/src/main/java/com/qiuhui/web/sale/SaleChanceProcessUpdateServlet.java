package com.qiuhui.web.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.service.SaleService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/sale/process/update")
public class SaleChanceProcessUpdateServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SaleService saleService = new SaleService();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saleId = req.getParameter("saleId");
		String process = req.getParameter("process");
		
		int staffId = getCurrStaff(req).getId();
		saleService.updateSaleChaneProcess(saleId,process,staffId);
		
		resp.sendRedirect("/sale/detail?saleId=" + saleId);
	}
}
