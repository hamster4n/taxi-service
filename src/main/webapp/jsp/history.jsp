<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>History</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12 table-responsive">
            <table class="table table-hover" id="myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="history.id"/></th>
                    <th scope="col"><fmt:message key="history.date"/></th>
                    <th scope="col"><fmt:message key="history.start"/></th>
                    <th scope="col"><fmt:message key="history.finish"/></th>
                    <th scope="col"><fmt:message key="history.license.plate"/></th>
                    <th scope="col"><fmt:message key="history.base"/></th>
                    <th scope="col"><fmt:message key="history.loyalty"/></th>
                    <th scope="col"><fmt:message key="history.discount"/></th>
                    <th scope="col"><fmt:message key="history.birthday"/></th>
                    <th scope="col"><fmt:message key="history.comfort"/></th>
                    <th scope="col"><fmt:message key="history.auto.feed"/></th>
                    <th scope="col"><fmt:message key="history.final"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.date}</td>
                        <td>${order.startDescription}</td>
                        <td>${order.finishDescription}</td>
                        <td>${order.car.licensePlate}</td>
                        <td bgcolor="FFFA00">${order.baseCost}</td>
                        <td bgcolor="93FF59">${order.loyaltyCost}</td>
                        <td bgcolor="93FF59">${order.discountCost}</td>
                        <td bgcolor="93FF59">${order.birthdayCost}</td>
                        <td bgcolor="4CD8FF">${order.comfortCost}</td>
                        <td bgcolor="4CD8FF">${order.autoFeed}</td>
                        <td bgcolor="FFFA00">${order.finalCost}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <nav aria-label="Navigation for history">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="history?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="history.prev"/></a>
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
                                                 href="history?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="history?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="history.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>