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

    <!-- Morris Charts CSS -->
    <link href="<c:url value='/backend/vendor/morrisjs/morris.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/backend/vendor/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>

</head>
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Food4jo의 통계</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	행사 일정 통계 - <span id="barGraph">6개월</span>
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        	기간 선택
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a id='quarter'>3개월</a>
                                        </li>
                                        <li><a id='half'>6개월</a>
                                        </li>
                                        <li><a id='year'>1년</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-bar-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	<span id="donutGraph">회원 및 트럭 통계</span>
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        	선택
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a id='all'>모든 회원 수</a>
                                        </li>
                                        <li><a id='seller'>지역별 트럭분포</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-donut-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Line Chart Example
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-line-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <!-- Area Chart Example -->
                            DB와 연동한 그래프입니다
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-area-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
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



    <!-- Morris Charts JavaScript -->
    <script src="<c:url value='/backend/vendor/raphael/raphael.min.js'/>"></script>
    <script src="<c:url value='/backend/vendor/morrisjs/morris.js'/>"></script>
    <script>
    $(function() {
    	$("#quarter").click(function(){
			console.log("버튼클릭함");
			$.ajax({
				url:"<c:url value='/Back/Graph.do'/>",
				type:'post',
				dataType:'text',
				data:'term=3',
				success:function(data){
					console.log("성공"+data);
					$("#barGraph").html("3개월");
					barChart.setData(JSON.parse(data));
				},
				error:function(data){
					console.log("에러발생 : "+data);
				}
			});
		});
		$("#half").click(function(){
			console.log("버튼클릭함");
			$.ajax({
				url:"<c:url value='/Back/Graph.do'/>",
				type:'post',
				dataType:'text',
				data:'term=6',
				success:function(data){
					console.log("성공"+data);
					$("#barGraph").html("6개월");
					barChart.setData(JSON.parse(data));
				},
				error:function(data){
					console.log("에러발생 : "+data);
				}
			});
		});
		$("#year").click(function(){
			console.log("버튼클릭함");
			$.ajax({
				url:"<c:url value='/Back/Graph.do'/>",
				type:'post',
				dataType:'text',
				data:'term=12',
				success:function(data){
					console.log("성공"+data);
					$("#barGraph").html("1년");
					barChart.setData(JSON.parse(data));
				},
				error:function(data){
					console.log("에러발생 : "+data);
				}
			});
		});
//////////////////////////////////////////////////////////////////이 아래부터 도넛그래프		
		$("#all").click(function(){
			console.log("버튼클릭함");
			$.ajax({
				url:"<c:url value='/Back/Graph.do'/>",
				type:'post',
				dataType:'text',
				data:'type=all',
				success:function(data){
					console.log("성공"+data);
					$("#donutGraph").html("회원 및 트럭 통계");
					donutChart.setData(JSON.parse(data));
				},
				error:function(data){
					console.log("에러발생 : "+data);
				}
			});
		});
		$("#seller").click(function(){
			console.log("버튼클릭함");
			$.ajax({
				url:"<c:url value='/Back/Graph.do'/>",
				type:'post',
				dataType:'text',
				data:'type=seller',
				success:function(data){
					console.log("성공"+data);
					$("#donutGraph").html("지역별 트럭 분포 통계");
					donutChart.setData(JSON.parse(data));
				},
				error:function(data){
					console.log("에러발생 : "+data);
				}
			});
		});		
//////////////////////////그래프 선택메뉴 관련////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////그래프 데이터 관련/////////////////////////////////////
        Morris.Area({
            element: 'morris-area-chart',
            data: //${data}
                [{
                period: '2010 Q1',
                iphone: 2666,
            },  {
                period: '2010 Q2',
                iphone: 2778,
            }, {
                period: '2010 Q3',
                iphone: 4912,
            }, {
                period: '2010 Q4',
                iphone: 3767,
            }, {
                period: '2011 Q1',
                iphone: 6810,
            }, {
                period: '2011 Q2',
                iphone: 5670,
            }, {
                period: '2011 Q3',
                iphone: 4820,
            }, {
                period: '2011 Q4',
                iphone: 15073,
            }, {
                period: '2012 Q1',
                iphone: 10687,
            }, {
                period: '2012 Q2',
                iphone: 8432,
            }] ,
            xkey: 'period',
            ykeys: ['iphone'],
            labels: ['IPHONE'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        });
        
        var donutChart = Morris.Donut({
            element: 'morris-donut-chart',
            data: ${memberCounts},
            resize: true
        });

        var barChart = Morris.Bar({
            element: 'morris-bar-chart',
            data: ${eventData},
            xkey: 'period',
            ykeys: ['count'],
            labels: ['갯수'],
            barColors:['green'],
            hideHover: 'auto',
            resize: true
        });
        
        Morris.Line({//이걸 매출액 버전으로 만들면 될것같긴하다.
        	  element: 'morris-line-chart',
        	  data: [
        	    { y: '2006', a: 100, b: 90 },
        	    { y: '2007', a: 75,  b: 65 },
        	    { y: '2008', a: 50,  b: 40 },
        	    { y: '2009', a: 75,  b: 65 },
        	    { y: '2010', a: 50,  b: 40 },
        	    { y: '2011', a: 75,  b: 65 },
        	    { y: '2012', a: 100, b: 90 }
        	  ],
        	  xkey: 'y',
        	  ykeys: ['a', 'b'],
        	  labels: ['Series A', 'Series B']
    	});
    });
    </script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>
	</body>
</html>