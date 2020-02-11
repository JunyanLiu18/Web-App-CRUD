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
        <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

        <div class="container">
            <form action="search-nav">
                <caption>
                    <h2> Tag Search </h2>
                </caption>
                <fieldset class="form-group">
                <div class="form-group">
                    <select name="item" class="form-control">
                        <option value="year">Year</option>
                        <option value="make">Make</option>
                        <option value="model">Model</option>
                    </select>
                </div>
                </fieldset>
                <fieldset class="form-group">
                <div class="form-group">
                    <input type="text" class="form-control" name="val"/>
                </div>
                </fieldset>
                <button type="submit" class="btn btn-success">Search</button>
            </form>

        </div>
    </div>
    </div>
</body>
</html>
