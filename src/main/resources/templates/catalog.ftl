<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 250px;
            margin: auto;
            text-align: center;
            font-family: arial, sans-serif;
        }

        .price {
            color: grey;
            font-size: 22px;
        }

        .card .down {
            border: none;
            outline: 0;
            padding: 12px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 100%;
            font-size: 15px;
        }

        .card .down:hover {
            opacity: 0.7;
        }
    </style>
</head>
<body>
    <#if user.role=="ADMIN">
    <form action="/catalog" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <label>IMDb ID
                <input type="text" name="imdbId">
            </label><br>
                <input type="submit">
        </form>
    </#if>
    <br>

    <button onclick="window.location.href='/my-account';">
        My account
    </button>
    <h3>Movie list:</h3>
        <ul style="list-style-type: none; display: flex">
            <#list movies as movie>
                <li style="margin-right: 15px;">
                    <div class="card">
                    <#if movie.image??>
                        <button style="background-image: url(${movie.image}); background-size: cover; height: 200px; width: 150px;" onclick="window.location.href='/item/${movie.id}';">

                        </button>
                    </#if>
                        <p style="margin: 8px 0; font-size: 15px">&#x2B50; ${movie.rating} (${movie.ratingCount} rates)</p>
                        <p style="height: 40px; font-weight: bold; margin: 0">${movie.title} (${movie.year?string("##0")})</p>
                    <p style="height: 100px; padding: 0 20px; font-size: 13px; margin: 1px">${movie.shortDescription}</p>

                    <button class="down" onclick="window.location.href='/item/add-favourite/${movie.id}';">
                        Add to favourite
                    </button>
                <#if user.role=="ADMIN">
                    <button class="down" onclick="window.location.href='/delete/${movie.id}';">
                        Delete from catalog
                    </button>
                </#if>
                    </div>
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