
const url = 'http://localhost:8080/restaurante/getInFilter?idCodigoPostal=1&idTipoCocina=1&idTipoEntrega=1'


//seguimos trabajando en ello



fetch(url)
.then(response => response.json())
.then(data => {
    let ordenRestaurante = parseInt(Math.floor(Math.random()*(2-0)+0));
  let restaurante = document.getElementById("restaurante")
  restaurante.innerHTML += `
           
  <div class="card col-md-4" style="background-color: rgba(243, 243, 243, 0.616);">
                <div class="card-title card-center">
                    <br>
                    <h4 style="text-align: center;">${data[ordenRestaurante].nombreRestaurante}</h4>
                </div>
                <div class="text-center">
                    <img class="card-img-top" src="./img/${data[ordenRestaurante].nombreRestaurante}.jpg" alt="Card image cap"
                        style="width: 200px; height: 200px;">
                </div>
                <div class="card-body">
                    <hr>
                    <div class="text-center">
                        <a href="escogerpaquete.html"><button class="btn btn-outline-secondary enviar"
                                type="button">Cambiar de restaurante</button></a>
                    </div>
                    <hr>
                    <textarea rows="10" cols="30" class="form-control"
                        placeholder="Informe aquí si tú o alguien para el que estás pidiendo tiene una alergia o intolerancia a algún alimento."
                        style="height: 40%;"></textarea>
                </div>    
            `;
            const urlCoste = 'http://localhost:8080/restaurantePaquete/getByIdRestaurante/'+ data[ordenRestaurante].idRestaurante;
            fetch(urlCoste)
            .then(response => response.json())
            .then(data => {
            let restauranteCoste = document.getElementById("restaurante");
            restauranteCoste.innerHTML += `
            <div class="card col-md-8">
                <div class="card-title">
                    <h1 style="text-align: center; color: orange;">Tipo de paquetes</h1>
                </div>
                <div class="card-body">
                    <h5 style="text-align: left;">Seleccione los paquetes que quiera pedir</h5>
                </div>
                <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
                    <div class="card-center card-title">
                        <br>
                        <h4 style="text-align: center; color : rgb(233, 164, 35)">Paquete pequeño</h4>
                    </div>
                    <div class="card-body card-center">
                        <p style="text-align: center;">${data[0].coste} €</p>
                        <p style="text-align: center;">Uno o dos entrantes </p>
                        <script>
                        
                        </script>
                        <div style="text-align: center;">
                            <button id="resta2"
                                style="border-radius: 10px; width: 25px; background-color: rgba(255, 166, 0, 0.651);">-</button>
                            <input id="paquetepequeño" name="paquetepequeño" type="number" disabled=”disabled”
                                style="text-align: center; width: 50%;">
                            <button id="suma2"
                                style="border-radius: 10px; width: 25px; background-color:  rgba(255, 166, 0, 0.651);">+</button>
                        </div>
                    </div>
                </div>
                <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
                    <div class="card-center card-title">
                        <br>
                        <h4 style="text-align: center; color : rgb(233, 164, 35)">Paquete mediano</h4>
                    </div>
                    <div class="card-body card-center">
                        <p style="text-align: center;">${data[1].coste} €</p>
                        <p style="text-align: center;">Un plato principal</p>
                        <div style="text-align: center;">
                            <button id="resta1"
                                style="border-radius: 10px; width: 25px; background-color:  rgba(255, 166, 0, 0.651);">-</button>
                            <input id="paquetemediano" name="paquetemediano" type="number" disabled=”disabled”
                                style="text-align: center; width: 50%;">
                            <button id="suma1"
                                style="border-radius: 10px; width: 25px; background-color:  rgba(255, 166, 0, 0.651);">+</button>
                        </div>
                    </div>
                </div>
                <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
                    <div class="card-center card-title">
                        <br>
                        <h4 style="text-align: center; color : rgb(233, 164, 35)">Paquete grande</h4>
                    </div>
                    <div class="card-body card-center">
                        <p style="text-align: center;">${data[2].coste} €</p>
                        <p style="text-align: center;">Un entrante y un plato principal</p>
                        <div style="text-align: center;">
                            <button id="resta"
                                style="border-radius: 10px; width: 25px; background-color: rgba(255, 166, 0, 0.651);">-</button>
                            <input id="paquetegrande" name="paquetegrande" type="number" disabled=”disabled”
                                style="text-align: center; width: 50%;">
                            <button id="suma"
                                style="border-radius: 10px; width: 25px; background-color: rgba(255, 166, 0, 0.651);">+</button>
                        </div>
                    </div>
                </div>
                <br>
                <div class="card text-center"
                    style="background-color: rgba(240, 248, 255, 0); border: 0px solid rgba(255, 255, 255, 0);">
                    <div class="row">
                        <div class="text-center col-12 col-md-12">
                            <button class="enviar" id="myBtn" type="button" style="width: 200px;" data-toggle="modal"
                                data-target="#exampleModal" onclick="paquetes()"><i class="far fa-bookmark"></i>
                                Pagar</button>
                        </div>
                    </div>
                    <br>
                </div>
            </div>
        </div>
        <br>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Información sobre tu pedido</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Tipo de paquete</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Precio c/u</th>
                                    <th scope="col">Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Paquete pequeño</td>
                                    <td class="text-center"><span id="cantidadPeque">0</span></td>
                                    <td class="text-center"><span id="precioPeque">${data[0].coste}</span>€</td>
                                    <td class="text-center"><span id="subtotalPeque">0</span>€</td>
                                </tr>

                                <tr>
                                    <td>Paquete mediano</td>
                                    <td class="text-center"><span id="cantidadMediano">0</span></td>
                                    <td class="text-center"><span id="precioMediano">${data[1].coste}</span>€</td>
                                    <td class="text-center"><span id="subtotalMediano">0</span>€</td>
                                </tr>
                                <tr>
                                    <td>Paquete grande</td>
                                    <td class="text-center"><span id="cantidadGrande">0</span></td>
                                    <td class="text-center"><span id="precioGrande">${data[2].coste}</span>€</td>
                                    <td class="text-center"><span id="subtotalGrande">0</span>€</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th colspan="3">Precio Total</th>
                                    <th colspan="2" class="text-center"><span id="total">30 </span>€</th>
                                </tr>
                                </tfood>
                        </table>
                        <hr>
                        <p>Tu pedido estara listo en 45 minutos.</p>
                        <div class="form-check">
                            <input type="radio" name="pagartarjeta" id="sitarjeta" onclick="javascript:pagartarjeta();"
                                checked>
                            <label class="form-check-label" for="exampleRadios2">Pagar en efectivo.</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" name="pagartarjeta" id="tarjeta" onclick="javascript:pagartarjeta();">
                            <label class="form-check-label" for="exampleRadios1">Pagar con tarjeta.</label>
                        </div>

                        <div id="myDIV" style="display:none;">
                            <br>
                            <p>El pago se realizara en la tarjeta: **** **** **** 5724</p>
                            <a href="#">
                                <p>Desea cambiar de tarjeta?</p>
                            </a>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="row text-center">
                            <div class="col-12 col-md-12">
                                <button type="button" class="enviar" style="width: 200px;"><i
                                        class="fab fa-amazon-pay"></i>Pagar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  `;
  console.log(data)
})
.catch(err => console.log(err));
  console.log(data)
})
.catch(err => console.log(err));

function paquetes() {
    var paquetePeque = document.getElementById("paquetepequeño").value;
    document.getElementById("cantidadPeque").innerText = paquetePeque;
    var paqueteMediano = document.getElementById("paquetemediano").value;
    document.getElementById("cantidadMediano").innerText = paqueteMediano;
    var paqueteGrande = document.getElementById("paquetegrande").value;
    document.getElementById("cantidadGrande").innerText = paqueteGrande;

    var precioPeque = document.getElementById("peque").innerText;
    document.getElementById("precioPeque").innerText = precioPeque;
    var precioMediano = document.getElementById("mediano").innerText;
    document.getElementById("precioMediano").innerText = precioMediano;
    var precioGrande = document.getElementById("grande").innerText;
    document.getElementById("precioGrande").innerText = precioGrande;

    var subtotalPeque = paquetePeque * precioPeque;
    var subtotalMediano = paqueteMediano * precioMediano;
    var subtotalGrande = paqueteGrande * precioGrande;
    document.getElementById("subtotalPeque").innerText = subtotalPeque;
    document.getElementById("subtotalMediano").innerText = subtotalMediano;
    document.getElementById("subtotalGrande").innerText = subtotalGrande;

    var total = subtotalPeque + subtotalMediano + subtotalGrande;
    document.getElementById("total").innerText = total;
}
/*
<div class="card col-md-8">
                <div class="card-title">
                    <h1 style="text-align: center; color: orange;">Tipo de paquetes</h1>
                </div>
                <div class="card-body">
                    <h5 style="text-align: left;">Seleccione los paquetes que quiera pedir</h5>
                </div>
                <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
                    <div class="card-center card-title">
                        <br>
                        <h4 style="text-align: center; color : rgb(233, 164, 35)">Paquete grande</h4>
                    </div>
                    <div class="card-body card-center">
                        <p style="text-align: center;">${data[0].coste} €</p>
                        <p style="text-align: center;">Un entrante y un plato principal</p>
                        <div style="text-align: center;">
                            <button id="resta"
                                style="border-radius: 10px; width: 25px; background-color: rgba(255, 166, 0, 0.651);">-</button>
                            <input id="paquetegrande" name="paquetegrande" type="number" disabled=”disabled”
                                style="text-align: center; width: 50%;">
                            <button id="suma"
                                style="border-radius: 10px; width: 25px; background-color: rgba(255, 166, 0, 0.651);">+</button>
                        </div>
                    </div>
                </div>
                <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
                    <div class="card-center card-title">
                        <br>
                        <h4 style="text-align: center; color : rgb(233, 164, 35)">Paquete mediano</h4>
                    </div>
                    <div class="card-body card-center">
                        <p style="text-align: center;">${data[1].coste} €</p>
                        <p style="text-align: center;">Un plato principal</p>
                        <div style="text-align: center;">
                            <button id="resta1"
                                style="border-radius: 10px; width: 25px; background-color:  rgba(255, 166, 0, 0.651);">-</button>
                            <input id="paquetemediano" name="paquetemediano" type="number" disabled=”disabled”
                                style="text-align: center; width: 50%;">
                            <button id="suma1"
                                style="border-radius: 10px; width: 25px; background-color:  rgba(255, 166, 0, 0.651);">+</button>
                        </div>
                    </div>
                </div>
                <div class="card" style="background-color: rgba(243, 243, 243, 0.616); border-radius: 10px;">
                    <div class="card-center card-title">
                        <br>
                        <h4 style="text-align: center; color : rgb(233, 164, 35)">Paquete pequeño</h4>
                    </div>
                    <div class="card-body card-center">
                        <p style="text-align: center;">${data[2].coste} €</p>
                        <p style="text-align: center;">Uno o dos entrantes </p>
                        <div style="text-align: center;">
                            <button id="resta2"
                                style="border-radius: 10px; width: 25px; background-color: rgba(255, 166, 0, 0.651);">-</button>
                            <input id="paquetepequeño" name="paquetepequeño" type="number" disabled=”disabled”
                                style="text-align: center; width: 50%;">
                            <button id="suma2"
                                style="border-radius: 10px; width: 25px; background-color:  rgba(255, 166, 0, 0.651);">+</button>
                        </div>
                    </div>
                </div>
                <br>
                <div class="card text-center"
                    style="background-color: rgba(240, 248, 255, 0); border: 0px solid rgba(255, 255, 255, 0);">
                    <div class="row">
                        <div class="text-center col-12 col-md-12">
                            <button class="enviar " id="myBtn" type="button" style="width: 200px;" data-toggle="modal"
                                data-target="#exampleModal"><i class="far fa-bookmark"></i> Pagar</button>
                        </div>
                    </div>
                    <br>
                </div>
            </div>
        </div>
        <br>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Información sobre tu pedido</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Tipo de paquete</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Precio</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Paquete grande</td>
                                    <td>1</td>
                                    <td>${data[2].coste}</td>
                                </tr>
                                <tr>
                                    <td>Paquete mediano</td>
                                    <td>0</td>
                                    <td>${data[1].coste}</td>
                                </tr>
                                <tr>
                                    <td>Paquete pequeño</td>
                                    <td>3</td>
                                    <td>${data[0].coste}</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Precio Total</th>
                                    <th></th>
                                    <th colspan="2">${data[0].coste + data[1].coste + data[2].coste}</th>
                                </tr>
                                </tfood>
                        </table>
                        <hr>
                        <p>Tu pedido estara listo en 45 minutos.</p>
                        <div class="form-check">
                            <input type="radio" name="pagartarjeta" id="sitarjeta" onclick="javascript:pagartarjeta();"
                                checked>
                            <label class="form-check-label" for="exampleRadios2">Pagar en efectivo.</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" name="pagartarjeta" id="tarjeta" onclick="javascript:pagartarjeta();">
                            <label class="form-check-label" for="exampleRadios1">Pagar con tarjeta.</label>
                        </div>

                        <div id="myDIV" style="display:none;">
                            <br>
                            <p>El pago se realizara en la tarjeta: **** **** **** 5724</p>
                            <a href="#">
                                <p>Desea cambiar de tarjeta?</p>
                            </a>
                        </div>
                    </div>
                    <div class="modal-footer row text-center">
                        <div class="col-12 col-md-12">
                            <button type="button" class="enviar" style="width: 200px;"><i class="fab fa-amazon-pay"></i>
                                Pagar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>*/