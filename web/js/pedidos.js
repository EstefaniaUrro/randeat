

async function loadPaquetesMap() {
	const response = await fetch(
		"http://localhost:8080/paquete/getAll"
	);
	const json = await response.json();

	return createIdElementMap(json, "idPaquete");
}

async function loadTipoEntregaMap() {
	const response = await fetch(
		"http://localhost:8080/tipoEntrega/getAll"
	);
	const json = await response.json();

	return createIdElementMap(json, "idTipoEntrega");
}

async function loadTipoCocinaMap() {
	const response = await fetch(
		"http://localhost:8080/tipoCocina/getAll"
	);
	const json = await response.json();

	return createIdElementMap(json, "idTipoCocina");
}

function createIdElementMap(json, idAttributeName) {
	let map = new Map();
	json.map(element => {
		map.set(
			element[idAttributeName],
			element
		);
	});

	return map;
}

function setPedidoPaquetesString(idPedido, tdPaquete, paquetesMap) {
	fetch(
		`http://localhost:8080/pedidoPaquete/getByIdPedido/${idPedido}`
	).then(
		response => response.json()
	).then(json => {
		let paquetesPedidoString = "";

		console.log("paquetesmap", paquetesMap);

		json.map(pedidoPaquete => {
			let piece = `${pedidoPaquete.cantidad} x ${paquetesMap.get(pedidoPaquete.idPaquete).nombre}`;

			console.log("piece: ", piece);
			paquetesPedidoString += piece + ", ";

			console.log("paquetesPedidoString: ", paquetesPedidoString);
		});

		paquetesPedidoString = paquetesPedidoString.substring(0, paquetesPedidoString.length - 2);

		tdPaquete.appendChild(document.createTextNode(paquetesPedidoString));
	});
}

async function loadPedidosJson() {
	let url = "";

	let cliente = JSON.parse(localStorage.getItem("cliente"));
	if (cliente !== null) {
		url = `http://localhost:8080/pedido/getByIdCliente/${cliente.idCliente}`;
	} else {
		let restaurante = JSON.parse(localStorage.getItem("restaurante"));
		if (restaurante !== null) {
			url = `http://localhost:8080/pedido/getByIdRestaurante/${restaurante.idRestaurante}`;

		} else {
			alert("Error al determinal el tipo de usuario. Por favor, cierra sesión y vuelve a conectarte.");
		}
	}
	console.log("loadPedidosJson url: ", url);

	const response = await fetch(url);
	return await response.json();
}



function loadPedidos() {
	Promise.all([
		loadPedidosJson(),
		loadTipoEntregaMap(),
		loadTipoCocinaMap(),
		loadPaquetesMap()
	]).then(values => {
		let pedidosJson = values[0];
		let tipoEntregaMap = values[1];
		let tipoCocinaMap = values[2];
		let paquetesMap = values[3];

		let tbody = document.getElementsByTagName("tbody")[0];
		for (let i = 0; i < pedidosJson.length;) {
			let cliente = JSON.parse(localStorage.getItem("cliente"));
			if (cliente !== null) {
				/*$("#tbodyModalCliente").append(`<tr><td>` + pedidosJson[i].restauranteIdRestaurante  + `</td><td>` + pedidosJson[i].direccionEnvio + `</td>
				<td>` + pedidosJson[i].comentario + `</td></tr>`);*/
			}
			let restaurante = JSON.parse(localStorage.getItem("restaurante"));

			if (restaurante !== null) {

			}
			i++;
		}
		pedidosJson.map(pedido => {
			console.log("procesando paquetepedido: ", pedido);
			let tr = document.createElement("tr");

			let thIdPedido = document.createElement("th");
			thIdPedido.setAttribute("scope", "row");
			let span = document.createElement("span");
			span.id = pedido.idPedido;
			span.appendChild(document.createTextNode(
				pedido.idPedido
			));
			thIdPedido.appendChild(span);
			tr.appendChild(thIdPedido);

			let tdPaquete = document.createElement("td");

			setPedidoPaquetesString(pedido.idPedido, tdPaquete, paquetesMap);

			tr.appendChild(tdPaquete);

			let tdTipoCocina = document.createElement("td");
			tdTipoCocina.appendChild(document.createTextNode(
				tipoCocinaMap
					.get(pedido.tipoCocinaIdTipoCocina)
					.nombre
			));
			tr.appendChild(tdTipoCocina);

			let tdTipoEntrega = document.createElement("td");
			tdTipoEntrega.appendChild(document.createTextNode(
				tipoEntregaMap
					.get(pedido.tipoEntregaIdTipoEntrega)
					.nombre
			));
			tr.appendChild(tdTipoEntrega);

			// TODO Formateo manualmente la fecha para eliminar los segundos y reemplazar la T que separa la fecha del tiempo por un espacio.
			let fecha = pedido.fecha
				.replace("T", " ")
				.substring(0, pedido.fecha.length - 3)
				;
			let tdFecha = document.createElement("td");
			tdFecha.appendChild(document.createTextNode(
				fecha
			));
			tr.appendChild(tdFecha);

			let tdInfo = document.createElement("button");
			tdInfo.type = "button";
			tdInfo.addEventListener("click", function () {
				nombre(tdInfo, pedido.idPedido);
			});

			tdInfo.classList.add("btn-primary");
			tdInfo.setAttribute("data-toggle", "modal");

			tdInfo.textContent = "+";
			tr.appendChild(tdInfo);
			tbody.appendChild(tr);


		});
	});
}
function nombre(button, pedido) {
	fetch(
		`http://localhost:8080/pedido/getById/${pedido}`
	).then(
		response => response.json()
	).then(pedido => {
		let cliente = JSON.parse(localStorage.getItem("cliente"));
		if (cliente !== null) {
			button.setAttribute("data-target", "#modalCliente");
			fetch(
				`http://localhost:8080/restaurante/getById/${pedido.restauranteIdRestaurante}`
			).then(
				response => response.json()
			).then(restaurante => {
				$("#tbodyModalCliente").append(`<tr><td>` + restaurante.nombreRestaurante + `</td><td>` + pedido.direccionEnvio + `</td>
			<td>` + pedido.comentario + `</td></tr>`);
			});
			$('#tbodyModalCliente').empty()
		}
		let restaurante = JSON.parse(localStorage.getItem("restaurante"));
		if (restaurante !== null) {
			button.setAttribute("data-target", "#modalRestaurante");
			fetch(
				`http://localhost:8080/cliente/getById/${pedido.clienteIdCliente}`
			).then(
				response => response.json()
			).then(cliente => {
				$("#tbodyModalRestaurante").append(`<tr><td>` + cliente.nombreCompleto + `</td><td>` + pedido.direccionEnvio + `</td>
			<td>` + pedido.comentario + `</td></tr>`);
			});
			$('#tbodyModalRestaurante').empty()
		}
	});
}

document.addEventListener('DOMContentLoaded', function (event) {
	loadPedidos();
});