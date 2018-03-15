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

	<script src="http://code.jquery.com/jquery-3.1.1.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
var jsonData;
function deleteOK(no){
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href="<c:url value='/Back/EventDelete.do?no="+no+"'/>";
	}
}
function sendWrite(){
	location.href="<c:url value='/Back/EventWrite.do'/>";
}
$(function(){
	$("#all").click(function(){
		console.log("모두선택 클릭함");
		$.ajax({
			url:"<c:url value='/Back/Ajax.do'/>",
			type:"post",
			dataType:"text",
			data:"type=all",
			success:function(data){
				jsonData=data;
			},
			error:function(data){
				console.log("에러발생:"+data)
			}
		});
	});
	$("#type1").click(function(){
		console.log("1선택 클릭함");
		$.ajax({
			url:"<c:url value='/Back/Ajax.do'/>",
			type:"post",
			dataType:"text",
			data:"type=1",
			success:function(data){
				jsonData=data;
			},
			error:function(data){
				console.log("에러발생:"+data)
			}	
		});
		
	});
	$("#type2").click(function(){
		console.log("2선택 클릭함");
		$.ajax({
			url:"<c:url value='/Back/Ajax.do'/>",
			type:"post",
			dataType:"text",
			data:"type=2",
			success:function(data){
				jsonData=data;
			},
			error:function(data){
				console.log("에러발생:"+data)
			}
		});
		
	});
});
function successAjax(data, target){
	console.log('서버로부터 받은 데이터 : ', data, ', 자료형 : ', typeof data);
	var tableString = "";
	$.each(data, function(index, record) {
		tableString+="<tr class='gradeA'>";
		tableString+="<td>"+record.eno+"</td><td>"+record.id+"</td><td>"+record.title+"</td><td>"+record.boardtype+"</td><td>"+record.s_date+"</td><td>"+편집영역+"</td>";
		tableString+="</tr>";
	})
	$(target).html(tableString);
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
                    <h1 class="page-header">지역 행사 안내글 목록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a id="all" href="#">전체보기</a>
                                        </li>
                                        <li><a id="type1" href="#">홈페이지 행사글만 보기</a>
                                        </li>
                                        <li><a id="type2" href="#">지역 행사글만 보기</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
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
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true,
            data: jsonData
            	
            ,columns:[
            	{data:"eno"},
            	{data:"id"},
            	{data:"title"},
            	{data:"boardtype"},
            	{data:"s_date"},
            	{data:"e_date"}            	
            ]
        });
    });
    </script>

</body>

</html>