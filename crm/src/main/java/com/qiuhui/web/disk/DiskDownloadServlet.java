package com.qiuhui.web.disk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.Disk;
import com.qiuhui.service.DiskService;
import com.qiuhui.util.Config;
import com.qiuhui.web.BasicServlet;

@WebServlet("/disk/download")

public class DiskDownloadServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DiskService diskService =  new DiskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String diskId = req.getParameter("id");
		String fileName = req.getParameter("fileName");
		
		if(StringUtils.isNotEmpty(fileName)){
			
			fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
		}
		
		
		if(StringUtils.isNumeric(diskId)) {
			Disk disk = diskService.findDiskByDiskId(Integer.parseInt(diskId));
			if(disk == null) {
				
				resp.sendError(404, "参数异常");
			} else {
				String filePath = Config.getConfig("file.upload.path");
				InputStream input = new FileInputStream(new File(filePath, disk.getSaveName()));
				OutputStream output = resp.getOutputStream();
				
				if(StringUtils.isNotEmpty(fileName)) {
					
					resp.setContentType("application/octet-stream");
					
					fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
					resp.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				} 
				
				IOUtils.copy(input, output);
				output.flush();
				output.close();
				input.close();
			}
			
			
		} else {
			
			resp.sendError(404, "参数异常");
		}
		
	}	
	
}
