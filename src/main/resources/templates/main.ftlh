<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>

<#--    // форма поиска-->
    <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/main" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по названию">
                    <button type="submit" class="btn btn-primary ml-2">Поиск</button>
                </form>
            </div>
        </div>

<#--    если пользователь админ показать форму добавления пользователей-->
    <#if isAdmin>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить новую компанию
        </a>
        <div class="collapse" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control" name="tag" placeholder="Название компании">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="text" placeholder="Краткое описание компании" />
                    </div>
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Выберите файл</label>
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>

        <br>
        <br>

    <#--    основной вывод компаний-->
    <div class="row mb-2">
        <#list messages as message>

            <div class="col-md-6">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
<#--                        <strong class="d-inline-block mb-2 text-primary">World</strong>-->
                        <h3 class="mb-0">${message.tag}</h3>
                        <div class="mb-1 text-muted">Здесь будет адрес компании</div>
                        <p class="card-text mb-auto">${message.text}</p>

        <#--Часть отвечающая за звездочки-->
                        <#if message.rank??>

                            <div class="mt-2">
                                <#if message.rank.isItOne(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                </#if>

                                <#if message.rank.isItOneHalf(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star-half"></i>
                                </#if>

                                <#if message.rank.isItTwo(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </#if>

                                <#if message.rank.isItTwoHalf(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star-half"></i>
                                </#if>

                                <#if message.rank.isItThree(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </#if>

                                <#if message.rank.isItThreeHalf(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star-half"></i>
                                </#if>

                                <#if message.rank.isItFour(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </#if>

                                <#if message.rank.isItFourHalf(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star-half"></i>
                                </#if>

                                <#if message.rank.isItFive(message.rank)>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </#if>
<#--                                отзывы-->
                                <#if message.commentNotEmpty()>
                                    <strong class="d-inline-block mb-2 text-primary">Отзывов: ${message.getNumberOfComment()}</strong>
                                <#else>
                                    <strong class="d-inline-block mb-2 text-primary">Отзывов: 0</strong>
                                </#if>
                            </div>

                        <#else>
                            <strong class="d-inline-block mb-2 text-primary">Комментарии отсутствуют</strong>
                        </#if>


                        <#--Ссылка на более подробную информацию-->
                        <div>
                            <form action="/file" method="get">
                                <input type="hidden" name="id" value=${message.id}>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <div class="form-group mt-1">
                                        <button type="submit" class="btn btn-primary">Узнать подробнее</button>
                                    </div>
                            </form>
                        </div>

                    </div>
                    <div class="col-auto d-none d-lg-block">
                        <#if message.filename??>

                            <img src="/img/${message.filename}" width="200" height="250" alt="Изображение недоступно">

                        <#else>
                            <img src="/img/${message.rnd()}.svg" width="200" height="250" alt="Изображение недоступно">
                        </#if>
                    </div>
                </div>
            </div>
        <#else>
            Информация о компаниях отсутствует, обратитесь к администратору.
        </#list>

    </div>

</@c.page>