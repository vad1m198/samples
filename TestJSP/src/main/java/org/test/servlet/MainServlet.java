package org.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/*"})
public class MainServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	  
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		Connection conn = (Connection) getServletContext().getAttribute("connection");
	    PreparedStatement selectPreparedStatement  = null;
	    try {
	    	conn.setAutoCommit(false);
	    	selectPreparedStatement = conn.prepareStatement("SELECT * FROM reports");
	    	ResultSet rs = selectPreparedStatement.executeQuery();
	    	while (rs.next()) {
	    		System.out.println("Id>"+rs.getInt("id")+" start_date>"+rs.getDate("start_date") + " end_date>"+rs.getDate("end_date")
	    				+ " performer>" + rs.getString("performer")+ " activity>" + rs.getString("activity"));
            }
	    	selectPreparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.println("<html><body>");  
		pw.println("Welcome to servlet");  
		pw.println("</body></html>");
		pw.close();  
	}  

}
