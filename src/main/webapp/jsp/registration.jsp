<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <h4><fmt:message key="reg.form"/></h4>
            <br>
            <form method="post" accept-charset="ISO-8859-1">
                <div class="form-group">
                    <label for="email"><fmt:message key="reg.email.address"/></label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key="req.enter.email"/>"/>
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="req.email.help"/>
                    </small>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="req.password"/></label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="<fmt:message key="req.enter.password"/>" minlength="8" maxlength="128"/>
                </div>
                <div class="form-group">
                    <label for="password2"><fmt:message key="req.password"/></label>
                    <input type="password" class="form-control" id="password2" name="password2"
                           placeholder="<fmt:message key="req.enter.password.second"/>" minlength="8" maxlength="128"/>
                </div>
                <div class="text-danger">
                    ${exception}
                </div>
                <input type="submit" value="<fmt:message key="req.button"/>" class="btn btn-success"/>
            </form>
        </div>
        <div class="col-lg-3">
            <div class="p-3 mb-2 bg-light text-dark">
                <p><fmt:message key="req.policy.one"/></p>
                <p><fmt:message key="req.policy.two"/></p>
                <p><fmt:message key="req.policy.three"/></p>
                <p><fmt:message key="req.policy.for"/></p>
                <p><fmt:message key="req.policy.five"/></p>
                <p><fmt:message key="req.policy.six"/></p>
                <p><fmt:message key="req.policy.seven"/></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>