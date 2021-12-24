<header>
    <div class="overlay">
        <h2>MOVIK</h2>
        <p>- your evening is our business!</p>
        <br>
        <button class="round-button" onclick="window.location.href='/catalog';"><span>Main page </span></button>
        <button class="round-button" onclick="window.location.href='/recommended';"><span>Recommendations </span>
        </button>
        <button class="round-button" onclick="window.location.href='/my-account';"><span>My account </span></button>
        <#if user??>
            <#if user.role=="ADMIN">
                <button class="round-button" onclick="window.location.href='/admin';"><span>Admin </span>
                </button>
            </#if>
            <#if user.role=="MODERATOR">
                <button class="round-button" onclick="window.location.href='/moderate';"><span>Moderation </span>
                </button>
            </#if>
            <form action="/logout" style="display: inline" method="post" class="logout-but">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="round-button" type="submit"><span>Logout </span></button>
            </form>
        </#if>
        <#if !user??>
            <button class="round-button" onclick="window.location.href='/login';"><span>Login </span></button>
        </#if>
    </div>
</header>

<br>