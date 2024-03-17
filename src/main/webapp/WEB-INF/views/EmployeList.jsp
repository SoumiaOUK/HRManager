<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.0.2/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employe List</title>
</head>
<body>
    <header>
        <h1>Employe List</h1>
        <br>
    </header>
    <main>
        <table class="table align-middle mb-0 bg-white">
            <thead class="bg-light">
            <tr>
                <th>Id</th> <th>Nom</th> <th>Prenom</th> <th>email</th> <th>Role</th> <th>Service</th> <th>Solde conge</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${empJsp}" var="employe">
                <tr>
                    <td>${employe.id}</td>
                    <td>${employe.nom}</td>
                    <td>${employe.prenom}</td>
                    <td>${employe.email}</td>
                    <td>${employe.role}</td>
                    <td>${employe.service}</td>
                    <td>${employe.soldeConges}</td>
                    <td><a href="deleteEmploye?id=${employe.id}" onclick="return confirm('Etes vous sur de vouloir supprimer cet employe')">Delete</a></td>
                    <td><a href="showEmploye?id=${employe.id}">modifier</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

    </main>
    <br>
    <footer>
        <a href="createEmploye">ajouter employe</a>
    </footer>
</body>
</html>
