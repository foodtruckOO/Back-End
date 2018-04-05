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

    <!-- DataTables CSS -->
    <link href="<c:url value='/backend/vendor/datatables-plugins/dataTables.bootstrap.css'/>" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<c:url value='/backend/vendor/datatables-responsive/dataTables.responsive.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/backend/dist/css/sb-admin-2.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/backend/vendor/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
	$(document).ready(function(){
		$('#table').DataTable({
			"order":[[0, "desc"]],
            responsive: true
		});
	});

	$("#frmDalete #delete").click(function(){
		console.log('버튼클릭인식함');
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#frmDelete').submit();
			//
		}
	});
	function deleteOK(no){
		if(confirm('정말로 삭제하시겠습니까?')){
			location.href="<c:url value='/Back/EventDelete.do?no="+no+"'/>";
		}
	}
	function sendWrite(){
		location.href="<c:url value='/Back/EventWrite.do'/>";
	}
	$(function(){
		var title = "";
		switch(${param.board}){
			case 1 : title="홈페이지 주관 행사 안내글 목록";break;
			case 2 : title="지역 행사 안내글 목록";break;
			default : title="오늘의 행사들";break;
		}
		$("h1.page-header").html(title);
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
                    <h1 class="page-header">게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="table">
                                <thead>
                                    <tr>
                                        <th width="8%">번호</th>
                                        <th width="10%">작성자</th>
                                        <th width="35%">제목</th>
                                        <th width="14%">분류</th>
                                        <th width="18%">기간</th>
                                        <th width="15%">편집</th>
                                    </tr>
                                </thead>
                                <tbody>
	                                <c:if test="${not empty list}">
	                                	<c:forEach var="item" items="${list}" varStatus="loop">
	                                		<tr class="gradeA">
	                                			<td>${item.eno}</td>
	                                			<td>${item.id}</td>
	                                			<td><a href="<c:url value='/Back/EventView.do?eno=${item.eno}'/>">${item.title}</a></td>
	                                			<td>${item.boardtype=='1'? '홈페이지 이벤트':item.boardtype=='1' ? '지역 이벤트':'진행중인 이벤트'}</td>
	                                			<c:if test="${item.s_date eq item.e_date}" var="oneday">
	                                				<td>${item.s_date}</td>
	                                			</c:if>
	                                			<c:if test="${not oneday }">
	                                				<td>${item.s_date} ~ ${item.e_date}</td>
	                                			</c:if>
	                                			<td>
	                                				<c:if test="${dto.grade=='1' || dto.id == item.id }" var="canModify">
		                                				<form method="get" action="<c:url value='/Back/EventEdit.do'/>" style="display: inline-block;">
		                                					<input type="submit" class="btn btn-info" value="수정">
		                                					<input type="hidden" value="${item.eno}"  name="no">
		                                				</form>
		                                				<button onclick="deleteOK(${item.eno})" class="btn btn-danger">삭제</button>
	                                				</c:if>
	                                				<c:if test="${not canModify}">
	                                					편집 권한이 없습니다
	                                				</c:if>
	                                			</td>
	                                		</tr>
	                                	</c:forEach>
	                                </c:if>
                                </tbody>
                            </table>
                            
                            <!-- /.table-responsive -->
                            <button class="btn btn-success" onclick="sendWrite()">글 작성</button>
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

    <!-- jQuery -->

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>


    <!-- DataTables JavaScript -->
    <script src="<c:url value='/backend/vendor/datatables/js/jquery.dataTables.min.js'/>"></script>
    <script src="<c:url value='/backend/vendor/datatables-plugins/dataTables.bootstrap.min.js'/>"></script>
    <script src="<c:url value='/backend/vendor/datatables-responsive/dataTables.responsive.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    </script>

</body>

</html>