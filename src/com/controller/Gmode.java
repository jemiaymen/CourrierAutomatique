package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Courrier;
import com.model.Mode;

/**
 * Servlet implementation class Gmode
 */
@WebServlet("/Gmode")
public class Gmode extends MyServlet {
	private static final long serialVersionUID = 1L;

    public Gmode() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request,response);
		int con ;
		
		try {
			con = Integer.parseInt(request.getParameter("confirm"));
		}catch(Exception ex){
			con = 0;
		}
		
		
		if(con != 0){
			request.setAttribute("mode", getMode(con));
		}
		
		request.setAttribute("courrier", getCourrier(_id));
		
		
		
		IsLogin(request,response,"4","GMode");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int con ,nbr;
		
		try {
			con = Integer.parseInt(request.getParameter("con"));
		}catch(Exception ex){
			con = 0;
		}
		
		
		try {
			nbr = Integer.parseInt(request.getParameter("nbr"));
		}catch(Exception ex){
			nbr = 0;
		}
		
		String tran = request.getParameter("tran");
		String chf = request.getParameter("chf");
		String type = request.getParameter("type");
		
		if(con != 0 && nbr != 0 && tran != null && chf != null && type != null){
			if(confMode(con,nbr,tran,chf,type,new Date())){
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<h2>Confirmation Avec Success</h2>");
				out.println("<script>");
				out.println("function red(){ window.location.href ='http://localhost:8080/CourrierAutomatique/Gmode';}");
				out.println("window.setTimeout(red,3000);");
				out.println("</script>");
				out.println("</body></html>");
			}else {
				response.sendRedirect("Gmode");
			}
		}
		

	}

	public boolean confMode(int con,int nbr,String tran ,String chf,String type,Date dt){
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			Courrier c = em.find(Courrier.class, con);
			c.setEtat(3);
			Mode m = new Mode(c,type,nbr,tran,chf,dt);
			em.persist(m);
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
	public String getMode(int con){
		
		String re = "";
		
		re +="<form class='form-horizontal' method='post'>";
		re +="<input type='hidden' name='con' value='"+con+ "' required>";
		re +="<div class='form-group'><label class='col-sm-2 control-label'>Type</label><div class='col-sm-4'><input class='form-control' name='type' type='text' required></div></div>";
		re+="<div class='form-group'><label class='col-sm-2 control-label'>Transporteur</label><div class='col-sm-4'><input class='form-control' name='tran' type='text' required></div></div>";
		re +="<div class='form-group'><label class='col-sm-2 control-label'>Chauffeur</label><div class='col-sm-4'><input class='form-control' name='chf' type='text' required></div></div>";
		re +="<div class='form-group'><label class='col-sm-2 control-label'>Nombre</label><div class='col-sm-4'><input class='form-control' name='nbr' type='text' required pattern='[0-9]{1,6}'></div></div>";
		re +="<div class='form-group'><div class='col-sm-offset-2 col-sm-4'><button type='submit' class='form-control btn btn-success'>Confirmer</button></div></div>";
		re +="</form>";
		return re;
	}
	
	public String[] getCourrier(int uid){
		EntityManager em = emf.createEntityManager();
		
		String re = "";
		String nbr ="0";
		try {
			List<Courrier> cs = em.createQuery("SELECT c FROM Courrier c WHERE c.userByChiuid.id =" + uid + "AND c.etat=" + 2, Courrier.class).getResultList();
			if(cs != null){
				re +="<br><h2>Courriers</h2>";
				re +="<table class='table' >";
				re +="<th>N°Pochette</th>";
				re +="<th>Chargé de courrier</th>";
				re +="<th>Destinataire final</th>";
				re +="<th>Ch final</th>";
				re +="<th>Ch intermédiaire</th>";
				re +="<th>Type</th>";
				re +="<th>Nature</th>";
				re +="<th>Montan</th>";
				re +="<th>Date</th>";
				re +="<th>Confirmation</th>";
				nbr = cs.size() + "";
				for(Courrier c : cs){
					re +="<tr>";
					re +="<td>" + c.getPochette().getId() + "</td><td>" + c.getUserByChuid().getLogin() + "</td><td>" + c.getUserByDfuid().getLogin() + "</td><td>" + c.getUserByChfuid().getLogin() + "</td><td>" + c.getUserByChiuid().getLogin() +"</td><td>" + c.getType() + "</td><td>" + c.getNature() +"</td><td>" + c.getMontant() + "</td><td>" + c.getInitdate() + "</td><td><a href='?confirm=" + c.getId() + "'>Ok</a></td>";
					re +="</tr>";
				}
			}
			
		} catch (Exception e) {
			return null;
		}
		String[] result = {re,nbr};
		return result;
	}
}
