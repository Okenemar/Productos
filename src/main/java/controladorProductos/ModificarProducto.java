package controladorProductos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import clases.Seccion;
import modelo.ModeloProducto;
import modelo.ModeloSeccion;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarProducto() {
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
		Producto producto = new Producto();

		ModeloProducto modeloProducto = new ModeloProducto();

		modeloProducto.conectar();

		producto = modeloProducto.getProducto(id);

		modeloProducto.cerrar();

		request.setAttribute("producto", producto);
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));

		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		Date caducidad = new Date();
		try {
			caducidad = fecha.parse(request.getParameter("caducidad"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String nombreSeccion = request.getParameter("nombreSeccion");
		
		Producto producto = new Producto();

		producto.setId(id);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setCaducidad(caducidad);

		Seccion seccion = new Seccion();
		ModeloSeccion modeloSeccion = new ModeloSeccion();

		modeloSeccion.conectar();
		seccion = modeloSeccion.getSeccionId(nombreSeccion);
		modeloSeccion.cerrar();

		producto.setSeccion(seccion);

		
		ModeloProducto modeloProducto = new ModeloProducto();

		modeloProducto.conectar();

		modeloProducto.modificarProducto(producto);

		modeloProducto.cerrar();

		response.sendRedirect("VerProductos");

	}

}
