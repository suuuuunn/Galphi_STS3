<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" href="./css/account.css">
<style>@import url('https://fonts.googleapis.com/css2?family=Gowun+Batang&family=Gowun+Dodum&display=swap')</style>
<script type="text/javascript" src="js/Account.js" defer="defer"></script>
</head>
<body style="font-family: 'Gowun Dodum', sans-serif;">
	<div class="container-fluid"
		style="background-image: url('./images/bg.jpg'); 
		background-repeat: no-repeat; 
		background-attachment: scroll; 
		background-size: cover;">
		<header class="container text-center"  style="background-color: rgba(255, 255, 255, 0.7);">
			<form action="account_insert" method="post" onsubmit="return account(this)">
			    <div id="container">
			        <!--  login 폼 영역을 : loginBox -->
			        <div id="loginBox"  style="background-color: rgba(255, 255, 255, 0.1);">
			            <!-- 로그인 페이지 타이틀 -->
			            <div id="loginBoxTitle">회원가입</div>
			            <!-- 아이디, 비번, 버튼 박스 -->
			            <div id="inputBox" style="font-weight: bold;">
			
			                <div class="input-form-box"><span>이름 </span>
			                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" class="form-control" placeholder="이름을 입력하세요"/>
			                </div>
			
			                <div class="input-form-box"><span>닉네임 </span>
			                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="nickname" class="form-control" placeholder="닉네임을 입력하세요"/>
			                    <input class="nick_Check" type="button" name="nick_Check" value="중복확인" onclick="nickCheck(form)"/>
			                </div>
			
			                <div class="input-form-box"><span>아이디 </span>
			                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" class="form-control" placeholder="아이디를 입력하세요"/>
			                    <input class="Id_Check" type="button" name="Id_Check" value="중복확인" onclick="IdCheck(form)"/>
			                </div>
			
			                <div class="input-form-box"><span>비밀번호 </span>
			                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요"/>
			                </div>
			
			                <div class="input-form-box"><span>비밀번호확인 </span>
			                    &nbsp;&nbsp;&nbsp;<input type="password" name="repassword" class="form-control" placeholder="비밀번호를 다시 입력하세요" onkeyup="pwCheck()"></div>
			                    <div id="checkMsg">
			                </div><br/>
			
			                <div class="button-login-box" >
			                    <input type="button" class="btn btn-primary btn-xs" style="width:47%" value="가입" onclick="CreateAcc(form)">
			                    <input type="button" class="btn btn-secondary btn-xs" style="width:47%" value="취소" onclick="location.href='index'">
			                </div>
			            </div>
			
			        </div>
			    </div>
			</form>
	</header>
</div>

</body>
</html>
