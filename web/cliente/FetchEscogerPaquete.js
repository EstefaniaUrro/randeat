

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

function loadRestaurantes() {
    fetch(buildUrl())
        .then(response => response.json())
        .then(data => {
            let ordenRestaurante = randomOrder(data.length);
            let restaurante = document.getElementById("restaurante");
            restaurante.innerText =data[ordenRestaurante].nombreRestaurante;
            let restauranteImg = document.getElementById("restauranteImg");
            restauranteImg.src =`./img/Restaurantes/${data[ordenRestaurante].nombreRestaurante}.jpg`;
            console.log(data);
            const urlCoste = 'http://localhost:8080/restaurantePaquete/getByIdRestaurante/'+ data[ordenRestaurante].idRestaurante;
            fetch(urlCoste)
                .then(response => response.json())
                .then(data =>{
                    let restauranteCostePeque = document.getElementById("peque");
                    restauranteCostePeque.innerText = data[0].coste;
                    let restauranteCosteMed = document.getElementById("mediano");
                    restauranteCosteMed.innerText = data[1].coste;
                    let restauranteCosteGran = document.getElementById("grande");
                    restauranteCosteGran.innerText = data[2].coste;
                })
        })
        .catch(err => console.log(err))
}

loadRestaurantes();
