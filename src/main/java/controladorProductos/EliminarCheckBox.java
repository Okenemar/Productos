package controladorProductos;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloProducto;

/**
 * Servlet implementation class EliminarCheckBox
 */
@WebServlet("/EliminarCheckBox")
public class EliminarCheckBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCheckBox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] productos = request.getParameterValues("productos");
		int[] idProductos = Arrays.stream(productos).mapToInt(Integer::parseInt).toArray();
		
		ModeloProducto modeloProducto = new ModeloProducto();
		
		modeloProducto.conectar();
		
		for (int i = 0; i < idProductos.length; i++) {
			modeloProducto.eliminarProducto(idProductos[i]);
		}
		modeloProducto.cerrar();
		
		response.sendRedirect("VerProductos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
