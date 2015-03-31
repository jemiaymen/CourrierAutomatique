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


@WebServlet("/ConfCourrierAR")
public class ConfCourrierAR extends MyServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ConfCourrierAR() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		
		int con,rej ;
		
		try {
			con = Integer.parseInt(request.getParameter("accept"));
			
		}catch(Exception ex){
			con = 0;
		}
		
		
		try {
			rej = Integer.parseInt(request.getParameter("rej"));
			
		}catch(Exception ex){
			rej = 0;
		}
		
		if(con != 0){
			Accepted(con);
		}

		
		if(rej != 0){
			request.setAttribute("rej", getFormRejet(rej));
		}
		
		request.setAttribute("courrier", getCourrier(_id));
		IsLogin(request,response,"3","ConfCourrierAR");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int con ;
		
		try {
			con = Integer.parseInt(request.getParameter("rej"));
			
		}catch(Exception ex){
			con = 0;
		}
		String motif = request.getParameter("motif");
		
		if(motif != null && con != 0){
			Rejet(con,motif);
		}
		
		doGet(request, response);
	}
	
	public String getFormRejet(int con){
		
		String re = "";
		
		re +="<form class='form-horizontal' method='post'>";
		re +="<input type='hidden' name='rej' value='"+con+ "' required>";
		re +="<div class='form-group'><label class='col-sm-offset-6 col-sm-2 control-label'>Motif de rejet</label><div class='col-sm-4'><input class='form-control' name='motif' type='text' required></div></div>";
		re +="<div class='form-group'><div class='col-sm-offset-8 col-sm-4'><button type='submit' class='form-control btn btn-success'>Rejet</button></div></div>";
		re +="</form>";
		return re;
	}

	public String[] getCourrier(int uid){
		EntityManager em = emf.createEntityManager();
		
		String re = "";
		String nbr ="0";
		try {
			List<Courrier> cs = em.createQuery("SELECT c FROM Courrier c WHERE c.userByDfuid.id =" + uid + "AND c.etat=" + 4, Courrier.class).getResultList();
			if(cs != null){
				re +="<br><h2>Courriers</h2>";
				re +="<table class='table' >";
				re +="<th>N°Pochette</th>";
				re +="<th>Ch courrier</th>";
				re +="<th>Des final</th>";
				re +="<th>Ch final</th>";
				re +="<th>Ch inter</th>";
				re +="<th>Type</th>";
				re +="<th>Nature</th>";
				re +="<th>Montan</th>";
				re +="<th>Date Envoi</th>";
				re +="<th>Date Reception</th>";
				re +="<th>Accept</th>";
				re +="<th>Rejet</th>";
				nbr = cs.size() + "";
				for(Courrier c : cs){
					re +="<tr>";
					re +="<td>" + c.getPochette().getId() + "</td><td>" + c.getUserByChuid().getLogin() + "</td><td>" + c.getUserByDfuid().getLogin() + "</td><td>" + c.getUserByChfuid().getLogin() + "</td><td>" + c.getUserByChiuid().getLogin() +"</td><td>" + c.getType() + "</td><td>" + c.getNature() +"</td><td>" + c.getMontant() + "</td><td>" + c.getInitdate() + "</td><td>" + c.getFinaldate() + "</td><td><a href='?accept=" + c.getId() + "'>Accept</a></td><td><a href='?rej=" + c.getId() + "'>Rejet</a></td>";
					re +="</tr>";
				}
			}

		} catch (Exception e) {
			return null;
		}
		String[] result = {re,nbr};
		return result;
	}
	
	
	public void Accepted(int cid){
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Courrier c = em.find(Courrier.class,cid);
			c.setEtat(5);
			c.setMotif("Accepted by :" + c.getUserByDfuid().getLogin());
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception ex) {
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
		}
	}
	
	
	public void Rejet(int cid,String motif){
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Courrier c = em.find(Courrier.class,cid);
			c.setEtat(6);
			c.setMotif(motif);
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception ex) {
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				em.close();
			}
		}
	}
}
