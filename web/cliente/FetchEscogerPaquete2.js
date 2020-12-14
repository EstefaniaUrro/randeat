

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
            restauranteImg.src =`./img/${data[ordenRestaurante].nombreRestaurante}.jpg`;
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

/*
fetch(url)
.then(response => response.json())
.then(data => {
  let ordenRestaurante = Math.floor(Math.random()*(2-0)+0);
  let restaurante = document.getElementById("restaurante")
  restaurante.innerHTML +=`
  <h4 style="text-align: center;">${data[ordenRestaurante].nombreRestaurante}</h4>
  `
  console.log(data);

  restaurante.innerHTML +=`
  <img class="card-img-top" src="./img/${data[ordenRestaurante].nombreRestaurante}.jpg" alt="Card image cap" style="width: 200px; height: 200px;">
  `

  console.log(data);
})
.catch(err => console.log(err));
*/
