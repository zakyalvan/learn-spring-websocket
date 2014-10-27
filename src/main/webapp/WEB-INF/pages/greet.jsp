<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
    

		<title>Spring - AngularJS - STOMP over SockJS</title>

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
						<li><a href="/basic">Native</a></li>
						<li class="active"><a href="/greet">Greet</a></li>
					</ul>
				</div>
			</div>
		</nav>		
		<div class="container">
			<!-- Content Start -->
			<div class="page-header">
				<h1>Greeting</h1>
			</div>
			<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable Javascript and reload this page!</h2></noscript>
			<div>
				<div class="btn-group">
					
				</div>
				<div id="conversationDiv" class="row">
					<div class="col-lg-12">
						<div class="input-group">
							<span class="input-group-btn">
								<button id="connect-button" class="btn btn-info">Connect</button>
								<button id="disconnect-button" class="btn btn-warning" disabled="disabled">Disconnect</button>
							</span>
							<input type="text" id="name" class="form-control">
							<span class="input-group-btn">
								<button id="send-button" class="btn btn-default" type="button">Send</button>
							</span>
						</div><!-- /input-group -->
					</div>
					<div class="col-lg-12">
						<p id="response"></p>
					</div>
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
		<script src="/assets/js/sockjs-0.3.4.min.js"></script>
		<script src="/assets/js/stomp.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#connect-button").on("click", function(event) {
					console.log("Try to connect.");
					connect();
				});
				$("#disconnect-button").on("click", function(event) {
					console.log("Try to disconnect.");
					disconnect();
				});
				$("#send-button").on("click", function(event) {
					sendName();
				});
			});
		
	        var stompClient = null;
	        function setConnected(connected) {
	        	$("#connect-button").attr("disabled", connected);
	        	$("#disconnect-button").attr("disabled", !connected);
	            //document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
	            $("#response").empty();
	        }
	
	        function connect() {
	            var socket = new SockJS('/hello');
	            stompClient = Stomp.over(socket);
	            stompClient.connect({}, function(frame) {
	                setConnected(true);
	                console.log('Connected: ' + frame);
	                stompClient.subscribe('/topic/hello', function(greeting){
	                	console.log("Greeting from server : " + greeting);
	                    showGreeting(JSON.parse(greeting.body).content);
	                });
	            });
	        }
	
	        function disconnect() {
	            stompClient.disconnect();
	            setConnected(false);
	            console.log("Disconnected");
	        }
	
	        function sendName() {
	            var name = $("#name").val();
	            console.log("Name to be sent : " + name);
	            stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
	        }
	        function showGreeting(message) {
	            var response = document.getElementById('response');
	            var p = document.createElement('p');
	            p.style.wordWrap = 'break-word';
	            p.appendChild(document.createTextNode(message));
	            response.appendChild(p);
	        }
	    </script>
	</body>
</html>
