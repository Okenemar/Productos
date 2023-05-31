<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<h1 class="fw-bold">Modificar Producto</h1>
			<c:if test="${error eq true}">
				<div class="alert alert-danger" role="alert">Has introducido
					un dato incorrectamente</div>
			</c:if>
			<form method="POST" action="ModificarProducto">
				<p class="fw-bold">
					Id: ${producto.id } <input type="hidden" name="id"
						value="${producto.id }" /> <br>
				</p>
				<p class="fw-bold">
					Codigo: <input type="text" name="codigo"
						value="${producto.codigo }" /> <br>
				</p>

				<p class="fw-bold">
					Nombre: <input type="text" name="nombre" value="${producto.nombre}" />
					<br>
				</p>
				<br>
				<p class="fw-bold">
					Cantidad: <input type="text" name="cantidad"
						value="${producto.cantidad}" /> <br>
				</p>
				<br>
				<p class="fw-bold">
					Caducidad: <input type="text" name="caducidad"
						value="${producto.caducidad}" /> <br>
				</p>
				<p class="fw-bold">
					Precio: <input type="text" name="precio"
						value="${producto.precio }" required /> <br>
				</p>
				<b>Secciones:</b> <select name="seccion">
						
						<c:forEach items="${secciones}" var="seccion">
							<c:if test="${seccion.id == producto.seccion.id}">
								<option value="${seccion.nombre}" selected>${seccion.nombre}</option>
							</c:if>	
							<c:if test="${seccion.id != producto.seccion.id}">
								<option value="${seccion.nombre}">${seccion.nombre}</option>
							</c:if>	
						</c:forEach>
						
					</select> <br> <br>
				<br> <input type="submit" class="btn btn-secondary"
					value="Modificar" /> <a href="VerProductos" class="btn btn-dark">Volver</a>
			</form>
		</div>
	</div>


</body>
</html>