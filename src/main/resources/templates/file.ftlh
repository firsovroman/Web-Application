




<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>


    <h3 xmlns:form="http://www.w3.org/1999/html">${message.tag}</h3>
    <p>${message.text}</p>

    <#if message.rank??>
        <p>${message.rank}</p>
    </#if>

    <#if isAdmin>
    <#--Удалить компанию-->
        <div>
            <form action="/file/delete" method="post">
                <input type="hidden" name="id" value=${message.id}>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group mt-1">
                    <button type="submit" class="btn btn-primary">Удалить эту компанию</button>
                </div>
            </form>
        </div>

    <#--Изменить параметры-->
        <div>
            <form action="/file" method="post">
                <input type="hidden" name="id" value=${message.id}>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <input type="text" class="form-control" name="tag" placeholder="Название компании">
                <input type="text" class="form-control" name="text" placeholder="Краткое описание компании" />
    <#--            <div class="custom-file">-->
    <#--                <input type="file" name="file" id="customFile">-->
    <#--                <label class="custom-file-label" for="customFile">Выберите файл</label>-->
    <#--            </div>-->
    <#--            <input type="text" class="form-control" name="rank" placeholder="Оценка" />-->
                <select class="form-select" name="rank">
    <#--                <option selected>Open this select menu</option>-->
                    <option value="ONE">One</option>
                    <option value="ONE_HALF">One_Half</option>
                    <option value="TWO">Two</option>
                    <option value="TWO_HALF">Two_Half</option>
                    <option value="THREE">Three</option>
                    <option value="THREE_HALF">Three_Half</option>
                    <option value="FOUR">Four</option>
                    <option value="FOUR_HALF">Four_Half</option>
                    <option value="FIVE">Five</option>
                </select>

                <div class="form-group mt-1">
                    <button type="submit" class="btn btn-primary">Изменить</button>
                </div>
            </form>
        </div>

<#--Добавить комментарий-->
        <div>
            <form action="/file/comment" method="post">
                <input type="hidden" name="messageId" value=${message.id}>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <input type="text" class="form-control" name="userId" placeholder="Пользовательский ID">
                <input type="text" class="form-control" name="textComment" placeholder="Текст комментария" />

                <select class="form-select" name="grade">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

                <div class="form-group mt-1">
                    <button type="submit" class="btn btn-primary">Добавить комментарий</button>
                </div>
            </form>
        </div>
    </#if>

    <br><br>


    <#--Добавить отзыв-->
        <div>
            <form action="/file/send" method="post">
                <input type="hidden" name="messageId" value=${message.id}>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <input type="hidden" name="userName" value=${name}>

                Поставьте оценку по пятибальной шкале:
                <select class="form-select" name="rank">
                    <#--                <option selected>Open this select menu</option>-->
                    <option value="ONE">1</option>
                    <option value="TWO">2</option>
                    <option value="THREE">3</option>
                    <option value="FOUR">4</option>
                    <option value="FIVE">5</option>
                </select>

                <input type="text" class="form-control" name="textComment" placeholder="Введите ваш отзыв" />

                <div class="form-group mt-1">
                    <button type="submit" class="btn btn-primary">Отправить оценку и отзыв</button>
                </div>
            </form>
        </div>


    <#if isItSended??>
        Комментарий отправлен. Он будет добавлен после проверки модератором.
    </#if>
<#--    -->

    <#if message.commentNotEmpty()>

        <#list message.getCommentSet() as comment>

            <#if comment.commentTextIsNotEmpty()>


                <div class="card">
                    <div class="card-header">
                        Комментарий оставлен пользователем: ${comment.authorName}
                    </div>
                    <div class="card-body">
<#--                        <blockquote class="blockquote mb-0">-->
<#--                            <p>Оценка: ${comment.grade} из 5</p>-->
                        <strong class="d-inline-block mb-2 text-primary">Оценка: ${comment.grade} из 5</strong>
                            <p>${comment.commentText}</p>
<#--                        </blockquote>-->
                        <footer class="blockquote-footer"><cite title="Source Title">${comment.dateTime}</cite></footer>
                    </div>
                </div>
                <#if isAdmin>
                <#--Удалить комментарий-->
                    <div>
                        <form action="/file/comment/delete" method="post">
                            <input type="hidden" name="messageId" value=${message.id}>
                            <input type="hidden" name="commentId" value=${comment.id}>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <div class="form-group mt-1">
                                <button type="submit" class="btn btn-primary">Удалить комментарий</button>
                            </div>
                        </form>
                    </div>
                </#if>
            </#if>

        </#list>

    </#if>

</@c.page>