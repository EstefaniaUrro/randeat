document.addEventListener('DOMContentLoaded', function (event) {
	loadNombreCliente();
	loadUsuario();
});

async function loadNombreCliente() {
	const response = await fetch(
		"http://localhost:8080/cliente/getById/1"
	);
	const json = await response.json();

	let map = json;
	console.log(map);
	document.getElementById("nombre").value = map.nombreCompleto;
	document.getElementById("codigo-postal").value = map.codigoPostalIdCodigoPostal;
}
async function loadUsuario() {
	const response = await fetch(
		"http://localhost:8080/usuario/getById/1"
	);
	const json = await response.json();

	let map = json;
	console.log(map);
	document.getElementById("email").value = map.correoElectronico;
	document.getElementById("telefono").value = map.telefono;
	document.getElementById("direccion").value = map.direccion;
	document.getElementById("poblacion").value = map.poblacion;
}
