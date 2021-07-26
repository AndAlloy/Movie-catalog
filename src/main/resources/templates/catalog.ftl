<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
</head>
<body>
    <#if user.role=="ADMIN">
    <form action="/catalog" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <label>Title
                <input type="text" name="title">
            </label><br>
            <label>Description
                <input type="text" name="description">
            </label><br>
            <label>Rating
                <input type="text" name="rating">
            </label><br>
            <label>Rating Count
                <input type="text" name="ratingCount">
            </label><br>
            <label>Year
                <input type="text" name="year">
            </label><br>
            <input type="submit">
        </form>
    </#if>
    <br>

    <a href="/myaccount">User info</a>
    <h3>Movie list:</h3>
    <#list movies as movie>
        <h2>${movie.title} </h2>
        ${movie.description} (${movie.year?string("##0")}) <br>
        &#x2B50; ${movie.rating} (${movie.ratingCount} rates) <br>
        <#if user.role=="ADMIN">
            <a href="/catalog/delete/${movie.id}">Delete</a>
        </#if>

    </#list>

</body>
</html>