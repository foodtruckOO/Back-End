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
				id:{required:true, minlength:3},
				pwd:{required:true, minlength:4},
				name:"required",
				tel:{required:true}
			},
			messages:{
				id:		{
					required:"아이디를 입력하셔야 합니다",
					minlength:"아이드는 3자 이상 입력하셔야 합니다"
					},
				pwd:	{
					required:"비밀번호를 입력하셔야 합니다",
					minlength:"비밀번호는 4자 이상 입력하셔야 합니다"
					},
				name:	{
					required:"이름을 입력하셔야 합니다"
					},
				tel:	{
					required:"연락처를 입력하셔야 합니다"
					}
			}
		});
		//type=submit이 아니라서 이렇게 적용. 가입전 모달 한번 띄워서 확인받기
		$(":button").click(function(){
			if($("#frm").valid()){
				if(confirm('이대로 수정하시겠습니까?')){
					$("#frm").submit();
				}
			}
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
                                    <form id="frm" action="<c:url value='/Back/MemberEdit.do'/>" method="post">
                                    	<input type="hidden" name="no" value="${editDto.g_no}">
                                    	<input type="hidden" name="type" value="customer">
                                        <div class="form-group">
                                            <label>아이디</label>
                                            <input class="form-control" name="id" value="${editDto.id}">
                                            </br>
                                        </div>
                                        <div class="form-group">
                                            <label>비밀번호</label>
                                            <textarea class="form-control" rows="3" name="pwd">${editDto.pwd}</textarea>
                                            </br>
                                        </div>
                                        <div class="form-group">
                                            <label>이름</label>
                                            <input class="form-control" value="${editDto.name}" name="name">
                                            </br>
                                        </div>
                                        <div class="form-group">
                                            <label>연락처</label>
                                            <input class="form-control" value="${editDto.tel}" name="tel">
                                            </br>
                                        </div>
                                        <input type="button" class="btn btn-success" value="수정"/>
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
