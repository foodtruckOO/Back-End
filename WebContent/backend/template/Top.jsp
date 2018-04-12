<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<jsp:include page="/backend/pages/common/IsMember.jsp"/>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value='/Back/Index.do'/>">Food4JO</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">   
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li>
                        	<c:if test="${empty sessionScope.dto}" var="isNotLogin">
                        		<a href="<c:url value='/Back/Login.do'/>"><i class="fa fa-sign-out fa-fw"></i> 로그인</a>
                        	</c:if>
                        	<c:if test="${!isNotLogin }">
                        		<a href="<c:url value='/Back/Logout.do'/>"><i class="fa fa-sign-out fa-fw"></i> 로그아웃</a>
                        	</c:if>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">

                        <li>
                        	<c:if test="${empty sessionScope.dto}" var="isNotLogin">
                        		<div style="padding-left: 10px;font-family: fantasy;">
                        			<h4>로그인 상태가 아님...</h4>
                        		</div>
                        	</c:if>
                        	<c:if test="${!isNotLogin }">
                        		<div style="padding-left: 10px;font-family: fantasy;">
                        			<h4>${dto.id}님 환영합니다</h4>
                        			<h4>회원등급 : ${dto.grade=='1'?'사장님':'중간관리자'}</h4>
                        			<p style="text-align: center;">
                        				<c:if test="${dto.grade=='1' }">
                        					<img src="<c:url value='/backend/img/boss.jpg'/>"/>
                        				</c:if>
                        				<c:if test="${dto.grade=='2' }">
                        					<img src="<c:url value='/backend/img/mid.jpg'/>"/>
                        				</c:if>
                        			</p>
                        		</div>
                        	</c:if>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-user fa-fw"></i>사용자관리<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                	<a href="<c:url value='/Back/UserList.do?user=customer'/>"><i class="fa fa-smile-o fa-fw"></i>고객리스트</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/Back/UserList.do?user=seller'/>"><i class="fa fa-money fa-fw"></i>사장리스트</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/Back/UserList.do?user=admin'/>"><i class="fa fa-desktop fa-fw"></i>관리자리스트</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>게시판관리<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                	<a href="<c:url value='/Back/Board.do?type=customer'/>"><i class="fa fa-list fa-fw"></i>고객게시판</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/Back/Board.do?type=seller'/>"><i class="fa fa-list fa-fw"></i>사장게시판</a>
                                </li>
                                <li>
	                                <a href="#"><i class="fa fa-sitemap fa-fw"></i>관리자게시판<span class="fa arrow"></span></a>
	                            	<ul class="nav nav-third-level">
		                                <li>
		                                    <a href="<c:url value='/Back/Board.do?type=admin&board=1'/>"><i class="fa fa-list fa-fw"></i>메인행사 게시판</a>
		                                </li>
		                                <li>
		                                    <a href="<c:url value='/Back/Board.do?type=admin&board=2'/>"><i class="fa fa-list fa-fw"></i>지역별행사 게시판</a>
		                                </li>
	                                </ul>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-truck fa-fw"></i>푸드트럭 관리<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
		                        <li>
		                            <a href="<c:url value='/Back/Map.do'/>"><i class="fa fa-map-marker fa-fw"></i>푸드트럭 위치보기</a>
		                        </li>
		                        <li>
		                            <a href="<c:url value='/Back/NoMemberRegister.do'/>"><i class="fa fa-user fa-fw"></i>비회원 트럭 등록</a>
		                        </li>
                            </ul>
                        </li>
	                    <li>
	                        <a href="#"><i class="fa fa-leaf fa-fw"></i>주문관리<span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
			                	<li>
			                		<a href="<c:url value='/Back/OrderOngoing.do'/>"><i class="fa fa-star fa-fw"></i>주문현황</a>
			                	</li>
			                    <li>
			                        <a href="<c:url value='/Back/OrderLog.do'/>"><i class="fa fa-star-o fa-fw"></i>주문내역조회</a>
			                    </li>
	                        </ul>
	                    </li>
                        <li>
                            <a href="<c:url value='/Back/Graph.do'/>"><i class="fa fa-bar-chart-o fa-fw"></i>각종 통계 확인</a>
                        </li>
                        <li>
                            <a href="<c:url value='/Back/Calendar.do'/>"><i class="fa fa-calendar fa-fw"></i>행사일정월력 관리</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<c:url value='/backend/pages/Panels-Wells.jsp'/>">Panels and Wells</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/backend/pages/Buttons.jsp'/>">Buttons</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/backend/pages/Notifications.jsp'/>">Notifications</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/backend/pages/Typography.jsp'/>">Typography</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/backend/pages/Icons.jsp'/>"> Icons</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/backend/pages/Grid.jsp'/>">Grid</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>