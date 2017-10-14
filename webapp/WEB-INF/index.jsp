<%@page import="java.util.List"%>
<%@page import="kz.news.dto.ArticleDTO" %>    

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Articles</title>
<link type="text/css" rel="stylesheet" href="public/css/main.css"></link>
</head>
<body>
	
	<%@include file="header.jsp" %>
    <h1>${top10News.amount}</h1>
	<div class="body">
		<table>
			<c:forEach items="${top10News.resultList}" var="a" varStatus="cnt">
				<c:if test = "${ cnt.index % 4 == 0 }">
    				<tr>
				</c:if>
					<td>
					   <div class="card">
					       <div class="image">
                                <a target="blank" href='article/<c:out value="${a.subURL}" />'>
                                    <img src="public/image/sample.jpg" title="Something"/>
                                </a>
					       
					       </div>
					       
					       <div class="content">
								<a target="blank" href='article/<c:out value="${a.subURL}" />'>
									<c:out value="${a.title}" />
								</a>
					       </div>
					   </div>
					
					
					</td>
	
   	            <c:if test = "${ cnt.last ||  cnt.index % 4 == 3 }">
    				</tr>
                </c:if>
    
				
			</c:forEach>
		</table>
		
		
		
		<div>
		  <c:forEach begin="1" end="${top10News.amount}" var="i" varStatus="cnt">
            <a target="blank" href='news?page=<c:out value="${i}" />'>
                <c:out value="${i}" />
            </a>
          </c:forEach>
		</div>
		
	</div>

	<%@include file="footer.jsp" %>

</body>
</html>