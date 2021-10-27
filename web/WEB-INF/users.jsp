<%-- 
    Document   : users
    Created on : 21-Oct-2021, 1:53:16 PM
    Author     : lixia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
	</head>
	<body>
		<header>
			<div class="container-fluid bg-light bg-gradient">
				<h1 class="text-center">Users List</h1>
			</div>
		</header>

		<section class="container-fluid bg-dark" style="height: 80px; box-shadow: 2px 2px darkgray">

		</section>

		<div class="container">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Email</th>
						<th scope="col">Active</th>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Role</th>
						<th scope="col-2">Operations</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<th scope="row">${user.email}</th>
							<th scope="row">${user.active}</th>
							<th scope="row">${user.firstName}</th>
							<th scope="row">${user.lastName}</th>
							<th scope="row">${user.role.roleName}</th>
							<th scope="row"><button class="btn col-md-12  btn-secondary" data-bs-toggle="modal" data-bs-target="#userModal" data-pre="Edit" 
										data-email="${user.email}"
										data-password="${user.password}"
										data-active="${user.active}"
										data-firstname="${user.firstName}"
										data-lastname="${user.lastName}"
										data-role="${user.role.roleName}"
										>Edit</button></th>

							<form action="" method="POST">
								<th scope="row"><button class="btn col-md-12 btn-danger">Delete</button></th>
								<input type="hidden" name="action" value="delete" />
								<input type="hidden" name="email" value="${user.email}" />
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#userModal" data-pre="Add">
				Add User
			</button>

			<!-- Modal -->
			<div class="modal fade" id="userModal" tabindex="-1" aria-labelledby="userModalLabel" aria-hidden="true">
  				<div class="modal-dialog">
    					<div class="modal-content">
      					<div class="modal-header">
        					<h5 class="modal-title" id="userModalLabel">User</h5>
        					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      					</div>
      					<div class="modal-body">
						<form id="userForm" class="row" action="user" method="POST">
							<div class="col-md-6 mb-3">
								<label for="email" class="form-label">Email</label>
								<input type="text" name="email" id="email" class="form-control" placeholder="Your Email Address" />
							</div>
							<div class="col-md-6 mb-3">
								<label for="password" class="form-label">Password</label>
								<input type="password" name="password" id="password" class="form-control" placeholder="Your Password" />
							</div>
							<div class="col-md-6 mb-3">
								<label for="firstName" class="form-label">First Name</label>
								<input type="text" name="firstName" id="firstName" class="form-control" placeholder="Your First Name" />
							</div>
							<div class="col-md-6 mb-3">
								<label for="lastName" class="form-label">Last Name</label>
								<input type="text" name="lastName" id="lastName" class="form-control" placeholder="Your Last Name" />
							</div>

							<div class="col-md-12 mb-3">
								<select id="role" class="col-md-4 form-select" name="roleName">
									<option selected>Select the role</option>
									<c:forEach items="${roles}" var="role">
										<option value="${role.roleName}">${role.roleName}</option>
									</c:forEach>
								</select>
							</div>
							<input id="formtype" type="hidden" name="action" value=""/>
							<input id="formactive" type="hidden" name="active" value="" />
						</form>
	
      					</div>
      					<div class="modal-footer">
        					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
						<button type="button" id="confirm" class="btn btn-primary" onclick="document.forms['userForm'].submit()">Confirm</button>
      					</div>
    					</div>
  				</div>
			</div>
		</div>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		<script src="js/modal.js" type="text/javascript" ></script>
	</body>
</html>
