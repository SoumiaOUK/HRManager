<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.0.2/css/bootstrap.min.css">

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
            <div class="row g-5">
                <div class="col">
                    <div data-mdb-input-init class="form-outline">
                        <label for="name"> Nom:</label>
                        <input type="text" for="nom" id="name" name="nom" value="${empJsp.nom}">
                    </div>
                </div>

                <div class="col">
                    <div data-mdb-input-init class="form-outline">
                        <label for="prenom">Prenom</label>
                        <input type="text" id="prenom" name="prenom" value="${empJsp.prenom}">
                    </div>
                </div>
            </div>

            <div class="row g-5">
                <div class="col">
                    <div data-mdb-input-init class="form-outline">
                        <label for="email">Email : </label>
                        <input type="text" name="email" id="email"value="${empJsp.email}">
                    </div>
                </div>

                <div class="col">
                    <div data-mdb-input-init class="form-outline">
                        <label for="service">Service : </label>
                        <input type="text" name="service" id="service" value="${empJsp.service}">
                    </div>
                </div>
            </div>

            <div class="row g-5">
                <div class="col">
                    <div data-mdb-input-init class="form-outline">
                        <label for="solde_conge">Solde Conge : </label>
                        <input type="text" name="soldeConges" id="solde_conge" ${empJsp.soldeConges}>
                    </div>
                </div>

                <div class="col">
                    <div data-mdb-input-init class="form-outline">
                        <label for="role">Role : </label>
                        <select id="role" name="role" value="${empJsp.role}">
                            <option value="option1">Option 1</option>
                            <option value="option2">Option 2</option>
                            <option value="option3">Option 3</option>
                        </select>
                    </div>
                </div>
            </div>

            <input type="submit" value="Enregistrer">
        </div>


    </form>
</main>
<footer>
    <a href="employeList">list employe</a>
</footer>
</body>
</html>
