
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
			<h1 class="fw-bold">Registrar Producto</h1>

			<form method="POST" action="RegistrarProducto">
				<c:if test="${error eq true}">
					<div class="alert alert-danger" role="alert">Has introducido
						un dato incorrectamente</div>
				</c:if>
				<p>
					<b>codigo:</b> <input type="text" name="codigo" /> <br>
				</p>
				<br>
				<p>
					<b>nombre:</b> <input type="text" name="nombre" /> <br>
				</p>
				<br>
				<p>
					<b>cantidad</b> <input type="text" name="cantidad" /> <br>
				</p>
				<br>
				<p>
					<b>precio</b> <input type="text" name="precio" /> <br>
				</p>
				<br>
				<p>
					<b>caducidad:</b> <input type="date" name="caducidad" /> <br>
				</p>
				<br>
				<p>


					Secciones: <select name="seccion">
						<option value="0"></option>
						<c:forEach items="${secciones}" var="seccion">
							<option value="${seccion.id}">${seccion.nombre}</option>
						</c:forEach>
					</select> <br> <br>
				</p>
				<br> <input type="submit" class="btn btn-secondary"
					value="Insertar" /> <a href="VerProductos" class="btn btn-dark">Volver</a>
			</form>


		</div>
	</div>
</body>
</html>