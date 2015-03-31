package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends MyServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MyServlet#MyServlet()
     */
    public home() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		switch(_role){
		
		case "1":
			response.sendRedirect("Signin");
			break;
		case "2":
			response.sendRedirect("Newcourrier");
			break;
		case "3" :
			response.sendRedirect("ConfRCourrierFD");
			break;
		case "4" :
			response.sendRedirect("ConfRCourrier");
			break;
		default :
			response.sendRedirect("Logout");
			break;
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
