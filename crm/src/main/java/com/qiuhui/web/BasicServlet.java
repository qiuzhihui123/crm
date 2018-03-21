package com.qiuhui.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.qiuhui.entity.Staff;

public class BasicServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	public Staff getCurrStaff(HttpServletRequest req){
		HttpSession session = req.getSession();
		Staff currStaff = (Staff)session.getAttribute("staff");
		return currStaff;
	}

	public void forward(String path,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		
		req.getRequestDispatcher("/WEB-INF/views/"+ path + ".jsp").forward(req, resp);
	}
	
	public void sendJson(Object res,HttpServletResponse resp) throws IOException{
		
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(new Gson().toJson(res));
		writer.flush();
		writer.close();
	}
}
