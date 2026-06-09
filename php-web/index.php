<?php
    require_once 'classes/Api.php';

    $dadosApi = new Api();
    $users = $dadosApi->listUser();
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div>
        <h1>Teste conexão com api via cUrl</h1>
        <table>
            <tr>
                <th>Nome:</th>
                <th>Email:</th>
            </tr>
            <tr>
            <?php foreach ($users as $row): ?>
                <td><?= $linha['name']?></td>
                <td>><?= $linha['email']?></td>
            <?php endforeach; ?>    
            </tr>
        </table>
    </div>
</body>
</html>