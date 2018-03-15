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
                                        <th width="20%">이름</th>
                                        <th width="20%">소속</th>
                                        <th width="20%">직책</th>
                                        <th width="20%">권한</th>
                                        <th width="20%">비고</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="gradeA">
                                        <td>컨카</td>
                                        <td>힘</td>
                                        <td>래디언트</td>
                                        <td class="center">이니시에이터</td>
                                        <td class="center">예측샷필요</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>칼드르</td>
                                        <td>지능</td>
                                        <td>다이어</td>
                                        <td class="center">디스에이블러</td>
                                        <td class="center">궁졸라잘씀</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>에잘로르</td>
                                        <td>지능</td>
                                        <td>래디언트</td>
                                        <td class="center">서포트</td>
                                        <td class="center">무천도사+간달프</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>로툰드지어</td>
                                        <td>지능</td>
                                        <td>다이어</td>
                                        <td class="center">캐리</td>
                                        <td class="center">졸라안죽음</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>바라스럼</td>
                                        <td>힘</td>
                                        <td>다이어</td>
                                        <td class="center">캐리</td>
                                        <td class="center">돌진성애자</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>오스타리온</td>
                                        <td>힘</td>
                                        <td>다이어</td>
                                        <td class="center">캐리</td>
                                        <td class="center">부활충</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>아눕세란</td>
                                        <td>민첩</td>
                                        <td>다이어</td>
                                        <td class="center">캐리</td>
                                        <td class="center">세란이 축지법 쓰신다</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>유네로</td>
                                        <td>민첩</td>
                                        <td>래디언트</td>
                                        <td class="center">캐리</td>
                                        <td class="center">내가 저거넛이여 썅것아</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>자르바코</td>
                                        <td>지능</td>
                                        <td>다이어</td>
                                        <td class="center">누커</td>
                                        <td class="center">루께릿고</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>레이고</td>
                                        <td>힘</td>
                                        <td>래디언트</td>
                                        <td class="center">이니시에이터</td>
                                        <td class="center">토템충</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>액스</td>
                                        <td>힘</td>
                                        <td>다이어</td>
                                        <td class="center">디스에이블러</td>
                                        <td class="center">운수도 좋은 날이구나!</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>자키로</td>
                                        <td>지능</td>
                                        <td>래디언트</td>
                                        <td class="center">이니시에이터</td>
                                        <td class="center">선딜레이 좀 어떻게 해줘봐</td>
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