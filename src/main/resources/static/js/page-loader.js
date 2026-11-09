const pageLoader = document.querySelector(".page-loader");

if (pageLoader) {
  const hidePageLoader = () => {
    pageLoader.classList.add("page-loader--hidden");
    pageLoader.setAttribute("aria-hidden", "true");
  };

  if (document.readyState === "complete") {
    hidePageLoader();
  } else {
    window.addEventListener("load", hidePageLoader, { once: true });
  }
}
