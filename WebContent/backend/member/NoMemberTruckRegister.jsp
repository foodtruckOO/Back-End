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

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<style>
	    label, input { display:block;}
	    input.text { margin-bottom:12px; width:95%; padding: .4em; }
	    fieldset { padding:0; border:0; margin-top:25px; }
	    h1 { font-size: 1.2em; margin: .6em 0; }
	    .ui-dialog .ui-state-error { padding: .3em; }
	</style>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/backend/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value='/backend/vendor/metisMenu/metisMenu.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/backend/dist/css/sb-admin-2.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/backend/vendor/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">  
    tr.a:hover { 
    color:#FE9A2E;
    font-size: 1.2em;
    }
</style> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<c:url value='/backend/js/jquery.tabletojson.min.js'/>"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4fa6b1aa17406c2b2c3553c2e41aad3a&libraries=services"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script language="javascript">
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrCoordUrl.do)를 호출하게 됩니다.
	var pop = window.open("<c:url value='/backend/member/jusoPopup.jsp'/>","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2){//,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo,entX,entY){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadFullAddr.value = roadFullAddr;//이부분에서 값이 안넘어온다는건가?뭐지?
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.roadAddrPart2.value = roadAddrPart2;
		document.form.addrDetail.value = addrDetail;
		document.form.roadAddrPart1_5.value="";
}
$(function(){//메뉴추가
	$("#addMenu").click(function(){
		$("#menuTbody").append("<tr>" +
			"<td>" + $("#menuName").val() + "</td>" +
			"<td>" + $("#menuPrice").val() + "</td>" +
			"<td>" + "<a id='menuDelete'>삭제</a>" + "</td>" +
			"</tr>");
	
		$("#menuName").val("");
		$("#menuPrice").val("");
		var table = $('#menuTable').tableToJSON({
			ignoreColumns: [2]//인덱스 2번에 해당하는 칼럼-삭제 연결하는 a태그는 담지 않음
		});
		$("#menuList").val(JSON.stringify(table));
		console.log($("#menuList").val());
		alert(JSON.stringify(table));
	});
	$(document).on("click","#menuDelete",function(){//메뉴삭제
		if(confirm("해당 메뉴를 삭제합니다 : "+$(this).parent().parent().children("td:first").html())){
			$(this).parent().parent().remove();
			var table = $('#menuTable').tableToJSON({
				ignoreColumns: [2]//인덱스 2번에 해당하는 칼럼-삭제 연결하는 a태그는 담지 않음
			});
			$("#menuList").val(JSON.stringify(table));
			console.log($("#menuList").val());
			alert(JSON.stringify(table));
		}
	});
});
</script>
</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">지도</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
							비회원 등록
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
	                       	<fieldset>
	                       		<form id="form" name="form" method="post" action="<c:url value='/Back/NoMemberRegister.do'/>" enctype="multipart/form-data">
	                       			<table style="width: 100%">
	                       				<tr>
	                       					<td width="50%">
												<label for="tname">상호명</label>
												<input type="text" name="tname" id="tname" value="" class="text ui-widget-content ui-corner-all">
											</td>
											<td width="50%">
												<label for="tal">연락처</label>
												<input type="text" name="tel" id="tel" value="" class="text ui-widget-content ui-corner-all">
											</td>
										</tr>
									</table>
									<label for="addr">위치</label>
								    <div id="mapDiv">
										<div id="map" style="width:100%;height:350px;"></div>
									</div>
		                        	<input type="button" onClick="goPopup()" class="form-control" value="검색해서 찾기" style="display: inline-block;"/>
									<input type="hidden" class="form-control" id="roadFullAddr" name="roadFullAddr"/><br>
									도로명주소 <input type="text" class="form-control" id="roadAddrPart1" name="roadAddrPart1" readonly/><br>
									지번주소 <input type="text" class="form-control" id="roadAddrPart1_5" name="roadAddrPart1_5" readonly/><br>
									고객입력 상세주소<input type="text" class="form-control" id="addrDetail" name="addrDetail"/><br>
									<input type="hidden" class="form-control" id="roadAddrPart2" name="roadAddrPart2" /><br>
									<!-- 
									메뉴리스트
									<table width="100%" class="table table-striped table-bordered table-hover" id="menuTable">
										<thead>
											<tr>
												<th>메뉴명</th>
												<th>가격</th>
												<th>편집</th>
											</tr>
										</thead>
										<tbody id="menuTbody" style="border: 1px green solid">
										</tbody>
									</table>
									메뉴명 <input type="text" size="10" style="display: inline-block;" id="menuName">&nbsp;&nbsp;&nbsp;
									가격<input type="text" size="8" style="display: inline-block;" id="menuPrice">&nbsp;&nbsp;&nbsp;
									<input type="button" value="메뉴 추가" style="display: inline-block;" id="addMenu"></br>
									<input type="hidden" id="menuList" name="menuList">
									 -->
									파일첨부<input type="file" id="attachedFile" name="attachedFile"/><br>
								 	<!-- Allow form submission with keyboard without duplicating the dialog button -->
									<input type="submit" value="작성">
								</form>
							</fieldset>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    
    <!-- ModalPage -->
    <!-- /ModalPage -->
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(37.566835, 126.978652), // 지도의 중심좌표
        level: 7 // 지도의 확대 레벨
    };
// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new daum.maps.Map(mapContainer, mapOption); 
//주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();
//지도를 클릭한 위치에 표출할 마커입니다
var marker = new daum.maps.Marker({ 

}); 
// 지도에 마커를 표시합니다
marker.setMap(map);
//마커가 드래그 가능하도록 설정합니다 
marker.setDraggable(true);
function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}
// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
daum.maps.event.addListener(map, 'click', function(mouseEvent) {
	searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        if (status === daum.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);
			if(result[0].address==null) document.getElementById('roadAddrPart1_5').value='선택하신 곳에서 지번주소를 얻을 수 없습니다.';
			else document.getElementById('roadAddrPart1_5').value=result[0].address.address_name;
			if(result[0].road_address==null) document.getElementById('roadAddrPart1').value='선택하신 곳에서 도로명주소를 얻을 수 없습니다.';
			else document.getElementById('roadAddrPart1').value=result[0].road_address.address_name;
        }   
    });
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    daum.maps.event.addListener(marker, 'dragend', function() {
    	latlng = marker.getPosition();
        searchDetailAddrFromCoords(latlng, function(result, status) {
            if (status === daum.maps.services.Status.OK) {
                marker.setPosition(latlng);
    			if(result[0].address==null) document.getElementById('roadAddrPart1_5').value='선택하신 곳에서 지번주소를 얻을 수 없습니다.';
    			else document.getElementById('roadAddrPart1_5').value=result[0].address.address_name;
    			if(result[0].road_address==null) document.getElementById('roadAddrPart1').value='선택하신 곳에서 도로명주소를 얻을 수 없습니다.';
    			else document.getElementById('roadAddrPart1').value=result[0].road_address.address_name;
            }
        });
    });
});

////////////////////////////////////////////////////////////////////////////////////////////////
</script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>
</body>
</html>