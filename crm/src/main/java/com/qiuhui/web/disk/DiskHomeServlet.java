package com.qiuhui.web.disk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.Disk;
import com.qiuhui.service.DiskService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/disk/home")
public class DiskHomeServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DiskService diskService = new DiskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pid = req.getParameter("pid");
		int pId = 0;
		if(StringUtils.isNumeric(pid)){
			pId = Integer.parseInt(pid);
		}
		
		List<Disk> diskList = diskService.findDisksByPid(pId);
		Disk disk = diskService.findDiskById(pId);
		req.setAttribute("diskList", diskList);
		req.setAttribute("disk",disk);
		
		forward("disk/home",req,resp);
	}

}
