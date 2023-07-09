<#import "parts/common.ftlh" as c>

<@c.page>
    Список пользователей


    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Name</th>
                <th>Role</th>
                <th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>
                        <#if user.email??>
                            ${user.email}
                        </#if>
                    </td>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list> </td>
                    <td><a href="/user/${user.id}">edit</a></td>
                    <td>
                        <form action="/user/delete" method="post">
                            <input type="hidden" name="userId" value=${user.id}>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <div class="form-group ml-1">
                                <button type="submit" class="btn btn-primary">Удалить</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

    <br><br><br>

    <h5>${stat}</h5>
</@c.page>