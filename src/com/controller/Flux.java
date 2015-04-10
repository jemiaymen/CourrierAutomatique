package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Courrier;

/**
 * Servlet implementation class Flux
 */
@WebServlet("/Flux")
public class Flux extends MyServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MyServlet#MyServlet()
     */
    public Flux() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("courrier", getCourrier());
		
		IsLogin(request,response,"1","Flux");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public String getCourrier(){
		EntityManager em = emf.createEntityManager();
		
		String re = "";
		try {
			List<Courrier> cs = em.createQuery("SELECT c FROM Courrier c ", Courrier.class).getResultList();
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
				re +="<th>Etat</th>";
				for(Courrier c : cs){
					re +="<tr>";
					re +="<td>" + c.getPochette().getId() + "</td><td>" + c.getUserByChuid().getLogin() + "</td><td>" + c.getUserByDfuid().getLogin() + "</td><td>" + c.getUserByChfuid().getLogin() + "</td><td>" + c.getUserByChiuid().getLogin() +"</td><td>" + c.getType() + "</td><td>" + c.getNature() +"</td><td>" + c.getMontant() + "</td><td>" + c.getInitdate() + "</td><td>" + c.getEtat() + "</td>";
					re +="</tr>";
				}
			}

		} catch (Exception e) {
			return "";
		}
		return re;
	}
}
