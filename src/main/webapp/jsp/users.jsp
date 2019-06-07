<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10 table-responsive">
            <table class="table table-hover" id = "myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="user.n"/></th>
                    <th scope="col"><fmt:message key="user.name"/></th>
                    <th scope="col"><fmt:message key="user.email"/></th>
                    <th scope="col"><fmt:message key="user.credit.card"/></th>
                    <th scope="col"><fmt:message key="user.discount"/></th>
                    <th scope="col"><fmt:message key="user.loyalty"/></th>
                    <th scope="col"><fmt:message key="user.birthday"/></th>
                    <th scope="col"><fmt:message key="user.role"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.creditCard}</td>
                        <td>${user.discount}</td>
                        <td>${user.loyalty}</td>
                        <td>${user.birthday}</td>
                        <td>${user.role.title}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
    </div>
    <nav aria-label="Navigation for users">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="cars.prev"/></a>
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
                                                 href="users?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="cars.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>