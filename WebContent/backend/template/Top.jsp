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
                <a class="navbar-brand" href="<c:url value='/backend/pages/Index.jsp'/>">이것은 푸드트럭 백엔드</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
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
                        			<h4>회원등급 : ${dto.grade=='1'?'사장님':'핫산'}</h4>
                        			<p style="text-align: center;">
                        				<c:if test="${dto.grade=='1' }">
                        					<img src="<c:url value='/backend/img/boss.jpg'/>"/>
                        				</c:if>
                        				<c:if test="${dto.grade=='2' }">
                        					<img src="<c:url value='/backend/img/hassan.png'/>"/>
                        				</c:if>
                        			</p>
                        		</div>
                        	</c:if>
                        </li>
                        <li>
                            <a href="<c:url value='/backend/pages/Index.jsp'/>"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
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
                                    <a href="<c:url value='/Back/Board.do?type=admin'/>"><i class="fa fa-list fa-fw"></i>관리자 게시판</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="<c:url value='/Back/Graph.do'/>"><i class="fa fa-bar-chart-o fa-fw"></i>각종 통계 확인</a>
                        </li>
                        <li>
                            <a href="<c:url value='/Back/Map.do'/>"><i class="fa fa-map-marker fa-fw"></i>푸드트럭 위치조회</a>
                        </li>
                        <li>
                            <a href="<c:url value='/Back/Calendar.do'/>"><i class="fa fa-calendar fa-fw"></i>행사일정월력 관리</a>
                        </li>
                        <li>
                            <a href="<c:url value='/backend/pages/Forms.jsp'/>"><i class="fa fa-edit fa-fw"></i>Forms</a>
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