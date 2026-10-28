const doctorSelect = document.getElementById("doctor_id");
const serviceSelect = document.getElementById("service_id");

const urlParams = new URLSearchParams(window.location.search);
const preDoctorId = urlParams.get("doctorId");
const preServiceId = urlParams.get("serviceId");
const preSpecialization = urlParams.get("specialization");

document.addEventListener("DOMContentLoaded", function () {
  if (preSpecialization) {
    const specSelect = document.getElementById("specialization");
    specSelect.value = preSpecialization;
    handleSpecializationChange.call(specSelect);
  }
});

document
  .getElementById("specialization")
  .addEventListener("change", handleSpecializationChange);

function handleSpecializationChange() {
  const specialization = this.value;

  doctorSelect.innerHTML = "<option>اختر الطبيب المفضل</option>";
  serviceSelect.innerHTML = "<option>اختر الخدمة المطلوبة</option>";
  document.getElementById("appointmentDate").disabled = true;
  document.getElementById("time-slots-container").style.display = "none";

  if (specialization === "اختر القسم المطلوب") return;

  doctorSelect.innerHTML = "<option>جاري التحميل...</option>";
  doctorSelect.disabled = true;
  serviceSelect.innerHTML = "<option>جاري التحميل...</option>";
  serviceSelect.disabled = true;

  fetch("/api/doctors?specialization=" + specialization)
    .then((r) => r.json())
    .then((r) => r.data.content)
    .then((doctors) => {
      doctorSelect.innerHTML = "<option>اختر الطبيب المفضل</option>";
      if (doctors.length === 0) {
        doctorSelect.innerHTML = "<option>لا يوجد أطباء في هذا التخصص</option>";
        serviceSelect.innerHTML =
          "<option>لا يوجد طبيب لهذه الخدمة الان</option>";
        serviceSelect.disabled = false;
      } else {
        doctors.forEach((d) => {
          const opt = document.createElement("option");
          opt.value = d.id;
          opt.textContent = d.fullName;
          doctorSelect.appendChild(opt);
        });

        if (preDoctorId) {
          doctorSelect.value = preDoctorId;
          doctorSelect.dispatchEvent(new Event("change"));
        }

        fetchServices(specialization);
      }
    })
    .catch(() => {
      doctorSelect.innerHTML = "<option>حدث خطأ، حاول مرة أخرى</option>";
      serviceSelect.innerHTML = "<option>حدث خطأ، حاول مرة أخرى</option>";
    })
    .finally(() => {
      doctorSelect.disabled = false;
    });
}

function fetchServices(specialization) {
  fetch("/api/services?specialization=" + specialization)
    .then((res) => res.json())
    .then((r) => r.data.content)
    .then((services) => {
      serviceSelect.innerHTML = "<option>اختر الخدمة المطلوبة</option>";
      if (services.length === 0) {
        serviceSelect.innerHTML =
          "<option>لا يوجد خدمات في هذا التخصص</option>";
        return;
      }
      services.forEach((service) => {
        const option = document.createElement("option");
        option.value = service.id;
        option.textContent =
          service.name +
          " - " +
          (service.consulating
            ? "استشاره " + service.consulating + " ر.س"
            : service.startFrom
              ? "يبدا من " + service.startFrom + " ر.س"
              : service.price
                ? "سعر الكشف " + service.price + " ر.س"
                : "");
        serviceSelect.appendChild(option);
      });

      if (preServiceId) {
        serviceSelect.value = preServiceId;
        serviceSelect.dispatchEvent(new Event("change"));
      }
    })
    .catch(() => {
      serviceSelect.innerHTML = "<option>حدث خطأ، حاول مرة أخرى</option>";
    })
    .finally(() => (serviceSelect.disabled = false));
}

let bookedDates = [];

function checkEnableDatePicker() {
  const doctorId = doctorSelect.value;
  const serviceId = serviceSelect.value;

  const dateInput = document.getElementById("appointmentDate");

  // لازم الاتنين يكونوا محددين صح
  if (
    !doctorId ||
    !serviceId ||
    doctorId === "اختر الطبيب المفضل" ||
    doctorId === "جاري التحميل..." ||
    serviceId === "اختر الخدمة المطلوبة" ||
    serviceId === "جاري التحميل..."
  ) {
    dateInput.disabled = true;
    dateInput.value = "";
    document.getElementById("time-slots-container").style.display = "none";
    return;
  }

  const today = new Date().toISOString().split("T")[0];
  dateInput.min = today;
  dateInput.disabled = false;

  fetch("/api/appointments/booked-dates?doctorId=" + doctorId)
    .then((r) => r.json())
    .then((d) => d.data)
    .then((dates) => {
      bookedDates = dates;
    });
}

doctorSelect.addEventListener("change", checkEnableDatePicker);
serviceSelect.addEventListener("change", checkEnableDatePicker);

document
  .getElementById("appointmentDate")
  .addEventListener("change", function () {
    const selected = this.value;
    const doctorId = doctorSelect.value;
    const slotsContainer = document.getElementById("time-slots-container");

    slotsContainer.style.display = "none";
    document.querySelectorAll(".time-slot").forEach((s) => {
      s.classList.remove("time-slot-disabled");
      s.querySelector("input[type='radio']").checked = false;
    });

    if (bookedDates.includes(selected)) {
      this.setCustomValidity("هذا اليوم محجوز بالكامل، اختر يوم آخر");
      this.reportValidity();
      this.value = "";
      return;
    }

    this.setCustomValidity("");

    fetch(
      "/api/appointments/available-slots?doctorId=" +
        doctorId +
        "&date=" +
        selected,
    )
      .then((r) => r.json())
      .then((d) => d.data)
      .then((bookedSlots) => {
        slotsContainer.style.display = "block";
        document.querySelectorAll(".time-slot").forEach((slot) => {
          const value = slot.querySelector("input[type='radio']").value;
          console.log(value)
          if (bookedSlots.includes(value)) {
            slot.classList.add("time-slot-disabled");
          }
        });
      });
  });

document.querySelectorAll(".time-slot").forEach((slot) => {
  slot.addEventListener("click", function () {
    if (this.classList.contains("time-slot-disabled")) return;
    document
      .querySelectorAll(".time-slot")
      .forEach((s) => s.classList.remove("time-slot-active"));
    this.classList.add("time-slot-active");
    this.querySelector("input[type='radio']").checked = true;
  });
});
