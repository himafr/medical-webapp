<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ar" dir="rtl">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> About </title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/about.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="shared/header.jsp" %>
        </div>

<%-- //TODO complete the missing sections --%>
        <section class="section-one">
            <div class="glass-bg"></div>
            <div class="container">
                <p class="btn-gold ">تأسست لترتقي بتجربتك الصحية</p>
                <h1>
                    عيادة الشفاء: إرث من
                    الثقة ورؤية لمستقبل
                    <span>الرعاية الصحية</span>
                </h1>
                <p>
                    في قلب فلسفتنا، تكمن "الرعاية المتمحورة حول الإنسان". نحن لا نعالج أعراضاً، بل
                    نرعى أفراداً. نؤمن بأن الطب هو مزيج متناغم بين العلم المتقدم واللمسة الإنسانية
                    الحانية، حيث تكرس كل طاقاتنا لتوفير رحلة علاجية تتسم بالكرامة، الاحترام، والتميز
                    السريري الذي لا يضاهى.
                </p>

            </div>
        </section>

<%-- //TODO section two  --%>
        <section class="section-three">
            <div class="container">
                <div class="row justify-content-around">
                    <div class="overlay-card col-md-5 mb-3">
                        <div class="icon-holder">
                            <img src="/svgs/web.svg" alt="icon"/>
                        </div>
                        <h5>رسالتنا المؤسسية</h5>
                        <p class="text-secondary">
                            تتمحور رسالتنا حول إرساء دعائم نظام صحي متكامل
                            يجمع بين الكفاءة التشغيلية والتميز الطبي. نحن نلتزم
                            بتقديم رعاية آمنة ورحيمة تتجاوز توقعات المرضى،
                            مع التركيز المستمر على تحسين الجودة النوعية
                            للخدمات من خلال الابتكار الطبي المستمر والالتزام
                            بأرقى أخلاقيات المهنة، لضمان حياة صحية ومستقرة
                            لكافة فئات المجتمع.
                        </p>
                    </div>
                    <div class="overlay-card col-md-5">
                        <div class="icon-holder">
                            <img src="/svgs/eye.svg" alt="icon"/>
                        </div>
                        <h5>رؤيتنا الاستراتيجية</h5>
                        <p class="text-secondary">
                            نتطلع إلى أن نكون المرجعية الأولى للتميز الطبي
                            والابتكار في الرعاية الصحية على مستوى المنطقة.
                            رؤيتنا تهدف إلى قيادة التحول في القطاع الصحي
                            الخاص عبر تبني تقنيات الذكاء الاصطناعي في
                            التشخيص، وتوسيع نطاق التخصصات الدقيقة، وخلق
                            منظومة صحية ذكية تضع المملكة في طليعة
                            الوجهات العالمية للسياحة العلاجية والاستشفاء.
                        </p>

                    </div>
                </div>
            </div>
        </section>

        <section class="section-four">
            <div class="container text-center">
                <h4 class="text-gold" >المعايير والاعتمادات</h4>
                <h2 class="text-primary">معايير التميز الطبي (Quality Standards)</h2>
                <p class="text-secondary">
                    نحن نؤمن بأن الجودة ليست خياراً، بل هي أساس العمل الطبي الرصين، ونتبع بروتوكولات صارمة لضمان
                    سلامة مرضانا.
                </p>
                <div class="row justify-content-around">
                    <div class="white-card col-md-2">
                        <div class="icon-holder">
                            <img src="/svgs/grad-cap.svg" alt="icon"/>
                        </div>
                        <h5>التعليم الطبي المستمر</h5>
                        <p class="text-secondary">
                            تطوير مستمر لمهارات
                            طاقمنا الطبي عبر برامج
                            تدريبية عالمية لمواكبة
                            أحدث البروتوكولات
                            العلاجية.
                        </p>
                    </div>


                    <div class="white-card col-md-2">
                        <div class="icon-holder">
                            <img src="/svgs/authentic.svg" alt="icon"/>
                        </div>
                        <h5> ضبط الجودة
                            النوعية</h5>
                            <p class="text-secondary">
                                نظام مراقبة صارم يشمل
                                كافة العمليات التشخيصية
                                والعلاجية لضمان تطابقها
                                مع المعايير الدولية.
                            </p>
                        </div>
                        <div class="white-card col-md-2">
                            <div class="icon-holder">
                                <img src="/svgs/shield.svg" alt="icon"/>
                            </div>
                            <h5> سلامة المرضى</h5>
                            <p class="text-secondary">
                                تطبيق أعلى معايير مكافحة
                                العدوى والتعقيم
                                وبروتوكولات الأمان
                                السريري داخل مرافقنا.
                            </p>
                        </div>
                        <div class="white-card col-md-2">
                            <div class="icon-holder">
                                <img src="/svgs/board.svg" alt="icon"/>
                            </div>
                            <h5> الطب القائم على
                                البرهان
                            </h5>
                            <p class="text-secondary">
                                استخدام أحدث الأبحاث
                                العلمية الموثقة لاتخاذ
                                القرارات العلاجية الأدق لكل
                                حالة بشكل منفرد.
                            </p>
                        </div>

                    </div>
                </div>
            </section>

            <%-- //TODO last section  --%>
            <%@ include file="shared/footer.jsp" %>
            <script src="./js/bootstrap.min.js"></script>
        </body>
    </html>
