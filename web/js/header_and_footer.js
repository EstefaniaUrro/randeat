const footerHTML = `
<footer class="page-footer font-small mt-auto text-center py-3">
    <div class="col-12 col-lg-10 mx-auto">
        <div class="row">
            <span class="col-12 col-md-4 col-lg-4">
                <i class="fas fa-home mr-3"></i>Barcelona, 08003, España
            </span>
            <span class="col-12 col-md-4 col-lg-4">
                <i class="fas fa-envelope mr-3"></i>randeat@randeat.com
            </span>
            <span class="col-12 col-md-4 col-lg-4">
                <i class="fas fa-phone mr-3"></i>+ 34 634 567 89
            </span>
        </div>
        <div class="row my-3 my-md-2">
            <div class="col-4 d-flex justify-content-around mx-auto">
                <a class="fb-ic" style="text-decoration: none;">
                    <i class="fab fa-facebook-f white-text mr-4"> </i>
                </a>
                <a class="tw-ic" style="text-decoration: none;">
                    <i class="fab fa-twitter white-text mr-4"> </i>
                </a>
                <a class="ins-ic" style="text-decoration: none;">
                    <i class="fab fa-instagram white-text"> </i>
                </a>
            </div>
        </div>
    </div>
	<div class="footer-copyright">
		© 2020 Copyright: RandEat
	</div>
</footer>
`;

function createHeaderRestaurante(usuario, restaurante) {
	return `
	<div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="./index.html">
                <img src="./img/logo.png" width="130" height="30" class="d-inline-block align-top" alt=""
                    loading="lazy">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fas fa-bars" style="color: #ffa600;"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="./pedidos.html"><i class="fas fa-shopping-cart"></i> Mis
                            pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./restaurante/restaurante_opciones.html"><i class="fas fa-utensils"></i>
                            Restaurante</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user"></i> ${restaurante.nombreRestaurante}</a>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="./restaurante/restaurante.html"><i class="fas fa-user-plus"></i> Mi
                                cuenta</a>
                            <div class="dropdown-divider"></div>
                            <a
                                class="dropdown-item"
                                href="javascript:void();"
                                onclick="logOut();"
                            >
                                <i class="fas fa-sign-out-alt"></i>
                                Cerrar sesión
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
	`;
}

function createHeaderNotLoggedIn() {
	return `
	<div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="./index.html">
                <img src="./img/logo.png" width="130" height="30" class="d-inline-block align-top" alt=""
                    loading="lazy">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fas fa-bars" style="color: #ffa600;"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="./inicio.html">
                            <i class="fas fa-user"></i> Iniciar sesión
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
	`;
}

function createHeaderCliente(usuario, cliente) {
	let nombre = cliente.nombreCompleto;

	return `
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="./index.html">
				<img src="./img/logo.png" width="130" height="30" class="d-inline-block align-top" alt=""
					loading="lazy">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<i class="fas fa-bars" style="color: #ffa600;"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link" href="./pedidos.html"><i class="fas fa-shopping-cart"></i> Mis pedidos</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fas fa-user"></i> ${nombre}</a>
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="./cliente/cliente.html"><i class="fas fa-user-plus"></i> Mi cuenta</a>
							<div class="dropdown-divider"></div>
                            <a
                                class="dropdown-item"
                                href="javascript:void();"
                                onclick="logOut();"
                            >
                                <i class="fas fa-sign-out-alt"></i>
                                Cerrar sesión
                            </a>
						</div>
					</li>
				</ul>
			</div>
		</nav>
	</div>
	`;
}

const footerBigHTML = `
<!-- Footer -->
    <footer class="page-footer font-small blue-grey lighten-5 mt-auto">

        <!-- Footer Links -->
        <div class="container text-center text-md-left mt-5">

            <!-- Grid row -->
            <div class="row mt-3 dark-grey-text">

                <!-- Grid column -->
                <div class="col-12 col-md-6 mx-auto mb-4">

                    <!-- Links -->
                    <h6 class="text-uppercase font-weight-bold">Cuenta</h6>
                    <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 100%;">
                    <p>
                        <a class="dark-grey-text" href="./inicio.html">Iniciar sesión o regístrate como cliente</a>
                    </p>
                    <p>
                        <a class="dark-grey-text" href="registro.html">Únete como restaurante</a>
                    </p>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-12 col-md-6 mx-auto mb-md-0 mb-4">

                    <!-- Links -->
                    <h6 class="text-uppercase font-weight-bold">Contactanos</h6>
                    <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 100%;">
                    <p>
                        <i class="fas fa-home mr-3"></i> Barcelona, 08003, España</p>
                    <p>
                        <i class="fas fa-envelope mr-3"></i> randeat@randeat.com</p>
                    <p>
                        <i class="fas fa-phone mr-3"></i> + 34 634 567 89</p>
                    <div class="col-8 d-flex justify-content-around mx-auto">

                        <!-- Facebook -->
                        <a class="fb-ic">
                            <i class="fab fa-facebook-f white-text mr-4"> </i>
                        </a>
                        <!-- Twitter -->
                        <a class="tw-ic">
                            <i class="fab fa-twitter white-text mr-4"> </i>
                        </a>
                        <!--Instagram-->
                        <a class="ins-ic">
                            <i class="fab fa-instagram white-text"> </i>
                        </a>

                    </div>

                </div>
                <!-- Grid column -->

            </div>
            <!-- Grid row -->

        </div>
        <!-- Footer Links -->

        <!-- Copyright -->
        <div class="footer-copyright text-center text-black-50 py-3">
            © 2020 Copyright: RandEat
        </div>
        <!-- Copyright -->

    </footer>
    <!-- Footer -->
`;

function logOut() {
    localStorage.clear();
    window.location.href = "index.html";
}

document.addEventListener('DOMContentLoaded', function (event) {
	let usuario = JSON.parse(localStorage.getItem("usuario"));
	let cliente = JSON.parse(localStorage.getItem("cliente"));
	let restaurante = JSON.parse(localStorage.getItem("restaurante"));

	let header = document.getElementById("header");
	
	// Cargar el header apropiado.
	if (cliente !== null) {
		header.innerHTML = createHeaderCliente(usuario, cliente);
	} else if (restaurante !== null) {
		header.innerHTML = createHeaderRestaurante(usuario, restaurante);
	} else {
		header.innerHTML = createHeaderNotLoggedIn();
	}

	// Cargar el footer pequeño si está definido. 
	let footerSmall = document.getElementById("footer");
	if (footerSmall !== null) {
        // Necesario para que se mantenga abajo.
        footerSmall.classList.add("mt-auto");
        footerSmall.innerHTML = footerHTML;
	}

	// Cargar el footer grande si está definido. 
	let footerBig = document.getElementById("footer-big");
	if (footerBig !== null) {
        // Necesario para que se mantenga abajo.
        footerBig.classList.add("mt-auto");
		footerBig.innerHTML = footerBigHTML;
	}
});