<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<#list users as user>
    <p>
    ${user.id} -- ${user.name} -- ${user.role} -- <a href="/admin/add-admin/${user.id}">Make admin</a> -
        <a href="/admin/delete-admin/${user.id}">Delete admin</a>

    </p>
</#list>
</body>
</html>