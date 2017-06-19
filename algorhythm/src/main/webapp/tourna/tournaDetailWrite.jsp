<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script
	src="https://devdogs-cdn.azureedge.net/algorhythm/lib/jquery/jquery.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body id="page-top">
	<span data-reactroot=""><div class="mask">
			<div class="mask-wrap">
				<div class="mask-content">
					<img src="/img/loading2.gif">
				</div>
			</div>
		</div>
		<nav class="navbar navbar-default navbar-fixed-top" id="mainNav">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand page-scroll navbar-brand" href="/"> <!-- react-text: 10 -->AlgoRhythm<!-- /react-text -->
						<i class="fa fa-music" aria-hidden="true"></i>
					</a>
					<button type="button" class="navbar-toggle">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
				</div>
				<div id="devdogs-nav" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class=""><a class="page-scroll" href="/">Home</a></li>
						<li class=""><a class="page-scroll" href="/exam/list">문제목록</a></li>
						<li class="active"><a class="page-scroll" href="/exam/write">문제출제</a></li>
						<li class=""><a class="page-scroll" href="/exam/rank">명예의전당</a></li>
						<li class=""><a class="page-scroll" href="/exam/my">내 문제</a></li>
						<li class=""><a class="page-scroll" href="/member/modify">정보수정</a></li>
						<li class=""><a class="page-scroll" href="">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</nav> <span><section id="exam" class="">
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 text-center">
							<h2 class="section-heading">문제출제</h2>
							<hr class="primary">
						</div>
					</div>
					<div class="row">
						<div class="col-md-10 col-md-offset-1 sr-contact" data-sr-id="5"
							style="visibility: visible; -webkit-transform: scale(1); opacity: 1; transform: scale(1); opacity: 1; -webkit-transition: -webkit-transform 0.6s cubic-bezier(0.6, 0.2, 0.1, 1) 0s, opacity 0.6s cubic-bezier(0.6, 0.2, 0.1, 1) 0s; transition: transform 0.6s cubic-bezier(0.6, 0.2, 0.1, 1) 0s, opacity 0.6s cubic-bezier(0.6, 0.2, 0.1, 1) 0s;">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">문제제목</label>
									<div class="col-sm-10">
										<input type="text" required="" class="form-control"
											name="subject" placeholder="title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">난이도</label>
									<div class="col-sm-10">
										<select required="" class="form-control" name="difficulty"><option
												value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option></select>
									</div>
								</div>
								<div class="form-group form-group-big">
									<label class="col-sm-2 control-label">내용</label>
									<div class="col-md-10">
										<label class="col-sm-12 exam-label text-center">내용</label>
										<div class="col-sm-12">
											<textarea required class="form-control" rows="5"
												name="tourna_content" id="tourna_content"></textarea>
											<script src="//cdn.ckeditor.com/4.7.0/standard/ckeditor.js"></script>
											<script>
													CKEDITOR.replace( 'tourna_content', {
													} );
												</script>

										</div>
									</div>
								</div>

								<div class="form-group form-group-big">
									<label class="col-sm-2 control-label">예시</label>
									<div class="col-md-5">
										<label class="col-sm-12 exam-label text-center">예시 입력</label>
										<div class="col-sm-12">
											<textarea required="" class="form-control" rows="5"
												name="test_input"></textarea>
										</div>
									</div>
									<div class="col-md-5">
										<label class="col-sm-12 exam-label text-center">예시 출력</label>
										<div class="col-sm-12">
											<textarea required="" class="form-control" rows="5"
												name="test_output"></textarea>
										</div>
									</div>
								</div>
								<div class="form-group form-group-big">
									<label class="col-sm-2 control-label">채점용</label>
									<div class="col-sm-10">
										<button type="button" class="btn btn-success">채점용 입출력
											추가</button>
										<span class="plusinfo">* 점수 = 100/채점용 입출력개수*맞춘 개수</span>
									</div>
								</div>
								<div class="form-group">
									<div>
										<div class="col-md-5 col-md-offset-2">
											<label class="col-sm-12 exam-label text-center"> <!-- react-text: 125 -->채점용
												입력<!-- /react-text --> <!-- react-text: 126 -->1<!-- /react-text -->
											</label>
											<div class="col-sm-12">
												<textarea required="" class="form-control" rows="5"
													name="testcase_input" id="0"></textarea>
											</div>
										</div>
										<div class="col-md-5">
											<label class="col-sm-12 exam-label text-center"> <!-- react-text: 131 -->채점용
												출력<!-- /react-text --> <!-- react-text: 132 -->1<!-- /react-text -->
											</label>
											<div class="col-sm-12">
												<textarea required="" class="form-control" rows="5"
													name="testcase_output" id="0"></textarea>
											</div>
										</div>
									</div>
								</div>
								<div class="text-center">
									<button type="button" class="btn btn-primary btn-lg">문제출제</button>
									<script type="text/javascript">
										$("#submitBtn").click(function(){
			
											var title = $("#title").val();
											var difficulty = $("#difficulty").val();
											var content = CKEDITOR.instances.tourna_content.getData();
											var test_input = $("#test_input").val();
											var test_output = $("#test_output").val();
											var testcase_input = $("#testcase_input").val();
											var testcase_output = $("#testcase_output").val();
											var req  = {
													url: '/api/tourna/write',
													method: 'POST',
													responseType: 'JSON',
													data: {
														title: title
														,content: content
														,test_input: test_input
														,test_output: test_output
														,difficulty: difficulty
													}
											}
											
											$.ajax(req).done(function(data, status) {
												alert(data);
											}).error(function() {
												alert("오류가 발생했습니다.");
											});
										});
									</script>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section></span> <footer class="text-center bg-dark">Copyright © 2017 SKHU
			Software Engineering Project. All rights reserved.</footer></span>


</body>
</html>
