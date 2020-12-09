async function loadPaquetes() {
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

function loadPedidosJson() {
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

	console.log("fing url: ", url);

	return fetch(url).then(response => response.json());
}

function loadPedidos() {
	Promise.all([
		loadPedidosJson(),
		loadTipoEntregaMap(),
		loadTipoCocinaMap()
	]).then(values => {
		let pedidosJson = values[0];
		console.log(pedidosJson);
		let tipoEntregaMap = values[1];
		let tipoCocinaMap = values[2];

		let tbody = document.getElementsByTagName("tbody")[0];

		pedidosJson.map(pedido => {
			let tr = document.createElement("tr");

			let thIdPedido = document.createElement("th");
			thIdPedido.setAttribute("scope", "row");
			thIdPedido.appendChild(document.createTextNode(
				pedido.idPedido
			));
			tr.appendChild(thIdPedido);
	
			let tdPaquete = document.createElement("td");
			tdPaquete.appendChild(document.createTextNode(
				"TODO"
			));
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
	
			tbody.appendChild(tr);
		});
	});
}

document.addEventListener('DOMContentLoaded', function (event) {
	loadPedidos();
});