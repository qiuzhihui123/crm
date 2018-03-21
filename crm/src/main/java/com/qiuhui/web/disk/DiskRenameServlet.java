package com.qiuhui.web.disk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.service.DiskService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/disk/rename")
public class DiskRenameServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DiskService diskService = new DiskService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String diskName = req.getParameter("diskName");
		String diskId = req.getParameter("diskId");
		
		diskService.diskRename(diskId,diskName);
		sendJson(AjaxResult.success(),resp);
	}
}
