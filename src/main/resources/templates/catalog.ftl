<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
</head>
<body>
    <#list movies as movie>
        <h2>${movie.title} </h2>
        ${movie.description} ${movie.year?string("##0")} <br>
        ${movie.rating} (${movie.ratingCount}) <br>
    </#list>

</body>
</html>