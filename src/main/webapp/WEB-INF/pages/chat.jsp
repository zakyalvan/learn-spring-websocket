<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Spring WebSocket Basic Example</title>

		<!-- Bootstrap core CSS -->
		<link href="/assets/css/bootstrap.min.css" rel="stylesheet">
		<link href="/assets/css/bootstrap-theme.min.css" rel="stylesheet">

		<!-- Custom styles -->
		
		<!-- Just for debugging purposes. Don't actually copy this line! -->
		<!--[if lt IE 9]><script src="ie8-responsive-file-warning.js"></script><![endif]-->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Learn Spring WebSocket</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="/basic">Basic</a></li>
						<li><a href="/greet">Greet</a></li>
						<li class="active"><a href="/chat">Chat</a></li>
					</ul>
				</div>
			</div>
		</nav>		
		<div class="container">
			<!-- Content Start -->
			<div class="page-header">
				<h1>Chat</h1>
			</div>
			<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable Javascript and reload this page!</h2></noscript>
			<div class="row">
				<div class="col-lg-5">
					<form id="register-form" method="post" action="/chat/users">
						<div class="input-group">
							<input type="text" id="username-text" name="username" class="form-control" autocomplete="off" placeholder="Select username" >
							<span class="input-group-btn">
								<button id="register-button" class="btn btn-default" type="submit">Register me</button>
							</span>
						</div>
					</form>
				</div>
			</div>
			<!-- Content End -->
			<hr>
			<footer>
				<p>&copy; <a href="http://innovez-one.com">Innovez One</a> 2014</p>
			</footer>
		</div> <!-- /container -->
		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="/assets/js/jquery-2.1.0.min.js"></script>
		<script src="/assets/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$("form#register-form").submit(function(event) {
					event.preventDefault();
					var username = $("#username-text").val();
					
					$.ajax("/chat/users", {
						type : $(this).attr("method"),
						data : JSON.stringify({'username' : username}),
						contentType : "application/json",
					}).done(function() {
						alert("Done, show chat window!");
					});
				});
			});
	    </script>
	</body>
</html>
