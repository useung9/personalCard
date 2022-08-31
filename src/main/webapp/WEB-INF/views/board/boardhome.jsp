<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includeFile/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이력서 목록</title>
</head>


<script type="text/javascript" src="${path}/resources/js/board/board.js"> </script>


<body>
	게시판
	<%-- ${userList} --%>

<form  id="fmtPost" method="POST">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Navbar</a>
			
			<div class="collapse navbar-collapse" id="navbarColor02">
					<select class="" id="findkey" name="findkey" style="width=150px;" >
					<option value="">검색조건</option>
					<option value="userName" <c:out value="${page.findkey == 'userName' ? 'selected':'' }"/>> 이름</option>
					<option value="userComp" <c:out value="${page.findkey == 'userComp' ? 'selected':'' }"/>> 소속회사</option>
					<option value="userDept"<c:out value="${page.findkey == 'userDept' ? 'selected':'' }"/>> 부서</option>
					<option value="userAddr"<c:out value="${page.findkey == 'userAddr' ? 'selected':'' }"/>> 주소</option>
					<option value="career_date"<c:out value="${page.findkey == 'career_date' ? 'selected':'' }"/>> 경력</option>
				</select>
				&nbsp;&nbsp;
				<input type="text" class="findvalue" id="findvalue" name="findvalue"  placeholder="검색명"  value="${page.findvalue}">
				 &nbsp;&nbsp;
				 <select class="" id="findcareer" name="findcareer" style="width=150px;">
					<option value="">경력사항</option>
					<option value="1" <c:out value="${page.findcareer == 'findcareer' ? '1':'' }"/>>1년이상</option>
					<option value="2" <c:out value="${page.findcareer == 'findcareer' ? '2':'' }"/>>2년이상</option>
					<option value="3" <c:out value="${page.findcareer == 'findcareer' ? '3':'' }"/>>3년이상</option>
					<option value="4" <c:out value="${page.findcareer == 'findcareer' ? '4':'' }"/>>4년이상</option>
					<option value="5" <c:out value="${page.findcareer == 'findcareer' ? '5':'' }"/>>5년이상</option>
					<option value="6" <c:out value="${page.findcareer == 'findcareer' ? '6':'' }"/>>6년이상</option>
					<option value="7" <c:out value="${page.findcareer == 'findcareer' ? '7':'' }"/>>7년이상</option>
					<option value="8" <c:out value="${page.findcareer == 'findcareer' ? '8':'' }"/>>8년이상</option>
					<option value="9" <c:out value="${page.findcareer == 'findcareer' ? '9':'' }"/>>9년이상</option>
					<option value="10" <c:out value="${page.findcareer == 'findcareer' ? '10':'' }"/> >10년이상</option>
				</select>
				
			<select id="genderSelect" name="genderSelect">
				<option value="" selected="selected" style="width=150px;">성별</option>
				<option value="남성" <c:out value="${page.genderSelect == '남성' ? 'selected':''}"/>>남성</option>
				<option value="여성" <c:out value="${page.genderSelect == '여성' ? 'selected':''}"/>>여성</option>
			</select>
			<!-- <input type="checkbox" id="userMaritalStatus" name="userMaritalStatus"> 결혼 여부 -->
			
			<!-- 결혼 여부 확인 -->
			<select id="userMaritalStatusSelect" name="userMaritalStatusSelect">
			<option value="" selected="selected"style="width=150px;">결혼 유무</option>
				<option value="미혼" <c:out value="${page.userMaritalStatusSelect == '미혼' ? 'selected':''}"/>>미혼</option>
				<option value="기혼" <c:out value="${page.userMaritalStatusSelect == '기혼' ? 'selected':''}"/>>기혼</option>
			</select>
			<!-- Sorting -->			
			<select id="userInfoDataSize" name="perPage"style="width=150px;">
				<option value="10" <c:out value="${page.perPage == '10' ? 'selected':''}"/>>10개씩</option>
				<option value="20" <c:out value="${page.perPage == '20' ? 'selected':''}"/> >20개씩</option>
			</select>
				
					<!-- <button class="btn btn-secondary my-2 my-sm-0" id="SearchData" type="button">Search</button> -->
					<button  onclick="btnclick(e)">검색</button>
				
			</div>
		</div>
	</nav>
</form>
<!-- 다른 폼을 이용한.  idx값 클릭시 실행 homectroller -> 유저 idx값 세팅 - home.jsp에서 해당 값 유무 확인 있다면 정보조회 -->
<form id="detailFrm" method="POST" action="${path}/">
	<input type="hidden" id="userIdx" name="userIdx" >
</form>

	<table class="table table-hover">
		<thead align="center">
			<tr class="table-info">
				<td>등록번호</td>
				<td>성명</td>
				<td>소속회사</td>
				<td>부서</td>
				<td>성별</td>
				<td>경력</td>
				<td>등록날짜</td>
			</tr>
		</thead>
		<c:if test="${page.totcnt != 0}">
		<tbody align="center">

			<c:forEach var="user" items="${userList}">
				<tr>
					<%-- <td>${user.RNUM}</td> --%>
					<td><button id="idxbtn" onclick="idxbtn('${user.USER_IDX}')">${user.USER_IDX}</button></td>
					<td>${user.USER_NAME}</td>
					<td>${user.USER_COMP}</td>
					<td>${user.USER_DEPT}</td>
					<td>${user.USER_SEX}</td>
					<td>${user.CAREER_DATE}</td>
					<td>
						<%-- ${user.USERREGISTERDATE} --%>
						<fmt:parseDate value="${user.USERREGISTERDATE}" pattern="yyyy-MM-dd" var="regidate"></fmt:parseDate>
						<fmt:formatDate value="${regidate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>


		</tbody>
	</table>
<!-- 페이징 라인 -->
		페이징
		${page}
		<br>
		조회에 맞는 데이터 수 page.totcnt: ${page.totcnt}
		<div align="center">
		  <ul class="pagination">
		  <c:if test = "${totcnt == 0}">
		  	<div>조회 데이터 없음.</div>
		  </c:if>
		  
			  <c:if test="${page.startPage != 1}">
			  	  <li class="page-item disabled">
			      <a class="page-link" href="${path}/board?curPage=${page.startPage-1}&findkey=${page.findkey}&findvalue=${page.findvalue}&findcareer=${page.findcareer}&genderSelect=${genderSelect}&userMaritalStatusSelect=${userMaritalStatusSelect}&userInfoDataSize=${userInfoDataSize}">&laquo;</a>
			    </li>
			  </c:if>
			  
			    
			    <c:forEach 	var="i" begin="${page.startPage}" end ="${page.endPage}">
			    <li class="page-item ">
			      <a class="page-link" href="${path}/board?curPage=${i}&findkey=${page.findkey}&findvalue=${page.findvalue}&findcareer=${page.findcareer}&genderSelect=${genderSelect}&userMaritalStatusSelect=${userMaritalStatusSelect}${userInfoDataSize}">${i}</a>
			    </li>
			    </c:forEach>
			 
			    <li class="page-item">
			      <a class="page-link"href="${path}/board?curPage=${page.startPage+1}&findkey=${page.findkey}&findvalue=${page.findvalue}&findcareer=${page.findcareer}&genderSelect=${genderSelect}&userMaritalStatusSelect=${userMaritalStatusSelect}">&raquo;</a>
			    </li>
			  </ul>
		  
		</div>

</body>
</html>