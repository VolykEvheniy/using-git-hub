<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Теми</title>
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
    <h2 style="margin-top: 60px">Теми форуму</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Заголовок теми</th>
            <th scope="col">№ дописів</th>
            <c:if test="${sessionScope.user.role.equals('admin')}">
                <th scope="col">Керуваня</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${topics}" var="topic">
        <tr>
            <th scope="row"><c:out value="${topic.topicId}" /></th>
            <td>
                <a href="topic?topicId=${topic.topicId}"><c:out value="${topic.title}" /></a> <br>
                Створено <c:out value="${topic.date}" />  користувачем <c:out value="${topic.author.email}" />
            </td>
            <td><c:out value="${fn:length(topic.posts)}" /></td>
            <c:if test="${sessionScope.user.role.equals('admin')}">
            <th scope="col">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <form action="editTopicOpen">
                        <input type="hidden" name="topicId" value="${topic.topicId}" />
                        <button type="submit" class="btn btn-secondary">Редагувати</button>
                    </form>
                    <form action="removeTopic">
                        <input type="hidden" name="topicId" value="${topic.topicId}" />
                        <button type="submit" class="btn btn-secondary">Видалити</button>
                    </form>
                </div>
            </th>
            </c:if>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${sessionScope.user.role.equals('admin')}" >
        <a href="addTopic">Додати тему</a>
    </c:if>

</div>
</body>
</html>
