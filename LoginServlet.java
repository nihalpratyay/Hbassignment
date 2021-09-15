package com.nihal.assignment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
    public LoginServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username=request.getParameter("un");
		String password = request.getParameter("pw");
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("user");
		EntityManager manager=factory.createEntityManager();
	    Query query1= manager.createQuery("from UserDTO where username='"+username+"' and password='"+password+"'");
	    
		try {
			UserDTO dto=(UserDTO) query1.getSingleResult();
			pw.print("Login Successful "+username);
			
			pw.print(dto.getFullname()+" "+dto.getUsername()+" "+dto.getPhoneno()+" "+dto.getAddress());
		} catch(NoResultException e) {
			pw.print("<p style='color:red'>Login failed please try again</p>");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
            rd.include(request, response);
		}
	    manager.close();
	    factory.close();
	}

}
