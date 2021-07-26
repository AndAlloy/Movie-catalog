<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
${error_message!"Hello"}

<form action="/registration" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label>Username
        <input name="name" type="text">
    </label>
    <label>Password
        <input name="password" type="password">
    </label>
    <label>Confrim password
        <input name="password_confirm" type="password">
    </label>
    <input type="submit">
</form>
</body>
</html>