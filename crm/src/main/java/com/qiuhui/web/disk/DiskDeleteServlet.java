package com.qiuhui.web.disk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.DiskService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;


@WebServlet("/disk/delete")
public class DiskDeleteServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DiskService diskService = new DiskService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("diskId");
		try{
			int pid = diskService.deleteDiskById(id);
			AjaxResult res = AjaxResult.success(pid);
			sendJson(res,resp);
		} catch(ServiceException e) {
			sendJson(AjaxResult.error(),resp);
		}
	}
	
	
}
