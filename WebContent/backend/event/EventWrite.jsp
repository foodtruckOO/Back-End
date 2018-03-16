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
    <script src="<c:url value='/backend/js/jquery.validate.js'/>"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script>
	//validator용
	var dateerror="";
	$(function(){
		//1]input type submit일 때
		$("#frm").validate({
			rules:{
				title:{required:true, minlength:1},
				content:{required:true, minlength:1},
				attachedfile:"required",
				startdate:"required",
				enddate:{required:true}
			},
			messages:{
				title:		{
					required:"제목을 입력하셔야 합니다",
					minlength:"제목을 입력하셔야 합니다"
					},
				content:	{
					required:"내용을 입력하셔야 합니다",
					minlength:"내용을 입력하셔야 합니다"
					},
				attachedfile : {
					required:"파일을 첨부하셔야 합니다"
				},
				startdate:	{
					required:"시작날짜를 설정하셔야 합니다"
					},
				enddate:	{
					required:"종료날짜를 설정하셔야 합니다"//,
					//lt: "종료날짜로 시작날짜보다 이전인 날짜를 고를 수 없습니다" , lt:$("#sdatepicker:last").val()
					}
			}
		});
		//type=submit이 아니라서 이렇게 적용. 가입전 모달 한번 띄워서 확인받기
		$(":button").click(function(){
			$("#span").html("");
			//console.log("버튼클릭했고, "+$("#sdatepicker").val()+" "+$("#edatepicker").val()+typeof $("#edatepicker").val());
			var sdate = parseInt($("#sdatepicker").val().replace("-", "").replace("-", ""));
			var edate = parseInt($("#edatepicker").val().replace("-", "").replace("-", ""));
			//console.log(edate>sdate?"종료날짜가 더 큼":"시작날짜가 더 큼");
			//console.log(sdate+"  "+$("#sdatepicker").val()+"  "+$("#sdatepicker").val().replace("-", "").replace("-", ""));
			if(sdate>edate){
				dateerror="시작날짜는 종료날짜보다 클 수 없습니다";
				$("#span").html("시작날짜는 종료날짜보다 클 수 없습니다");
			}
			else if($("#frm").valid()){
				dateerror="";
				if(confirm('이대로 작성하시겠습니까?')){
					$("#frm").submit();
				}
			}
		});
	});
	//datepicker용
	$(function(){
		$("#sdatepicker, #edatepicker").datepicker({
			dateFormat:"yy-mm-dd"/*,
			showOn: "both" ,
			buttonImage:"<c:url value='/Images/calendar-icon.jpg'/>",
			buttonImageOnly:false */
			
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
</style>
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
                                    <form id="frm" action="<c:url value='/Back/EventWrite.do'/>" method="post">
                                        <div class="form-group">
                                            <label>글 제목</label>
                                            <input class="form-control" placeholder="제목을 입력하세요" name="title">
                                            </br>
                                        </div>
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="3" name="content"></textarea>
                                            </br>
                                        </div>
                                        <div class="form-group" >
                                            <label>게시판 분류</label>
                                            <select class="form-control"name="boardtype">
                                                <option value="1">메인페이지 이벤트 게시판</option>
                                                <option value="2">지역 행사 게시판</option>
                                            </select>
                                            </br>
                                        </div>
                                        <div class="form-group">
                                        	<label>행사일자</label></br>
                                        	<div style="display: inline-block; position: relative;"align="left">
                                        		시작일자 : <input type="text" id="sdatepicker" name="startdate" size="15" value='${s_date}'>
                                        	</div>
                                        	<div style="display: inline-block; position: relative;"align="left">
                                        		종료일자 : <input type="text" id="edatepicker" name="enddate" size="15" value='${s_date}'>
                                        		<span id="span" style="color: red"></span>
                                        	</div>
                                        </div>
                                        <div class="form-group">
                                            <label>첨부파일</label>
                                            <input type="file" name="attachedfile">
                                            </br>
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

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>

</body>

</html>
