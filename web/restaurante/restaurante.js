document.addEventListener('DOMContentLoaded', function (event) {
    let idRestaurante = JSON.parse(
        localStorage.getItem("restaurante")
    ).idRestaurante;

    loadTiposCocina(idRestaurante);
    loadRestaurantePaquetes(idRestaurante);
    loadTiposEntrega(idRestaurante);
    getUlByIdAndApplyClasses("visibility-options");

});
function selectLiOption(element) {
    element.classList.toggle("selected");
}

function toggleVisibilityOption(element) {
    if (element.id === "visible" && !element.classList.contains("green")) {
        document.getElementById("no-visible").classList.remove("red");
        element.classList.add("green");
    } else if (element.id === "no-visible" && !element.classList.contains("red")) {
        document.getElementById("visible").classList.remove("green");
        element.classList.add("red");
    }
}

function createLiOption(id, name, description) {
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

    return li;
}

function createPaqueteLi(paquete) {
    let li = document.createElement("li");
    li.classList.add("list-group-item");
    li.classList.add("list-group-item-action");
    li.id = paquete.idPaquete;

    let nameSpan = document.createElement("span");
    nameSpan.innerHTML = `<b>${paquete.nombre}</b>`;
    li.appendChild(nameSpan);
    li.appendChild(document.createElement("br"));

    let descriptionSpan = document.createElement("span");
    descriptionSpan.classList.add("option-description");
    descriptionSpan.innerHTML = paquete.descripcion;
    li.appendChild(descriptionSpan);
    
    let input = document.createElement("input");
    input.id = `precio-${paquete.idPaquete}`;
    input.classList.add("form-control");
    input.classList.add("col-12");
    input.classList.add("col-md-4");
    input.classList.add("d-none");
    input.type = 'number';
    input.placeholder = "Precio";
    // input.innerHTML = precio;
    li.appendChild(input);

    li.addEventListener("click", function () {
        javascript: selectLiOption(this);

        input.classList.toggle("d-none");

        if (input.classList.contains("d-none")) {
            input.value = "";
        }
    });

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
                inputPrecio.classList.remove("d-none");
                inputPrecio.parentElement.classList.add("selected");
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

    // fetch(
    //     "http://localhost:8080/tipoCocina/getAll"
    // ).then(response => response.json())
    // .then(tiposCocina => {
    //     tiposCocina.map(tipoCocina => {
    //         console.log("tipococina", tipoCocina);
    //         let option = document.createElement("option");
    //         option.setAttribute("value", tipoCocina.idTipoCocina);
    //         option.appendChild(document.createTextNode(tipoCocina.nombre));
    //         // option.innerHTML = tipoCocina.nombre;

    //         // console.log(select.selectedOptions);
            
    //         select.appendChild(option);
    //     });

    //     fetch(
    //         `http://localhost:8080/restauranteTipoCocina/getIdsTipoCocinaByIdRestaurante/${idRestaurante}`
    //     ).then(response => response.json())
    //     .then(restauranteTiposCocina => {
    //         console.log("restico", restauranteTiposCocina);
    //         console.log("select", select);
    //         $(select).val(restauranteTiposCocina);
    //         $(select).selectpicker();
    //         document.getElementsByClassName("bootstrap-select")[0].classList.remove("invisible");
    //     });
    // });
}

function save() {
    let form = document.getElementById("restaurante-opciones");

    /* Tipos de cocina seleccionados */

    let tiposCocina = Array.from(form
        .tiposCocina
        .selectedOptions
    ).map(e => e.value);
    console.log(tiposCocina);

    if (tiposCocina.length === 0) {
        alert("Selecciona algún tipo de cocina");
        return;
    }

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

    let jsonString = `{

    }`;
}