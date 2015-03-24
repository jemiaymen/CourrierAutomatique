package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;


@WebServlet("/Signin")
public class Signin extends MyServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourrierAutomatique");
       

    public Signin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int edit,del;
		
		try{
			edit = Integer.parseInt(request.getParameter("edit"));
		}catch(Exception ex){
			edit = 0;
		}
		
		try{
			del = Integer.parseInt(request.getParameter("del"));
		}catch(Exception ex){
			del = 0;
		}
		
		
		
		if(del != 0 ){
			delUser(del);
		}
		
		if(edit != 0){
			request.setAttribute("editu", Find(edit));
		}
		
		
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
		String _id = request.getParameter("_id");
		String lbl = request.getParameter("lbl");
		String lbltype = request.getParameter("typeloc");
		String lblzone = request.getParameter("lblzone");
		
		
		
		
		if(role != null && login != null && pw != null && pren != null &&
		    nom != null && cin != null && tel != null && 
		   adress != null && _id == null && lbl != null && lbltype != null &&
		   lblzone != null){
			User u = new User(role,login ,pw,pren,nom,cin,adress,tel);
			if(!addUser(u,lbl,lbltype,lblzone)){
				request.getRequestDispatcher("/GUser.jsp?error=yes").forward(request, response);
			}else {
				response.sendRedirect("Signin?success=yes");
			}
		}else if(role != null && login != null && pw != null && pren != null &&
				    nom != null && cin != null && tel != null && 
				   adress != null && _id != null && lbl != null && lbltype != null &&
				   lblzone != null){

			if(!updateUser(_id,role,login, pw,pren,nom,cin,tel, adress,lbl,lbltype,lblzone)){
				request.getRequestDispatcher("/GUser.jsp?error=yes").forward(request, response);
			}else {
				response.sendRedirect("Signin?success=yes");
			}
			
		}
		
		
	}

	public boolean updateUser(String _id,String role,String login, String pw,String pren,String nom,
			String cin,String tel, String adress,String lbl,String lbltype,String lblzone ){
		EntityManager em = emf.createEntityManager();
		
		try{
			int id = Integer.parseInt(_id);
			
			
			em.getTransaction().begin();
			User u = em.find(User.class, id);
			
			u.setRole(role);
			u.setLogin(login);
			u.setPw(pw);
			u.setPrenom(pren);
			u.setNom(nom);
			u.setCin(cin);
			u.setTel(tel);
			u.setAdress(adress);
			
			
			for (Loc l : u.getLocs()){
				l.setLbl(lbl);
				l.setLblzone(lblzone);
				l.setType(lbltype);
			}
			
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
	
	public boolean addUser(User u,String lbl ,String lbltype,String lblzone){
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			
			em.persist(u);
			

			
			Loc l = new Loc(u,lbl,lbltype,lblzone);
			em.persist(l);
			
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
