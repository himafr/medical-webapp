document.addEventListener('DOMContentLoaded', () => {
    if (typeof gsap === 'undefined' || typeof ScrollTrigger === 'undefined') {
        return;
    }

    gsap.registerPlugin(ScrollTrigger);

    const motionPreference = window.matchMedia('(prefers-reduced-motion: reduce)');
    if (motionPreference.matches) {
        return;
    }

    const introItems = gsap.utils.toArray('.hero .right-side > *');
    if (introItems.length) {
        gsap.from(introItems, {
            autoAlpha: 0,
            y: 28,
            duration: 0.8,
            stagger: 0.12,
            ease: 'power3.out',
            delay: 0.12,
            clearProps: 'all'
        });
    }

    const heroImage = document.querySelector('.hero .hero-image');
    if (heroImage) {
        gsap.fromTo(heroImage,
            { autoAlpha: 0, y: 24, scale: 0.97 },
            { autoAlpha: 1, y: 0, scale: 1, duration: 1, delay: 0.2, ease: 'power3.out' }
        );

        gsap.to(heroImage, {
            y: -10,
            duration: 3.2,
            repeat: -1,
            yoyo: true,
            ease: 'sine.inOut',
            delay: 1.2
        });

        gsap.to(heroImage, {
            scale: 1.035,
            ease: 'none',
            scrollTrigger: {
                trigger: heroImage,
                start: 'top top',
                end: 'bottom top',
                scrub: 0.8
            }
        });
    }

    const revealItems = gsap.utils.toArray([
        'section h1',
        'section h2',
        'section h3',
        'section .lead',
        'section .card',
        'section .card-doctor',
        'section .white-card',
        'section .gray-card',
        'section .green-card',
        'section .brief-card',
        'section .whats-card',
        'section .site-map',
        'section form',
        'section .doctor-details',
        'section .profile-header',
        'section .section-buttons',
        'section .feature-icon',
        'section .icon-holder',
        'section .hero-rate',
        'section .avatar-group',
        'section .rating',
        '.sidebar-item',
        '.content > .card',
        'table tbody tr',
        'footer .footer-box > div',
        'footer .footer-tail'
    ].join(',')).filter((item) => !item.closest('.hero'));

    if (revealItems.length) {
        ScrollTrigger.batch(revealItems, {
            start: 'top 88%',
            once: true,
            batchMax: 5,
            onEnter: (items) => {
                gsap.fromTo(items,
                    { autoAlpha: 0, y: 32, scale: 0.98 },
                    {
                        autoAlpha: 1,
                        y: 0,
                        scale: 1,
                        duration: 0.8,
                        stagger: { each: 0.1, from: 'start' },
                        ease: 'power3.out',
                        overwrite: true,
                        clearProps: 'transform,opacity,visibility'
                    }
                );
            }
        });
    }

    if (window.matchMedia('(hover: hover) and (pointer: fine)').matches) {
        gsap.utils.toArray('.card, .card-doctor, .white-card, .gray-card, .brief-card, .feature-icon')
            .forEach((card) => {
                card.addEventListener('pointermove', (event) => {
                    const bounds = card.getBoundingClientRect();
                    const x = (event.clientX - bounds.left) / bounds.width - 0.5;
                    const y = (event.clientY - bounds.top) / bounds.height - 0.5;

                    gsap.to(card, {
                        rotateX: y * -4,
                        rotateY: x * 4,
                        y: -5,
                        transformPerspective: 900,
                        duration: 0.35,
                        ease: 'power2.out',
                        overwrite: 'auto'
                    });
                });

                card.addEventListener('pointerleave', () => {
                    gsap.to(card, {
                        rotateX: 0,
                        rotateY: 0,
                        y: 0,
                        duration: 0.55,
                        ease: 'elastic.out(1, 0.55)',
                        overwrite: 'auto'
                    });
                });
            });
    }

    ScrollTrigger.refresh();
});
