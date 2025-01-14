<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Activation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #007bff;
        }
        p {
            font-size: 16px;
            line-height: 1.5;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Assalomu alaykum ! ${username}</h1>
    <p>Sizning ro'yxatdan o'tishingiz muvaffaqiyatli amalga oshirildi. Hisobingizni faollashtirish uchun quyidagi tugmani bosing:</p>
    <a href="${url}" class="btn">Hisobni Faollashtirish</a>
    <p>Agar siz ushbu xatni noto'g'ri olgan bo'lsangiz, uni e'tiborsiz qoldiring.</p>
    <p>Rahmat!</p>
</div>
</body>
</html>
