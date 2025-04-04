<#import "_layout.ftlh" as layout />
<@layout.header title="Users">
    <h2>List of users</h2>
    <table>
        <tr>
            <th>Username</th>
            <th>E-Mail</th>
            <th>Age</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td><a href="/users/${user.id}">Details</a> </td>
            </tr>
        </#list>
    </table>
    <br>
    <h2>Details User</h2>
    <#if user??>
        <form action="/users/save" method="POST">
            <label>Name:
                <input type="text" name="name" value="${user.name!''}" />
            </label>
            <label>Email:
                <input type="text" name="email" value="${user.email!''}" />
            </label>
            <label>Age:
                <input type="number" name="age" value="${user.age!''}" />
            </label>
            <input type="submit" value="Save" />
        </form>
    </#if>
</@layout.header>
