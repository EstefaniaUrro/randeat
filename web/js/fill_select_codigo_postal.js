async function fetchCodigosPostales() {
	const r = await fetch("http://localhost:8080/codigoPostal/getAll");
	return await r.json();
}

document.addEventListener('DOMContentLoaded', function (event) {
	fetchCodigosPostales().then(json => {
		let codigoPostalSelect = document.getElementById("codigo-postal");
	
		json.map(codigoPostal => {
			let option = document.createElement("option");
			option.value = codigoPostal.idCodigoPostal;
			option.innerHTML = codigoPostal.numero;
	
			codigoPostalSelect.appendChild(option);
		});
	});
});