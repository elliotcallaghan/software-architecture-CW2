<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form action="/login" method="post">
		User Name : <input type="text" name="username"/><br/>
		Password: <input type="password" name="password"/><br/>
		<input type="submit"/><br/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>