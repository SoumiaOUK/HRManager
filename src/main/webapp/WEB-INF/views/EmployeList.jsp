<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employe List</title>
</head>
<body>
    <header>
        <h1>Employe List</h1>
    </header>
    <main>
        <table>
            <tr>
                <th>Id</th> <th>Nom</th> <th>Prenom</th> <th>Role</th> <th>Service</th> <th>Solde conge</th>
            </tr>
            <c:forEach items="${empJsp}" var="employe">
                <tr>
                    <td>${employe.id}</td>
                    <td>${employe.nom}</td>
                    <td>${employe.prenom}</td>
                    <td>${employe.role}</td>
                    <td>${employe.service}</td>
                    <td>${employe.soldeConges}</td>
                </tr>
            </c:forEach>
        </table>
    </main>
    <footer>
        <a href="CreateEmploye">Employe Creation</a>
    </footer>
</body>
</html>
