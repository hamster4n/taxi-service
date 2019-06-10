<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10 table-responsive">
            <table class="table table-hover" id="myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="cars.n"/></th>
                    <th scope="col"><fmt:message key="cars.license"/></th>
                    <th scope="col"><fmt:message key="cars.comfort"/></th>
                    <th scope="col"><fmt:message key="cars.year"/></th>
                    <th scope="col"><fmt:message key="cars.available"/></th>
                    <th scope="col"><fmt:message key="cars.model"/></th>
                    <th scope="col"><fmt:message key="cars.photo"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${carList}">
                    <tr>
                        <th scope="row">${car.id}</th>
                        <td>${car.licensePlate}</td>
                        <td>${car.type.title}</td>
                        <td>${car.productionYear}</td>
                        <td>${car.available}</td>
                        <td>${car.model.name}</td>
                        <td>
                            <img src="${car.model.photo}" class="img-thumbnail" itemprop="image"
                                 onError="this.src='${pageContext.request.contextPath}/resources/img/image404.jpg'">
                        </td>
                     </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
    </div>
    <nav aria-label="Navigation for car">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="cars?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="cars.prev"/></a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="cars?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="cars?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="cars.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>