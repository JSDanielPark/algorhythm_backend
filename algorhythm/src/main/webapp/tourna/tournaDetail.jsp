<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AlgoRhythm - 알고리즘 트레이닝</title>

<link
	href="https://devdogs-cdn.azureedge.net/algorhythm/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://devdogs-cdn.azureedge.net/algorhythm/lib/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link
	href="https://devdogs-cdn.azureedge.net/algorhythm/lib/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link
	href='https://devdogs-cdn.azureedge.net/algorhythm/lib/highlight/styles/monokai-sublime.css'
	rel='stylesheet' type='text/css'>
<link
	href='https://devdogs-cdn.azureedge.net/algorhythm/lib/codemirror/lib/codemirror.css'
	rel='stylesheet' type='text/css'>
<link
	href='https://devdogs-cdn.azureedge.net/algorhythm/lib/codemirror/theme/monokai.css'
	rel='stylesheet' type='text/css'>
<link href="https://devdogs-cdn.azureedge.net/algorhythm/css/style.css"
	rel="stylesheet">
<script src="https://devdogs-cdn.azureedge.net/algorhythm/lib/jquery/jquery.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body id="page-top">
	<div id="wrap"></div>

	<span data-reactroot="">
		<div class="mask">
			<div class="mask-wrap">
				<div class="mask-content">
					<img src="/img/loading2.gif">
				</div>
			</div>
		</div>
		<nav class="navbar navbar-default navbar-fixed-top" id="mainNav">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand page-scroll navbar-brand" href="/"> <!-- react-text: 10 -->AlgoRhythm
						<!-- /react-text --> <i class="fa fa-music" aria-hidden="true">
					</i>
					</a>
					<button type="button" class="navbar-toggle">
						<span class="sr-only">Toggle navigation </span> <span
							class="icon-bar"> </span> <span class="icon-bar"> </span> <span
							class="icon-bar"> </span>
					</button>
				</div>
				<div id="devdogs-nav" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class=""><a class="page-scroll" href="/">Home </a></li>
						<li class="active"><a class="page-scroll" href="/exam/list">문제목록
						</a></li>
						<li class=""><a class="page-scroll" href="/exam/write">문제출제
						</a></li>
						<li class=""><a class="page-scroll" href="/exam/rank">명예의전당
						</a></li>
						<li class=""><a class="page-scroll" href="/exam/my">내 문제
						</a></li>
						<li class=""><a class="page-scroll" href="/member/modify">정보수정
						</a></li>
						<li class=""><a class="page-scroll" href="">로그아웃 </a></li>
					</ul>
				</div>
			</div>
		</nav> <span>
			<section id="member" class="">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="comment-header">
								<h1 class="comment-header-title">문제풀이</h1>
								<span class="comment-header-comment">알고리즘 문제풀이 공간 </span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="text-center">
								<table class="table table-board">
									<thead>
										<tr>
											<th class="text-center">번호</th>
											<th class="text-center">난이도</th>
											<th class="text-center">제목</th>
											<th class="text-center">등록일</th>
										</tr>
									</thead>
									<tbody>
										<script>
										$(document).ready(function() {
											$.extend({
											    getUrlVars: function(){
											        var vars = [], hash;
											        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
											        for(var i = 0; i < hashes.length; i++) {
											            hash = hashes[i].split('=');
											            vars.push(hash[0]);
											            vars[hash[0]] = hash[1];
											        }
											        return vars;
											    },
											    getUrlVar: function(name) {
											        return $.getUrlVars()[name];
											    }
											});

											var req  = {
													url: '/api/tourna/detail/' + $.getUrlVar('page') + '&' + $.getUrlVar('tourna_no'),
													method: 'GET',
													responseType: 'JSON',
													data: {
													}
											}

											$.ajax(req).done(function(data, status) {
												for(j = 0; j < data.detail.length; j++){
													var star="";
													for(i = 0; i < data.detail[j].Difficult; i++)
													{
														star = star + "★";	
													}
													$( "tbody" ).append('<tr><td>'
															+ data.detail[j].Tourna_Detail_No + '</td><td>'
															+ star +'</td><td class="text-left"><a href="/exam/view/'
															+ data.detail[j].Tourna_Detail_No +'">'
															+ data.detail[j].Title + '</a></td><td>'
															+ data.detail[j].Reg_Date + '</td></tr>');
												}
											}).error(function() {
												alert("오류가 발생했습니다.");
											});
										});								
										</script>
									</tbody>
								</table>
								<ul class="pagination">
									<li class="disabled"><a role="button" href=""
										tabindex="-1" style="pointer-events: none;"> <span
											aria-label="First">« </span>
									</a></li>
									<li class="active"><a role="button" href="">1 </a></li>
									<li class="disabled"><a role="button" href=""
										tabindex="-1" style="pointer-events: none;"> <span
											aria-label="Last">» </span>
									</a></li>
								</ul>
								<div class="text-center">
									<form class="form-inline">
										<div class="form-group"
											style="display: inline-block; width: auto;">
											<input type="text" placeholder="검색" value=""
												class="form-control"
												style="display: inline-block; width: auto;">
										</div>
										<button type="submit" class="btn btn-default"
											style="background-color: rgb(240, 95, 64); color: rgb(255, 255, 255); margin-left: 10px; font-size: 20px; display: inline-block;">
											<i class="fa fa-search" aria-hidden="true"> </i>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
	</span> <footer class="text-center bg-dark">Copyright © 2017 SKHU
			Software Engineering Project. All rights reserved. </footer>
	</span>

</body>
</html>
