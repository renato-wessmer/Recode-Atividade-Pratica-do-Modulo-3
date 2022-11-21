package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.EstudanteDao;

import model.Estudante;


@WebServlet("/EstudanteFindAndUpdate")
public class EstudanteFindAndUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EstudanteFindAndUpdate() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int estudanteId = Integer.parseInt(request.getParameter("estudanteId"));
		Estudante estudante = EstudanteDao.findByPk(estudanteId); 
		
		request.setAttribute("estudante", estudante);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("formUpdate.jsp") ;
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Estudante estudante = new Estudante();
        
		estudante.setId(Integer.parseInt(request.getParameter("id")));
        estudante.setNome(request.getParameter("nome"));
        estudante.setSobrenome(request.getParameter("sobrenome"));
        estudante.setNascimento(request.getParameter("nascimento"));
        estudante.setSerie(request.getParameter("serie"));
        estudante.setEstado(request.getParameter("estado"));
        estudante.setCidade(request.getParameter("cidade"));
        estudante.setEmail(request.getParameter("email"));
        estudante.setSenha(request.getParameter("senha"));
        
        EstudanteDao.update(estudante);
		
		EstudanteCreateAndFind estudanteCreateAndFind = new EstudanteCreateAndFind();
		estudanteCreateAndFind.doGet(request, response);
	}

}
