package controllers;

import java.io.IOException;
import java.util.List;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstudanteDao;
import model.Estudante;

@WebServlet("/CreateAndFind")
public class EstudanteCreateAndFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 public EstudanteCreateAndFind() {
	        super();
	    }



	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String pesquisa = request.getParameter("pesquisa");
			
			if(pesquisa == null) {
				pesquisa="";
			}
	        
	        List<Estudante> estudantes = EstudanteDao.find(pesquisa);
	        
	        request.setAttribute("estudantes", estudantes);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("lista.jsp");
	        requestDispatcher.forward(request, response);
	    }
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        Estudante estudante = new Estudante();
	        
	        estudante.setNome(request.getParameter("nome"));
	        estudante.setSobrenome(request.getParameter("sobrenome"));
	        estudante.setNascimento(request.getParameter("nascimento"));
	        estudante.setSerie(request.getParameter("serie"));
	        estudante.setEstado(request.getParameter("estado"));
	        estudante.setCidade(request.getParameter("cidade"));
	        estudante.setEmail(request.getParameter("email"));
	        estudante.setSenha(request.getParameter("senha"));
	        
	        EstudanteDao.create(estudante);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginE.html");
	        requestDispatcher.forward(request, response);
	        doGet(request, response);
	    }



	   
	}
