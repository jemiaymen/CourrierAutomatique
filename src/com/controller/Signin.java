package com.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourrierAutomatique");
       

    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/SignIn.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String pw = request.getParameter("pw");
		String pren = request.getParameter("pren");
		String nom = request.getParameter("nom");
		String cin = request.getParameter("cin");
		String tel = request.getParameter("tel");
		String adress = request.getParameter("adress");
		
		
		if(login != null && pw != null && pren != null && pren != null && nom != null && cin != null && tel != null && adress != null){
			User u = new User(login ,pw,pren,nom,cin,adress,tel);
			if(!addUser(u)){
				request.getRequestDispatcher("/SignIn.jsp?erro=yes").forward(request, response);
			}else {
				response.sendRedirect("Login");
			}
		}
	}

	public boolean addUser(User u){
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			
			em.persist(u);
			
			em.getTransaction().commit();
			
			em.close();
			return true;
			
		}catch(Exception ex){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
			return false;
		}
	}
}
