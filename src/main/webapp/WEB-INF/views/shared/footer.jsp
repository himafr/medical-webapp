<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<footer>
    <div class="footer-box">
        <div>
            <div class="text-icon">
                <div class="footer-logo-holder">
                    <img src="/images/round_royal_logo.png" width="32" alt="logo" class="logo">
                </div>
                <p class="footer-head-line">
                    عيادة الشفاء
                </p>
            </div>
            <p class="footer-p-text">نلتزم بتقديم رعاية صحية متميزة ترتكز
                على المريض، باستخدام أحدث
                التقنيات الطبية وفريق من الخبراء
                المتخصصين ذوي الخبرات العالمية.</p>
<%-- //TODO link the media --%>

                <div class="footer-media">

                    <div class="icon-holder">
                        <img src="/svgs/facebook.svg" />
                    </div>

                    <div class="icon-holder">
                        <img src="/svgs/twitter.svg" />
                    </div>

                    <div class="icon-holder">
                        <img src="/svgs/box.svg" />
                    </div>

                </div>

            </div>

            <div>

                <div>
                    <p class="footer-head-line">
                        عيادة الشفاء
                    </p>
                    <span class="footer-hr"></span>
                </div>


                <div class="text-icon">
                    <img src="/svgs/location.svg" alt="logo" width="16" >
                    <p class="footer-p-text">
                        الرياض، شارع الملك فهد،
                        مبنى النخبة الطبي، الدور
                        الثالث
                    </p>
                </div>
                <div class="text-icon">
                    <img src="/svgs/call.svg" alt="icon" width="16" >
                    <p class="footer-p-text" style="direction:ltr;">
                        +20 114 683 9894
                    </p>
                </div>
                <div class="text-icon">
                    <img src="/svgs/mail.svg" alt="logo" width="16" >
                    <p class="footer-p-text">
                        hema@abusallem.com
                    </p>
                </div>

            </div>




            <div class="footer-border-bottom-effect">
                <div>
                    <p class="footer-head-line">
                        ساعات العمل
                    </p>
                    <span class="footer-hr"></span>
                </div>


                <div class="footer-text-text">
                    <p class="footer-p-text">
                        السبت - الخميس
                    </p>
                    <p class="footer-p-text-gold">
                        9:00 ص - 9:00 م
                    </p>
                </div>
                <div class="footer-text-text">
                    <p class="footer-p-text">
                        الجمعه
                    </p>
                    <p class="footer-p-text-gold">
                        9:00 م - 4:00 م
                    </p>
                </div>
                <div class="footer-text-text">
                    <p class="footer-p-text">
                        الطوارئ
                    </p>
                    <p class="footer-p-text-icon">
                        24 ساعة
                    </p>
                </div>


            </div>
            <div class="footer-map">
            <a href="https://maps.app.goo.gl/W4H2F3D5N2QLAtVa8" target="_blank">
                <img src="/images/map.png"  />
            </a>
            </div>

        </div>
        
        <div class="footer-tail">
            <p class="footer-p-text">&copy;  <%= java.time.Year.now().getValue() %> عيادة الشفاء. جميع الحقوق محفوظة.</p>

            <div>
                <p class="footer-p-text">
                    <a href="#">
                        سياسة الخصوصية
                    </a>
                </p>
                <p class="footer-p-text">
                    <a href="#">
                        الشروط والأحكام
                    </a>
                </p>
            </div>

        </div>
    </footer>