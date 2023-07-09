<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mining Tools Aggregator</title>
    <link rel="stylesheet" href="/static/style.css">


    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fontawesome Kit-->
    <script src="https://kit.fontawesome.com/475e925fcc.js" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://www.google.com/recaptcha/api.js"></script>


    <!-- Put this script tag to the <head> of your page -->
<#--    <script-->
<#--            type="text/javascript"-->
<#--            src="https://vk.com/js/api/openapi.js?168"-->
<#--            charset="utf-8"-->
<#--    ></script>-->
<#--    <script type="text/javascript">-->
<#--        VK.init({ apiId: 8167810 });-->
<#--    </script>-->

</head>
<body>
<#--В самом начале тела добавляем ссылку на навигационную модель-->
    <#include "navbar.ftlh">
    <div class="container mt-5">
        <#nested>
    </div>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <footer class="blog-footer ml-2">
        <div class="mx-auto" style="width: 200px;">
            <small class="d-block mb-3 text-muted">&copy; Developed by <a href="https://t.me/roman_f_dev">@roman</a>. 2022</small>
        </div>
    </footer>
</body>
</html>
</#macro>