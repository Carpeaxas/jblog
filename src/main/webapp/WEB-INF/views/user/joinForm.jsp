<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join" >
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
	
	</div>
		<!-- 메인 푸터  자리-->
	

</body>

<script type="text/javascript">
$("#joinForm").on("submit",function(){
	
	console.log("버튼 클릭");
	var id = $("#txtId").val();
	var pass = $("#txtPassword").val();
	var check = $("#chkAgree").is(":checked");
	
	if(id.length < 1){
		alert("아이디를 입력하세요");
		return false;		
	}if(pass.length < 1){
		alert("비밀번호를 입력하세요");
		return false;	
	}if(check ==false){
		alert("서비스 약관에 동의하세요");
		return false;
	}
	return true;	
});

$("#btnIdCheck").on("click",function(){
	console.log("버튼 클릭");
	var id = $("#txtId").val();
	console.log(id);
	//통신  id////////////////////////////////////////////
	$.ajax({
		url : "${pageContext.request.contextPath }/user/idcheck",		
		type : "post",
		/* contentType : "application/json", */
		data : {id : id},

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult);
			
			if(jsonResult.result == 'success'){ //처리성공
				//사용가능한지 불가능 한지 표현한다
				if(jsonResult.data == true){
					//사용가능
					$("#tdMsg").html( id+ "는 사용가능 합니다.");    
				}else {
					//사용불가
					$("#tdMsg").html( id+ "는 사용중입니다.");    
				}
				
			}else {
				//메세지 출력
				var msg = jsonResult.failMsg;
				alert(msg);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});

</script>


</html>