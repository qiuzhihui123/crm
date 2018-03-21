package com.qiuhui.web.sale;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.SaleChance;
import com.qiuhui.entity.SaleChanceRecord;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.SaleService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/sale/detail")
public class MySaleChanceDetailServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String saleId = req.getParameter("saleId");
		int staffId = getCurrStaff(req).getId();
		try {
			SaleChance saleChance = saleService.findSaleChanceById(saleId,staffId);
			List<SaleChanceRecord> recordList = saleService.findRecordListBySaleId(saleId);
			List<String> processList = saleService.findAllProcess();
			
			req.setAttribute("saleChance", saleChance);
			req.setAttribute("recordList", recordList);
			req.setAttribute("processList", processList);
			forward("sale/detail", req, resp);
		} catch(ServiceException e) {
			resp.sendError(404,e.getMessage());
		} catch(ForbiddenException e) {
			resp.sendError(403,e.getMessage());
		}
	}
	
	
}
