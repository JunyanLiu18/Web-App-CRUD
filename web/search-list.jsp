<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/2/11
  Time: 12:37 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Vehicle Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #1aa4db">
        <div>
            <a href="https://www.mitchell.com" class="navbar-brand"> Vehicle
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Vehicles</a></li>
        </ul>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/search"
                   class="nav-link">Search</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="row">

    <div class="container">
        <h3 class="text-center"><%=request.getParameter("item").substring(0, 1).toUpperCase() +  request.getParameter("item").substring(1)%> Search of Vehicles</h3>
        <hr>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Year</th>
                <th>Make</th>
                <th>Model</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="vehicle" items="${searchVehicle}">

                <tr>
                    <td><c:out value="${vehicle.id}"/></td>
                    <td><c:out value="${vehicle.year}"/></td>
                    <td><c:out value="${vehicle.make}"/></td>
                    <td><c:out value="${vehicle.model}"/></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>