<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тема <c:out value="${topic.title}" /></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <nav class="navbar fixed-top navbar-light bg-light" style="background-color: darkgray">
        <a class="navbar-brand" href="topics">Форум</a>
        <c:if test="${sessionScope.user == null}" >
            <div class="login-container">
                <form action="login" method="post">
                    <input type="email" placeholder="Email" name="email">
                    <input type="password" placeholder="Password" name="password">
                    <button type="submit">Login</button>
                </form>
            </div>
        </c:if>
        <a class="" href="logout">Logout</a>
        <a class="" href="createAccount">Створити акаунт</a>
    </nav>
    <h2 style="margin-top: 60px">Дописи до теми "<c:out value="${topic.title}" />"</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Автор</th>
            <th scope="col">Текст допису</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${topic.posts}" var="post">
            <tr>
                <th scope="row"><c:out value="${post.postId}" /></th>
                <td>
                    <c:out value="${post.author.email}" /> <br>
                    <c:out value="${post.date}" />
                </td>
                <td><c:out value="${post.postText}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${sessionScope.user.role.equals('user') || sessionScope.user.role.equals('admin')}">
        <form action="addPostOpen" method="post">
            <input type="hidden" name="topicId" value="${topic.topicId}" />
            <input type="submit" value="Додати допис">
        </form>
    </c:if>
</div>
</body>
</html>
