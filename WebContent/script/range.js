var slider = document.getElementById("myRange");
var all = document.getElementById("all");
slider.oninput = function() {
  if (slider.value != 100&&slider.value != 0) {
    all.innerHTML = "da 0 € a " + this.value + " €";
  }
  else {
    all.innerHTML = "Qualsiasi";
  }
}
