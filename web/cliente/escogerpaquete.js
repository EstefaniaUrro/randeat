// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

$("#paquetegrande").val('0');

var contador = parseInt($("input#paquetegrande").val());

//configurar el boton de resta
$("button#resta").click(function () {
  if (contador != 0) {
    $("input#paquetegrande").val(contador - 1);
    contador = parseInt($("input#paquetegrande").val())
  } else {
    contador = parseInt($("input#paquetegrande").val())
  }
});

//configurar el boton de suma
$("button#suma").click(function () {
  if (contador != 10) {
    $("input#paquetegrande").val(contador + 1);
    contador = parseInt($("input#paquetegrande").val())
  } else {
    contador = parseInt($("input#paquetegrande").val())
  }
});

$("#paquetemediano").val('0');

var contador1 = parseInt($("input#paquetemediano").val());

//configurar el boton de resta
$("button#resta1").click(function () {
  if (contador1 != 0) {
    $("input#paquetemediano").val(contador1 - 1);
    contador1 = parseInt($("input#paquetemediano").val())
  } else {
    contador1 = parseInt($("input#paquetemediano").val())
  }
});

  //configurar el boton de suma
  $("button#suma1").click(function () {
    if (contador1 != 10) {
      $("input#paquetemediano").val(contador1 + 1);
      contador1 = parseInt($("input#paquetemediano").val())
    } else {
      contador1 = parseInt($("input#paquetemediano").val())
    }
  });

$("#paquetepequeño").val('0');
var contador2 = parseInt($("input#paquetepequeño").val());

//configurar el boton de resta
$("button#resta2").click(function () {
  if (contador2 != 0) {
    $("input#paquetepequeño").val(contador2 - 1);
    contador2 = parseInt($("input#paquetepequeño").val())
  } else {
    contador2 = parseInt($("input#paquetepequeño").val())
  }
});

//configurar el boton de suma
$("button#suma2").click(function () {
  if (contador2 != 10) {
    $("input#paquetepequeño").val(contador2 + 1);
    contador2 = parseInt($("input#paquetepequeño").val())
  } else {
    contador2 = parseInt($("input#paquetepequeño").val())
  }
});

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function () {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}