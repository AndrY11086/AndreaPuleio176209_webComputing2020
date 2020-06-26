<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Recupera Password</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="js/recuperaPassword.js"></script>

</head>
<body style="background-color: #36373c">

	<jsp:include page="jsp/menu.jsp"></jsp:include>

	<div align="center" style="margin-top: 20%">
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModal">Recupera Password</button>
	</div>

	<!-- Modal -->
	<form action="RecuperaPassword" method="post">
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Inserisci
							email</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class='form-group'>
							<label class='control-label col-sm-2' for='nome'>email:</label>
							<div class='col-sm-10'>
								<input type='text' class='form-control' id='email'
									placeholder='Inserisci email' name='email'>
							</div>
						</div>
						<div class="modal-footer">
							<button name="button" value="close" 
								class="btn btn-secondary" data-dismiss="modal">Close</button>
							<button name="button" value="recover"
								class="btn btn-primary">Invia</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>