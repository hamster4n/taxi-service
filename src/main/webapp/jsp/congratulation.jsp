<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Congratulations</title>
</head>
<body>
<div class="container-fluid">
    <c:set var="order" value="${order}"/>
    <div class="row justify-content-center">
        <div><h4><fmt:message key="congrat.congrat"/></h4></div>
        <table class="table table-hover" id="myTable">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="congrat.license"/></th>
                <th scope="col"><fmt:message key="congrat.comfort"/></th>
                <th scope="col"><fmt:message key="congrat.year"/></th>
                <th scope="col"><fmt:message key="congrat.model"/></th>
                <th scope="col"><fmt:message key="congrat.photo"/></th>
                <th scope="col"><fmt:message key="congrat.time"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${order.car.licensePlate}</td>
                <td>${order.car.type.title}</td>
                <td>${order.car.productionYear}</td>
                <td>${order.car.model.name}</td>
                <td>
                    <img src="${order.car.model.photo}" class="img-thumbnail" itemprop="image"
                         onError="this.src='/resources/img/image404.jpg'">
                </td>
                <td>${order.car.arrivalTime}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>