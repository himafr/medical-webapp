<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Contact us </title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/contact.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="shared/header.jsp" %>
        </div>
        <section class="section-one">
            <div class="container ">
                <div class="contact-s1">
                    <p class="btn-gold ">تأسست لترتقي بتجربتك الصحية</p>
                    <h1 class="text-primary">تواصل مع فريقنا الطبي </h1>
                    <p class="text-secondary">نسعد بالإجابة على استفساراتكم الطبية ومساعدتكم في حجز المواعيد. فريقنا جاهز
                        لخدمتكم على مدار الساعة بمهنية واحترافية.
                    </p>
                </div>
            </div>
        </section>
        <section class="section-two row justify-content-between" >
            <div class="col-md-6 ps-md-4" >
                <form >
                    <h2 class="text-primary">أرسل لنا رسالة</h2>
                    <p class="text-secondary">سنقوم بالرد عليك في أقرب وقت ممكن عبر بريدك الإلكتروني.</p>
                    <div class="form-group row">

                        <div class="mb-3 col-md" >
                            <label for="fullName" class="form-label text-primary">الاسم بالكامل</label>
                            <div  class="input-holder">
                                <input type="text" class="form-control" id="fullName" placeholder="ادخل اسمك ">
                                <span>   <img src="./svgs/at_mark.svg" alt="email icon">
                                </span>
                            </div>
                        </div>

                        <div class="mb-3 col" >
                            <label for="email" class="form-label text-primary">البريد الالكتروني </label>
                            <div  class="input-holder">
                                <input  type="email" class="form-control" id="email" placeholder="hema@example.com">
                                <span>   <img src="./svgs/at_mark.svg" alt="email icon">
                                </span>
                            </div>
                        </div>

                        <div class="mb-3 " >
                            <label for="subject" class="form-label text-primary">الموضوع </label>
                            <div  class="input-holder">
                                <input  type="email" class="form-control" id="subject" placeholder="موضوع الرساله ">
                                <span>   <img src="./svgs/at_mark.svg" alt="email icon">
                                </span>
                            </div>
                        </div>

                    </div>

                    <div class="mb-3">
                        <label for="message" class="form-label text-primary">الرسالة</label>
                        <textarea class="form-control" id="message" rows="5" placeholder="اكتب تفاصيل استفسارك هنا "></textarea>
                    </div>
                    <button type="submit" class="btn-green col-md-5" > إرسال الرسالة
                        <img src="./svgs/send.svg" alt="icon" style="margin-right: 16px;">
                    </button>
                </form>

                <div class="whats-card row align-items-center justify-content-around ">
                    <div class="text-icon col-6">
                        <div class="round-gold">
                            <img src="./svgs/logo-dark.svg" alt="logo" class="logo">
                        </div>

                        <div >
                            <p class="text-primary mb-0" >
                                هل
                                تفضل المحادثة الفورية؟
                            </p>
                            <p class="text-secondary mb-0" >
                                تحدث معنا مباشرة عبر الواتساب
                            </p>
                        </div>
                    </div>
                    <a href="https://wa.me/201146839894?text=مرحبا%20بك%20" target="_blank" class="whats-button col text-white pe-md-3" >
                        <img src="./svgs/whatsappmsg.svg" alt="icon" >
                        تواصل واتساب
                    </a>
                </div>

            </div>
            <div class="col-md-6 pe-md-4" >

                <div class="row justify-content-between">
                    <div class="gray-card col-md-5 mb-3">
                        <div class="col-md-5 flex">
                            <p class="btn-square-gold">
                                <img src="/svgs/location.svg" alt="icon" />
                            </p>
                        </div>
                        <h4 class="text-primary"> العنوان
                        </h4>
                        <p class="text-secondary">
                            شارع الملك فهد، حي الصحافة
                            الرياض، المملكة العربية السعودية
                        </p>
                    </div>
                    <div class="gray-card col-md-5">
                        <div class="col-md-5 flex">
                            <p class="btn-square-gold">
                                <img src="/svgs/call.svg" alt="icon" />
                            </p>
                        </div>
                        <h4 class="text-primary"> أرقام التواصل
                        </h4>
                        <p class="text-secondary ltr" >
                            +966 11 234 5678
                            <br>
                            +966 50 123 4567
                        </p>
                    </div>
                </div>

                <div class="gray-card row mt-3">
                    <div class=" row justify-content-between">
                        <div class="col-5 flex">
                            <p class="btn-square-gold">
                                <img src="/svgs/clock.svg" alt="icon" />
                            </p>
                        </div>

                        <div class="col-5 flex-l">
                            <p class="btn-gold ">
                                مفتوح الآن
                            </p>
                        </div>

                    </div>
                    <h4 class="text-primary"> ساعات العمل
                    </h4>
                    <div class="row justify-content-between">
                        <div class="col-md-5">
                            <h5 class="text-primary">السبت - الخميس
                            </h5>
                            <p class="text-secondary">
                                9:00 صباحاً - 9:00 مساءً
                            </p>
                        </div>
                        <div class="col-md-5">
                            <h5 class="text-primary"> الجمعة
                            </h5>
                            <p class="text-secondary">
                                4:00 مساءً - 9:00 مساءً
                            </p>
                        </div>
                    </div>
                </div>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7412.98508602273!2d29.90948124578411!3d31.19139722900235!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14f5c394ebd03ec1%3A0x16cbff5d844ff34b!2z2YXYudmH2K8g2KrZg9mG2YjZhNmI2KzZitinINin2YTZhdi52YTZiNmF2KfYqiAtIElUSQ!5e0!3m2!1sar!2seg!4v1775650092549!5m2!1sar!2seg" height="320" class="site-map" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </section>
        <section class="flex justify-content-center" >
            <div class="green-card row align-items-center justify-content-around ">
                <div  class="col-md-6 " >
                    <h3 class="text-gold" >
                        اشترك في نشرتنا الصحية
                    </h3>

                    <p  style="text-align: justify;">
                        احصل على نصائح طبية وعروض حصرية تصلك مباشرة إلى بريدك الإلكتروني.
                    </p>
                </div>

                <div class="col ">
                    <div class="row">
                        <div class=" col-8 col-md-6" >
                            <input type="text"  class="form-control"  placeholder="email " >
                        </div>
                        <button href="#" class="col green-card-contact  btn-gold text-gold " style="border-radius: 12px; padding:3px; line-height: normal; width: 100%;"> انضم لفريقنا </button>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="shared/footer.jsp" %>
        <script src="./js/bootstrap.min.js"></script>
    </body>
</html>
