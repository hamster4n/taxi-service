<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/besttaxi/"><img src="${pageContext.request.contextPath}/resources/img/logo.jpg"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/carpark"><fmt:message key="index.carpark"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/login"> <fmt:message key="index.login"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/registration"> <fmt:message key="index.registration"/></a>
            </li>
            <li class="nav-item"><a class="nav-link" href="?sessionLocale=en"><fmt:message key="menu.en"/></a></li>
            <li class="nav-item"><a class="nav-link" href="?sessionLocale=ru"><fmt:message key="menu.ru"/></a></li>
        </ul>
    </div>
</nav>
