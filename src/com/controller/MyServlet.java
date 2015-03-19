package com.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;


public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourrierAutomatique");   

    public MyServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void IsLogin(HttpServletRequest request, HttpServletResponse response,String role,String url)
			throws IOException, ServletException {
		String uid = null;
		EntityManager em = emf.createEntityManager();

		Cookie[] c = request.getCookies();
		if (c != null) {
			for (Cookie cookie : c) {
				if (cookie.getName().equals("uid")) {
					uid = cookie.getValue();
				}
			}
			if (uid != null) {
				try {
					int id = Integer.parseInt(uid);
					User u = (User) em.find(User.class, id);
					if (u == null) {
						response.sendRedirect("Logout");
					} else {
						if(u.getRole().equals(role)){
							request.setAttribute("user", u);
							request.getRequestDispatcher("/" + url +".jsp").forward(
									request, response);
						}else {
							response.sendRedirect("Logout");
						}
						
					}
				} catch (Exception ex) {
					response.sendRedirect("Logout");
				}

			} else {
				response.sendRedirect("Logout");
			}
		} else {
			response.sendRedirect("Logout");
		}

	}
}
