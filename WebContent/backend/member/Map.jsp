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
    <script src="<c:url value='/backend/js/sojaeji2.js'/>"></script><!-- 소재지 파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4fa6b1aa17406c2b2c3553c2e41aad3a&libraries=services"></script>
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
                            Area Chart Example
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<article>
								<fieldset>
									<legend>주소그룹</legend>
									<div style="margin-left: 30px;">
										<select class="form-control" name="sido" id="sido" style="width: 10%; float: left; margin-right: 5px"></select>
										<select class="form-control" name="gugun" id="gugun" style="width: 10%; float: left; margin-right: 5px"></select>
										<select class="form-control" name="dong" id="dong" style="width: 10%; float: left; margin-right: 5px"></select>
										<input id="address" class="form-control" type="text" placeholder="상호명" style="width: 20%; float:left; margin-right: 5px"/>
										<input id="submit" class="btn btn-primary" type="button" value="검색"/>
									</div>
								</fieldset>
							</article>
                            <div id="map" style="width:100%;height:700px;margin-top: 20px;"></div>
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
    <script type="text/javascript">
		new sojaeji('sido', 'gugun', 'dong');
    </script>
	<script>
	var container = document.getElementById('map');
	var options = {
		center: new daum.maps.LatLng(33.450701, 126.570667),
		level: 3
	};
	var positions = [
	                 {
	                     title: '카카오', 
	                     latlng: new daum.maps.LatLng(33.450705, 126.570677)
	                 },
	                 {
	                     title: '생태연못', 
	                     latlng: new daum.maps.LatLng(33.450936, 126.569477)
	                 },
	                 {
	                     title: '텃밭', 
	                     latlng: new daum.maps.LatLng(33.450879, 126.569940)
	                 },
	                 {
	                     title: '근린공원',
	                     latlng: new daum.maps.LatLng(33.451393, 126.570738)
	                 }
	             ];
	var map = new daum.maps.Map(container, options);
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성
	var mapTypeControl = new daum.maps.MapTypeControl();
	// 지도에 컨트롤을 추가해야 지도위에 표시
	// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성
	var zoomControl = new daum.maps.ZoomControl();
	map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);

	var marker = new daum.maps.Marker({ 
	    // 지도 중심좌표에 마커를 생성합니다 
	    position: map.getCenter() 
	}); 
	// 지도에 마커를 표시합니다
	//marker.setMap(map);
	//var iwContent = '<div style="padding:5px;">한국소프트웨어<br>인재개발원</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //iwPosition = new daum.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다
	// 인포윈도우를 생성합니다
	var infowindow = new daum.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
	});
	// 마커에 마우스오버 이벤트를 등록합니다
	daum.maps.event.addListener(marker, 'mouseover', function() {
	  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
	    infowindow.open(map, marker);
	});

	// 마커에 마우스아웃 이벤트를 등록합니다
	daum.maps.event.addListener(marker, 'mouseout', function() {
	    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
	    infowindow.close();
	});


	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	//infowindow.open(map, marker)
	// 장소 검색 객체를 생성합니다
	var ps = new daum.maps.services.Places();
	// 키워드를 생성한다
	var keyword="";
	<!-- 검색한 주소값 keyword에 저장 -->
	$(function(){
		$('#submit').click(function(){
			var a=$('#sido').val();			
			var b=$('#gugun').val();
			var c=$('#dong').val();
			var d=$('#address').val()==null?" ":$('#address').val();
			keyword = a+" "+b+" "+c+" "+d;
			// 키워드로 장소를 검색합니다
			ps.keywordSearch(keyword, placesSearchCB); 
			console.log(keyword);
		});
	});
	// 키워드로 장소를 검색합니다
	//ps.keywordSearch("경기도 이천시 장호원읍", placesSearchCB); 
		
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB (data, status, pagination) {
	    if (status === daum.maps.services.Status.OK) {
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new daum.maps.LatLngBounds();
	        for (var i=0; i<data.length; i++) {
	        	//일단 마커표시안함!!!
	        	//일단 마커표시안함!!!
	        	//일단 마커표시안함!!!
	            //displayMarker(data[i]);    
	            bounds.extend(new daum.maps.LatLng(data[i].y, data[i].x));
	        }       
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	        map.setBounds(bounds);
	    } 
	}
	</script>
    <!-- jQuery -->
    <script src="<c:url value='/backend/vendor/jquery/jquery.min.js'/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>
	
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>
</body>
</html>