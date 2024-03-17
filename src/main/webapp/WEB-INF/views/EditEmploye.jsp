<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employe Edit</title>
</head>
<body>
<header>
    <h1>Modifer Employe</h1>
</header>
<main>
    <form action="updateEmploye" method="post">
        <div>
            <label hidden="hidden" for="id"> id: </label>
            <input hidden="hidden" type="text" name="id" for="id" id="id">
        </div>

        <div>
        <label for="name"> Nom:</label>
        <input type="text" for="nom" id="name" name="nom" value="${empJsp.nom}">
        </div>

        <div>
        <label for="prenom">Prenom</label>
        <input type="text" id="prenom" name="prenom" value="${empJsp.prenom}">
        </div>

        <div>
        <label for="email">Email : </label>
        <input type="text" name="email" id="email" value="${empJsp.email}">
        </div>

        <div>
        <label for="service">Service : </label>
        <input type="text" name="service" id="service" value="${empJsp.service}">
        </div>

        <div>
        <label for="solde_conge">Solde Conge : </label>
        <input type="text" name="solde_conge" id="solde_conge" value="${empJsp.soldeConges}">
        </div>

        <div>
        <label for="role">Role : </label>
        <select id="role" name="role" value="${empJsp.role}">
            <option value="option1">Option 1</option>
            <option value="option2">Option 2</option>
            <option value="option3">Option 3</option>
        </select>
        </div>

        <input type="submit" value="Enregistrer">
    </form>
</main>
<footer>
    <a href="employeList">list employe</a>
</footer>
</body>
</html>
