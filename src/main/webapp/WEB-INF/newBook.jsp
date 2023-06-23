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
    <div class="d-flex justify-content-between">
        <h1>Add a Book to Your Shelf!</h1>
        <a href="/books">back to the shelves</a>
    </div>
    <form:form action="/books/add" method="post" modelAttribute="book" cssClass="p-1">
        <form:errors path="title" cssClass="text-danger"/>
        <div class="d-flex">
            <form:label path="title" cssClass="col-3 mt-2">Title</form:label>
            <form:input path="title" cssClass="form-control my-2"/>
        </div>

        <form:errors path="author" cssClass="text-danger"/>
        <div class="d-flex">
            <form:label path="author" cssClass="col-3 mt-2">Author</form:label>
            <form:input path="author" cssClass="form-control my-2"/>
        </div>

        <form:errors path="thoughts" cssClass="text-danger"/>
        <div class="d-flex">
            <form:label path="thoughts" cssClass="col-3 mt-2">My Thoughts</form:label>
            <form:input path="thoughts" cssClass="form-control my-2"/>
        </div>

        <form:input type="hidden" path="user" value="${userId}"/>
        <button class="btn btn-primary my-3 mx-2">Submit</button>
    </form:form>
</body>
</html>

