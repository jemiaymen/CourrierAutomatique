package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Courrier;
import com.model.Pochette;


/**
 * Servlet implementation class GPochette
 */
@WebServlet("/GPochette")
public class GPochette extends MyServlet {
	private static final long serialVersionUID = 1L;
       

    public GPochette() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int c ;
		
		try{
			c = Integer.parseInt(request.getParameter("p"));
			request.setAttribute("courrier",getAllCourrier(c));
		}catch(Exception ex){
			c = 0;
		}
		
		request.setAttribute("ps", getAllPochette());
		IsLogin(request,response,"2","GPochette");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String addp = request.getParameter("addp");
		if(addp != null && addp.equals("yes")){
			Addp();
		}

		request.setAttribute("ps", getAllPochette());
		IsLogin(request,response,"2","GPochette");
	}
	
	public void Addp(){
		EntityManager em = emf.createEntityManager();
		try {
			Pochette p = new Pochette(0);
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			
		}
	}
	
	public String getAllPochette(){
		EntityManager em = emf.createEntityManager();
		String re = "";
		try {
			List<Pochette> ps = em.createQuery("SELECT p FROM Pochette p ", Pochette.class).getResultList();
			if(ps != null){
				for(Pochette p : ps){
					re +="<tr>";
					re +="<td>"+ p.getId() +"</td><td>"+ p.getNbr() +"</td>";
					re +="<td><a href='?p="+ p.getId() + "'>Voir Courrier</a></td>";
					re +="</tr>";
				}
			}
			
		} catch (Exception e) {
			re = "";
		}
		return re;
	}
	
	public String getAllCourrier(int pid){
		EntityManager em = emf.createEntityManager();
		String re = "";
		try{
			Pochette p = em.find(Pochette.class, pid);
			
			if(p != null && p.getNbr() > 0){
				re +="<br><h2>Courriers</h2>";
				re +="<table class='table' >";
				re +="<th>Type</th>";
				re +="<th>Nature</th>";
				re +="<th>Montan</th>";
				re +="<th>Date Intial</th>";
				for(Courrier c : p.getCourriers()){
					re +="<tr>";
					re +="<td>" + c.getType() + "</td><td>" + c.getNature() +"</td><td>" + c.getMontant() + "</td><td>" + c.getInitdate()+"</td>";
					re +="</tr>";
					
				}
				
				re +="</table>";
			}else {
				return re;
			}
			
		}catch(Exception ex){
			
		}
		return re;
	}

}
