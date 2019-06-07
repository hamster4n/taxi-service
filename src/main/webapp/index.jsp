<%@include file="jsp/parts/common_head.jsp" %>
<html>
<head>

    <title>Home</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
            <div class="p-3 mb-2 bg-light text-dark text-center table-responsive">
                <h3><fmt:message key="index.best"/></h3>
                <h4><fmt:message key="index.comfort"/></h4>

                <table class="table ">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="index.budget"/></th>
                        <th scope="col"><fmt:message key="index.business"/></th>
                        <th scope="col"><fmt:message key="index.luxury"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <img src="http://www.crazy.org.ua/cars/skoda/fabia.jpg" class="img-thumbnail"
                                 itemprop="image"
                                 onError="this.src='/resources/img/image404.jpg'">
                        </td>
                        <td>
                            <img src="http://www.crazy.org.ua/cars/skoda/octavia.jpg" class="img-thumbnail"
                                 itemprop="image"
                                 onError="this.src='/resources/img/image404.jpg'">
                        </td>
                        <td>
                            <img src="http://www.crazy.org.ua/cars/honda/pilot.jpg" class="img-thumbnail"
                                 itemprop="image"
                                 onError="this.src='/resources/img/image404.jpg'">
                        </td>
                    </tr>
                    </tbody>
                </table>
                <h4><fmt:message key="index.action"/></h4>
                <h4><fmt:message key="index.work"/></h4>
                <h4><fmt:message key="index.good"/></h4>
                <h4><fmt:message key="index.consult"/></h4>

            </div>
            <p class="text-center">
                <fmt:message key="index.please"/>, <a href="/registration"><fmt:message key="index.register"/></a>
                <fmt:message key="index.or"/> <a href="/login"><fmt:message key="index.login2"/></a>.
            </p>
        </div>
        <div class="col-lg-1"></div>
    </div>
</div>
</body>
</html>
