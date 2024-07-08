<%@page import="com.tjoeun.Galphi.vo.BookCommentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>@import url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap')</style>
<style>@import url('https://fonts.googleapis.com/css2?family=Gowun+Batang&family=Gowun+Dodum&display=swap')</style>
</head>
<body>

	<div class="container-fluid"
		style="background-image: url('./images/bg.jpg'); 
		background-repeat: no-repeat; 
		background-attachment: scroll; 
		background-size: cover;
		width: 100%; height: 100%">
		<!-- 헤더 -->
		<header class="container text-center"
			style="background-color: rgba(255, 255, 255, 0.7);">
			<!-- 로고 / 검색 폼 / 회원가입-->
			<div class="row">
				<!-- 로고 -->
				<div class="col-3">
					<button class="btn" type="button" onclick="location.href='index'">
						<img alt="갈피" src="./images/logo.png" title="로고">
					</button>
				</div>
				<!-- 로고 끝 -->
				<!-- 검색 폼 -->
				<div class="col-6 pt-5">
					<br/>
					<form class="form-control form-control-sm d-flex" action="list"
						method="post" name="search-requirement"
						style="background-color: #6D4C3D; border-radius: 12px; font-family: 'Gowun Dodum', sans-serif;">
						<select class="mr-2 text-center" name="category"
							style="width: 100px; background-color: #6D4C3D; color: white; border: none;">
							<option>제목</option>
							<option>저자</option>
							<option>제목+저자</option>
						</select>&nbsp; 
						<input type="text" class="form-control form-control-sm" placeholder="&nbsp;검색어를 입력하세요" name="item"> 
						<input type="hidden" name="list" value="Search"/>&nbsp;
						<button class="btn bg-primary" type="submit">
							<i class="bi bi-search text-white"></i>
						</button>
					</form>
					<!-- 검색 폼 끝 -->
				</div>
				<!-- 로그인/회원가입 폼 -->
				<div class="col-3" style="font-family: 'Gowun Dodum', sans-serif; color: gray;">
					<c:if test="${nickname == null}">
						<button class="btn btn-sm text-black-50 pt-4" type="button" onclick="location.href='login'">로그인</button>
						<button class="btn btn-sm text-black-50 pt-4" type="button" onclick="location.href='account_create'">회원가입</button>
					</c:if>
					<c:if test="${nickname != null}">
						<button type="button" class="btn btn-sm text-black-50 pt-4">${nickname}님 로그인 되었습니다.</button>
						<button type="button" class="btn btn-sm text-black-50 pt-4" onclick="location.href='logout'">로그아웃</button>
					</c:if>
				</div>
				<!-- 로그인/회원가입 폼 끝-->
			</div>
			<!-- 로고 / 검색 폼 / 회원가입 끝-->

			<!-- 카테고리 네비게이션 -->
			<div class="d-flex mx-5 justify-content-start">
				<ul class="d-flex nav nav-tabs rounded-pill" style="border: none;">
					<li class="nav-item dropdown"
						style="background-color: none; border-color: #6D4C3D;">
						<a class="nav-link dropdown-toggle link-light" data-bs-toggle="dropdown" href="#"> 
							<span style="color: #6D4C3D; font-family: 'Nanum Myeongjo', serif;">카테고리</span>
						</a>
						<ul class="dropdown-menu" style="opacity: 0.8">
							<li style="font-family: 'Nanum Myeongjo', serif;"><a class="dropdown-item" href="list?list=Novel">
								<span style="color: #6D4C3D;">소설</span></a></li>
							<li style="font-family: 'Nanum Myeongjo', serif;"><a class="dropdown-item" href="list?list=Develop">
								<span style="color: #6D4C3D;">자기계발</span></a></li>
							<li style="font-family: 'Nanum Myeongjo', serif;"><a class="dropdown-item" href="list?list=It">
								<span style="color: #6D4C3D;">IT/컴퓨터</span></a></li>
							<li style="font-family: 'Nanum Myeongjo', serif;"><a class="dropdown-item" href="list?list=Child">
								<span style="color: #6D4C3D;">아동</span></a></li>
							<li style="font-family: 'Nanum Myeongjo', serif;"><a class="dropdown-item" href="list?list=History">
								<span style="color: #6D4C3D;">역사</span></a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- 카테고리 네비게이션 끝 -->
		</header>
		<main class="container text-center pt-1"
		style="background-color: rgba(255, 255, 255, 0.7);">
		<div class="container px-4 px-lg-5" style="font-family: 'Gowun Dodum', sans-serif;">
			<form class="m-3" action="updatecommentOK2" method="post" name="commentForm"> 
				<input type="hidden" name="size" size="1" value="${size}"/>
				<input type="hidden" name="avg" size="1" value="${avg}"/>
				<input type="hidden" name="coscore" size="1" value="${coscore}"/>
				<table style="width: 700px; margin-left: auto; margin-right: auto; background-color: rgba(255, 255, 255, 0.1);">
					<c:set var="co" value="${co}"/>
					<tr>
						<th class="align-middle text-center" colspan="4" style="font-size: 30px;">
							후기 수정<br/><br/>
						</th>
					</tr>
					
					<!-- 이 줄은 원래 보이면 안되는 줄로 테스트가 완료된 후 화면에 보이지 않게 한다. -->
					<!-- <tr style="display: none;"> -->
					<tr style="display: none;">
						<td colspan="4">
							<!-- 수정 또는 삭제할 댓글의 책번호-->
							ISBN: <input type="text" name="ISBN" size="1" value="${co.ISBN}"/>
							<!-- 현재 댓글이 누구의(?) 댓글인가 -->
							nick: <input type="text" name="nick" size="1" value="${co.nick}"/>
							
							idx:  <input type="text" name="idx" size="1" value="${co.idx}"/>
							<!-- 작업 모드, 1 => 댓글 저장, 2 => 댓글 수정, 3 => 댓글 삭제 -->
							<!-- mode: <input type="text" name="mode" value="1" size="1"/> -->
							<!-- 메인글이 표시되던 페이지 번호 -->
							<%-- currentPage: <input type="text" name="currentPage" value="${currentPage}" size="1"/> --%>
						</td>
					</tr>
					
					<!-- 이 줄 부터 댓글 입력, 수정, 삭제에 사용한다. -->
					<tr>
						<th class="align-middle text-center" style="width: 100px;">
							<label for="score">별점</label>
						</th>
						<td style="width: 250px;">
							<input id="score" class="form-control form-control-sm" type="text" name="score" value="${co.score}"/>
						</td>
						<th class="align-middle text-center" style="width: 100px;">
							<label for="wDate">작성일</label>
							
						</th>
						<td class="text-center align-middle" style="width: 250px;">
							<fmt:formatDate value="${co.wDate}" pattern="yy년 MM월 dd일(E)"/>에 남긴글
						</td>
					</tr>
				
					<tr>
						<th class="align-middle text-center" style="width: 100px;">
							<label for="memo">후기</label>
						</th>
						<td colspan="3" style="width: 600px;">
							<textarea 
								id="memo" 
								class="form-control form-control-sm" 
								rows="5" 
								name="memo"
								style="resize: none;">${co.memo}</textarea>
						</td>
					</tr>
					<tr class="table-warning">
						<td class="align-middle text-center" colspan="4">
							<br/>
							<input 
								class="btn btn-outline-dark btn-sm" 
								type="submit" 
								value="댓글수정" 
								style="font-size: 13px;"
								
								name="commentBtn"/>
							<!-- <input 
								class="btn btn-outline-danger btn-sm" 
								type="button" 
								value="다시쓰기" 
								style="font-size: 13px;"/> -->
						</td>
					</tr>
				</table>
			</form><br/><br/><br/>
			<!-- Footer-->
			<footer class="container bg-dark">
				<br/><br/>
				<!-- 카피라이트 -->
				<div class="container px-4 px-lg-5">
					<p class="m-0 text-center text-white">Copyright &copy; TJoeun
						Academy Team Project: Galphi 2024</p>
				</div>
				<!-- 만든이 / 연락처 -->
				<br/><br/>
			</footer>
		</div>
		</main>
	</div>

</body>
</html>















