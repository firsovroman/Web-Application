<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<#--<@c.page>-->
<#--    ${message?ifExists}-->
<#--    <@l.login "/login" false />-->
<#--</@c.page>-->

<@c.page>

    Зарегистрируйтесь чтобы оставлять комментарии. Вам потребуется электронная почта и 5 минут свободного времени.
    <br><br>
<#--    ${message?ifExists}-->
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <@l.login "/login" false/>
</@c.page>