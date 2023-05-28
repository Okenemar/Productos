package controladorProductos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloProducto;

/**
 * Servlet implementation class EliminarProductosPorTexto
 */
@WebServlet("/EliminarProductosPorTexto")
public class EliminarProductosPorTexto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProductosPorTexto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto modeloProducto = new ModeloProducto();
		
		String codigosProductosCaja = request.getParameter("codigoProducto");
		String[] codigoProductos = codigosProductosCaja.split(",");
		boolean productoExiste = false;
		
		modeloProducto.conectar();
		
		for (int i = 0; i < codigoProductos.length; i++) {
			String codigoProducto = codigoProductos[i];
			productoExiste = modeloProducto.productoExiste(codigoProducto);
			
			if (productoExiste ==false) {
				break;
			}
		}
		
		modeloProducto.cerrar();
		
		if (productoExiste == true) {
			modeloProducto.conectar();
			for (int i = 0; i < codigoProductos.length; i++) {
				modeloProducto.eliminarProductoPorCodigo(codigoProductos[i]);
			}
			modeloProducto.cerrar();
		}
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
