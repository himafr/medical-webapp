(function () {
    const toastTypes = new Set(['success', 'error', 'warning', 'info']);

    function showToast(message, type) {
        const text = typeof message === 'string' ? message.trim() : '';
        const normalizedType = toastTypes.has(type) ? type : 'info';
        if (!text) {
            return null;
        }

        if (typeof Toastify === 'function') {
            const toast = Toastify({
                text,
                duration: 3000,
                close: true,
                gravity: 'top',
                position: 'right',
                stopOnFocus: true,
                escapeMarkup: true,
                ariaLive: normalizedType === 'error' ? 'assertive' : 'polite',
                className: `app-toast app-toast--${normalizedType}`,
                offset: { x: 16, y: 16 }
            }).showToast();
            toast.toastElement.setAttribute('role', normalizedType === 'error' ? 'alert' : 'status');
            toast.toastElement.setAttribute('dir', 'rtl');
            return toast;
        }

        const fallback = document.createElement('div');
        fallback.className = `app-toast-fallback app-toast-fallback--${normalizedType}`;
        fallback.setAttribute('role', normalizedType === 'error' ? 'alert' : 'status');
        fallback.textContent = text;
        document.body.appendChild(fallback);
        window.setTimeout(() => fallback.remove(), 4500);
        return fallback;
    }

    window.showToast = showToast;
    window.showSuccessToast = (message) => showToast(message, 'success');
    window.showErrorToast = (message) => showToast(message, 'error');
    window.showWarningToast = (message) => showToast(message, 'warning');
    window.showInfoToast = (message) => showToast(message, 'info');
    window.AppToast = {
        show: showToast,
        success: window.showSuccessToast,
        error: window.showErrorToast,
        warning: window.showWarningToast,
        info: window.showInfoToast,
        handleSuccess: window.showSuccessToast,
        handleFail: window.showErrorToast
    };

    document.addEventListener('DOMContentLoaded', () => {
        const serverMessageSources = document.querySelectorAll('[data-toast-message], [data-toast-source]');
        serverMessageSources.forEach((source) => {
            const toast = showToast(source.textContent, source.dataset.toastType || 'info');
            if (toast && typeof Toastify === 'function') {
                source.hidden = true;
            }
        });

        const params = new URLSearchParams(window.location.search);
        if (serverMessageSources.length === 0 && params.has('error')) {
            showToast('البريد الإلكتروني أو كلمة المرور غير صحيحة', 'error');
        } else if (serverMessageSources.length === 0 && params.has('logout')) {
            showToast('تم تسجيل الخروج بنجاح', 'success');
        } else if (serverMessageSources.length === 0 && params.has('activated')) {
            showToast('تم تفعيل حسابك بنجاح', 'success');
        }
    });
})();
