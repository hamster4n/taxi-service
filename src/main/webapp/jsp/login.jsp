<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <h4><fmt:message key="login.form"/></h4>
            <br>
            <div class="text-primary">
                ${successful_registration}
            </div>
            <form method="post">
                <div class="form-group">
                    <label for="email"><fmt:message key="login.email.address"/></label>
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder='<fmt:message key="login.enter.email"/>'/>
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="login.email.help"/></small>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="login.password"/></label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder='<fmt:message key="login.enter.password"/>'/>
                </div>
                <div class="text-danger">
                    ${exception}
                </div>
                <input type="submit" value="<fmt:message key="login.button"/>" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
</body>
</html>