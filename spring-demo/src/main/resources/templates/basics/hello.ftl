<!DOCTYPE html>
<html lang="en" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <script src="/webjars/bootstrap/5.3.3/dist/js/bootstrap.min.js"></script>
    <title>Hello</title>
</head>
<body>
<h2>Hello Freemarker</h2>
<h3>Add new Message</h3>
<form action="/hello" method="post">
    <input type="hidden" id="id" name="id" value="${id!0}"/>
    <label>Name
        <input type="text" id="name" name="name" value="${name!''}">
    </label>
    <label>Message
        <input type="text" id="message" name="message" value="${message!''}">
    </label>
    <input type="submit" value="submit">
</form>
<br/>
<#if hellos??>
    <h3>Messages</h3>
    <ul>
        <#list hellos as hello>
            <li>
                ${hello.name} says: ${hello.message}
                <a href="/hello/${hello.id}">Edit</a>
            </li>
        </#list>
    </ul>
<#else>
    <a href="/hello/list">Get All Messages</a>
</#if>
</body>
</html>