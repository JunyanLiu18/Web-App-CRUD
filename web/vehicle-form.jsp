<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/2/10
  Time: 11:31 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
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
            <a href="https://www.mitchell.com" class="navbar-brand"> Vehicle Management App </a>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${vehicle != null}">
            <form action="update" method="POST">
                </c:if>

                <c:if test="${vehicle == null}">
                <form action="insert" method="POST">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${vehicle != null}">
                                Edit Vehicle
                            </c:if>
                            <c:if test="${vehicle == null}">
                                Add New Vehicle
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${vehicle != null}">
                        <input type="hidden" name="id" value="<c:out value='${vehicle.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Vehicle Year</label> <input type="number" min="1950" max="2050"
                                                           value="<c:out value='${vehicle.year}' />"
                                                           class="form-control"
                                                           name="year" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Vehicle Make</label> <input type="text" required="required"
                                                           value="<c:out value='${vehicle.make}' />"
                                                           class="form-control"
                                                           name="make">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Vehicle Model</label> <input type="text" required="required"
                                                            value="<c:out value='${vehicle.model}' />"
                                                            class="form-control"
                                                            name="model">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
