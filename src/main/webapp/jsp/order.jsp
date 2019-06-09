<html>
<head>
    <title>Order</title>
    <%@include file="parts/common_head.jsp" %>
    <meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no'/>
</head>
<body>
<form method="post" accept-charset="ISO-8859-1">
    <div class="container-fluid">
        <c:set var="order" value="${order}"/>
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-10">
                <div class="row">
                    <br/>
                    <div class="col-lg-5">
                        <div class="form-group">
                            <div id='map'></div>
                            <script>
                                mapboxgl.accessToken = 'pk.eyJ1IjoiaGFtc3RlcjRuIiwiYSI6ImNqdzZkZjdjajIyeGE0M3F2dGpxdTYzeDgifQ.k_v3Sczgtke04Ssj0hOrhQ';
                                var map = new mapboxgl.Map({
                                    container: 'map',
                                    style: 'mapbox://styles/mapbox/streets-v11',
                                    center: [30.52698, 50.45913],
                                    zoom: 11
                                });
                                map.addControl(new MapboxDirections({accessToken: mapboxgl.accessToken}), 'top-left');
                                map.addControl(new mapboxgl.GeolocateControl({
                                    positionOptions: {
                                        enableHighAccuracy: true
                                    },
                                    trackUserLocation: true
                                }));

                                var directions = new MapboxDirections({
                                    accessToken: mapboxgl.accessToken
                                });
                                directions.on("route", e => {
                                    let routes = e.route;
                                    document.getElementById('distance').value = routes.map(r => Math.round(r.distance));
                                    arr = routes.map(r => r.legs[0].summary.split(','));
                                    document.getElementById('startDescription').value = arr[0][0];
                                    document.getElementById('finishDescription').value = arr[0][1];
                                    console.log("Route lengths", routes.map(r => r.legs[0].summary.split(',')))
                                });
                            </script>
                        </div>
                    </div>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-5">
                        <div class="row">
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <label for="startDescription"><fmt:message key="ord.from"/></label>
                                    <input class="form-control" id="startDescription" name="startDescription"
                                           value="${order.startDescription}" readonly/>
                                </div>
                            </div>
                            <div class="col-lg-2"></div>
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <label for="finishDescription"><fmt:message key="ord.to"/></label>
                                    <input class="form-control" id="finishDescription" name="finishDescription"
                                           value="${order.finishDescription}" readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <label for="currentDate"><fmt:message key="ord.date"/></label>
                                    <input class="form-control" id="currentDate" name="currentDate"
                                           value="${currentDate}" readonly/>
                                </div>
                            </div>
                            <div class="col-lg-2"></div>
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <label for="distance"><fmt:message key="ord.distance"/></label>
                                    <input class="form-control" id="distance" name="distance" value="${order.distance}"
                                           readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-5">
                                <label for="selectComfort"><fmt:message key="ord.comfort"/></label>
                                <select class="custom-select" id="selectComfort" name="selectComfort">
                                    <option value="1">ECONOMY</option>
                                    <option value="2">BUSINESS</option>
                                    <option value="3">LUX</option>
                                </select>
                            </div>
                            <div class="col-lg-2"></div>
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <label for="finalCost"><fmt:message key="ord.final"/></label>
                                    <input class="form-control" id="finalCost" name="finalCost"
                                           value="${order.finalCost}" readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-5">
                                <input type="submit" name="button" value="Calculate" id="calculate"
                                       class="btn btn-primary"/>
                            </div>
                            <div class="col-lg-2"></div>
                            <div class="col-lg-5"></div>
                        </div>
                        <div class="text-danger"> ${exception} </div>
                    </div>
                </div>

            </div>
            <div class="col-lg-1"></div>
        </div>
        <div class="row justify-content-center">
            <c:choose>
                <c:when test="${order.car eq null}">
                    <c:if test="${order.distance > 0 }">
                        <div>
                            <fmt:message key="ord.no.car"/>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div><h4><fmt:message key="ord.found"/></h4></div>
                    <table class="table table-hover" id="myTable">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="ord.license"/></th>
                            <th scope="col"><fmt:message key="ord.comfort"/></th>
                            <th scope="col"><fmt:message key="ord.year"/></th>
                            <th scope="col"><fmt:message key="ord.model"/></th>
                            <th scope="col"><fmt:message key="ord.photo"/></th>
                            <th scope="col"><fmt:message key="ord.time"/></th>
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
                            <td>
                                <input type="submit" name="button" value="Order now!" class="btn btn-success"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</form>
</body>
</html>