<%@page import="java.util.List"%>
<%@page import="kz.news.dto.ArticleDTO" %>    

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="public/css/main.css"></link>
</head>
<body>
	
	<%@include file="header.jsp" %>

	<div class="body">
		<%
			List<ArticleDTO> top10NewsList = (List)request.getAttribute("top10NewsList");
		%>
		
		<table>
		
		<%
			for (ArticleDTO a: top10NewsList) {
		%>
		
			<tr>
				<td>
					<a target="blank" href="article/<%=a.getSubURL() %>">
						<%=a.getTitle() %>
					</a>
				</td>
			</tr>
		
		<%
			}
		%>
		
		</table>
		
	</div>

	<%@include file="footer.jsp" %>

</body>
</html>