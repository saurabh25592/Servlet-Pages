package com.jbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	resp.setContentType("text/html");
    	
    	
        // Get form data from request
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password =req.getParameter("password");
        
        String email = req.getParameter("Email");
        String phoneNumberStr = req.getParameter("phone_number");
        String dob = req.getParameter("DOB");
        String gender = req.getParameter("gender");
        String graduationYear = req.getParameter("graduationYear");
        String branch = req.getParameter("branch");
        String semesterStr = req.getParameter("semester");
        String tenthMarksStr = req.getParameter("tenth");
        String twelfthMarksStr = req.getParameter("twelfth");
     
        
        try {
            // Convert values to appropriate data types
            long phoneNumber = Long.parseLong(phoneNumberStr);  // phone number is a bigint in the table
            int semester = Integer.parseInt(semesterStr);  // semester is an integer
            int tenthMarks = Integer.parseInt(tenthMarksStr);  // 10th_marks is an integer
            int twelfthMarks = Integer.parseInt(twelfthMarksStr);  // 12th_marks is an integer

            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "root");

            // Prepare SQL statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO registration (first_name, last_name,password, email, phone_number, dob, gender, graduation_year, specification, semester, 10th_marks, 12th_marks) VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Set parameters
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setNString(3, password);
            ps.setString(3, email);
            ps.setLong(4, phoneNumber);
            ps.setString(5, dob);  // Assuming 'dob' is passed as a string in 'yyyy-mm-dd' format
            ps.setString(6, gender);
            ps.setString(7, graduationYear);  // Graduation year is a 'year' data type, so we keep it as a string
            ps.setString(8, branch);  // Specification/branch is varchar
            ps.setInt(9, semester);
            ps.setInt(10, tenthMarks);  // 10th_marks as int
            ps.setInt(11, twelfthMarks);  // 12th_marks as int

            // Execute the statement
            int i = ps.executeUpdate();

            // Provide feedback to the user
            PrintWriter out = resp.getWriter();
            if (i > 0) {
                out.print("Registration is completed successfully.");
            } else {
                out.print("Registration failed.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.getWriter().println("Error: Invalid number format in form fields.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Error occurred: " + e.getMessage());
        }
    }
}
