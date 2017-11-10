package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.ProdutoDAO;
import model.Produto;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		switch (request.getParameter("btnAcaoProduto")) {
		case "Cadastrar":
			try {
				Produto produto = new Produto();
				produto.setNome(request.getParameter("txtNome"));
				produto.setPreco(Double.parseDouble(request.getParameter("txtPreco")));
				produto.setQuantidade(Integer.parseInt(request.getParameter("txtQuantidade")));
				produto.setCategoria(request.getParameter("txtCategoria"));
				if (ProdutoDAO.cadastrarProduto(produto)) {
					response.sendRedirect("CadastrarProduto.jsp?mensagem=2");
				} else {
					response.sendRedirect("CadastrarProduto.jsp?mensagem=3");
				}
			} catch (NumberFormatException e) {
				response.sendRedirect("CadastrarProduto.jsp?mensagem=1");
			}
			break;
		case "Alterar":
			int idProduto = Integer.parseInt(request.getParameter("txtId"));
			try {
				Produto produto = ProdutoDAO.buscarProdutoPorId(idProduto);
				produto.setNome(request.getParameter("txtNome"));
				produto.setPreco(Double.parseDouble(request.getParameter("txtPreco")));
				produto.setQuantidade(Integer.parseInt(request.getParameter("txtQuantidade")));
				produto.setCategoria(request.getParameter("txtCategoria"));
				if (ProdutoDAO.alterarProduto(produto)) {
					response.sendRedirect("AlterarProduto.jsp?mensagem=2&idProduto=" + idProduto);
				} else {
					response.sendRedirect("AlterarProduto.jsp?mensagem=3&idProduto=" + idProduto);
				}
			} catch (NumberFormatException e) {
				response.sendRedirect("AlterarProduto.jsp?mensagem=1&idProduto=" + idProduto);
			}
			break;
		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
