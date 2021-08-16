<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Moderate</title>
</head>
<body>
<#list movieList as movie>
    <p>
        ${movie.title} -
    <ul>
        <#list movie.temporaryReview as propName, propValue>
            <li>User #${propName} -- ${propValue}</li>
            <button onclick="window.location.href='/approve/item-${movie.id}/${propName}';">
                Approve
            </button>
            <button onclick="window.location.href='/delete/item-${movie.id}/${propName}';">
                Delete review
            </button>
            <button onclick="window.location.href='#';">
                Block user
            </button>
        </#list>
    </ul>
    </p>
</#list>
</body>
</html>