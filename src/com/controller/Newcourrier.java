package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Courrier;
import com.model.Pochette;
import com.model.User;

/**
 * Servlet implementation class Newcourrier
 */
@WebServlet("/Newcourrier")
public class Newcourrier extends MyServlet {
	private static final long serialVersionUID = 1L;
       

    public Newcourrier() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("dfuid",OptionUser("dfuid"));
		request.setAttribute("chfuid",OptionUser("chfuid"));
		request.setAttribute("chiuid",OptionUser("chiuid"));
		request.setAttribute("ps",OptionPochette("ps"));
		IsLogin(request,response,"2","NewCourrier");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dfuid,ps,chuid,chfuid,chiuid;
		
		try{
			dfuid = Integer.parseInt(request.getParameter("dfuid"));
		}catch(Exception ex){
			dfuid = 0;
		}
		
		try{
			ps = Integer.parseInt(request.getParameter("ps"));
		}catch(Exception ex){
			ps = 0;
		}
		
		try{
			chuid = Integer.parseInt(request.getParameter("chuid"));
		}catch(Exception ex){
			chuid = 0;
		}
		
		try{
			chfuid = Integer.parseInt(request.getParameter("chfuid"));
		}catch(Exception ex){
			chfuid = 0;
		}
		
		try{
			chiuid = Integer.parseInt(request.getParameter("chiuid"));
		}catch(Exception ex){
			chiuid = 0;
		}
		
		
		String type = request.getParameter("type");
		String nature = request.getParameter("nature");
		String montan = request.getParameter("mnt");
		
		
		if(dfuid != 0 && ps != 0 && chuid != 0 && chfuid != 0 && chiuid != 0 && type != null && nature != null && montan != null){
			if(AddCourrier(dfuid,chuid,chfuid,chiuid,ps,montan,type,nature)){
				request.setAttribute("success", "<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button><strong>Success</strong> Ajout Courrier avec Success .</div>");
			}else {
				request.setAttribute("error", "<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button><strong>Error!</strong> Error d'ajout Courrier .</div>");
			}
		}
		
		request.setAttribute("dfuid",OptionUser("dfuid"));
		request.setAttribute("chfuid",OptionUser("chfuid"));
		request.setAttribute("chiuid",OptionUser("chiuid"));
		request.setAttribute("ps",OptionPochette("ps"));
		IsLogin(request,response,"2","NewCourrier");
	}
	
	public String OptionUser(String n){
		EntityManager em = emf.createEntityManager();
		String re =" <select name='" + n + "' required class='form-control'>";
		try{
			List<User> users = em.createQuery("SELECT u FROM User u ", User.class).getResultList();
			
			for (User u : users){
				re += "<option value='"+ u.getId() + "' >"+ u.getLogin() +"</option>";
			}
			re += "</select>";
			return re;
		}catch(Exception ex){
			return "";
		}
	}
	
	
	public String OptionPochette(String n){
		EntityManager em = emf.createEntityManager();
		String re =" <select name='" + n + "' required class='form-control'>";
		try{
			List<Pochette> ps = em.createQuery("SELECT p FROM Pochette p ", Pochette.class).getResultList();
			
			for (Pochette p : ps){
				re += "<option value='"+ p.getId() + "' >"+ p.getId() +"</option>";
			}
			re += "</select>";
			return re;
		}catch(Exception ex){
			return "";
		}
	}
	
	public boolean AddCourrier(int dfuid,int chuid,int chfuid,int chiuid,int ps,String montan,String type,String nature){
		EntityManager em = emf.createEntityManager();
		
		try{
			User u1 = em.find(User.class, dfuid);
			User u2 = em.find(User.class, chuid);
			User u3 = em.find(User.class,chfuid);
			User u4 = em.find(User.class, chiuid);
			Pochette p = em.find(Pochette.class, ps);
			Courrier c = new Courrier(p,u1,u2,u3,u4,type,nature,montan,new Date());
			c.setEtat(1);
			em.getTransaction().begin();
			em.persist(c);
			p.setNbr(p.getNbr() + 1);
			em.getTransaction().commit();
			em.close();
			return true;
			
		}catch(Exception ex){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
		}
		return false;
		
	}

}
