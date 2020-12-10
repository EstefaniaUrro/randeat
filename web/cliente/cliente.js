document.addEventListener('DOMContentLoaded', function (event) {
	loadCodigosPostales();
	loadNombreCliente();
	loadUsuario();
});
function loadCodigosPostales() {
	let selectCodigoPostal = document.getElementById("select-codigo-postal");

	fetch("http://localhost:8080/codigoPostal/getAll")
		.then(response => response.json())
		.then(json => {
			console.log("then init");

			json.map(codigoPostal => {
				console.log("adding codigopostal:");
				console.log(codigoPostal);

				let option = document.createElement("option");
				option.appendChild(document.createTextNode(
					codigoPostal.numero
				));
				option.setAttribute("value", codigoPostal.idCodigoPostal);

				selectCodigoPostal.appendChild(option);

				console.log("added");
			});
		})
		.catch(asd => {
			console.log("catch!!");
		})
		;
}
async function loadNombreCliente() {
	const response = await fetch(
		"http://localhost:8080/cliente/getById/1"
	);
	const json = await response.json();

	let map = json;
	console.log(map);
	document.getElementById("nombre").value = map.nombreCompleto;
	document.getElementById("select-codigo-postal").value = map.codigoPostalIdCodigoPostal;
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
