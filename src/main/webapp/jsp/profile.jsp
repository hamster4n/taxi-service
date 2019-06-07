<html>
<head>
    <title>Profile</title>
    <%@include file="parts/common_head.jsp" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <h5><fmt:message key="profile.fill"/></h5>
            <c:set var="user" value="${user}"/>
            <form method="post" accept-charset="ISO-8859-1">
                <div class="form-group">
                    <label for="name"><fmt:message key="profile.user.name"/></label>
                    <input class="form-control" id="name" name="name" value="${user.name}"/>
                    <small id="nameHelp" class="form-text text-muted"><fmt:message key="profile.change.username"/></small>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="profile.password"/></label>
                    <input type="text" class="form-control" id="password" name="password"
                           value="" minlength="8" maxlength="128" placeholder="<fmt:message key="profile.new.password"/>"/>
                    <small id="passwordHelp" class="form-text text-muted"><fmt:message key="profile.change.password"/></small>
                </div>
                <div class="form-group">
                    <label for="creditCard"><fmt:message key="profile.credit.card"/></label>
                    <input class="form-control" id="creditCard" name="creditCard" minlength="10" maxlength="19"
                           value="${user.creditCard}"/>
                    <small id="creditCardHelp" class="form-text text-muted"><fmt:message key="profile.change.card"/></small>
                </div>
                <div class="form-group">
                    <label for="date"><fmt:message key="profile.birthday"/></label>
                    <input class="form-control" id="date" name="date" value="${date}"/>
                    <small id="dateHelp" class="form-text text-muted"><fmt:message key="profile.enter.birthday"/>
                    </small>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="profile.email"/></label>
                    <input type="email" class="form-control" id="email" name="email"
                           value="${user.email}" readonly/>
                </div>
                <div class="form-group">
                    <label for="discount"><fmt:message key="profile.discount"/></label>
                    <input class="form-control" id="discount" name="discount" value="${user.discount}" readonly/>
                </div>
                <div class="form-group">
                    <label for="traveledDistance"><fmt:message key="profile.traveled.distance"/></label>
                    <input class="form-control" id="traveledDistance" name="traveledDistance"
                           value="${user.traveledDistance}" readonly/>
                </div>
                <div class="form-group">
                    <label for="loyalty"><fmt:message key="profile.loyalty"/></label>
                    <input class="form-control" id="loyalty" name="loyalty" value="${user.loyalty}" readonly/>
                </div>
                <div class="text-danger">
                    ${exception}
                </div>
                <input type="submit" value="<fmt:message key="profile.button"/>" class="btn btn-success"/>
            </form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
</body>
</html>