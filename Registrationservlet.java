package com.nihal.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registrationservlet")
public class Registrationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String fullname = request.getParameter("fn");
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		String phone = request.getParameter("ph");
		String address= request.getParameter("ad");
		
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("user");
		EntityManager manager=factory.createEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		
		UserDTO dto=new UserDTO();
		dto.setFullname(fullname);
	    dto.setUsername(username);
	    dto.setPassword(password);
	    dto.setPhoneno(phone);
	    dto.setAddress(address);
	    manager.persist(dto);
	    transaction.commit();
	    manager.close();
	    factory.close();
	    pw.print("Registration Successful");
	}

}
