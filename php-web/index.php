<?php
    require_once 'classes/Api.php';

    $dadosApi = new Api();
    $users = $dadosApi->listUser();
?>


