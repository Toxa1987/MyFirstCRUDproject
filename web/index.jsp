<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Users Start Page</title>
    <link rel="stylesheet" type="text/css" href="Styles1.css">

  </head>
  <body>
  <h2>List of users</h2>
  <p><a  href="<c:url value="/create"></c:url> ">Add new user</a> </p>
  <div>
  <table>
    <tr><th>Id</th><th>Login</th><th>Password</th><th>Email</th><th>Action</th> </tr>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.password}</td>
        <td>${user.email}</td>
        <td><a href="<c:url value="/edit?id=${user.id}"/>">Edit</a>
          <form method="post" action="<c:url value="/delete"/>" style="display: inline"> "
            <input type="hidden" name="id" value="${user.id}"/>
            <input type="submit" value="Delete">
          </form>
        </td></tr>
    </c:forEach>

  </table>

     <table >
      <tr>
        <td>
          <form method="post" action="<c:url value="/index?page=1"/>">
            <input type="submit" name="back" value="Back">
          </form>
        </td>

        <td>
            <form method="post" action="<c:url value="/index?page=${CurrentPage-1}"/>">
              <input type="submit" name="back" value="Previous">
            </form>
          </td>


        <c:forEach begin="1" end="${pages}" var="i">
          <c:choose>
            <c:when test="${CurrentPage eq i}">
              <td>${i}</td>
            </c:when>
            <c:otherwise>
              <td><a href="<c:url value="/index?page=${i}"/>">${i}</a></td>
            </c:otherwise>
          </c:choose>
        </c:forEach>
        <td>
          <form method="post" action="<c:url value="/index?page=${CurrentPage+1}"/>">
            <input type="submit" name="next" value="Next">
          </form>
        </td>
        <td>
          <form method="post" action="<c:url value="/index?page=${pages}"/>">
            <input type="submit" name="end" value="End">
          </form>


        </td>
        <td><form method="post" action="<c:url value="/index?page=1"/>">
          <select name="recordsPerPage"  style="display: inline" >
            <option >10</option>
            <option> 20</option>
            <option>50</option>
            <option>100</option>
            <option value="1">all</option>
          </select>
          <input type="submit" value="Choose">
        </form>
        </td>
      </tr>

    </table>
  </div>

  </body>
</html>