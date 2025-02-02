<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, Flutter, PHP e muito mais - Casa do Código</title>
</head>
<body>
	<h1>Casa do Código - Lista de Produtos</h1>
	
	<div>
		${sucesso}
		${falha}
	</div>

	<table>
		<thead>
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Páginas</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td>
						<a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">
							${produto.titulo}
						</a>
					</td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>