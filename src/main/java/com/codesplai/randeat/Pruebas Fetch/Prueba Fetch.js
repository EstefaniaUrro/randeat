
const url = 'http://localhost:8080/cliente/getById/1';
const urlr = 'http://localhost:8080/tipoCocina/getById/1';
const url2 = 'https://pokeapi.co/api/v2/pokemon/1';
const url3 = 'http://localhost:8080/tipoCocina/getInFilter/1/1';
const url4 = 'http://localhost:8080/tipoCocina/getAll'

fetch(url4)
.then(response => response.json())
.then(data => {
  let element = document.getElementById("element")
  element.innerHTML = `
  <p>${data[0].nombre}</p>
  <p>${data[1].nombre}</p>`;
  console.log(data)
})
.catch(err => console.log(err));
