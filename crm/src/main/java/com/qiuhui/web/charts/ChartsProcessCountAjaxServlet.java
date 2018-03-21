package com.qiuhui.web.charts;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.service.SaleService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/charts/process/count")
public class ChartsProcessCountAjaxServlet extends BasicServlet{

	SaleService saleService = new SaleService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Map<String,Object>> mapList = saleService.findProcessCount();
		AjaxResult res = AjaxResult.success(mapList);
		sendJson(res,resp);
	}

}
