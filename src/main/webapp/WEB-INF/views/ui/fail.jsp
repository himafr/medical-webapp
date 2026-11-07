<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ar">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>تعذر إتمام العملية</title>

        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <style>
            .congratulation-wrapper {
                max-width: 550px;
                margin-inline: auto;
                -webkit-box-shadow: 0 0 20px #f3f3f3;
                box-shadow: 0 0 20px #f3f3f3;
                padding: 30px 20px;
                background-color: #fff;
                border-radius: 10px;
            }
            
            .congratulation-contents.center-text .congratulation-contents-icon {
                margin-inline: auto;
            }
            .congratulation-contents-icon {
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-align: center;
                -ms-flex-align: center;
                align-items: center;
                -webkit-box-pack: center;
                -ms-flex-pack: center;
                justify-content: center;
                height: 100px;
                width: 100px;
                background-color: #65c18b27;
                color: #fff;
                font-size: 50px;
                border-radius: 50%;
                margin-bottom: 30px;
            }
            .congratulation-contents-title {
                font-size: 32px;
                line-height: 36px;
                margin: -6px 0 0;
            }
            .congratulation-contents-para {
                font-size: 16px;
                line-height: 24px;
                margin-top: 15px;
            }
            .btn-wrapper {
                display: block;
            }
            .cmn-btn.btn-bg-1 {
                background: #6176f6;
                color: #fff;
                border: 2px solid transparent;
                border-radius:3px;
                text-decoration: none;
                padding:5px;
            }
        </style>
    </head>

    <body>
        <!-- Congratulations area start -->
        <div class="congratulation-area text-center mt-5">
            <div class="container">
                <div class="congratulation-wrapper">
                    <div class="congratulation-contents center-text">
                        <div class="congratulation-contents-icon">
                            <img src="/svgs/close.svg" width="80">
                        </div>
                        <h4 class="congratulation-contents-title">تعذر إتمام العملية</h4>
                        <p class="congratulation-contents-para" style="color:red;"> ${failMessage} </p>
                        <div class="btn-wrapper mt-4">
                            <a href="/home" class="btn-primary btn-bg-1">العودة إلى الرئيسية</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Congratulations area end -->

        <!-- Latest compiled JavaScript -->
        <%@ include file="../shared/toast.jsp" %>
        <script src="/js/bootstrap.min.js"></script>

    </body>
</html>