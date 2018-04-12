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

    <!-- DataTables CSS -->
    <link href="<c:url value='/backend/vendor/datatables-plugins/dataTables.bootstrap.css'/>" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<c:url value='/backend/vendor/datatables-responsive/dataTables.responsive.css'/>" rel="stylesheet">

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
<script>
var interval =  window.setInterval(function() {
	$.ajax({
		url : "<c:url value='/Back/OrderOngoing.do'/>",
		type : 'post',
		dataType : 'json',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data){
			console.log(data);
			ajaxResultSetter(data);
		},
		error : function(request, status, error){
			console.log("에러남");
			console.log(error);
		}
	});
}, 3000)
$(function(){
	$("#refresh").toggle(function(){
		clearInterval(interval);
	});
});
function ajaxResultSetter(data){
	var text = "";
	$.each(data, function(index, record) {
    	text+='<tr class="gradeA">';
    	text+='<td>'+record.o_no+'</td>';
    	text+='<td class="center">'+record.gname+'</td>';
    	text+='<td class="center">'+record.sname+'</td>';
    	text+='<td class="center">'+record.fname+'</td>';
    	text+='<td class="center">'+record.price+'</td>';
    	text+='<td class="center">'+record.stringPostdate+'<br/>'+record.timeOfReceipt+'</td>';
    	text+='<td class="center">'+record.content+'</td>';
    	text+='</tr>'
	});
	$("tbody").html(text);
}
</script>
</head>

<body>
<!-- 이 페이지는 실시간으로 처리되어야 한다. 웹소켓을 쓰던지 하지 않으면 매우 난처해지지 싶은 느낌이다. -->
    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">주문 목록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        <button id="refresh">최신화</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="table">
                                <thead>
                                    <tr>
                                        <th width="10%">번호</th>
                                        <th width="10%">주문자</th>
                                        <th width="12%">판매자</th>
                                        <th width="18%">상품</th>
                                        <th width="10%">가격</th>
                                        <th width="18%">주문/수령예정시간</th>
                                        <th width="20%">기타</th>
                                    </tr>
                                </thead>
                                <tbody>
	                            	<c:forEach var="order" items="${list}">
			                        	<tr class="gradeA">
			                            	<td>${order.o_no}</td>
			                            	<td class="center">${order.gname}</td>
			                            	<td class="center">${order.sname}</td>
			                            	<td class="center">${order.fname}</td>
			                            	<td class="center">${order.price}</td>
			                            	<td class="center">${order.stringPostdate}<br/>${order.timeOfReceipt}</td>
			                            	<td class="center">${order.content}</td>
			                        	</tr>
		                        	</c:forEach>
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
    <script src="<c:url value='/backend/vendor/jquery/jquery.min.js'/>"></script>

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

</body>
</html>