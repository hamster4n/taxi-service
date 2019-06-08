<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/besttaxi/"><img src="${pageContext.request.contextPath}/resources/img/logo.jpg"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link active" href="/besttaxi/order"><fmt:message key="nav.order"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/history"><fmt:message key="nav.history"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/carpark"><fmt:message key="index.carpark"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/profile"><fmt:message key="nav.profile"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/users"><fmt:message key="nav.users"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/besttaxi/cars"><fmt:message key="nav.cars"/></a>
            </li>
        </ul>
        <div class="navbar-text mr-3">${email}</div>
        <div class="navbar-text mr-3"><a href="/besttaxi/logout"><fmt:message key="nav.logout"/></a></div>
    </div>
</nav>
<div class="container-fluid bg-light">
    <div id="navigation" class="float-right" >
        <ul>
            <li><a href="?sessionLocale=en"><fmt:message key="menu.en"/></a></li>
            <li><a href="?sessionLocale=ru"><fmt:message key="menu.ru"/></a></li>
        </ul>
    </div>
</div>