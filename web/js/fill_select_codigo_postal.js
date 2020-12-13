async function fetchCodigosPostales() {
	const r = await fetch("http://localhost:8080/codigoPostal/getAll");
	return await r.json();
}

function fillSelectCodigoPostal(idCodigoPostal) {
	fetchCodigosPostales().then(json => {
		let codigoPostalSelect = document.getElementById("codigo-postal");
	
		json.map(codigoPostal => {
			let option = document.createElement("option");
			option.value = codigoPostal.idCodigoPostal;
			option.innerHTML = codigoPostal.numero;

			if (idCodigoPostal !== null && idCodigoPostal == codigoPostal.idCodigoPostal) {
				option.selected = true;
			}
	
			codigoPostalSelect.appendChild(option);
		});
	});
}