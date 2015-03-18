package com.controller;

import java.io.IOException;
import java.util.List;

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
public class Signin extends MyServlet {
	private static final long serialVersionUID = 1L;
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourrierAutomatique");
       

    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		int edit,del;
//		
//		try{
//			edit = Integer.parseInt(request.getParameter("edit"));
//		}catch(Exception ex){
//			edit = 0;
//		}
//		
//		try{
//			del = Integer.parseInt(request.getParameter("del"));
//		}catch(Exception ex){
//			del = 0;
//		}
//		
//		
//		
//		if(del != 0 ){
//			delUser(del);
//		}
//		
//		if(edit != 0){
//			request.setAttribute("editu", Find(edit));
//		}
//		
		
		request.setAttribute("users",getAll());
		IsLogin(request,response,"1","GUser");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String pw = request.getParameter("pw");
		String pren = request.getParameter("pren");
		String nom = request.getParameter("nom");
		String cin = request.getParameter("cin");
		String tel = request.getParameter("tel");
		String adress = request.getParameter("adress");
		String role = request.getParameter("role");
		
		
		
		if(role != null && login != null && pw != null && pren != null && pren != null && nom != null && cin != null && tel != null && adress != null){
			User u = new User(role,login ,pw,pren,nom,cin,adress,tel);
			if(!addUser(u)){
				request.getRequestDispatcher("/GUser.jsp?error=yes").forward(request, response);
			}else {
				response.sendRedirect("Singin?success=yes");
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
	
	public List<User> getAll() {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("SELECT u FROM User u ", User.class)
					.getResultList();
		} catch (Exception ex) {

		}

		return null;
	}
	
	public boolean delUser(int id){
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			User u = em.find(User.class, id);
			em.remove(u);
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
	
	public User Find(int id){
		EntityManager em = emf.createEntityManager();
		try{
			return em.find(User.class, id);
		}catch(Exception ex){
			return null;
		}
	}
}
