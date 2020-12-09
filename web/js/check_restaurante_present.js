// Si el usuario logueado no es un restaurante, ir a la página principal.
if (localStorage.getItem("restaurante") === null) {
	window.location.href = "index.html";
}