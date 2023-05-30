package controladorProductos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import modelo.ModeloProducto;
import modelo.ModeloSupermercado;

/**
 * Servlet implementation class Eliminar
 */
@WebServlet("/Eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Eliminar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ModeloProducto modeloProducto = new ModeloProducto();
		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();

		modeloProducto.conectar();
		Producto producto = new Producto();
		producto = modeloProducto.getProducto(id);
		modeloProducto.cerrar();
		
		modeloSupermercado.conectar();
		boolean existe = modeloSupermercado.productoSuperExiste(id);
		modeloSupermercado.cerrar();
		

		if (producto.getCantidad() > 0) {
			modeloProducto.conectar();

			producto.setCantidad(producto.getCantidad() - 1);
			modeloProducto.modificarProducto(producto);
			modeloProducto.cerrar();

		} else if (producto.getCantidad() == 0 && existe == true) {
			modeloSupermercado.conectar();

			modeloSupermercado.eliminarProductoSuper(id);
			modeloSupermercado.cerrar();

		} else if (producto.getCantidad() == 0 && existe == false) {
			modeloProducto.conectar();

			modeloProducto.eliminarProducto(id);
			modeloProducto.cerrar();

		}

		response.sendRedirect("VerProductos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
