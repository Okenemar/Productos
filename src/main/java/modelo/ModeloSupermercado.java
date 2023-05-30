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
		
		ArrayList <Supermercado> supermercados = new ArrayList<>();
		try {
			prt = con.prepareStatement("SELECT id, nombre FROM supermercados");
			
			ResultSet result = prt.executeQuery();
			
			while(result.next()) {
				Supermercado supermercado = new Supermercado();
				
				supermercado.setId(result.getInt(1));
				supermercado.setNombre(result.getString(2));
				
				supermercados.add(supermercado);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return supermercados;
	}


	

	public void registrarSuperProducto(int idProducto, int idSupermercado) {
		
		
		try {
			prt = con.prepareStatement("INSERT INTO productos_supermercados(id_producto, id_supermercado) VALUES (?,?)");
		
			prt.setInt(1, idProducto);
			prt.setInt(2, idSupermercado);
			
			prt.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean productoSuperExiste(int idProducto) {
		boolean existe = false;
		try {
			prt = con.prepareStatement("SELECT id_supermercado FROM productos_supermercados WHERE id_producto=?");
			
			prt.setInt(1, idProducto);
			
			ResultSet resultado = prt.executeQuery();
			
			if (resultado.next()) {
				existe = true;
				
			}else {
				existe = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	 public void eliminarProductoSuper(int idProducto) {
		 
		 try {
			prt = con.prepareStatement("DELETE FROM productos_supermercados WHERE id_producto=?");
			prt.setInt(1, idProducto);

			prt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	
	
}
