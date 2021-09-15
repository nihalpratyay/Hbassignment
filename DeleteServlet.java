package com.nihal.assignment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String user=request.getParameter("un");
		String pass=request.getParameter("pw");
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("user");
		EntityManager manager=factory.createEntityManager();
		EntityTransaction transaction =manager.getTransaction();
		transaction.begin();
		
	    javax.persistence.Query query=manager.createQuery("delete from UserDTO where username='"+user+"' and password='"+pass+"'");
		int rows=query.executeUpdate();
		pw.print("Your Account has been Deleted successfully");
        transaction.commit();
        manager.close();
        factory.close();
	}

}
