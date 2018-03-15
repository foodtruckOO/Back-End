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
	$("#frmDalete #delete").click(function(){
		console.log('버튼클릭인식함');
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#frmDelete').submit();
			//
		}
	});
	function deleteOK(no){
		if(confirm('정말로 삭제하시겠습니까?')){
			location.href="<c:url value='/Back/AdminDelete.do?no="+no+"'/>";
		}
	}
</script>
</head>

<body>
    <div id="wrapper">
        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">관리자 명단</h1>
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
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th width="10%">이름</th>
                                        <th width="15%">권한</th>
                                        <th width="25%">직책</th>
                                        <th width="20%">이메일</th>
                                        <th width="30%">편집</th>
                                    </tr>
                                </thead>
                                <tbody>
	                                <c:if test="${not empty list}">
	                                	<c:forEach var="item" items="${list}" varStatus="loop">
	                                		<tr class="gradeA">
	                                			<td>${item.id}</td>
	                                			<td>${item.grade=='1'?'마스터 관리자':item.grade=='2'?'중간 관리자':'가입 대기'}</td>
	                                			<td>${item.grade=='1'?'모든 권한':item.grade=='2'?'관리자 관련 권한 제외 모든 권한':'권한 없음'}</td>
	                                			<td>${item.email}</td>
	                                			<td>
	                                				<c:if test="${dto.grade=='1' }">
		                                				<form method="get" action="<c:url value='/Back/AdminEdit.do'/>" style="display: inline-block;">
		                                					<input type="submit" class="btn btn-info" value="수정">
		                                					<input type="hidden" value="${item.a_no}" name="no">
		                                				</form>
		                                				<button onclick="deleteOK(${item.a_no})" class="btn btn-danger">삭제</button>
	                                				</c:if>
	                                				<c:if test="${dto.grade !='1' }">
	                                					마스터 관리자만이 편집 가능합니다
	                                				</c:if>
	                                			</td>
	                                		</tr>
	                                	</c:forEach>
	                                </c:if>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
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
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>

</body>

</html>