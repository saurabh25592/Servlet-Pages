package com.jbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signIn")
public class SignIn extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		PrintWriter out = resp.getWriter();
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "root");
            
            String query = "select * from rdetails where email=? and password=?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs= ps.executeQuery();
            if (rs.next()) {
            	out.print("successfully login");
            	resp.sendRedirect("http://localhost:8080/ServletProject/jsp.jsp");
                
            	
            }
            else {
            	out.print("Fields didnt match"); 
            	 resp.sendRedirect("http://localhost:8080/ServletProject/Register.html");
            	
            }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
