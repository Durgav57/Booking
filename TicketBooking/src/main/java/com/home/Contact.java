package com.home;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Contact() {
        super();
         }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
			String fst=request.getParameter("fn");
			String mail=request.getParameter("ma");
			String Msg=request.getParameter("msg");
			java.io.PrintWriter pw=response.getWriter();
			String url="jdbc:mysql://localhost:3306/db";
			String u="root";
			String p="";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(url,u,p);
				String q="insert into feedback(Name,Mail,feedback) values(?,?,?)";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, fst);
				ps.setString(2, mail);
				ps.setString(3, Msg);		
				int res=ps.executeUpdate();
				if(res==1)
				{RequestDispatcher rd=request.getRequestDispatcher("Contact.html");
				rd.include(request, response);
				pw.print("<br><h1>send Successfully</h1>");
				}
			}
			catch(SQLException | ClassNotFoundException e) {
				System.out.println(e);	}
			   		

				
	}

}
