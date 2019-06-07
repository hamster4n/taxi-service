<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Car park</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10 table-responsive">
            <div class="text-center">
                <h4><fmt:message key="carpark.company"/></h4>
            </div>
            <table class="table table-hover" id="myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="carpark.comfort"/></th>
                    <th scope="col"><fmt:message key="carpark.model"/></th>
                    <th scope="col"><fmt:message key="carpark.photo"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${carList}">
                    <tr>
                        <td>${car.type.title}</td>
                        <td>${car.model.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${car.model.photo eq ''}">
                                    <img src="/resources/img/image404.jpg" class="img-thumbnail" itemprop="image">
                                </c:when>
                                <c:otherwise>
                                    <img src="${car.model.photo}" class="img-thumbnail" itemprop="image"
                                         onError="this.src='/resources/img/image404.jpg'">
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
    </div>
</div>
</body>
</html>