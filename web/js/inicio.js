const validateUrl = "http://localhost:8080/usuario/getByCredentials";
const restauranteUrl = "http://localhost:8080/restaurante/getByIdUsuario";
const clienteUrl = "http://localhost:8080/cliente/getByIdUsuario";

function goToMainPage() {
	window.location.href = "index.html";
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
		fetch(`${validateUrl}?email=${email}&password=${password}`)
		.then(response => response.json())
		.then(jsonUsuario => {
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
				fetch(`${restauranteUrl}/${jsonUsuario.idUsuario}`)
					.then(response => response.json())
					.then(jsonRestaurante => {
						if (jsonRestaurante === null) {
							alert("no es un restaurante, voy a ver si es un cliente");

							fetch(`${clienteUrl}/${jsonUsuario.idUsuario}`)
							.then(response => response.json())
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
							});
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