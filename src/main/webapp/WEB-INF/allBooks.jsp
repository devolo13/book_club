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
        <h1>Welcome, <c:out value="${userName}"/></h1>
        <a href="/logout" class="mt-3">logout</a>
    </div>
    <div class="d-flex justify-content-between my-2">
        <h5>Books from everyone's shelves:</h5>
        <a href="/books/new">+ Add a book to my shelf!</a>
    </div>
    <table class="table table-striped text-center align-middle">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author Name</th>
            <th>Posted By</th>
        </tr>
        <c:forEach var="i" begin="0" end="${allBooks.size() -1}">
            <tr>
                <td><c:out value="${allBooks[i].id}"/></td>
                <td><a href="/books/${allBooks[i].id}"><c:out value="${allBooks[i].title}"/></a></td>
                <td><c:out value="${allBooks[i].author}"/></td>
                <td><c:out value="${allBooks[i].user.name}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

