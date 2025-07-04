document.addEventListener("DOMContentLoaded", function () {
  const flash = document.getElementById("flash-data");
  if (flash) {
    const jumlah = flash.dataset.jumlah;

    Swal.fire({
      title: "Anggaran Ditambahkan!",
      text: "Sebesar Rp " + new Intl.NumberFormat('id-ID').format(jumlah),
      icon: "success",
      confirmButtonText: "Oke",
    });
  }
});
