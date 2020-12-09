// Si no hay ningún usuario logueado, ir a la página principal.
if (localStorage.getItem("usuario") === null) {
	window.location.href = "index.html";
}