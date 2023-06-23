<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <title>Book Club</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>
</head>
<body class="container pt-4">
    <div class="d-flex justify-content-between mb-5">
        <h1><c:out value="${book.title}"/></h1>
        <a href="/books">back to the shelves</a>
    </div>
<%--    userName should be replaced with the books poster  --%>
    <p><c:out value="${book.user.name}"/> read <c:out value="${book.title}"/> by <c:out value="${book.author}"/>.</p>
    <p>Here are <c:out value="${book.user.name}"/>'s thoughts:</p>
    <div class="container">
        <hr>
        <c:out value="${book.thoughts}"/>
        <hr>
    </div>
    <c:if test="${userId == book.user.id}">
        <div class="d-flex">
            <a href="/books/<c:out value="${book.id}"/>/edit" class="btn btn-warning me-2 my-2">edit</a>
            <a href="/books/<c:out value="${book.id}"/>/delete" class="btn btn-danger m-2">delete</a>
        </div>
    </c:if>
</body>
</html>

