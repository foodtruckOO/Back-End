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
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="<c:url value='/backend/js/jquery.validate.js'/>"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4fa6b1aa17406c2b2c3553c2e41aad3a&libraries=services"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
	.ui-datepicker-trigger{
		position:relative;
		width:30px;
		height:20px;
		top:10px;
	}
    label, input { display:block;}
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<script>
	dialog = $( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 400,
		width: 350,
		modal: true,
		buttons: {
		    "수정": function(){
		    	alert("수정버튼누름");
			},
			"삭제": function(){
		    	alert("삭제버튼누름");
			},
		    "취소": function() {
		    	dialog.dialog( "close" );
		    }
		},
		close: function() {
			dialog.dialog( "close" );
		}
	});
	
	form = dialog.find( "form" ).on( "submit", function( event ) {
	  event.preventDefault();
	  addUser();
	});
$("#showModal").button().click(function(){
    dialog.dialog( "open" );
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
                    <h1 class="page-header">이벤트 작성</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	이벤트 게시판 내용 작성
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="frm" action="<c:url value='/Back/NoMemberRegister.do'/>" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label>상호명</label>
                                            <input class="form-control" placeholder="상호명을 입력하세요" name="tname">
                                            </br>
                                        </div>
                                        <div class="form-group">
                                        	<label>지역</label>
                                        	<a id="showModal">지도에서 선택하기</a>
                                        </div>
                                        <input type="button" class="btn btn-success" value="작성"/>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
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

	<!-- ModalPage -->
    <div id="dialog-form" title="Create new user">
		<p class="validateTips">지역을 선택해 주세요</p>
		<div id="map" style="width:80%;height:350px;margin-top: 20px;"></div>
	</div>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
		
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new daum.maps.Map(mapContainer, mapOption); 
	</script>
    <!-- /ModalPage -->

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>

</body>

</html>
