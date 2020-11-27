document.addEventListener('DOMContentLoaded', function (event) {

    cargarOpcionesPaquete();
    createDeliveryOptions();
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

function createLiOptions(name, description) {
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
    li.addEventListener("click", function () {
        javascript: selectLiOption(this);
    });

    return li;
}
function createLiOption(name, description, precio) {
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
    let input = document.createElement("input");
    input.classList.add("form-control");
    input.classList.add("col-12");
    input.classList.add("col-md-4");
    input.type = 'number';
    input.placeholder = "Precio";
    input.innerHTML = precio;
    li.appendChild(input);
    input.addEventListener("click", function () {
        javascript: selectLiOption(this);
    });
    li.addEventListener("click", function () {
        javascript: selectLiOption(this);
    });
    return li;
}
function getUlByIdAndApplyClasses(id) {
    let ul = document.getElementById(id);
    ul.classList = "list-group col-12 col-md-8 col-lg-8 col-xl-8 mx-auto py-3";
    return ul;
}

function cargarOpcionesPaquete() {
    let packageOptions = [
        ["Pequeño", "Uno o dos entrantes", " "],
        ["Mediano", "Un plato principal", " "],
        ["Grande", "Un entrante y un plato principal", " "]
    ];
    let ul = getUlByIdAndApplyClasses("package-options");

    packageOptions.forEach(function (item, index) {
        ul.appendChild(createLiOption(item[0], item[1], item[2]));

    });
}

function createDeliveryOptions() {
    let deliveryOptions = [
        ["Enviar", "Los clientes reciben la comida a su domicilio"],
        ["Recoger", "Los clientes vienen a recoger la comida"]
    ];

    let ul = getUlByIdAndApplyClasses("delivery-options");

    deliveryOptions.forEach(function (item, index) {
        ul.appendChild(createLiOptions(item[0], item[1]));
    });
}


