package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Producto;

import conexion.Conector;

public class ModeloProducto extends Conector {

	public ArrayList<Producto> getProductos() {

		PreparedStatement prt;
		ModeloSeccion mSeccion = new ModeloSeccion();
		mSeccion.setConexion(this.con);
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try {
			prt = con.prepareStatement("SELECT * FROM productos ");

			ResultSet result = prt.executeQuery();

			while (result.next()) {
				Producto producto = new Producto();

				producto.setId(result.getInt(1));
				producto.setCodigo(result.getString(2));
				producto.setNombre(result.getString(3));
				producto.setCantidad(result.getInt(4));
				producto.setPrecio(result.getDouble(5));
				producto.setCaducidad(result.getDate(6));

				producto.setIdSeccion(mSeccion.getSeccion(result.getInt(7)));

				productos.add(producto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}

	public void RegistrarProducto(Producto producto) {

		PreparedStatement prt;

		try {
			prt = con.prepareStatement(
					"INSERT INTO productos(codigo,nombre,cantidad, precio, caducidad,id_seccion) VALUES (?,?,?,?,?,?)");

			prt.setString(1, producto.getCodigo());
			prt.setString(2, producto.getNombre());
			prt.setInt(3, producto.getCantidad());
			prt.setDouble(4, producto.getPrecio());

			prt.setDate(5, new Date(producto.getCaducidad().getTime()));

			prt.setInt(6, producto.getSeccion().getId());

			prt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean CodigoDuplicado(String codigo) {
		PreparedStatement prt;
		boolean codigoDuplicado = false;

		try {
			prt = con.prepareStatement("SELECT  codigo FROM productos WHERE codigo=?");

			prt.setString(1, codigo);

			ResultSet result = prt.executeQuery();

			if (result.next()) {
				codigoDuplicado = true;
			} else {
				codigoDuplicado = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return codigoDuplicado;
	}

	public Producto getProducto(int id) {

		ModeloSeccion mSeccion = new ModeloSeccion();
		mSeccion.setConexion(this.con);
		PreparedStatement prt;
		Producto producto = new Producto();

		try {
			prt = con.prepareStatement(
					"SELECT id,codigo,nombre,cantidad,precio,caducidad,id_seccion FROM productos WHERE id=?");

			prt.setInt(1, id);

			ResultSet result = prt.executeQuery();

			if (result.next()) {

				producto.setId(result.getInt(1));
				producto.setCodigo(result.getString(2));
				producto.setNombre(result.getString(3));
				producto.setCantidad(result.getInt(4));
				producto.setPrecio(result.getDouble(5));
				producto.setCaducidad(result.getDate(6));
				producto.setIdSeccion(mSeccion.getSeccion(result.getInt(7)));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;

	}

	public void modificarProducto(Producto producto) {

		PreparedStatement prt;

		try {
			prt = con.prepareStatement(
					"UPDATE productos SET codigo=?,nombre=?,cantidad=?,precio=?,caducidad=?,id_seccion=? WHERE id=?");

			prt.setString(1, producto.getCodigo());
			prt.setString(2, producto.getNombre());
			prt.setInt(3, producto.getCantidad());
			prt.setDouble(4, producto.getPrecio());
			prt.setDate(5, new Date(producto.getCaducidad().getTime()));
			prt.setInt(6, producto.getSeccion().getId());
			prt.setInt(7, producto.getId());
			prt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
 public void eliminarProducto(int id) {
	 
	 PreparedStatement prt;
	 try {
		prt = con.prepareStatement("DELETE FROM productos WHERE id=?");
		prt.setInt(1, id);

		prt.execute();
	} catch (Exception e) {
		// TODO: handle exception
	}
 }

}
