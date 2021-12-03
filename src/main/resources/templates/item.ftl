<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<button onclick="window.location.href='/catalog';">
    Catalog
</button>
<br>
<#--Hi, ${user.name}<br>-->

<ul style="list-style-type: none; display: flex; flex-direction: column">

    <li style="padding-top: 15px; display: flex">
        <span style="width: 175px;">
        <#if movie.image??>
            <img src="${movie.image}" alt="movie-image" height="220px" style="padding-bottom: 10px">
        </#if> <br>
            &#9193; ${movie.title} (${movie.year?string("##0")})<br>
        &#x2B50; ${movie.rating} (${movie.ratingCount} rates) (IMDb)

        </span>
        <div style="width: 400px; padding-left: 30px; padding-right: 30px;">${movie.description}</div>
    </li>
</ul>
<#if isFavourite==false>
<button class="down" onclick="window.location.href='/item/add-favourite/${movie.id}';">
    Add to favourite
</button>
</#if>
<#if user.nonRestricted?string('true', 'false') == 'true'>
    <#if !userReview??>
        <form action="/item/${movie.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <label>Write review -
                <input type="text" name="comment">
            </label>
            <input type="submit">
        </form>
    </#if>

    <#if userReview??>
        <p>My review: (Moderated: ${approved})</p>
        <span style="padding: 15px; background-color: lightgrey;">${userReview}</span>
        <button style="padding: 5px; margin-left: 10px"
                onclick="window.location.href='/delete/item-${movie.id}/${user.id}';">
            Delete review
        </button>
    </#if>
</#if>

<p>User reviews:</p>
<#list movie.review as propName, propValue>
    <ul style="list-style: none">
        <li>
            <div style="display: flex; flex-direction: row;  border: 2px">
                <button style="background-image: url(https://i.ibb.co/Hgs161z/default.jpg); background-size: cover; height: 50px; width: 50px;"
                        onclick="window.location.href='/user/${propName}';">
                </button>
                <span style="padding: 15px; background-color: lightgrey;">User #${propName} -- ${propValue}</span>
            </div>
        </li>
    </ul>
</#list>
</body>
</html>