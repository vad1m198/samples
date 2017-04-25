package org.test.listener;

import java.io.FileReader;
import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.tools.RunScript;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		Connection conn = (Connection) sce.getServletContext().getAttribute("connection");
	    try {
			RunScript.execute(conn, new FileReader(getClass().getResource("/h2db/schema.sql").getFile()));
			RunScript.execute(conn, new FileReader(getClass().getResource("/h2db/insert-data.sql").getFile()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	
	}


}
