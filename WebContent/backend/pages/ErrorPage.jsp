<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <!--IE Compatibility modes-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--Mobile first-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>500</title>
    
    <meta name="description" content="Free Admin Template Based On Twitter Bootstrap 3.x">
    <meta name="author" content="">
    
    <meta name="msapplication-TileColor" content="#5bc0de" />
    <meta name="msapplication-TileImage" content="assets/img/metis-tile.png" />
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/backend/vendor/bootstrap/css/bootstrap.css'/>">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/backend/vendor/bootstrap/fonts/font-awesome.css">
    
    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="<c:url value='/backend/vendor/metisMenu/main.css'/>">
    
    <!-- metisMenu stylesheet -->
    <link rel="stylesheet" href="<c:url value='/backend/vendor/metisMenu/metisMenu.css'/>">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
	function gotoBack() {
		history.back();
	}
</script>
</head>

<body class="error">
    <div class="container">
        <div class="col-lg-8 col-lg-offset-2 text-center">
            <div class="logo">
                <h1>���� �߻�!</h1></br>
            </div>
            <p class="lead text-muted" style="color: white;">�̷�...����ġ ���� ��Ȳ��...</p>
            <div class="col-lg-6 col-lg-offset-3">
            	<h3 style="color: white;text-align: left;">�Ʒ� ���׵��� Ȯ���� �ּ���</h3>
                <p style="color: white;text-align: left;">�������� ����Ǿ����ϴ�</p>
                <p style="color: white;text-align: left;">�������� ��η� �����Ͽ����ϴ�</p>
            </div>
            <div class="clearfix"></div>
            <div class="sr-only">
                &nbsp;
            </div>
            <br>
            <div class="col-lg-6 col-lg-offset-3">
                <div class="btn-group btn-group-justified">
                    <a href="<c:url value='/Back/Login.do'/>" class="btn btn-info">�α��� ȭ������ ����</a>
                    <a onclick="gotoBack()" class="btn btn-warning">�ڷ� ����</a>
                </div>
            </div>
        </div>
        <!-- /.col-lg-8 col-offset-2 -->
    </div>
</body>

</html>
