package com.home;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Pay() {
        super();
       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.io.PrintWriter pw=response.getWriter();
		pw.print("<script>alert('Paid successfully...')</script>");

	}

}
