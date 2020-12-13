document.addEventListener('DOMContentLoaded', function (event) {
	loadClienteInfo();
});

function loadClienteInfo() {
	let usuario = JSON.parse(localStorage.getItem("usuario"));
	let cliente = JSON.parse(localStorage.getItem("cliente"));

	document.getElementById("nombre").value = cliente.nombreCompleto;
	document.getElementById("email").value = usuario.correoElectronico;
	document.getElementById("telefono").value = usuario.telefono;
	document.getElementById("poblacion").value = usuario.poblacion;
	document.getElementById("direccion").value = usuario.direccion;

	fillSelectCodigoPostal(cliente.codigoPostalIdCodigoPostal);
}