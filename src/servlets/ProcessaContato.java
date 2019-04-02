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
import dao.ContatoDAO;
import vo.Contato;
import vo.Operadora;

@WebServlet("/ProcessaContato")
public class ProcessaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessaContato() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Contato p = new Contato();
		p.setNome(request.getParameter("nome"));
		p.setTelefone(request.getParameter("telefone"));
		Operadora op = new Operadora();
		op.setNome(request.getParameter("operadora"));
		p.setOperadora(op);
		
		if(p.getNome().equals("")){
			Mensagem.addMensagem("Campo 'Nome' obrigatorio");
		}else if(p.getTelefone().equals("")) {
			Mensagem.addMensagem("Campo 'Telefone' obrigatorio");
		}else if(!p.getNome().equals("")||!p.getTelefone().equals("")) {
			ContatoDAO cDao = new ContatoDAO();
			try {
				if(cDao.inserir(p)) {
					Mensagem.addMensagem("Contato salvo com sucesso");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Mensagem.addMensagem("Erro ao salvar");
		}
		
		response.sendRedirect("inicial.jsp");
		
	}

}












