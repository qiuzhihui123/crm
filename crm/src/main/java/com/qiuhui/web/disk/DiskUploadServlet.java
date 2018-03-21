package com.qiuhui.web.disk;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.qiuhui.service.DiskService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/disk/upload")
@MultipartConfig
public class DiskUploadServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DiskService diskService =  new DiskService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("file");
		
		InputStream input = part.getInputStream();
		
		
		String name = req.getParameter("name");
		
		long fileSize = part.getSize();
		
		 
		String pid = req.getParameter("pid");
		
		
		try{
			int staffId = getCurrStaff(req).getId();
			diskService.saveNewFile(input, name, fileSize, Integer.parseInt(pid), staffId);
			sendJson(AjaxResult.success(), resp);
		} catch(NullPointerException e) {
			sendJson(AjaxResult.error("登录信息已过期，请重新登录"), resp);
		}
		
	}	
	
}
