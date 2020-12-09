// Si el usuario logueado no es un cliente, ir a la página principal.
if (localStorage.getItem("cliente") === null) {
	window.location.href = "index.html";
}