package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		IsLogin(request,response,"2","NewCourrier");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

}
