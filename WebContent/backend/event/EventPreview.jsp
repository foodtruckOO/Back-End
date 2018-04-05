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
	<div id="TOP">
		<header>
			<script>
				var width = document.body.scrollWidth;
			</script>
			<script type="text/javascript">
				function popupOpen2() {
					var windowW = 650;
					var windowH = 500;
					var left = Math.ceil((window.screen.width - windowW) / 2);
					var top = Math.ceil((window.screen.height - windowH) / 2);
					var popUrl = "<c:url value='/com.sajo.foodtruck/front-end/views/member/Join.jsp'/>"; //팝업창에 출력될 페이지 URL
					var popOption = "width=" + windowW + ", height=" + windowH
							+ ", resizable=no, scrollbars=no, status=no, left="
							+ left + ", top=" + top + ", location=no;"; //팝업창 옵션(optoin)
					window.open(popUrl, "", popOption);
				}
			</script>
			<nav class="navbar navbar-default navbar-fixed-top" id="top_top">
				<!--  navbar-fixed-top -->
				<!-- logo -->
				<div class="container" id="top_bar">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="<c:url value='/Allevent.event'/>">FOODTRUCK</a>
					</div>

					<div id="navbar" class="navbar-collapse collapse">
						<!-- mypage login join -->
						<ul id="top_login" style="margin-bottom: 5px">
							<li><a href="<c:url value='/Member.page'/>">MYPAGE</a></li>&emsp;
							<li><a
								href="<c:url value='/com.sajo.foodtruck/front-end/views/login/Logout.jsp'/>">LOGOUT</a></li>&emsp;
						</ul>
						<!-- bar menu -->
						<ul class="nav navbar-nav navbar-right" id="top_menu">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">ABOUT
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a
										href="<c:url value='/com.sajo.foodtruck/front-end/views/about/FoodTruck.jsp'/>">푸드트럭이란</a></li>
									<li><a
										href="<c:url value='/com.sajo.foodtruck/front-end/views/about/FoodTruck_Introduce.jsp'/>">사이트
											소개</a></li>
									<li><a
										href="<c:url value='/com.sajo.foodtruck/front-end/views/about/FoodTruck_Person.jsp'/>">운영진</a></li>
								</ul></li>

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">FOODTRUCK
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="<c:url value='/list.foodtruck?area=all'/>">전체</a></li>
									<!-- <li><a href="#">종류별</a></li> -->
									<li><a
										href="<c:url value='/com.sajo.foodtruck/front-end/views/foodtruck/all/foodtruck_list.jsp'/>">지역별</a></li>
								</ul></li>

							<li><a
								href="<c:url value='/com.sajo.foodtruck/front-end/views/map/Findtruck.jsp'/>">FINDTRUCK</a></li>

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">EVENT
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="<c:url value='/Homeevent.event'/>">홈페이지
											이벤트</a></li>
									<li><a href="<c:url value='/Localevent.event'/>">지역
											이벤트</a></li>
								</ul></li>

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">COMMUNITY
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="<c:url value='/Com.board'/>">손님용 게시판</a></li>
									<li><a href="<c:url value='/Pizza.board'/>">사장님용 게시판</a></li>
								</ul></li>
						</ul>

					</div>
					<!--/.nav-collapse -->
				</div>
			</nav>
		</header>
	</div>
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
				<a href="<c:url value='/Homeevent.event'/>"><button class="btn btn-danger btn-lg btn-block" type="button">
								돌아가기</button></a>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
    <div>
	<footer id="footer_footer">
	<div class="container" style="background-color: orange; width: 100%">
		<div style="float: right; padding-top: 10px">푸드트럭소식을 만날 수 있는 공식 SNS 채널
				<img alt="facebook" src="<c:url value='/com.sajo.foodtruck/front-end/images/sns_f.png'/>" style="width: 30px; height: 30px;">
				<img alt="instagram" src="<c:url value='/com.sajo.foodtruck/front-end/images/sns_i.png'/>" style="width: 30px; height: 30px;">
				<img alt="twitter" src="<c:url value='/com.sajo.foodtruck/front-end/images/sns_t.png'/>" style="width: 30px; height: 30px;">
		</div>
		<div style="background-color: #ffc253; float: left; width: 65%; height: 30px; margin-top: 1%; padding: 0.4%">
			<span style="border: 1px solid orange; font-weight: bold; margin-top: 30%">공지사항</span>&emsp;
			<span style="font-weight: bold; margin-top: 30%">03/15(목) 도서문화상품권 결제 일시중단 안내</span>
		</div>
		<div class="page-header">
			<h5 style="text-align: center;"><br/>
				<a href="#"> 인재채용</a><a href="#"> 협력업체등록</a><a href="#"> 공지사항</a> 
				<a href="#"> 고객의 소리</a><a href="#"> 개인정보취급방침</a><a href="#"> 이용약관</a>
			</h5>
		</div>
		<ul class="footer_line">
			<li>㈜푸드트럭코리아 대표이사 한소인</li>
			<li>경기도 성남시 분당구 판교로 111번길 1</li>
			<li>전화 : 1588-7777</li>
			<li>팩스 : 0123-456-8888</li>
			<br/>
			<li>E-mail : foodtruck@sajo.co.kr</li>
			<li>사업자등록번호 : 220-00-33333호</li>
			<li>통신판매업 신고번호 : 제2018-경기성남-1111호 사업자정보확인</li>
		</ul>
		<p>&emsp;&emsp;&emsp;© 2018 FOODTRUCK Korea Corporation All
			Rights Reserved.</p>
	</div>
	</footer>
 	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
    </body>
</html>