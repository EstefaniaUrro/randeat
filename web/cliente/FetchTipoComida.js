
const url = 'http://localhost:8080/tipoCocina/getAll'

fetch(url)
.then(response => response.json())
.then(data => {
  for (let tipoCocinaId = 0; tipoCocinaId <8; ++tipoCocinaId) {
  let tipoCocina = document.getElementById("tipoCocina")
  tipoCocina.innerHTML += `
  <div class="card col-6 col-sm-6 col-md-3"style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
    <div class="card-body text-center">
      <a href="escogerpaquete.html">
        <h5 class="card-tittle" style="color: rgb(255, 115, 0);">${data[tipoCocinaId].nombre}</h5>
      </a>
    </div>
  </div>
  `};
  console.log(data)
})
.catch(err => console.log(err));


/*<div class="card">
      <div class="text-center">
        <h1 class="card-title" style="color : orange" ;>¿Qué te apetece comer?</h1>
      </div>
      <div class="row borde">
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="escogerpaquete.html">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
      </div>
      <div class="row borde">
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class=" card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
        <div class="card col-6 col-sm-6 col-md-3"
          style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
          <div class="card-body text-center">
            <a href="#">
              <h5 class="card-tittle" style="color: rgb(255, 115, 0);">Pollo</h5>
            </a>
          </div>
        </div>
      </div>
      <br>
    </div>
    */
