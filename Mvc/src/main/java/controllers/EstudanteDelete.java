package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstudanteDao;


@WebServlet("/EstudanteDelete")
public class EstudanteDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EstudanteDelete() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int estudanteId = Integer.parseInt(request.getParameter("estudanteId"));
		EstudanteDao.delete(estudanteId);
		
		EstudanteCreateAndFind estudanteCreateAndFind = new EstudanteCreateAndFind();
		estudanteCreateAndFind.doGet(request, response);
		
		
	}

}
