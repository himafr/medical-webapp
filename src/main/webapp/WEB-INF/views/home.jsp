<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ar" dir="rtl" >
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home </title>
        <link rel="icon" type="image/svg+xml" href="/images/round_royal_logo.png">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="./css/home.css">
        <link rel="stylesheet" href="./responsive.css">
    </head>
    <body >
        <div class="container">
            <%@ include file="shared/header.jsp" %>
            <%-- hero section --%>
            <section class="hero">
                <div class="row align-items-center">
                    <div class="col-md-6 right-side">
                        <h1 class="display-4">رعاية طبية
                            متكاملة
                            <span>
                                باحترافية وثقة
                            </span>

                        </h1>
                        <p class="lead text-secondary">نقدم أفضل الخدمات الطبية بأحدث التقنيات لضمان صحتكم
                            وسلامتكم. فريقنا الطبي المتخصص جاهز لخدمتكم على مدار
                            الساعة لضمان راحة بالكم.
                        </p>
                        <div class="hero-btns">
                            <a href="/book-appointment" class="btn btn-primary btn-lg">احجز موعدك الان </a>
                            <a href="/about" class="btn btn-secondary btn-lg"> تعرف علينا </a>
                        </div>

                        <div class="hero-rate">
                            <div class="avatar-group">
                                <img src="/images/Doctor avatar.png" class="avatar" />
                                <img src="/images/Doctor avatar2.png" class="avatar" />
                                <img src="/images/Doctor avatar3.png" class="avatar" />
                            </div>
                            <div class="rating">
                                <span>
                                    <img src="/svgs/star.svg" alt="icon"/>
                                    <img src="/svgs/star.svg" alt="icon"/>
                                    <img src="/svgs/star.svg" alt="icon"/>
                                    <img src="/svgs/star.svg" alt="icon"/>
                                    <img src="/svgs/star.svg" alt="icon"/>
                                </span>

                                <div class="text-primary" >أكثر من 5000 مريض سعيد</div>
                            </div>


                        </div>

                    </div>

                    <div class="col-md-6">
                        <img src="/images/Modern clinic interior.png"/ class="hero-image" >
                    </div>
                </div>
            </section>

            <section>
                <div class="row align-items-center">
                    <div class="col-md-12" style="text-align: center;">
                        <h2 class="text-primary"> لماذا تختار عيادتنا؟
                        </h2>
                        <p class="lead text-secondary">
                            نتميز بتقديم خدمات طبية عالية الجودة مع التركيز على راحة المريض ودقة التشخيص.
                        </p>
                    </div>

                    <div class="row home-features" style="justify-content:space-around; gap:16px">
                       
                        <div class="col-md-2">
                            <div class="card" >
                                <div class="card-body">
                                    <div class="header-logo-holder">
                                        <img src="./svgs/logo.svg" alt="logo" class="logo">
                                    </div>
                                    <h5 class="card-title text-primary">أحدث المعدات
                                        الطبية
                                    </h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">نستخدم أحدث التقنيات
                                        وأفضل الأجهزة لضمان
                                        التشخيص والعلاج الدقيق
                                        والسريع.
                                    </h6>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-2 ">
                            <div class="card" >
                                <div class="card-body">
                                    <div class="header-logo-holder">
                                        <img src="./svgs/logo.svg" alt="logo" class="logo">
                                    </div>
                                    <h5 class="card-title text-primary">فريق طبي متخصص</h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">أطباء استشاريون ذوي خبرة
                                        عالية وشهادات معتمدة
                                        في مختلف التخصصات
                                        الطبية.
                                    </h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="card" >
                                <div class="card-body">
                                    <div class="header-logo-holder">
                                        <img src="./svgs/logo.svg" alt="logo" class="logo">
                                    </div>
                                    <h5 class="card-title text-primary">متابعة دقيقة للحالات</h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">نظام ملفات إلكتروني
                                        متكامل لمتابعة تاريخك
                                        المرضي وتطورات حالتك
                                        بدقة متناهية.
                                    </h6>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="card" >
                                <div class="card-body">
                                    <div class="header-logo-holder">
                                        <img src="./svgs/logo.svg" alt="logo" class="logo">
                                    </div>
                                    <h5 class="card-title text-primary">تذكير تلقائي بالمواعيد</h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">نرسل رسائل تذكيرية نصية
                                        وعبر التطبيق لضمان عدم
                                        تفويت مواعيدك المهمة.
                                    </h6>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>


            <section class="bg-gray full-width-section home-services-section">
                <div class="container">
                    <div class="row align-items-center home-services">

                        <div class="col-md-12 home-services-head" >
                            <h2 class="text-primary head-line-right-border">
                                خدماتنا الطبية
                            </h2>
                            <div class="sub-header-holder">
                                <p class="lead text-secondary">
                                    نغطي كافة احتياجاتك الصحية تحت سقف واحد
                                </p>
                                <a href="/services" class="btn-link">
                                    عرض جميع الخدمات
                                    <img src="/svgs/arrow-left-short.svg" alt="icon" />
                                </a>
                            </div>
                        </div>

                        <div class="row" style="justify-content:space-around;gap:16px">
                            <div class="col-md-2">
                                <div class="card" style="width: 18rem;">
                                    <div class="text-in-img">
                                        <img src="/images/Doctor consultation.png" class="card-img-top" alt="image">
                                        <div class="overlay"></div>
                                        <h5 class="card-title">
                                            <img src="/svgs/doc1.svg" alt="icon" style="margin: 10px;"/>
                                            الاستشارات الطبية
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text text-secondary">احصل على تشخيص دقيق وخطة
                                            علاجية مخصصة لحالتك من قبل
                                            نخبة من الاستشاريين في كا...
                                        </p>
                                        <%-- //TODO PUT THE URLS --%>
                                        <a href="/services/search" class="btn-link">التفاصيل
                                            <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card" style="width: 18rem;">
                                    <div class="text-in-img">
                                        <img src="/images/Medical lab test.png" class="card-img-top" alt="image">
                                        <div class="overlay"></div>
                                        <h5 class="card-title">
                                            <img src="/svgs/doc2.svg" alt="icon" style="margin: 10px;"/>
                                            الإجراءات الطبية
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text text-secondary">
                                            خدمات التحاليل المخبرية
                                            والأشعة والإجراءات البسيطة
                                            التي تتم داخل العيادة بأعلى...
                                        </p>
                                        <a href="#" class="btn-link">التفاصيل
                                            <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card" style="width: 18rem;">
                                    <div class="text-in-img">
                                        <img src="/images/Periodic checkup.png" class="card-img-top" alt="image">
                                        <div class="overlay"></div>
                                        <h5 class="card-title">
                                            <img src="/svgs/doc3.svg" alt="icon" style="margin: 10px;"/>
                                            المتابعة الدورية
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text text-secondary">
                                            برامج فحص دوري شاملة
                                            للأفراد والعائلات للتأكد من
                                            الصحة العامة والكشف المبكر
                                        </p>
                                        <a href="#" class="btn-link">التفاصيل
                                            <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card" style="width: 18rem;">
                                    <div class="text-in-img">
                                        <img src="/images/Rehabilitation therapy.png" class="card-img-top" alt="image">
                                        <div class="overlay"></div>
                                        <h5 class="card-title">
                                            <img src="/svgs/doc4.svg" alt="icon" style="margin: 10px;"/>
                                            برامج العلاج
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text text-secondary">
                                            خطط علاجية طويلة وقصيرة
                                            المدى، تشمل العلاج الطبيعي
                                            والتأهيلي تحت إشراف
                                        </p>
                                        <a href="#" class="btn-link">
                                            التفاصيل
                                            <img src="/svgs/arrow-left-long.svg" alt="icon" />
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
            <%-- testimonials --%>
            <section class="text-center testimonial ">
                <h5 class="text-gold">آراء المرضى</h5>
                <h2 class="text-primary">قصص نجاح مرضانا</h2>
                <div class="row justify-content-around">
                    <div class="col-md-4 mb-3">
                        <div class="card" >
                            <img src="/svgs/66.svg" alt="icon" class="icon-to-left" />
                            <div class="card-body">
                                <div style="display: flex;gap:16px;">
                                    <div class="avatar">
                                        <img src="./images/user1.png"  class="user photo">
                                    </div>
                                    <div class="rating">
                                        <div class="text-primary" >أحمد المحمدي</div>
                                        <span>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                        </span>

                                    </div>
                                </div>
                                <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">نظام ملفات إلكتروني
                                    "تجربة ممتازة جداً. الطاقم الطبي
                                    محترف والتعامل راقي. والأهم من
                                    ذلك دقة المواعيد والنظافة العامة
                                    للعيادة. أنصح بها بشدة."
                                </h6>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card" >
                            <img src="/svgs/66.svg" alt="icon" class="icon-to-left" />
                            <div class="card-body">
                                <div style="display: flex;gap:16px;">
                                    <div class="avatar">
                                        <img src="./images/user1.png"  class="user photo">
                                    </div>
                                    <div class="rating">
                                        <div class="text-primary" >أحمد المحمدي</div>
                                        <span>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                        </span>

                                    </div>
                                </div>
                                <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">نظام ملفات إلكتروني
                                    "تجربة ممتازة جداً. الطاقم الطبي
                                    محترف والتعامل راقي. والأهم من
                                    ذلك دقة المواعيد والنظافة العامة
                                    للعيادة. أنصح بها بشدة."
                                </h6>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="card" >
                            <img src="/svgs/66.svg" alt="icon" class="icon-to-left" />
                            <div class="card-body">
                                <div style="display: flex;gap:16px;">
                                    <div class="avatar">
                                        <img src="./images/user1.png"  class="user photo">
                                    </div>
                                    <div class="rating">
                                        <div class="text-primary" >أحمد المحمدي</div>
                                        <span>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                            <img src="/svgs/star.svg" alt="icon"/>
                                        </span>

                                    </div>
                                </div>
                                <h6 class="card-subtitle mb-2 text-body-secondary text-secondary">نظام ملفات إلكتروني
                                    "تجربة ممتازة جداً. الطاقم الطبي
                                    محترف والتعامل راقي. والأهم من
                                    ذلك دقة المواعيد والنظافة العامة
                                    للعيادة. أنصح بها بشدة."
                                </h6>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section>
                <div class="green-card row align-items-center justify-content-between text-center">
                    <h2 class="mb-2 mb-md-5">
                        تواصل معناابدأ رحلتك العلاجية مع نخبة من
                        الاستشاريين اليوم
                    </h2>
                    <p>
                        صحتك هي استثمارك الأهم. لا تتردد في التواصل معنا للحصول على استشارة فورية أو حجز
                        موعد يناسبك.
                    </p>

                <div class="row flex justify-content-around mt-md-5" >
                    <a href="/book-appointment" class="btn btn-gold btn-lg text-gold col-5" style="border-radius: 12px; line-height: normal;"> احجز موعدك الآن</a>
                    <a href="tel:+201146839894" dir="ltr" class="btn btn-secondary btn-lg text-white col-5" style="background-color: inherit;">
             +201146839894 
                    <img src="/svgs/call.svg" alt="icon" style="margin: 10px;"/>
             </a>
                </div>
                </div>
            </section>
        </div>
        <%@ include file="shared/footer.jsp" %>
        <script src="./js/bootstrap.min.js"></script>
    </body>
</html>
