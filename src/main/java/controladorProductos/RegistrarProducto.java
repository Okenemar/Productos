package controladorProductos;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Servlet implementation class RegistrarProducto
 */
@WebServlet("/RegistrarProducto")
public class RegistrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModeloSeccion modeloSeccion = new ModeloSeccion();

		ArrayList<Seccion> secciones = new ArrayList<>();


		modeloSeccion.conectar();
		secciones = modeloSeccion.getSecciones();
		modeloSeccion.cerrar();

		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("VistaRegistrarProducto.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModeloProducto modeloProducto = new ModeloProducto();

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

		modeloProducto.conectar();

		boolean codigoDupli = modeloProducto.CodigoDuplicado(codigo);

		modeloProducto.cerrar();

		if (codigoDupli == true || cantidad < 0 || precio < 0 || caducidad.before(new Date())) {
			ModeloSeccion modeloSeccion = new ModeloSeccion();

			ArrayList<Seccion> secciones = new ArrayList<>();
			modeloSeccion.conectar();

			boolean error = true;
			secciones = modeloSeccion.getSecciones();

			modeloSeccion.cerrar();

			request.setAttribute("error", error);
			request.setAttribute("secciones", secciones);

			request.getRequestDispatcher("VistaRegistrarProducto.jsp").forward(request, response);
		}

		Producto producto = new Producto();

		Seccion seccion = new Seccion();

		int id = Integer.parseInt(request.getParameter("seccion"));

		seccion.setId(id);

		producto.setCaducidad(caducidad);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setIdSeccion(seccion);

		ModeloProducto modeloProducto2 = new ModeloProducto();

		modeloProducto2.conectar();

		modeloProducto2.RegistrarProducto(producto);

		response.sendRedirect("VerProductos");

	}

}
