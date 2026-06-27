<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<%-- //TODO change the page name --%>
<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home </title>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/style.css">
        <link rel="stylesheet" href="/css/home.css">
        <link rel="stylesheet" href="/responsive.css">
    </head>
    <body>
        <div class="container">
            <section class="d-flex align-items-center min-vh-100 py-5">
                <div class="container py-5">
                    <div class="row align-items-center">
                        <div class="col-md-6 order-md-2">
                            <div class="lc-block">
                                <img src="/images/error.png" width="80%" alt="error image">
                            </div>
                        </div>
                        <div class="col-md-6 text-center text-md-start ">
                            <div class="lc-block mb-3">
                                <div editable="rich">
                                    <!-- <h1 class="fw-bold h4">PAGE NOT FOUND!<br></h1> -->
                                </div>
                            </div>
                            <div class="lc-block mb-3">
                                <div editable="rich">
                                    <h1 class="display-1 fw-bold text-muted">Error</h1>

                                </div>
                            </div>
                            <div class="lc-block mb-5">
                                <div editable="rich">
                                    <p class="rfs-11 fw-light">something went wrong please try again later</p>
                                    <p class="rfs-11 fw-light">${error}</p>
                                    <p class="rfs-11 fw-light">${status}</p>

                                </div>
                            </div>
                            <div class="lc-block">
                                <a class="btn btn-lg btn-secondary" href="/home" role="button">Back to homepage</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <%-- //TODO super important remove this in production  --%>
            <%= exception.printStackTrace() %>
                

            <script src="/js/bootstrap.min.js"></script>
        </body>
    </html>


