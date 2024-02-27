<%--
  Created by IntelliJ IDEA.
  User: Mouan
  Date: 27/02/2024
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css.css" type="text/css">
    <title>Événements</title>
</head>
<body>

<h3>Liste des événements</h3>
<c:forEach items="${requestScope.evenements}" var="evt">
    <h4>${evt.nom}</h4>
    <p><b>Date de début : </b>${evt.dateHeureDebut}</p>
    <p><b>Date de fin : </b>${evt.dateHeureFin}</p>
    <p><b>Nombre maximum de participants : </b>${evt.maxParticipants}</p>
    <p>
        <b>Lieu : </b>${evt.lieu.nom}, ${evt.lieu.adresse}, ${evt.lieu.codePostal} ${evt.lieu.ville}
    </p>
</c:forEach>

</body>
</html>
