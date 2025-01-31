document.addEventListener("DOMContentLoaded", () => {
  const tabButtons = document.querySelectorAll(".tab-btn");
  const tabPanes = document.querySelectorAll(".tab-pane");

  tabButtons.forEach((button) => {
    button.addEventListener("click", () => {
      tabButtons.forEach((btn) => {
        btn.classList.remove("bg-[#3D5CB8]", "text-white");
        btn.classList.add("text-gray-600");
      });
      tabPanes.forEach((pane) => pane.classList.add("hidden"));

      button.classList.add("bg-[#3D5CB8]", "text-white");
      const tabId = button.getAttribute("data-tab");
      document.getElementById(tabId).classList.remove("hidden");
    });
  });
});
