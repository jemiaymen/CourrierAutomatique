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


@WebServlet("/ConfRCourrierFD")
public class ConfRCourrierFD extends MyServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MyServlet#MyServlet()
     */
    public ConfRCourrierFD() {
        super();

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
			confirm(con);
		}
		
		request.setAttribute("courrier", getCourrier(_id));
		IsLogin(request,response,"3","ConfRCourrierDF");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
	public String[] getCourrier(int uid){
		EntityManager em = emf.createEntityManager();
		
		String re = "";
		String nbr ="0";
		try {
			List<Courrier> cs = em.createQuery("SELECT c FROM Courrier c WHERE c.userByDfuid.id =" + uid + "AND c.etat=" + 3, Courrier.class).getResultList();
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

	public void confirm(int cid){
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			Courrier c = em.find(Courrier.class,cid);
			c.setEtat(4);
			c.setFinaldate(new Date());
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
