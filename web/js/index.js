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

	/* Información para hacer un pedido que necesita escogertipocomida.html. */
	localStorage.setItem("infoPedido", JSON.stringify({
		"idCodigoPostal": idCodigoPostal,
		"idTipoEntrega": idTipoEntrega
	}));

	window.location.href = "./cliente/escogertipocomida.html";
}

/* Al ir a la pantalla principal, eliminar cualquier información para hacer un pedido que pueda estar guardada. */
localStorage.removeItem("infoPedido");

document.addEventListener('DOMContentLoaded', function (event) {
	fillSelectCodigoPostal(null);
});