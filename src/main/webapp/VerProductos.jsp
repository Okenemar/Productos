<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

</head>
<body>

	<div class="container">
		<div class="form-container">
			<h1 class="fw-bold">GESTION DE PRODUCTOS</h1>
			<form method="get" action="Buscador">
				<p class="fw-bold">
					Buscar producto <input type="text" name="buscador" /><br> <br>
					<input type="submit" class="btn-primary" value="Buscar">
				</p>
				<a href="VerProductos" class="btn btn-dark">Refrescar</a>


			</form>
			
			<form method="get" action="Precio">
				<p class="fw-bold">
					Filtrar precio 
					<input type="text" name="precioMin" placeholder="minPrecio">
					<input type="text" name="precioMax" placeholder="maxPrecio">
					
					<input type="submit" class="btn-primary" value="Buscar">
					
				</p>
				</form>
				<a href="OrdenAscendente" class="btn btn-dark">Orden Ascendente</a>
				<a href="OrdenDescendente" class="btn btn-dark">Orden Descendente</a>
				
			<form method="get" action="EliminarProductos">
				<p class="fw-bold">Eliminar Varios Productos</p>
				
				<input type="text" name="eliminarVarios" placeholder="Selecciona varios productos para eliminar">
				<input type="submit" class="btn-danger" value="Eliminar">
			</form>
			<table class="table">

				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">id</th>
						<th scope="col">codigo</th>
						<th scope="col">nombre</th>
						<th scope="col">cantidad</th>
						<th scope="col">precio</th>
						<th scope="col">caducidad</th>
						<th scope="col">seccion</th>

						<th scope="col"></th>
						<th scope="col"></th>

						<td><a href="RegistrarProducto" class="btn btn-dark">Insertar
						</a></td>


					</tr>
				</thead>
				<tbody>

					<c:forEach items="${productos}" var="producto">
						<tr>
							<th scope="row"></th>
							<td>${producto.id}</td>
							<td>${producto.codigo}</td>
							<td>${producto.nombre}</td>
							<td>${producto.cantidad}</td>
							<td>${producto.precio}</td>
							<td>${producto.caducidad}</td>
							<td>${producto.seccion.nombre}</td>



							<td><a href="ModificarProducto?id=${producto.id}"
								class="btn btn-secondary ">Modificar </a></td>
							<td><a href="Eliminar?id=${producto.id}"
								class="btn btn-danger ">Eliminar </a></td>


						</tr>

					</c:forEach>



				</tbody>
			</table>
		</div>
	</div>

</body>
</html>