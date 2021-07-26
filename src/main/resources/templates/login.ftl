<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
${message!""}
<form action="/login" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label>Username
        <input name="username" type="text">
    </label>
    <label>Password
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>
</body>
</html>