var t;
var slideIndex = 1;

// Next/previous controls
function plusSlides(n) {
  clearTimeout(t);
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  clearTimeout(t);
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  t=setTimeout(plusSlides,4000,1); // Change image every 2 seconds
}

function changePage(pagina){
		$.post("/Gio.Ca-Toys2/prodotti.html", {page: pagina}, function(data, status){
			$("#prod").html(data);
		});
};

$(document).ready(function(){
	$(".aLog").click(function(){
		$("#formLog").fadeToggle();
	});
	$(".serButton2").click(function(){
	    $(".formSearch").fadeToggle();  
	});
	$("#div-mobil").click(function(){
	    $("#div-source").fadeToggle();  
	});4
	$(".collapsed").click(function(){
	    $(".navbar").fadeToggle();  
	});
    $(function(){
      $("footer").load("footer.html"); 
     });

    var staticGifSuffix = ".jpg";
    var gifSuffix = ".gif";
    $(".category").hover(
        function()
        {
            var originalSrc = $(".imgCat",this).attr("src");
            $(".imgCat",this).attr("src", originalSrc.replace(staticGifSuffix, gifSuffix));
        },
        function()
        {
            var originalSrc = $(".imgCat",this).attr("src");
            $(".imgCat",this).attr("src", originalSrc.replace(gifSuffix, staticGifSuffix));  
        }
    );
});
/*
function showLogin(){
  if(form.style.display=="block"){
    form.style.display="none";
  }
  else{
    form.style.display="block";
  }
}

function showSearch(){
  if(formSearch.style.display=="block"){
    formSearch.style.display="none";
  }
  else{
    formSearch.style.display="block";
  }
}
*/
function resize2(){
  var c =  document.getElementById("collapsed");
  var n = document.getElementById("navbar");
  if(window.innerWidth>650){
     c.style.display="none";
     n.style.display="block";
  }else{
    c.style.display="block";
    n.style.display="none";
  }
}
function resize(){
  var s = document.getElementById("div-source");
  var m = document.getElementById("div-mobil");
  var p = document.getElementById("p-mobil");
  var c =  document.getElementById("collapsed");
  var n = document.getElementById("navbar");

  if(window.innerWidth>650){
     	c.style.display="none";
     	n.style.display="block";
     	s.style.display="block";
     	m.style.display="none";
     	n.style.diplay="block";
   }else{
	   p.innerHTML = "Clicca e trova le migliori idee regalo";
	   s.style.display="none";
	   m.style.display="block";
	   c.style.display="block";
	   n.style.display="none";
    }

}

function showAdvSearch(){
  var s =document.getElementById("div-source");
  var p = document.getElementById("p-mobil");
  if(s.style.display=="block"){
    p.innerHTML = "Clicca e trova le migliori idee regalo";
  }
  else{
    p.innerHTML = "Clicca per nascondere"
  }
}