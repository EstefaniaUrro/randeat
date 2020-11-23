document.addEventListener('DOMContentLoaded', function(event) {
  
    cargarOpcionesPaquete();
    createDeliveryOptions();
    createDrinkLists();
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

function createLiOption(name, description) {
    let li = document.createElement("li");
    li.classList.add("list-group-item");
    li.classList.add("list-group-item-action");
    li.addEventListener("click", function() {
        javascript:selectLiOption(this);
    });

    let nameSpan = document.createElement("span");
    nameSpan.innerHTML = `<b>${name}</b>`;
    li.appendChild(nameSpan);
    
    li.appendChild(document.createElement("br"));

    let descriptionSpan = document.createElement("span");
    descriptionSpan.classList.add("option-description");
    descriptionSpan.innerHTML = description;
    li.appendChild(descriptionSpan);

    return li;
}

function getUlByIdAndApplyClasses(id) {
    let ul = document.getElementById(id);
    ul.classList = "list-group col-12 col-md-8 col-lg-10 col-xl-8 mx-auto py-3";
    return ul;
}

function createFoodOptions() {
    let foodOptions = [
        ["Italiano", "Pasta, pizza..."],
        ["Chino", "Fideos, sopitas..."],
        ["Vegano", "Ensaladas, tofu (XD)"]
    ];

    let ul = getUlByIdAndApplyClasses("food-options");

    foodOptions.forEach(function(item, index) {
        ul.appendChild(createLiOption(item[0], item[1]));
    });
}

function cargarOpcionesPaquete() {
    let packageOptions = [
        ["Pequeño", "Uno o dos entrantes"],
        ["Mediano", "Un plato principal"],
        ["Grande", "Un entrante y un plato principal"]
    ];

    let ul = getUlByIdAndApplyClasses("package-options");

    packageOptions.forEach(function(item, index) {
        ul.appendChild(createLiOption(item[0], item[1]));
    });
}

function createDeliveryOptions() {
    let deliveryOptions = [
        ["Enviar", "Los clientes reciben la comida a su domicilio"],
        ["Recoger", "Los clientes vienen a recoger la comida"],
        ["Comer ahí", "Los clientes comen en el local"]
    ];

    let ul = getUlByIdAndApplyClasses("delivery-options");

    deliveryOptions.forEach(function(item, index) {
        ul.appendChild(createLiOption(item[0], item[1]));
    });
}

function drinkItemOnClick(drinkElement, fromListId, toListId) {
    document.getElementById(fromListId).removeChild(drinkElement);
    document.getElementById(toListId).appendChild(drinkElement);

    drinkElement.onclick = function() {
        drinkItemOnClick(this, toListId, fromListId);
    };
}

function createDrinkLists() {
    let drinksNotAddedList = ["Coca-Cola", "Fanta naranja", "Fanta limón", "Pepsi", "Orchata"];
    let drinksAddedList = ["Agua", "Agua de coco", "Agüita fresquita", "Aguarrás"];

    let drinksNotAdded = document.getElementById("drinks-not-added");
    let drinksAdded = document.getElementById("drinks-added");

    addDrinksToList(drinksNotAddedList, drinksNotAdded, drinksAdded.id);
    addDrinksToList(drinksAddedList, drinksAdded, drinksNotAdded.id);
}

function addDrinksToList(drinksList, listElement, otherListId) {
    drinksList.forEach(function(item, index) {
        let li = document.createElement("li");
        li.classList.add("list-group-item");
        li.classList.add("list-group-item-action");
        
        li.innerHTML = item;
        li.onclick = function() {
            drinkItemOnClick(this, listElement.id, otherListId);
        };

        listElement.appendChild(li);
    });
}