<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>푸드트럭 - 놀랍도록 빠르고 굉장할 정도로 맛있습니다!</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/backend/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value='/backend/vendor/metisMenu/metisMenu.min.css'/>" rel="stylesheet">

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
    <!-- jQuery -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value='/backend/js/jquery.validate.js'/>"></script>
    
<script>
	var validateId="";
	$(function(){
		//1]input type submit일 때
		$("#frm").validate({
			rules:{
				id:{required:true, minlength:2, maxlength:8},
				pwd:{required:true, minlength:4, maxlength:10},
				pwd2:{required:true, minlength:4, maxlength:10, equalTo: "input[name=pwd]"},
				email:{required:true, minlength:2, email: "true"}
			},
			messages:{
				id:		{
					required:"id를 입력하셔야 합니다",
					minlength:"아이디는 2자 이상 입력하셔야 합니다",
					maxlength:"아이디는 8자 이하로 입력하셔야 합니다"
					},
				pwd:	{
					required:"비밀번호를 입력하셔야 합니다",
					minlength:"비밀번호는 4자 이상 입력하셔야 합니다",
					maxlength:"비밀번호는 10자 이하로 입력하셔야 합니다"
					},
				pwd2:	{
					required:"비밀번호를 입력하셔야 합니다",
					minlength:"비밀번호는 4자 이상 입력하셔야 합니다",
					maxlength:"비밀번호는 10자 이하로 입력하셔야 합니다",
					equalTo:"비밀번호가 일치하지 않습니다"
					},
				email:	{
					required:"이메일을 입력하셔야 합니다",
					email: "이메일 형식에 맞지 않습니다"
					}
			}
		});
		$(":button:first").click(function(){
			var id = $("input[name=id]").val();
			$.ajax({
				url:'<c:url value="/Back/IdCheck.do"/>',
				type:'post',
				dataType:'text',
				data:"id="+id,
				success:function(data){
					$('#idCheckResult').html(data);
					if(data=="사용 가능한 아이디입니다"){
						validateId=$("input[name=id]").val();
					}
					console.log("다음 아이디는 유효합니다 : "+validateId);
					
				},
				error:function(data){
					console.log("에러발생:"+data);
				}
			});
		});
		//type=submit이 아니라서 이렇게 적용. 가입전 모달 한번 띄워서 확인받기
		$(":button:last").click(function(){
			console.log("버튼클릭했음");
			if($("#frm").valid()&&$("#idCheckResult").html() == "사용 가능한 아이디입니다"){
				if(confirm('기입된 정보대로 회원가입을 하시겠습니까?')){
					$("#frm").submit();
				}
			}
		});
		$("input[name=id]").on("keydown",function(){
			if($(this).val()!=validateId){
				$.ajax({
					url:'<c:url value="/Back/IdCheck.do"/>',
					type:'get',
					success:function(data){
						$('#idCheckResult').html("");
						validateId="";
					}
				})
			}
		});
		$("input[name=id]").on("keyup",function(){
			if($(this).val()!=validateId){
				$.ajax({
					url:'<c:url value="/Back/IdCheck.do"/>',
					type:'get',
					success:function(data){
						$('#idCheckResult').html("");
						validateId="";
					}
				})
			}
		});
		
	});
</script>
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">관리자 회원가입 페이지</h3>
                    </div>
                    <div class="panel-body">
                        <form id="frm" action="<c:url value='/Back/Register.do'/>" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="아이디는 영문 및 숫자만 가능합니다" name="id" type="txt" autofocus style="display: inline-block;width: 78%">
                                    <input type="button" class="btn btn-warning" value="id검사" style="display:inline; width: 18%"><br/>
                                    <label class="error" for="idInput" generated="true"></label></br>
                                    <label id="idCheckResult"></label>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="비밀번호를 입력하세요" name="pwd" type="password" value="">
                                    <br/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="비밀번호 한번 더 입력" name="pwd2" type="password" value="">
                                    <br/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="이메일을 입력하세요" name="email" type="email">
                                    <br/>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="button" class="btn btn-lg btn-success btn-block" value="회원가입">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>
</body>
</html>
