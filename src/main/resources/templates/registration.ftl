<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>


<form action="/registration" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label>Username
        <input name="name" type="text">
    </label>
    <label>Email*
        <input name="email" type="text" required>
    </label>
    <label>Password*
        <input name="password" type="password" required>
    </label>
    <label>Confrim password*
        <input name="password_confirm" type="password" required>
    </label>
    <input type="submit">
</form>
</body>
</html>