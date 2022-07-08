package com.home;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Booking() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fst=request.getParameter("fn");
		String mail=request.getParameter("ma");
		String fromp=request.getParameter("from");
		String topl=request.getParameter("to");
		int seat=Integer.parseInt(request.getParameter("s"));
		String date=request.getParameter("d");
		java.io.PrintWriter pw=response.getWriter();
		String url="jdbc:mysql://localhost:3306/db";
		String u="root";
		String p="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,u,p);
			String q="insert into booking values(?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(q);
			ps.setString(1, fst);
			ps.setString(2, mail);
			ps.setString(3, fromp);	
			ps.setString(4, topl);
			ps.setInt(5, seat);
			ps.setString(6, date);
			int res=ps.executeUpdate();
			if(res==1)
			{
			String query="select * from bus where fromp=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, fromp);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String c1=rs.getString(1);
				String c2=rs.getString(2);
				int c3=rs.getInt(3);
				int c4=rs.getInt(4);
				if(c1.equals(fromp) && c2.equals(topl))
				{
					if(c3>1)
					{
					int amt=seat*c4;
					int s=c3-seat;
					String updates="update bus set seat=? where fromp=? and top=?";
					PreparedStatement psts=con.prepareStatement(updates);
					psts.setInt(1, s);
					psts.setString(2, fromp);
					psts.setString(3, topl);
					psts.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("TicketBooking.html");
					rd.include(request, response);
					pw.print("<br><center><h3 style='color:blue'>Ticket Booked Successfully</h3></center>");
					pw.print("<center><h4 style='color:red'>Amount:"+amt +"</h4><a href='Payment.html' style='color:blue'>Pay amount here...</a></center>");
					}
				}
				
			}
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println(e);	}
		   		


	}

}
