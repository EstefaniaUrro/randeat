function chooseTipoCocinaEntrega() {
	chooseTipoCocina(1);
}

function chooseTipoCocinaDomicilio() {
	chooseTipoCocina(2);
}

function chooseTipoCocina(idTipoEntrega) {
	let idCodigoPostal = document.getElementById("codigo-postal").value;

	if (idCodigoPostal === "none") {
		return;
	}

	localStorage.setItem("idCodigoPostal", idCodigoPostal);
	localStorage.setItem("idTipoEntrega", idTipoEntrega);

	window.location.href = "./cliente/escogertipocomida.html";
}