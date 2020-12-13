document.addEventListener('DOMContentLoaded', function (event) {
    let restaurante = JSON.parse(
        localStorage.getItem("restaurante")
    );

    loadTiposCocina(restaurante.idRestaurante);
    loadRestaurantePaquetes(restaurante.idRestaurante);
    loadTiposEntrega(restaurante.idRestaurante);
    getUlByIdAndApplyClasses("visibility-options");
    loadActivo(restaurante.activo);
});

function selectLiOption(element) {
    element.classList.toggle("selected");
}

function toggleVisibilityOption(element) {
    if (element.id === "activo" && !element.classList.contains("green")) {
        document.getElementById("no-activo").classList.remove("red");
        element.classList.add("green");
    } else if (element.id === "no-activo" && !element.classList.contains("red")) {
        document.getElementById("activo").classList.remove("green");
        element.classList.add("red");
    }
}

function createLiOption(id, name, description) {
    // let li = document.createRange().createContextualFragment(`
    //     <li class="list-group-item list-group-item-action" value="${id}">
    //         <span>
    //             <b>${name}</b>
    //         </span>

    //         <br/>

    //         <span class="option-description">
    //             ${description}
    //         </span>
    //     </li>
    // `);
    let li = document.createElement("li");
    li.classList.add("list-group-item");
    li.classList.add("list-group-item-action");

    let nameSpan = document.createElement("span");
    nameSpan.innerHTML = `<b>${name}</b>`;
    li.appendChild(nameSpan);
    li.appendChild(document.createElement("br"));
    let descriptionSpan = document.createElement("span");
    descriptionSpan.classList.add("option-description");
    descriptionSpan.innerHTML = description;
    li.appendChild(descriptionSpan);
    li.value = id;
    li.addEventListener("click", function () {
        javascript: selectLiOption(this);
    });


    console.log("li.lii:", li);
    return li;
}

function createPaqueteLi(paquete) {
    let li = document.createRange().createContextualFragment(`
        <li class="list-group-item d-flex justify-content-between">
            <div>
                <span>
                    <b>${paquete.nombre}</b>
                </span>

                <br/>

                <span class="option-description">
                    ${paquete.descripcion}
                </span>
            </div>
            
                <input id="precio-${paquete.idPaquete}" type="number" step="0.01" class="precio-paquete form-control col-12 col-md-4" placeholder="Precio" idPaquete="${paquete.idPaquete}">
        </li>
    `);

    return li;
}

function getUlByIdAndApplyClasses(id) {
    let ul = document.getElementById(id);
    ul.classList = "list-group col-12 col-md-8 col-lg-8 col-xl-8 mx-auto py-3";
    return ul;
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

async function loadPaquetesMap() {
	const response = await fetch(
		"http://localhost:8080/paquete/getAll"
	);
	const json = await response.json();

	return createIdElementMap(json, "idPaquete");
}

async function loadRestaurantePaquetesJson(idRestaurante) {
    const response = await fetch(
        `http://localhost:8080/restaurantePaquete/getByIdRestaurante/${idRestaurante}`
    );
    
    const json = await response.json();

    return json;
}

function loadRestaurantePaquetes(idRestaurante) {
    let fetchPaquetes = fetch(
        "http://localhost:8080/paquete/getAll"
    ).then(response => response.json());

    let fetchRestaurantePaquetes = fetch(
        `http://localhost:8080/restaurantePaquete/getByIdRestaurante/${idRestaurante}`
    ).then(response => response.json());

    fetchPaquetes.then(paquetes => {
        let ul = getUlByIdAndApplyClasses("package-options");

        paquetes.map(paquete => {
            console.log("paquete: ", paquete);
            ul.appendChild(
                createPaqueteLi(paquete)
            );
        });

        fetchRestaurantePaquetes.then(restaurantePaquetes => {
            restaurantePaquetes.map(restaurantePaquete => {
                let inputPrecio = document.getElementById(
                    `precio-${restaurantePaquete.paqueteIdPaquete}`
                );
                inputPrecio.value = restaurantePaquete.coste;
            });
        });
    })
}

function loadTiposEntrega(idRestaurante) {
    let tipoEntregaJson = fetch(
        "http://localhost:8080/tipoEntrega/getAll"
    ).then(response => response.json());
    
    let restauranteTipoEntregaJson = fetch(
        `http://localhost:8080/restauranteTipoEntrega/getIdsTipoEntregaByIdRestaurante/${idRestaurante}`
    ).then(response => response.json());
    // .then(json => createIdElementMap(json, "tipo_entrega_id_tipo_entrega"));

    console.log("idrest", idRestaurante);

    Promise.all(
        [tipoEntregaJson, restauranteTipoEntregaJson]
    ).then(values => {
        let tiposEntrega = values[0];
        console.log("values1", values[1]);

        let restauranteTiposEntregaSet = new Set(Array.from(values[1]));

        console.log("restien", restauranteTiposEntregaSet);

        let ul = getUlByIdAndApplyClasses("delivery-options");

        tiposEntrega.map(tipoEntrega => {
            let liOption = createLiOption(tipoEntrega.idTipoEntrega, tipoEntrega.nombre, tipoEntrega.descripcion);

            console.log("created li option", liOption);
                
            if (restauranteTiposEntregaSet.has(tipoEntrega.idTipoEntrega)) {
                liOption.classList.add("selected");
                console.log("has");
            }

            ul.appendChild(liOption);
        });
    })
}

function loadTiposCocina(idRestaurante) {
    let select = document.getElementById("tipos-cocina");
    /* Lo hago invisible hasta que esté todo listo, porque si no primero se carga como un select multiple "básico", que es más grande, y luego se hace más pequeño, y se ve feo. Hasta que no llamo $(select).selectpicker(). */

    select.classList.add("invisible");

    let tiposCocinaJson = fetch(
        "http://localhost:8080/tipoCocina/getAll"
    ).then(response => response.json());

    let restauranteTipoCocinaJson = fetch(
        `http://localhost:8080/restauranteTipoCocina/getIdsTipoCocinaByIdRestaurante/${idRestaurante}`
    ).then(response => response.json());

    Promise.all(
        [tiposCocinaJson, restauranteTipoCocinaJson]
    ).then(values => {
        let tiposCocina = values[0];
        let restauranteTipoCocina = values[1];

        tiposCocina.map(tipoCocina => {
            console.log("tipococina", tipoCocina)
            let option = document.createElement("option");
            option.setAttribute("value", tipoCocina.idTipoCocina);
            option.innerHTML = tipoCocina.nombre;

            console.log("restico", restauranteTipoCocina);

            console.log(select.selectedOptions);
            
            select.appendChild(option);
        });
        $(select).val(restauranteTipoCocina);
        $(select).selectpicker();

        /* Deja de ser invisible una vez está todo puesto en su sitio. Al llamar a selectpicker se cambia el html y es esa clase la que queda invisible. */
        document
            .getElementsByClassName("bootstrap-select")[0]
            .classList.remove("invisible")
        ;


    });
}

function loadActivo(isActivo) {
    if (isActivo) {
        document.getElementById("activo").classList.add("green");
    } else {
        document.getElementById("no-activo").classList.add("red");
    }
}

function save() {
    let idRestaurante = JSON.parse(
        localStorage.getItem("restaurante")
    ).idRestaurante;

    let form = document.getElementById("restaurante-opciones");

    /* Tipos de cocina seleccionados */

    let tiposCocina = Array.from(
        // form.tiposCocina
        document.getElementById("tipos-cocina")
        .selectedOptions
    ).map(e => e.value);
    console.log(tiposCocina);

    if (tiposCocina.length === 0) {
        alert("Selecciona algún tipo de cocina");
        return;
    }

    /* Precio de los paquetes */

    let idPaquetePrecio = {};
    let inputsPecioPaquete = document.getElementsByClassName("precio-paquete");

    for (let input of inputsPecioPaquete) {
        let value = input.value;
        if (value == "") {
            alert("Establece un precio para todos los paquetes.");
            return;
        }

        idPaquetePrecio[input.getAttribute("idPaquete")] = Number.parseFloat(value);
    }

    console.log("idpapre", idPaquetePrecio);

    /* Tipos de entrega seleccionados */

    let tiposEntrega = [];

    let deliveryOptions = document.getElementById("delivery-options").children;
    
    for (let i = 0; i < deliveryOptions.length; i += 1) {
        let tipoEntregaLi = deliveryOptions[i];
        if (tipoEntregaLi.classList.contains("selected")) {
            tiposEntrega.push(tipoEntregaLi.value);
        }
    }

    console.log("tiposentrega: ", tiposEntrega);

    if (tiposEntrega.length === 0) {
        alert("Selecciona algún tipo de entrega");
        return;
    }

    let activo = false;
    if (document.getElementById("activo").classList.contains("green")) {
        activo = true;
    }

    let jsonString = `{
        "idRestaurante": "${idRestaurante}",
        "idsTipoCocina": ${JSON.stringify(tiposCocina)},
        "idPaquetePrecio": ${JSON.stringify(idPaquetePrecio)},
        "idsTipoEntrega": ${JSON.stringify(tiposEntrega)},
        "activo": ${activo}
    }`;

    console.log("save json", JSON.parse(jsonString));

    // const url = `http://localhost:8080/restaurante/setRestauranteOpciones/${jsonString}`;
    const url = "http://localhost:8080/restaurante/setRestauranteOpciones";
    const options = {
		"method": "POST",
		"body": jsonString,
		"headers": {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
    };
    
    fetch(
        url, options
    ).then(
        r => r.json()
    ).then(t => {
        console.log("bien");

        /* Si todo ha ido bien, actualizo el restaurante almacenado en localStorage. */
        fetch(
            `http://localhost:8080/restaurante/getById/${idRestaurante}`
        ).then(
            response => response.json()
        ).then(json => {
            localStorage.setItem("restaurante", JSON.stringify(json));

            alert("Datos guardados correctamente");
        });
    }).catch(c => {
        console.log("nope");
    });
}