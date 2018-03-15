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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
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
                                        <th width="10%">글번호</th>
                                        <th width="20%">작성자</th>
                                        <th width="50%">글제목</th>
                                        <th width="10%">유형</th>
                                        <th width="10%">작성일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="gradeA">
                                        <td>1</td>
                                        <td>ㅇㅇ</td>
                                        <td>아니이건무슨</td>
                                        <td class="center">문의</td>
                                        <td class="center">18/02/12</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>2</td>
                                        <td>망캐</td>
                                        <td>사이트 운영 하긴 하는거냐</td>
                                        <td class="center">문의</td>
                                        <td class="center">18/02/13</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>3</td>
                                        <td>HOS</td>
                                        <td>히어로즈 오브 더 스☆톰 가입시$$전원 카드팩☜☜뒷면100%증정※ 월드오브 워크래프트 펫 무료증정￥</td>
                                        <td class="center">기타</td>
                                        <td class="center">18/02/14</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>4</td>
                                        <td>광고아님</td>
                                        <td>여의도 감성포차 추천</td>
                                        <td class="center">소개</td>
                                        <td class="center">18/02/15</td>
                                    </tr>
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
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>

</body>

</html>