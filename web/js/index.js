function chooseTipoCocinaEntrega() {
	chooseTipoCocina(1);
}

function chooseTipoCocinaDomicilio() {
	chooseTipoCocina(2);
}

function chooseTipoCocina(idTipoEntrega) {
	if (localStorage.getItem("restaurante") !== null) {
		alert("Inicia sesión como cliente para hacer pedidos.");
		return;
	}
	
	let idCodigoPostal = document.getElementById("codigo-postal").value;

	if (idCodigoPostal === "none") {
		return;
	}

	/* Información para hacer un pedido que necesita escogertipocomida.html. */
	localStorage.setItem("idCodigoPostal", idCodigoPostal);
	localStorage.setItem("idTipoEntrega", idTipoEntrega);

	window.location.href = "./cliente/escogertipocomida.html";
}

/* Al ir a la pantalla principal, eliminar cualquier información para hacer un pedido que pueda estar guardada. */
localStorage.removeItem("idCodigoPostal");
localStorage.removeItem("idTipoEntrega");
localStorage.removeItem("idTipoCocina");

document.addEventListener('DOMContentLoaded', function (event) {
	fillSelectCodigoPostal(null);
});