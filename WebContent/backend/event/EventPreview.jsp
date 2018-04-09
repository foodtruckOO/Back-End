<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Foodtruck:: ver1.0</title>

    <!-- Bootstrap core CSS -->    
    <link href="<c:url value='/bootStrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="<c:url value='/bootStrap/css/bootstrap-theme.min.css'/>" rel="stylesheet">
    <!-- YangGeum template CSS -->    
    <link rel="stylesheet" href="<c:url value='/bootStrap/css/previewTemplate.css'/>" type="text/css" />
    <!-- Custom styles for this template -->    
	
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>	
		$(function(){
			var currentPosition = parseInt($("#sidebox").css("top"));
			$(window).scroll(function() {
				var position = $(window).scrollTop(); 
				$("#sidebox").stop().animate({"top":position+currentPosition+"px"},1000);
			});
			
		});
	</script>
</head>
<body>
	<!-- 내용 시작 -->
	<div class="container-fluid">
	
		<div class = "row">
			<div class="col-md-2">
			<h2>${dto.title}</h2>
			<h2 style="font-size:22px ;">${dto.content}</h2>
			<h3>기간</h3>
				<p>${dto.s_date} ~ ${dto.e_date}<p>
			</div>
			<div class="col-md-8">
				<img class ="img-responsive" src="<c:url value='/com.sajo.foodtruck/front-end/images/${dto.contentfile}'/>"/>
			</div>
		</div>
		<div class = "row" style = "padding-top:50px;padding-bottom: 50px">
			<div class = "col-md-offset-4 col-md-3">
				<a href="<c:url value='/Homeevent.event'/>">
					<button class="btn btn-danger btn-lg btn-block" type="button">돌아가기</button>
				</a>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
    </body>
</html>