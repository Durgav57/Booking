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
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public HomePage() {
        super();
       }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String From=request.getParameter("from");
		String arr=request.getParameter("to");
		java.io.PrintWriter pw=response.getWriter();
		String url="jdbc:mysql://localhost:3306/db";
		String u="root";
		String p="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,u,p);
			String query="select * from bus where fromp=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, From);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				String c1=rs.getString(1);
				String c2=rs.getString(2);
				int c3=rs.getInt(3);
				if(c1.equals(From) && c2.equals(arr))
				{
					if(c3>1)
					{
					RequestDispatcher rd=request.getRequestDispatcher("Home.html");
					rd.include(request, response);
					pw.print("<center><h4 style='color:red'>Available Ticket:"+c3 +"</h4></center>");
					}
				}
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println(e);	}
		   		


	}

}
