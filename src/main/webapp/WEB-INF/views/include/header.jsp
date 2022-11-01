<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ======= Header ======= -->
<!-- <header id="header" class="fixed-top">  -->
  <div class="container d-flex align-items-center">
    <h1 class="logo me-auto"><a href="${root}?action=goHome">Live</a></h1>
    <!-- Uncomment below if you prefer to use an image logo -->
    <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

    <nav id="navbar" class="navbar">
      <ul>
        <li><a class="nav-link scrollto active" href="${root}?action=goHome">Home</a></li>
        <li><a class="nav-link scrollto" href="${root}?action=goAptInfo">아파트 매매 정보</a></li>
        <li><a class="nav-link scrollto" href="${root}?action=goIntro">소개</a></li>
        <li><a class="nav-link scrollto" href="${root}/boards">공지사항</a></li>
        <li class="dropdown">
          <a href="#" class="getstarted scrollto"><span>Log In</span> <i class="bi bi-chevron-down"></i></a>
          <ul>
          	<c:choose>
	          	<c:when test="${empty sessionScope.userId}">
		            <li><a href="${root}?action=goLogin">로그인</a></li>
		            <li><a href="${root}?action=goRegist">회원가입</a></li>
		            <li><a href="${root}?action=goFindId">ID PWD 찾기</a></li>
		        </c:when>
	            <c:otherwise>
		            <li><a href="${root}/user?action=logout">로그아웃</a></li>
		            <li><a href="${root}/user?action=confirm">회원정보 확인</a></li>
		            <li><a href="${root}/interest?action=list">관심지역 조회</a></li>
		        </c:otherwise>
            </c:choose>
          </ul>
        </li>
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav>
    <!-- .navbar -->
  </div>
</header>
<!-- End Header -->