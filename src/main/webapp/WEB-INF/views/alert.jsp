<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script>
	var msg = "<c:out value='${msg}'/>";
	var url = "<c:out value='${url}'/>";
	if (msg == "닉네임을 입력해주세요." || msg == "사용 가능한 닉네임입니다." || msg == "이미 사용중인 닉네임입니다.") {
		alert(msg);
		location.href = history.go(-1);	
	} else if (msg == 'ID를 입력해주세요.' || msg == '이미 사용중인 아이디입니다.' || msg == '사용 가능한 아이디입니다.') {
		alert(msg);
		location.href = history.go(-1);	
	} else if (msg == '후기를 입력해주세요.' || msg == '별점을 0 ~ 10 사이로 입력해 주세요.' || msg == '별점이 없습니다.') {
		alert(msg);
		location.href = history.go(-1);
	} else {
		alert(msg);
		location.href = url;
	} 
</script>
