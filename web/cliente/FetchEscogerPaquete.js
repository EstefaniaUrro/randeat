const url = 'http://localhost:8080/restaurante/getInFilter?idCodigoPostal=1&idTipoCocina=1&idTipoEntrega=1'

//seguimos trabajando en ello
fetch(url)
.then(response => response.json())
.then(data => {
  let ordenRestaurante = Math.floor(Math.random()*(2-0)+0)
  let restaurante = document.getElementById("restaurante")
  restaurante.innerHTML += `
            <div class="card col-md-4" style="background-color: rgba(243, 243, 243, 0.616);">
                <div class="card-title card-center">
                    <br>
                    <h4 style="text-align: center;">${data[ordenRestaurante].nombreRestaurante}</h4>
                </div>
                <div class="text-center">
                    <img class="card-img-top" src="../img/${data[ordenRestaurante].nombreRestaurante}.jpg" alt="Card image cap"
                        style="width: 200px; height: 200px;">
                </div>
                <div class="card-body">
                    <hr>
                    <div class="text-center">
                        <button class="btn btn-outline-secondary enviar" type="button"><a
                                href="escogerpaquete.html">Cambiar de restaurante</a></button>
                    </div>
                    <hr>
                    <textarea rows="10" cols="30" class="form-control"
                        placeholder="Alergias o otros comentarios para el restaurante" style="height: 40%;"></textarea>
                </div>
            </div>
  `;
  console.log(data)
})
.catch(err => console.log(err));

/*
<div class="card col-md-4" style="background-color: rgba(243, 243, 243, 0.616);">
                <div class="card-title card-center">
                    <br>
                    <h4 style="text-align: center;">"Nombre del restaurante"</h4>
                </div>
                <div class="text-center">
                    <img class="card-img-top" src="../img/lasmuns.png" alt="Card image cap"
                        style="width: 200px; height: 200px;">
                </div>
                <div class="card-body">
                    <hr>
                    <div class="text-center">
                        <button class="btn btn-outline-secondary enviar" type="button"><a
                                href="escogerpaquete.html">Cambiar de restaurante</a></button>
                    </div>
                    <hr>
                    <textarea rows="10" cols="30" class="form-control"
                        placeholder="Alergias o otros comentarios para el restaurante" style="height: 40%;"></textarea>
                </div>
            </div>
*/