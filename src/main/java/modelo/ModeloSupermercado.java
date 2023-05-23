package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Supermercado;
import conexion.Conector;

public class ModeloSupermercado extends Conector {
	PreparedStatement prt;

	public ArrayList<Supermercado> getSupermercados() {
		ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();

		try {
			prt = con.prepareStatement("SELECT * FROM supermercados");
			ResultSet result = prt.executeQuery();

			while (result.next()) {
				Supermercado supermercado = new Supermercado();

				supermercado.setId(result.getInt("id"));
				supermercado.setNombre(result.getString("nombre"));

				supermercados.add(supermercado);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return supermercados;
	}

	public Supermercado getSupermercado(int id) {
		Supermercado supermercado = new Supermercado();

		try {
			prt = con.prepareStatement("SELECT * FROM supermercados WHERE id=?");
			prt.setInt(1, id);
			ResultSet result = prt.executeQuery();

			result.next();

			supermercado.setId(result.getInt("id"));
			supermercado.setNombre(result.getString("nombre"));

			return supermercado;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return supermercado;
	}

	public void insertarProductoSupermercado(int id_producto, int idSupermercado) {

		try {
			prt = con.prepareStatement("INSERT INTO productos_supermercados (id_producto,id_supermercado) VALUES(?,?)");

			prt.setInt(1, id_producto);
			prt.setInt(2, idSupermercado);

			prt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
