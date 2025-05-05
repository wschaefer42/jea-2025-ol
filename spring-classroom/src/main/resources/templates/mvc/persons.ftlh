<!DOCTYPE html>
<html>
<head>
    <title>Persons</title>
</head>
<body>
<h1>Persons</h1>
<h2>Person List</h2>
<table>
    <tr>
        <th>Name</th>
        <th>City</th>
    </tr>
    <#list persons as person>
        <tr>
            <td>${person.name}</td>
            <td>${person.city}</td>
            <td><a href="/persons/${person.id}">Edit</a></td>
        </tr>
    </#list>
</table>
<br>
<h2>Detail Person</h2>
<#if person??>
    <from action="/persons/save" method="post">
        <input type="hidden" name="id" value="${person.id}">
        <label>Name
            <input type="text" name="name" value="${person.name}">
        </label>
        <label>City
            <input type="text" name="city" value="${person.city}">
        </label>
        <input type="submit" value="Save">
    </from>
</#if>
</body>
</html>
