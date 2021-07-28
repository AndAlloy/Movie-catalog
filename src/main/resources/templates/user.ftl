<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
ID - ${user.id}<br>
Name - ${user.name}<br>
<ul>
<#list movies as movie>
    <li>
        &#9193; ${movie.title} <br>
        ${movie.description} (${movie.year?string("##0")}) <br>
        &#x2B50; ${movie.rating} (${movie.ratingCount} rates) <br>
<#--        <a href="/item/delete-favourite/${movie.id}">Delete from favourite</a>-->
        <button onclick="window.location.href='/item/delete-favourite/${movie.id}';">
            Delete from favourite
        </button>
    </li>
</#list>
</ul>
</body>
</html>