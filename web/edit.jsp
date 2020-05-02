<%--
  Created by IntelliJ IDEA.
  User: Toxa
  Date: 17.04.2020
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
    <link rel="stylesheet" type="text/css" href="Styles1.css">
</head>
<body>
<p><a href="index">Back</a> </p>
<h3>Edit User</h3>
<div>
    <form method="post">
    <input  type="hidden" value= "${user.id}" name="id"/><br>
    <label>Name</label><br>
    <input name="login" value="${user.login}"><br><br>
    <label>password</label><br>
    <input name="password" value="${user.password}" type="password" ><br><br>
    <label>Email</label><br>
    <input name="email" value="${user.email}" type="email"  ><br><br>

    <input type="submit" value="Send">
    </form>
</div>
</body>
</html>
