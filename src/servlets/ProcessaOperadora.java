package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Agenda;
import controller.Mensagem;
import dao.OperadoraDAO;
import vo.Operadora;

@WebServlet("/ProcessaOperadora")
public class ProcessaOperadora extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessaOperadora() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Operadora o = new Operadora();
		OperadoraDAO oDao = new OperadoraDAO();
		o.setNome(request.getParameter("operadora"));
		o.setCodigo(Integer.parseInt(request.getParameter("codigo")));

		if (o.getNome().equals("") || o.getCodigo().equals("")) {
			Mensagem.addMensagem("Todos os campos devem ser preenchidos");
		} else
			try {
				if(oDao.inserir(o)){
					Mensagem.addMensagem("Operadora Adicionada com Sucesso");
				}else{
					Mensagem.addMensagem("Erro ao salvar");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		response.sendRedirect("inicial.jsp?mensagem=Operadora Adicionada");

		
	}
}
