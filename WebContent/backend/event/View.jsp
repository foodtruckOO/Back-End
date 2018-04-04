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
function deleteOK(no){
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href="<c:url value='/Back/EventDelete.do?no="+no+"'/>";
	}
}
function editOK(no){
	location.href="<c:url value='/Back/EventEdit.do?no="+no+"'/>";
}
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
                    <h1 class="page-header">이벤트 상세보기페이지임</h1>
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
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                	<tr>
                                		<td width="20%">작성자</td>
                                		<td width="80%">${eventdto.id}</td>
                                	</tr>
                                	<tr>
                                		<td rowspan="2">제목</td>
                                		<td>${eventdto.title}</td>
                                	</tr>
                                	<tr>
                                		<td><img alt="${eventdto.title}" src="${titlePath}"></td>
                                	</tr>
            						
                                	<tr>
                                		<td>내용</td>
                                		<td><img alt="${eventdto.content}" src="${contentPath}"></td>
                                	</tr>
                                	<tr>
                                		<td>작성일자</td>
                                		<td>${eventdto.postdate}</td>
                                	</tr>
                                	<tr>
                                		<td>이벤트 기간</td>
                                		<c:if test="${eventdto.s_date == eventdto.e_date}" var="oneDayOnly">
                                			<td>${eventdto.s_date}</td>
                                		</c:if>
                                		<c:if test="${not oneDayOnly}">
                                			<td>${eventdto.s_date} ~ ${eventdto.e_date}</td>
                                		</c:if>
                                	</tr>
                                </table>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
		                    <button onclick="editOK(${eventdto.eno})" class="btn btn-info">수정</button>
		                    <button onclick="deleteOK(${eventdto.eno})" class="btn btn-danger">삭제</button>
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
