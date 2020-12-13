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
	document.getElementById("contrasena").value = usuario.contrasena;

	fillSelectCodigoPostal(cliente.codigoPostalIdCodigoPostal);

	fetch(
		`http://localhost:8080/tarjeta/getByIdCliente/${cliente.idCliente}`
	).then(
		response => response.json()
	).then(json => {
		json.map(tarjeta => {
			// TODO Hacemos caso sólo a la primera tarjeta.
			document.getElementById("tarjeta").value = tarjeta.numero;
			return;
		});
	});
}

function save() {
	alert("asd");
	let form = document.getElementsByTagName("form")[0];
	let idUsuario = JSON.parse(localStorage.getItem("usuario")).idUsuario;
	let idCliente = JSON.parse(localStorage.getItem("cliente")).idCliente;

	let jsonString = `{
		"idUsuario": ${idUsuario},
		"idCliente": ${idCliente},
		"nombre": "${form.nombre.value}",
		"correoElectronico": "${form.email.value}",
		"direccion": "${form.direccion.value}",
		"idCodigoPostal": "${form.codigoPostal.value}",
		"telefono": "${form.telefono.value}",
		"poblacion": "${form.poblacion.value}",
		"tarjeta": "${form.tarjeta.value}",
		"contrasena": "${form.contrasena.value}"
	}`;
	alert("asd");

	console.log(jsonString);
	alert("asd");

	let url = "http://localhost:8080/cliente/update";
	let options = {
		"method": "POST",
		"body": jsonString,
		"headers": {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	};

	fetch(
		url, options
	).then(
		response => response.json()
	).then(ok => {
		console.log(ok);

		// Actualizo el cliente y usuario almacenados en localStorage.
		fetch(
            `http://localhost:8080/cliente/getById/${idCliente}`
        ).then(
            response => response.json()
        ).then(json => {
            localStorage.setItem("cliente", JSON.stringify(json));
		});
		
		fetch(
            `http://localhost:8080/usuario/getById/${idUsuario}`
        ).then(
            response => response.json()
        ).then(json => {
            localStorage.setItem("usuario", JSON.stringify(json));
        });
	}).catch(err => {
		console.error(err);
	});
}