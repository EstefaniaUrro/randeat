const usuarioUrl = "http://localhost:8080/usuario/getByCredentials";
const restauranteUrl = "http://localhost:8080/restaurante/getByIdUsuario";
const clienteUrl = "http://localhost:8080/cliente/getByIdUsuario";

function goToMainPage() {
	window.location.href = "index.html";
}

async function fetchUsuario(email, password) {
	const response = await fetch(`${usuarioUrl}?email=${email}&password=${password}`);
	return await response.json();
}

async function fetchClienteByIdUsuario(idUsuario) {
	const response = await fetch(`${clienteUrl}/${idUsuario}`);
	return await response.json();
}

async function fetchRestauranteByIdUsuario(idUsuario) {
	const response = await fetch(`${restauranteUrl}/${idUsuario}`);
	return await response.json();
}


function showRestaurantForm() {
	let restaurantForms = document.getElementsByClassName("restaurant-form");

	for (let i = 0; i < restaurantForms.length; i += 1) {
		let restaurantForm = restaurantForms.item(i);

		restaurantForm.getElementsByClassName("restaurant-input")[0].toggleAttribute("required");

		restaurantForm.classList.toggle("d-none");
	}
}


function performLogin() {
	localStorage.clear();

	let form = document.forms["login"];

	let email = form.email.value;
	console.log("email: ", email);

	let password = form.password.value;
	console.log("password: ", password);

	if (email === "" || password === "") {
		alert("Introduce todos los datos, por favor");
		return;
	} else {
		fetchUsuario(email, password).then(jsonUsuario => {
			if (jsonUsuario === null) {
				alert("Correo electrónico o contraseña incorrectos");
			} else {
				console.log("found usuario: ", jsonUsuario);
				localStorage.setItem(
					"usuario",
					JSON.stringify(jsonUsuario)
				);

				console.log(localStorage);

				alert("Usuario logueado correctamente, voy a ver si es un cliente o un restaurante");

				// Mirar si es Cliente o Restaurante.
				fetchRestauranteByIdUsuario(jsonUsuario.idUsuario)
					.then(jsonRestaurante => {
						if (jsonRestaurante === null) {
							alert("no es un restaurante, voy a ver si es un cliente");

							fetchClienteByIdUsuario(jsonUsuario.idUsuario)
								.then(jsonCliente => {
									if (jsonCliente === null) {
										alert("tampoco es un cliente? algo va mal");
									} else {
										alert("es un cliente!!!!! guardando datos");

										localStorage.setItem(
											"cliente",
											JSON.stringify(jsonCliente)
										);

										goToMainPage();
									}
								})
								.catch(err => {
									console.log("Error en fetch de cliente: ", err);
								})
							;
						} else {
							alert("es un restaurante!!! guardando datos");

							localStorage.setItem(
								"restaurante",
								JSON.stringify(jsonRestaurante)
							);

							goToMainPage();
						}
					})
					.catch(err => {
						console.log("Error en fetch de restaurante: ", err)
					})
				;
			}
		})
		.catch(err => {
			console.log("Error en fetch de usuario: ", err);
		});
	}
}


function performRegistroCliente(form, jsonString) {
	let email = form.email.value;
	let password = form.contrasena1.value;

	jsonString += `
			"nombreCompleto": "${form["nombre"].value}"
		}
	`;

	console.log(JSON.parse(jsonString));

	const url = `http://localhost:8080/cliente/add/${jsonString}`;

	const options = {
		"method": "GET",
		// "body": responseBody,
		"headers": {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	};

	fetch(url, options)
		.then(response => response.json())
		.then(json => {
			console.log(json);

			alert("usuario+cliente registrado correctamente");

			fetchUsuario(email, password)
				.then(jsonUsuario => {
					localStorage.setItem(
						"usuario",
						JSON.stringify(jsonUsuario)
					);

					fetchClienteByIdUsuario(jsonUsuario.idUsuario)
						.then(jsonCliente => {
							localStorage.setItem(
								"cliente",
								JSON.stringify(jsonCliente)
							);

							goToMainPage();
						})
						.catch(err => {
							alert("error fetch cliente registrado");
						})
					;
				})
				.catch(err => {
					console.log("err", err);
					alert("error fetch usuario registrado");
				})
			;
		})
		.catch(err => {
			console.log("err: ", err);
			alert("Error durante el proceso de registro, revisa los datos y vuelve a intentarlo.");
		})
	;
}

function performRegistroRestaurante(form, jsonString) {
	let email = form.email.value;
	let password = form.contrasena1.value;

	// TODO codigo postal tipo entrega tipo cocina población
	jsonString += `
			"nombreRestaurante": "${form["nombre"].value}",
			"cif": "${form["cif"].value}",
			"iban": "${form["iban"].value}",
			"nombrePropietario": "${form["nombre-propietario"].value}"
		}
	`;

	console.log(JSON.parse(jsonString));

	const url = `http://localhost:8080/restaurante/add/${jsonString}`;

	const options = {
		"method": "GET",
		// "body": responseBody,
		"headers": {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	};

	fetch(url, options)
		.then(response => response.json())
		.then(json => {
			console.log(json);

			alert("usuario+restaurante registrado correctamente");

			fetchUsuario(email, password)
				.then(jsonUsuario => {
					localStorage.setItem(
						"usuario",
						JSON.stringify(jsonUsuario)
					);

					fetchRestauranteByIdUsuario(jsonUsuario.idUsuario)
						.then(jsonRestaurante => {
							console.log("then restaurante: ", jsonRestaurante);
							localStorage.setItem(
								"restaurante",
								JSON.stringify(jsonRestaurante)
							);

							goToMainPage();
						})
						.catch(err => {
							alert("error fetch restaurante registrado");
						})
					;
				})
				.catch(err => {
					alert("error fetch usuario registrado");
				})
			;
		})
		.catch(err => {
			console.log("err: ", err);
			alert("Error durante el proceso de registro, revisa los datos y vuelve a intentarlo.");
		})
	;
}

function performRegistro() {
	console.log(document.forms);
	console.log(document.forms["registro"]);
	console.log(document.forms["registro"].email.value);
	let form = document.forms["registro"];

	let email = form.email.value;
	let password = form.contrasena1.value;

	let restauranteCheckbox = form["restaurante"];

	console.log("idcopo", form["codigo-postal"].value);

	let jsonString  = `
		{
			"correoElectronico": "${email}",
			"contrasena": "${password}",
			"telefono": "${form["telefono"].value}",
			"poblacion": "${form["poblacion"].value}",
			"direccion": "${form["direccion"].value}",
			"idCodigoPostal": "${form["codigo-postal"].value}",
	`;

	if (restauranteCheckbox !== undefined && restauranteCheckbox.checked) {
		alert('checked');

		performRegistroRestaurante(form, jsonString);
	} else {
		alert('else');

		performRegistroCliente(form, jsonString);
	}
}


document.addEventListener('DOMContentLoaded', function (event) {
	fillSelectCodigoPostal(null);
});