package org.test.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.test.beans.Report;

@WebServlet(urlPatterns = { "/"})
public class MainServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
	    String startDate = request.getParameter("start");
	    String endDate = request.getParameter("end");
	    String performer = request.getParameter("performer");
	    SimpleDateFormat parser = new SimpleDateFormat("MMM d, yyyy");	    
	    String error = "";
	    PreparedStatement statement = null;
		
	    if(startDate != null && endDate != null && performer != null) {
	    	String query = "SELECT * FROM reports";
	    	Connection conn = (Connection) this.getServletContext().getAttribute("connection");
	    	request.setAttribute("start", startDate);
	    	request.setAttribute("end", endDate);
	    	request.setAttribute("performer", performer);

	    	if(!startDate.isEmpty() && !endDate.isEmpty()) {
	    		try {
					Date start = new Date(parser.parse(startDate).getTime());
					Date end = new Date(parser.parse(endDate).getTime());
					String performerQuery = "";
					query += " WHERE start_date >= ? AND end_date <= ?";
					if(!performer.isEmpty() && !performer.equals("All Performers")) {
						performerQuery = performer;
			    		query += " AND performer = ?";			    		
			    	}
					statement = conn.prepareStatement(query);
					statement.setDate(1, start);
					statement.setDate(2, end);
					if(!performerQuery.isEmpty()) {
						statement.setString(3, performer);
					}
				} catch (ParseException e) {					
					error += "Invalid date format. Date format should be like: 'Jun 1, 2000'. ";
				} catch (Exception ex){
					ex.printStackTrace();
				}
	    	} else if(!performer.isEmpty() && !performer.equals("All Performers")) {
	    		query += " WHERE performer = ?";	    		
				try {
					statement = conn.prepareStatement(query);
					statement.setString(1, performer);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	} else {
	    		try {
					statement = conn.prepareStatement(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	if(error.isEmpty()) {
	    		List<Report> reports = new ArrayList<Report>();
	    		ResultSet rs;
				try {
					rs = statement.executeQuery();
					while (rs.next()) {
			    		Report r = new Report(rs.getInt("id"), rs.getDate("start_date"), rs.getDate("end_date"),rs.getString("performer"),rs.getString("activity"));	    		
			    		reports.add(r);
		            }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    	
	    		if(!reports.isEmpty()) {
	    			request.setAttribute("reports", reports);
	    		} else {
	    			error += "No reports were found";	    			
	    		}		    	
	    	}
	    	request.setAttribute("error", error);
	    }
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp");		
        dispatcher.forward(request, response);
	}  

}

