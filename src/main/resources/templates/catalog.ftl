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
            <label>IMDb ID
                <input type="text" name="imdbId">
            </label><br>
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

    <a href="/my-account">My account</a>
    <h3>Movie list:</h3>
        <ul>
            <#list movies as movie>
                <li>
                <h3>&#x1F4AC; ${movie.title} </h3>
                ${movie.description} (${movie.year?string("##0")}) <br>
                &#x2B50; ${movie.rating} (${movie.ratingCount} rates) <br>
                    <button onclick="window.location.href='/item/add-favourite/${movie.id}';">
                        Add to favourite
                    </button>
<#--                <a href="/item/add-favourite/${movie.id}">Add to favourite</a>-->
                <#if user.role=="ADMIN">
<#--                    <a href="/catalog/delete/${movie.id}"> - Delete from catalog</a>-->
                    <button onclick="window.location.href='/catalog/delete/${movie.id}';">
                        Delete from catalog
                    </button>
                </#if>
                </li>
            </#list>
        </ul>
    <br>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>
</body>
</html>