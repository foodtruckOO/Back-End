<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Food4JO - Fresh, Overpowered, Omnivorous, Delicious한 음식의 세계로</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/backend/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value='/backend/vendor/metisMenu/metisMenu.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/backend/dist/css/sb-admin-2.css'/>" rel="stylesheet">
	<!-- datepicker용 -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />

    <!-- Custom Fonts -->
    <link href="<c:url value='/backend/vendor/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value='/backend/js/jquery.validate.js'/>"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4fa6b1aa17406c2b2c3553c2e41aad3a&libraries=services"></script>
<script> 
var geocoder = new daum.maps.services.Geocoder();
geocoder.addressSearch('${sellerInfo.addr}', function(result, status) {//첫 인자로 주소를 넣어야 한다. 따라서 json으로 주소를 넘겨주는 게 필수
	if (status === daum.maps.services.Status.OK) {
        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: coords, // 지도의 중심좌표
            level: 4 // 지도의 확대 레벨
        };
        var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new daum.maps.Marker({
            map: map,
            position: coords
        });
    } 
});//지오코드 종료
function deleteOK(no){
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href="<c:url value='/Back/SellerDelete.do?no="+no+"'/>";
	}
}
function editOK(no){
	location.href="<c:url value='/Back/SellerEdit.do?no="+no+"'/>";
}
$(function(){
	$('.imageHover').mouseover(function(e) {
		var sWidth = window.innerWidth;
		var sHeight = window.innerHeight;
		var oWidth = $('.imagePanel').width();
		var oHeight = $('.imagePanel').height();
	// 레이어가 나타날 위치를 셋팅한다.
		var divLeft = e.clientX + 10;
		var divTop = e.clientY + 5;
		// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
		if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
		if( divTop + oHeight > sHeight ) divTop -= oHeight;
	
		// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
		if( divLeft < 0 ) divLeft = 0;
		if( divTop < 0 ) divTop = 0;
	
		$('#imagePanel').css({
			"top": divTop,
			"left": divLeft,
			"position": "absolute"
		}).show();
	}).mouseout(function(e) {
		$('#imagePanel').hide();
	});
});







</script>

<style>
	.ui-datepicker-trigger{
		position:relative;
		width:30px;
		height:20px;
		top:10px;
	}
	#imagePanel {
		position: absolute;
		display: none;
		background-color: #ffffff;
		border: solid 2px #d0d0d0;
		width: 350px;
		height: 150px;
		padding: 10px;
	}
</style>
</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">사장 게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	사장 게시판 내용
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <table width="100%" class="table table-striped table-bordered table-hover">
                                	<tr>
                                		<td width="20%">번호</td>
                                		<td width="80%">${sellerInfo.s_no}</td>
                                	</tr>
                                	<tr>
                                		<td>ID</td>
                                		<td>${sellerInfo.id}</td>
                                	</tr>
                                	<tr>
                                		<td>이름</td>
                                		<td>${sellerInfo.name}</td>
                                	</tr>
                                	<tr>
                                		<td>상호명</td>
                                		<td>${sellerInfo.tname}</td>
                                	</tr>
                                	<tr>
                                		<td rowspan="2">주소</td>
                                		<td>${sellerInfo.addr}<br/>${sellerInfo.addr2}</td>
                                	</tr>
                                	<tr>
                                		<td>
                                			<div id="map" style="width:450px;height:350px; min-width: 300px;"></div>
                                		</td>
                                	</tr>
                                	<tr>
                                		<td>연락처</td>
                                		<td>${sellerInfo.tel}</td>
                                	</tr>
                                	<tr>
                                		<td>사업자번호</td>
                                		<td>${sellerInfo.corporate_no}</td>
                                	</tr>
                                </table>
                                <!-- /.col-lg-6 (nested) -->
                                <p></p>
                                <c:if test="${not empty foodList}">
	                                <table width="100%" class="table table-striped table-bordered table-hover">
	                                	<thead>
	                                		<tr>
	                                			<th>음식명</th>
	                                			<th>분류</th>
	                                			<th>설명</th>
	                                			<th>사진</th>
	                                			<th>가격</th>
	                                		</tr>
	                                	</thead>
	                                	<tbody>
	                                		<c:forEach var="food" items="${foodList}">
	                                			<tr>
	                                				<td>${food.fname}</td>
	                                				<td>${food.type}</td>
	                                				<td>${food.content}</td>
	                                				<td class="imageHover">${food.picture}</td>
	                                				<td>${food.price}</td>
	                                			</tr>
	                                		</c:forEach>
	                                	</tbody>
	                                </table>
                                </c:if>
                                
                                <c:if test="${not empty tEventList}">
                                	<table>
	                                	<thead>
	                                		<tr>
	                                			<th>번호</th>
	                                			<th>제목</th>
	                                			<th>기간</th>
	                                			<th>작성일</th>
	                                		</tr>
	                                	</thead>
	                                	<tbody>
	                                		<c:forEach var="tEvent" items="${tEventList}">
	                                			<tr>
	                                				<td>${tEvent.eno}</td>
	                                				<td>${tEvent.title}</td>
	                                				<c:if test="${tEvent.s_date == tEvent.s_date}" var="oneday">
	                                					<td>${tEvent.s_date}</td>
	                                				</c:if>
	                                				<c:if test="${not oneday}">
	                                					<td>${tEvent.s_date}~${tEvent.s_date}</td>
	                                				</c:if>
	                                				<td>${tEvent.postdate}</td>
	                                			</tr>
	                                		</c:forEach>
	                                	</tbody>
	                                </table>
                                </c:if>
                       		</div>
                       		<div id="imagePanel">
                       			<img alt="이미지를 찾을 수 없습니다" src="">
                       		</div>
		                    <button onclick="editOK(${sellerInfo.s_no})" class="btn btn-info">수정</button>
		                    <button onclick="deleteOK(${sellerInfo.s_no})" class="btn btn-danger">삭제</button>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>
</body>
</html>
