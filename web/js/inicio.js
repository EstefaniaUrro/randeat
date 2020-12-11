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

function performLogin() {
	localStorage.clear();

	let form = document.forms["login"];

	let email = form["email"].value;
	console.log("email: ", email);

	let password = form["password"].value;
	console.log("password: ", password);

	if (email === "" || password === "") {
		alert("Introduce todos los datos, por favor");
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

function performRegistro() {
	let form = document.forms["register"];

	let responseBody = `
	{
		"idUsuario": 0,
		"correoElectronico": "${form["email"].value}",
		"contrasena": "${form["contrasena1"].value}",
		"telefono": "${form["telefono"].value}",
		"poblacion": "Barcelona",
		"direccion": "${form["direccion"].value}",
		"idCliente": 0,
		"usuarioIdUsuario": 0,
		"nombreCompleto": "${form["nombre"].value}",
		"codigoPostalIdCodigoPostal": 1
	}
`;

	console.log(JSON.parse(responseBody));

	const url = `http://localhost:8080/cliente/add/${responseBody}`;

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

			fetchUsuario(form["email"].value, form["contrasena1"].value)
				.then(jsonUsuario => {
					localStorage.setItem("usuario", JSON.stringify(jsonUsuario));

					fetchClienteByIdUsuario(jsonUsuario.idUsuario)
						.then(jsonCliente => {
							localStorage.setItem("cliente", JSON.stringify(jsonCliente));

							goToMainPage();
						})
						.catch(err => {
							alert("error fetch cliente registrado");
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