function loadCodigosPostales() {
	let selectCodigoPostal = document.getElementById("select-codigo-postal");

	fetch("http://localhost:8080/codigoPostal/getAll")
		.then(response => response.json())
		.then(json => {
			console.log("then init");

			json.map(codigoPostal => {
				console.log("adding codigopostal:");
				console.log(codigoPostal);

				let option = document.createElement("option");
				option.appendChild(document.createTextNode(
					codigoPostal.numero
				));
				option.setAttribute("value", codigoPostal.idCodigoPostal);

				selectCodigoPostal.appendChild(option);

				console.log("added");
			});
		})
		.catch(asd => {
			console.log("catch!!");
		})
	;
}

document.addEventListener('DOMContentLoaded', function (event) {
	loadCodigosPostales();
});