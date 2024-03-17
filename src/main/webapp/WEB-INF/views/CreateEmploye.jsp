<html>
<head>
    <title>Add Employe</title>
</head>
<body>
<h1>Add Employee by answering theese questions !</h1>
<form action="saveEmploye" method="post">
    <label for="name"> Nom:</label>
    <input type="text" for="nom" id="name" name="nom">

    <label for="prenom">Prenom</label>
    <input type="text" id="prenom" name="prenom">

    <label for="email">Email : </label>
    <input type="text" name="email" id="email">

    <label for="service">Email : </label>
    <input type="text" name="service" id="service">

    <label for="solde_conge">Solde Conge : </label>
    <input type="text" name="solde_conge" id="solde_conge">

    <label for="role">Role : </label>
    <select id="role" name="role">
        <option value="option1">Option 1</option>
        <option value="option2">Option 2</option>
        <option value="option3">Option 3</option>
    </select>

    <input type="submit" value="Enregistrer">

</form>
</body>
</html>