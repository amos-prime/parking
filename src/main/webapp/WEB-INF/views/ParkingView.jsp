<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Parking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <style type="text/css">
    p { padding-bottom: 12px; }
    .bordered {
    border-style:solid;
    border-width:2px;
    }
    </style>
</head>
<body>
<div class="container">
    <h1>Parking Site</h1>
     <span class="span6">
     <table class="table table-condensed table-bordered">
        <c:forEach items="${map}" var="entry">
        <tr>
           <td class="bordered">
           <c:out value="${entry.key.username}" /></td>
           <c:forEach items="${entry.value}" var="day">
           <td class="bordered">
               <c:out value="${day.date}" /> <br>
               <c:out value="${day.dayState}" />
           </td>
           </c:forEach>
        </tr>
        </c:forEach>
    </table>
    </span>
</div>
</body>
</html>