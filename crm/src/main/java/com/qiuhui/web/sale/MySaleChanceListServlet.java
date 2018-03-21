package com.qiuhui.web.sale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.SaleChance;
import com.qiuhui.service.SaleService;
import com.qiuhui.util.Page;
import com.qiuhui.web.BasicServlet;

@WebServlet("/sale/my/list")
public class MySaleChanceListServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);	
		}
		int staffId = getCurrStaff(req).getId();
				
		Page<SaleChance> page = saleService.findSaleListByPage(pageNo,staffId);
		req.setAttribute("page", page);
		forward("sale/mylist", req, resp);
	}
}
