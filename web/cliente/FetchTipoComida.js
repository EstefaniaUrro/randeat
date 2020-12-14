
const url = 'http://localhost:8080/tipoCocina/getAll'


function storeTipoCocina(idTipoCocina){
localStorage.setItem("idTipoCocina",idTipoCocina);

}

//crea las diferentes tarjetas de tipoCocina en el HTML
fetch(url)
.then(response => response.json())
.then(data => {
  for (let tipoCocinaId = 0; tipoCocinaId <8; ++tipoCocinaId) {
  let tipoCocina = document.getElementById("tipoCocina")
  tipoCocina.innerHTML += `
  <div class="col-6 col-sm-6 col-md-3">
      <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
          <div onclick="storeTipoCocina(${data[tipoCocinaId].idTipoCocina})" class="card-body text-center">
              <a href="./cliente/PruebasFetch.html">
                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">${data[tipoCocinaId].nombre}</h5>
              </a>
          </div>
      </div>
  </div>
  `};
  console.log(data)
})
.catch(err => console.log(err));


/*<div class="container">
        <div class="card">
            <div class="text-center">
                <h1 class="card-title" style="color : orange" ;>¿Qué te apetece comer?</h1>
            </div>
            <br>
            <div class="row borde">
                <div class="col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="escogerpaquete.html">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class=" col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="#">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="#">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="#">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row borde">
                <div class="col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="escogerpaquete.html">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class=" col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="#">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="#">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-sm-6 col-md-3">
                    <div class="card"
                        style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px; margin-top: 10px;">
                        <div class="card-body text-center">
                            <a href="#">
                                <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <br>
        </div>
    </div>
    */
