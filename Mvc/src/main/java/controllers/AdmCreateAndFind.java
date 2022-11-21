package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdmDao;

import model.Adm;




@WebServlet("/AdmCreateAndFind")
public class AdmCreateAndFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdmCreateAndFind() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pesquisa = request.getParameter("pesquisa");
		
		if(pesquisa == null) {
			pesquisa="";
		}
		
		List<Adm> adms = AdmDao.findAdm(pesquisa);
		
		request.setAttribute("adms", adms);
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Adm adm = new Adm();
		
		adm.setNome(request.getParameter("nome"));
		adm.setSobrenome(request.getParameter("sobrenome"));
		adm.setEmail(request.getParameter("email"));
		adm.setSenha(request.getParameter("senha"));
		
		AdmDao.createAdm(adm);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginA.html");
		requestDispatcher.forward(request, response);
	}
	

}
