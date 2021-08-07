<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<button onclick="window.location.href='/catalog';">
Catalog
</button><br>
<#--Hi, ${user.name}<br>-->

<ul style="list-style-type: none; display: flex; flex-direction: column">

    <li style="padding-top: 15px; display: flex">
        <span style="width: 175px;">
        <#if movie.image??>
            <img src="${movie.image}" alt="movie-image" height="220px" style="padding-bottom: 10px">
        </#if> <br>
            &#9193; ${movie.title} (${movie.year?string("##0")})<br>
        &#x2B50; ${movie.rating} (${movie.ratingCount} rates)

        </span>
        <div style="width: 400px; padding-left: 30px; padding-right: 30px;">${movie.description}</div>

        <button style="height: 27px" onclick="window.location.href='/item/delete-favourite/${movie.id}';">
            Delete from favourite
        </button>
    </li>
</ul>
<form action="/item/${movie.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label>Write review -
        <input type="text" name="comment">
    </label>
    <input type="submit">
</form>

<p>User reviews:</p>
<#list movie.review as propName, propValue>
    <ul>
        <li>${propValue}</li>
    </ul>

</#list>
</body>
</html>