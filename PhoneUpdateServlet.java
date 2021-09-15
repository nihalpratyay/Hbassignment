package com.nihal.assignment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhoneUpdateServlet")
public class PhoneUpdateServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String phone=request.getParameter("ph");
		String pass=request.getParameter("pw");
		long phoneNumber =Long.parseLong(phone);
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("user");
		EntityManager manager=factory.createEntityManager();
		EntityTransaction transaction =manager.getTransaction();
		transaction.begin();
		
	    javax.persistence.Query query=manager.createQuery("update UserDTO set phoneno='"+phoneNumber+"' where password='"+pass+"'");
		int rows=query.executeUpdate();
		pw.print("Phone number updated successfully");
        transaction.commit();
        manager.close();
        factory.close();
		
	}

}
