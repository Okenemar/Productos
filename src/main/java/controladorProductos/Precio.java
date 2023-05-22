package controladorProductos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import modelo.ModeloProducto;

/**
 * Servlet implementation class Precio
 */
@WebServlet("/Precio")
public class Precio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Precio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double precioMin = Double.parseDouble(request.getParameter("precioMin"));
		Double precioMax = Double.parseDouble(request.getParameter("precioMax"));
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		ArrayList<Producto> productos = modeloProducto.getProductos();
		modeloProducto.cerrar();
		Iterator<Producto> iterator = productos.iterator();
		while(iterator.hasNext()) {
			Producto producto = iterator.next();
			if(!(producto.getPrecio() >= precioMin) || !(producto.getPrecio() <= precioMax) ){
				iterator.remove();
			}
		}
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
