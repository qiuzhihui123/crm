package com.qiuhui.web.disk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.service.DiskService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/disk/new/folder")
public class DiskNewFolderServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DiskService diskService =  new DiskService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String  folderName = req.getParameter("name");
		String  pid = req.getParameter("pid");
		
		int pId = 0;
		
		if(StringUtils.isNumeric(pid)){
			
			pId = Integer.parseInt(pid); 
		}
		
		try{
			int staffId = getCurrStaff(req).getId();
			diskService.saveFolder(folderName, pId, staffId);
			sendJson(AjaxResult.success(),resp);
		}catch(NullPointerException e) {
			sendJson(AjaxResult.error("登录过期，请重新登录"),resp);
		}
		
		
		
	}	
	
}
