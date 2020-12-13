function chooseTipoCocina(idTipoCocina) {
	let infoPedido = JSON.parse(localStorage.getItem("infoPedido"));
	infoPedido.idTipoCocina = idTipoCocina;

	localStorage.setItem("infoPedido", JSON.stringify(infoPedido));

	window.location.href = "./cliente/escogerpaquete.html";
}

function fetchTipoCocinaFilter(idCodigoPostal, idTipoEntrega) {
    const url = "http://localhost:8080/tipoCocina/getInFilter?"
        + `idCodigoPostal=${idCodigoPostal}`
        + `&idTipoEntrega=${idTipoEntrega}`    
    ;
    
    //crea las diferentes tarjetas de tipoCocina en el HTML
    fetch(url)
        .then(response => response.json())
        .then(json => {
            let tipoCocinaDiv = document.getElementById("tipoCocina")

            json.map(tipoCocina => {
                tipoCocinaDiv.innerHTML += `
                <div class="col-6 col-sm-6 col-md-3" onclick="chooseTipoCocina(${tipoCocina.idTipoCocina});">
                    <div class="btn card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <h5 class="card-tittle" style="color: rgb(255, 115, 0);">${tipoCocina.nombre}</h5>
                        </div>
                    </div>
                </div>
            `});
        })
        .catch(err => console.log(err))
    ;
}

document.addEventListener('DOMContentLoaded', function (event) {
    let infoPedido = JSON.parse(localStorage.getItem("infoPedido"));

	fetchTipoCocinaFilter(infoPedido.idCodigoPostal, infoPedido.idTipoEntrega);
});