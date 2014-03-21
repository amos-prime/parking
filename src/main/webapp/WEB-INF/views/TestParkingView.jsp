<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.vattenfall.model.*, java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Parking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    </style>
</head>
<body>

<div class="container">
    <div class="panel panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">Parking schedule for this month</h3>
      </div>
      <div class="panel-body">
        <table class="table table-condensed table-bordered">
         <thead>
          <tr>
             <th>Username</th>
             <c:forEach items="${dates}" var="entry">
             <th> <c:out value="${entry.key}" /> <c:out value="${entry.value}" /> </th>
             </c:forEach>
          </tr>
         </thead>
        <c:forEach items="${map}" var="entry">
        <tr>
           <td>
           <c:out value="${entry.key.username}" />
           </td>
           <c:forEach items="${entry.value}" var="day">
           <td>
               <span class="label label-primary">
               <c:out value="${day.dayState}" />
               </span> <br>
               <input type="checkbox">
           </td>
           </c:forEach>
        </tr>
        </c:forEach>
    </table>
                <!-- Action Button -->
                        <div class="btn-group">
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            Action <span class="caret"></span>
                          </button>
                          <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                          </ul>
                        </div>
                <!-- End Action Button -->
    </div>
    </div>

</div>
</body>
</html>