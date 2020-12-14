

function buildUrl(){
    let idCodigoPostal = getIdCodigoPostal();
    let idTipoCocina = getIdTipoCocina();
    let idTipoEntrega = getIdTipoEntrega();
    return `http://localhost:8080/restaurante/getInFilter?idCodigoPostal=${idCodigoPostal}&idTipoCocina=${idTipoCocina}&idTipoEntrega=${idTipoEntrega}`;
}

function getIdCodigoPostal(){
    return localStorage.getItem("idCodigoPostal") || 1;
}

function getIdTipoCocina(){
    return localStorage.getItem("idTipoCocina") || 1;
}

function getIdTipoEntrega(){
    return localStorage.getItem("idTipoEntrega") || 1;
}
//seguimos trabajando en ello

function randomOrder(length){
    return Math.floor(Math.random()*(length-0)+0);
}

function dec(idInputPrecioPaquete) {
    let input = document.getElementById(idInputPrecioPaquete);
    let value = parseInt(input.value);

    if (value > 0) {
        input.value = value - 1;
    }
}

function inc(idInputPrecioPaquete) {
    let input = document.getElementById(idInputPrecioPaquete);

    input.value = parseInt(input.value) + 1;
}

function pagartarjeta() {
    if ( document.getElementById("tarjeta").checked) {
         document.getElementById("myDIV").style.display = "block"
    }
    else 
        document.getElementById("myDIV").style.display = "none";
    
}

document.addEventListener('DOMContentLoaded', function (event) {
    loadRestaurantes();
});

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

function loadRestaurantes() {
    fetch(buildUrl())
        .then(response => response.json())
        .then(data => {
            let ordenRestaurante = randomOrder(data.length);
            let restaurante = document.getElementById("restaurante");
            restaurante.innerText =data[ordenRestaurante].nombreRestaurante;

            localStorage.setItem("idRestaurante", data[ordenRestaurante].idRestaurante);

            let restauranteImg = document.getElementById("restauranteImg");
            restauranteImg.src =`./img/Restaurantes/${data[ordenRestaurante].nombreRestaurante}.jpg`;
            console.log(data);

            const fetchRestaurantePaquete = fetch(
                `http://localhost:8080/restaurantePaquete/getByIdRestaurante/${data[ordenRestaurante].idRestaurante}`
            ).then(response => response.json());

            const fetchPaquete = fetch(
                "http://localhost:8080/paquete/getAll"
            ).then(response => response.json());

            Promise.all([fetchRestaurantePaquete, fetchPaquete])
                .then(values => {
                    let restaurantePaquetes = values[0];
                    let paquetesMap = createIdElementMap(values[1], "idPaquete");
                    let restaurantePaquetesDiv = document.getElementById(
                        "restaurante-paquetes"
                    );

                    console.log("respajs", restaurantePaquetes);

                    restaurantePaquetes.map(rp => {
                        let paqueteById = paquetesMap.get(rp.paqueteIdPaquete);

                        restaurantePaquetesDiv.innerHTML +=
`<div class="row restaurante-paquete">
    <h3 class="text-center col-12">
        ${paqueteById.nombre}
    </h3>
    <h5 class="text-center col-12">
        ${paqueteById.descripcion}
    </h5>
    <p class="text-center col-12">${rp.coste} €</p>

    <div class="mx-auto col-12 text-center">
        <button class="dec-button" onclick="dec('precio${paqueteById.idPaquete}')">-</button>
        <input
            id="precio${paqueteById.idPaquete}"
            name="precio${paqueteById.idPaquete}"
            class="precio-paquete" type="number" value="0" disabled
            idPaquete="${paqueteById.idPaquete}">
        <button class="inc-button" onclick="inc('precio${paqueteById.idPaquete}')">+</button>
    </div>
</div>

<hr>`
                        ;

                        document.getElementById("button-pagar").addEventListener("click", function() {
                            realizarPedido(createIdElementMap(restaurantePaquetes, "paqueteIdPaquete"));
                        });
                    });
                })
            ;
        })
    ;
}

function realizarPedido(restaurantePaqueteMap) {
    console.log("respama", restaurantePaqueteMap);
    let inputsPrecioPaquete = Array.from(
        document.getElementsByClassName("precio-paquete")
    );
    let modalPaquetes = document.getElementById("modal-paquetes");

    let idRestaurante = localStorage.getItem("idRestaurante");
    let idCliente = JSON.parse(localStorage.getItem("cliente")).idCliente;

    let algunoDistintoDeCero = false;

    modalPaquetes.innerHTML = "";

    let total = 0.0;
    let infoPedidoJsonPaquetes = "";

    inputsPrecioPaquete.forEach(input => {
        let cantidad = parseInt(input.value);
        let idPaquete = parseInt(input.getAttribute("idPaquete"));

        if (cantidad !== 0) {
            algunoDistintoDeCero = true;
        }

        let subtotal = restaurantePaqueteMap.get(idPaquete).coste * cantidad;
        total += subtotal;

        infoPedidoJsonPaquetes += `"${idPaquete}": ${cantidad},`;

        modalPaquetes.innerHTML +=
`<tr>
    <td>
        ${restaurantePaqueteMap.get(idPaquete).paqueteNombre}
    </td>
    <td class="text-center">${cantidad}</td>
    <td class="text-center">
        ${restaurantePaqueteMap.get(idPaquete).coste} €
    </td>
    <td class="text-center">
        ${subtotal} €
    </td>
</tr>`
    });

    // Si no se ha seleccionado ningún paquete no se puede hacer un pedido.
    if (!algunoDistintoDeCero) {
        alert("pon algo no");
        return;
    }

    infoPedidoJsonPaquetes = infoPedidoJsonPaquetes.substring(
        0,
        infoPedidoJsonPaquetes.length - 1
    );

    // console.log(`realizaré un pedido al restaurante ${idRestaurante}, con ${paquetePeque} peq, ${paqueteMediano} med y ${paqueteGrande} gran`);

    $("#exampleModal").modal("show");

    document.getElementById("total").innerText = total;

    let comentario = document.getElementById("comentario").value;
    let idTipoEntrega = localStorage.getItem("idTipoEntrega");
    let idTipoCocina = localStorage.getItem("idTipoCocina");
    let direccionEnvio = "PILLAR DEL CLIENTE";

    let pedidoJsonData = `{
        "idRestaurante": ${idRestaurante},
        "idCliente": ${idCliente},
        "pedidoPaquetes": {${infoPedidoJsonPaquetes}},
        "comentario": "${comentario}",
        "idTipoEntrega": ${idTipoEntrega},
        "idTipoCocina": ${idTipoCocina},
        "direccionEnvio": "${direccionEnvio}"
    }`;

    console.log("info pedido:", JSON.parse(pedidoJsonData));

    let buttonConfirmar = document.getElementById("button-confirmar");
    // let onClick = function() {
    //     confirmarPedido(pedidoJsonData);
    // };
    
    // buttonConfirmar.removeEventListener("click", onClick);
    // document.getElementById(
    //     "button-confirmar"
    // ).addEventListener("click", onClick);

    // buttonConfirmar.onclick = `confirmarPedido(${pedidoJsonData})`;

    // Con jQuery es más fácil eliminar un evento posiblemente ya seteado.
    // Lo de arriba con JavaScript puro no funciona, habrá alguna manera
    // de hacerlo pero qué palo.
    $("#button-confirmar").unbind("click");
    $("#button-confirmar").click(function() {
        confirmarPedido(pedidoJsonData);
    });
}

function confirmarPedido(pedidoJsonData) {
    const url = "http://localhost:8080/pedido/add";
    const options = {
        "method": "POST",
        "body": pedidoJsonData,
        "headers": {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    };

    console.log("click");

    fetch(url, options)
        .then(ok => {
            localStorage.removeItem("idCodigoPostal");
            localStorage.removeItem("idTipoEntrega");
            localStorage.removeItem("idTipoCocina");
            localStorage.removeItem("idRestaurante");

            alert("¡Pedido realizado correctamente!");

            window.location.href = "./pedidos.html";
        })
        .catch(err => {
            alert("Error al realizar el pedido...");

            console.error(err);
        })
    ;
}