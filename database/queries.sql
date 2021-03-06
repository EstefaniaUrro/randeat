USE pistaccio;

DELETE FROM pedido_paquete;
DELETE FROM pedido;
ALTER TABLE pedido AUTO_INCREMENT = 1;
DELETE FROM restaurante_paquete;
DELETE FROM paquete;

DELETE FROM restaurante_bebida;
DELETE FROM bebida;
ALTER TABLE bebida AUTO_INCREMENT = 1;

DELETE FROM restaurante_tipo_entrega;
DELETE FROM restaurante_tipo_cocina;
DELETE FROM tipo_cocina;
DELETE FROM tipo_cocina;
DELETE FROM tipo_entrega;
DELETE FROM restaurante;
ALTER TABLE restaurante AUTO_INCREMENT = 1;

DELETE FROM cliente_tarjeta;
DELETE FROM tarjeta;
ALTER TABLE tarjeta AUTO_INCREMENT = 1;
DELETE FROM cliente;
ALTER TABLE cliente AUTO_INCREMENT = 1;

DELETE FROM usuario;
ALTER TABLE usuario AUTO_INCREMENT = 1;

DELETE FROM codigo_postal;
ALTER TABLE codigo_postal AUTO_INCREMENT = 1;

INSERT INTO codigo_postal (numero) VALUES ('08005');
INSERT INTO codigo_postal (numero) VALUES ('08014');
INSERT INTO codigo_postal (numero) VALUES ('08028');
INSERT INTO codigo_postal (numero) VALUES ('08009');
INSERT INTO codigo_postal (numero) VALUES ('08018');
INSERT INTO codigo_postal (numero) VALUES ('08013');


INSERT INTO paquete VALUES (1, 'Pequeño', 'Un entrante');
INSERT INTO paquete VALUES (2, 'Mediano', 'Un plato principal');
INSERT INTO paquete VALUES (3, 'Grande', 'Un entrante y un plato principal');

INSERT INTO tipo_entrega VALUES (1, 'Enviar', 'Los clientes reciben la comida a su domicilio');
INSERT INTO tipo_entrega VALUES (2, 'Recoger', 'Los clientes vienen a recoger la comida');

INSERT INTO bebida (nombre) VALUES ("Coca-Cola");
INSERT INTO bebida (nombre) VALUES ("Fanta naranja");
INSERT INTO bebida (nombre) VALUES ("Agua");
INSERT INTO bebida (nombre) VALUES ("Tinto de verano Don Simón");

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	1,
	'bernatespinas@gmail.com',
    'tengo_hambre',
    '666666666',
    'Barcelona',
    'Carrer Marina 23'
);

INSERT INTO cliente (id_cliente, usuario_id_usuario, nombre_completo, codigo_postal_id_codigo_postal) VALUES (
	1,
	1,
	'Bernat Espinàs Añor',
	1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	2,
	'ismamuño@gmail.com',
    'tengo_hambre',
    '688688688',
    'Barcelona',
    'Carrer Marina 27'
);

INSERT INTO cliente (id_cliente, usuario_id_usuario, nombre_completo, codigo_postal_id_codigo_postal) VALUES (
	2,
	2,
    'Ismael Muñoz',
    1
);

INSERT INTO tarjeta VALUES (1, 2349209342);

INSERT INTO cliente_tarjeta VALUES (1, 1);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	3,
	'gatosmuertos@gmail.com',
    'miau',
    '888889888',
    'Barcelona',
    'Carrer Marina 23'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	1,
    3,
    'cif campeador',
    'ES 1231231231231',
    'Cocido Gatileño',
    'Señor Michi',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	4,
	'pizzas@melissa.com',
    'melissa',
    '888889888',
    'Barcelona',
    'Carrer Marina 23'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	2,
    4,
    'pzzmlsTODO',
    'ES 909090909090',
    'Pizzas Melissa',
    'Melisa',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	5,
	'natillas@pro.com',
    'natillas',
    '888889888',
    'Barcelona',
    'Carrer Marina 23'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	3,
    5,
    '12312TODO',
    'ES 343434343434',
    'Natillas Pro',
    'Natialia',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	6,
	'estefania@gmail.com',
    'perretes',
    '655655655',
    'Barcelona',
    'Carrer Martina 82'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	4,
    6,
    '78912ASUA',
    'ES 754896374185',
    'EstefaFood',
    'Estefania',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	7,
	'dave@gmail.com',
    'davesito',
    '674167249',
    'Barcelona',
    'Carrer Mateu 1'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	5,
    7,
    '16483NAIX',
    'ES 843715498753',
    'Hamburguesa Acción',
    'David',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	8,
	'pancho@gmail.com',
    'ohcnap',
    '674268943',
    'Barcelona',
    'Carrer Matias 46'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	6,
    8,
    '69715PAYR',
    'ES 789654125478',
    'Pistacchio Pancho',
    'Pancho',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	9,
	'nacho@gmail.com',
    'nachete',
    '684135749',
    'Barcelona',
    'Carrer Matias 2'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	7,
    9,
    '93715NAUR',
    'ES 745813469275',
    'Taglio Pancho',
    'Nacho',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	10,
	'alberto@gmail.com',
    'luna',
    '674215938',
    'Barcelona',
    'Carrer Marc 92'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	8,
    10,
    '24687CAID',
    'ES 356712486246',
    'Tenedor Alberto',
    'Alberto',
    1,
    1
);

INSERT INTO usuario (id_usuario, correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	11,
	'perrosmuertos@gmail.com',
    'gau',
    '888889988',
    'Barcelona',
    'Carrer Marina 24'
);

INSERT INTO restaurante (id_restaurante, usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal, activo) VALUES (
	9,
    11,
    'cif campeadora',
    'ES 1231231231241',
    'BurguerDog',
    'Señor Wow',
    1,
    1
);

INSERT INTO tipo_cocina VALUES (1, 'Chino');
INSERT INTO tipo_cocina VALUES (2, 'Pizza');
INSERT INTO tipo_cocina VALUES (3, 'Sushi');
INSERT INTO tipo_cocina VALUES (4, 'Hamburguesa');
INSERT INTO tipo_cocina VALUES (5, 'Pollo');
INSERT INTO tipo_cocina VALUES (6, 'Pasta');
INSERT INTO tipo_cocina VALUES (7, 'Kebab');
INSERT INTO tipo_cocina VALUES (8, 'Tacos');

INSERT INTO restaurante_tipo_cocina VALUES (1, 1);
INSERT INTO restaurante_tipo_cocina VALUES (1, 5);
INSERT INTO restaurante_tipo_cocina VALUES (2, 4);
INSERT INTO restaurante_tipo_cocina VALUES (2, 2);
INSERT INTO restaurante_tipo_cocina VALUES (3, 1);
INSERT INTO restaurante_tipo_cocina VALUES (3, 3);
INSERT INTO restaurante_tipo_cocina VALUES (4, 3);
INSERT INTO restaurante_tipo_cocina VALUES (4, 5);
INSERT INTO restaurante_tipo_cocina VALUES (5, 4);
INSERT INTO restaurante_tipo_cocina VALUES (5, 5);
INSERT INTO restaurante_tipo_cocina VALUES (6, 7);
INSERT INTO restaurante_tipo_cocina VALUES (6, 8);
INSERT INTO restaurante_tipo_cocina VALUES (7, 2);
INSERT INTO restaurante_tipo_cocina VALUES (7, 6);
INSERT INTO restaurante_tipo_cocina VALUES (8, 7);
INSERT INTO restaurante_tipo_cocina VALUES (8, 8);
INSERT INTO restaurante_tipo_cocina VALUES (9, 4);
INSERT INTO restaurante_tipo_cocina VALUES (9, 7);



INSERT INTO restaurante_tipo_entrega VALUES (1, 1);
INSERT INTO restaurante_tipo_entrega VALUES (1, 2);
INSERT INTO restaurante_tipo_entrega VALUES (2, 1);
INSERT INTO restaurante_tipo_entrega VALUES (2, 2);
INSERT INTO restaurante_tipo_entrega VALUES (3, 1);
INSERT INTO restaurante_tipo_entrega VALUES (3, 2);
INSERT INTO restaurante_tipo_entrega VALUES (4, 1);
INSERT INTO restaurante_tipo_entrega VALUES (4, 2);
INSERT INTO restaurante_tipo_entrega VALUES (5, 1);
INSERT INTO restaurante_tipo_entrega VALUES (5, 2);
INSERT INTO restaurante_tipo_entrega VALUES (6, 1);
INSERT INTO restaurante_tipo_entrega VALUES (6, 2);
INSERT INTO restaurante_tipo_entrega VALUES (7, 1);
INSERT INTO restaurante_tipo_entrega VALUES (7, 2);
INSERT INTO restaurante_tipo_entrega VALUES (8, 1);
INSERT INTO restaurante_tipo_entrega VALUES (8, 2);
INSERT INTO restaurante_tipo_entrega VALUES (9, 1);
INSERT INTO restaurante_tipo_entrega VALUES (9, 2);

INSERT INTO restaurante_paquete VALUES (1, 1, 6.75);
INSERT INTO restaurante_paquete VALUES (1, 2, 12.75);
INSERT INTO restaurante_paquete VALUES (1, 3, 15.75);

INSERT INTO restaurante_paquete VALUES (2, 1, 5.50);
INSERT INTO restaurante_paquete VALUES (2, 2, 9.50);
INSERT INTO restaurante_paquete VALUES (2, 3, 13.50);

INSERT INTO restaurante_paquete VALUES (3, 1, 7.99);
INSERT INTO restaurante_paquete VALUES (3, 2, 12.99);
INSERT INTO restaurante_paquete VALUES (3, 3, 17.99);

INSERT INTO restaurante_paquete VALUES (4, 1, 4.25);
INSERT INTO restaurante_paquete VALUES (4, 2, 8.25);
INSERT INTO restaurante_paquete VALUES (4, 3, 12.25);

INSERT INTO restaurante_paquete VALUES (5, 1, 6.55);
INSERT INTO restaurante_paquete VALUES (5, 2, 11.55);
INSERT INTO restaurante_paquete VALUES (5, 3, 15.55);

INSERT INTO restaurante_paquete VALUES (6, 1, 5.99);
INSERT INTO restaurante_paquete VALUES (6, 2, 11.99);
INSERT INTO restaurante_paquete VALUES (6, 3, 14.99);

INSERT INTO restaurante_paquete VALUES (7, 1, 7.77);
INSERT INTO restaurante_paquete VALUES (7, 2, 17.77);
INSERT INTO restaurante_paquete VALUES (7, 3, 27.77);

INSERT INTO restaurante_paquete VALUES (8, 1, 8.66);
INSERT INTO restaurante_paquete VALUES (8, 2, 12.66);
INSERT INTO restaurante_paquete VALUES (8, 3, 16.66);

INSERT INTO restaurante_paquete VALUES (9, 1, 5.65);
INSERT INTO restaurante_paquete VALUES (9, 2, 10.65);
INSERT INTO restaurante_paquete VALUES (9, 3, 13.65);

INSERT INTO restaurante_bebida VALUES (1, 1);
INSERT INTO restaurante_bebida VALUES (1, 2);
INSERT INTO restaurante_bebida VALUES (1, 3);
INSERT INTO restaurante_bebida VALUES (2, 4);
INSERT INTO restaurante_bebida VALUES (3, 1);
INSERT INTO restaurante_bebida VALUES (3, 4);

INSERT INTO pedido (id_pedido, cliente_id_cliente, restaurante_id_restaurante, tipo_cocina_id_tipo_cocina, tipo_entrega_id_tipo_entrega, direccion_envio, fecha_date, fecha_time, comentario) VALUES (
	1,
    1,
    2,
    2,
    1,
	-- direccion_envio porque tipo_entrega_id_tipo_entrega es 1, enviar
    "Calle del hambre, 23, Barcelona, 08009",
    "2020/12/16",
    "13:35:49",
    'Crudo porfavor'
);

INSERT INTO pedido (id_pedido, cliente_id_cliente, restaurante_id_restaurante, tipo_cocina_id_tipo_cocina, tipo_entrega_id_tipo_entrega, fecha_date, fecha_time, comentario) VALUES (
	2,
    1,
    2,
    2,
    2,
    "2020/12/16",
    "13:43:15",
    'hehe :P'
);

INSERT INTO pedido (id_pedido, cliente_id_cliente, restaurante_id_restaurante, tipo_cocina_id_tipo_cocina, tipo_entrega_id_tipo_entrega, direccion_envio, fecha_date, fecha_time, comentario) VALUES (
	3,
    1,
    2,
    4,
    1,
	-- direccion_envio porque tipo_entrega_id_tipo_entrega es 1, enviar
    "Calle del JavaScript, 6, Barcelona, 08009",
    "2020/12/16",
    "13:57:02",
    'Extra de salsa'
);

INSERT INTO pedido (id_pedido, cliente_id_cliente, restaurante_id_restaurante, tipo_cocina_id_tipo_cocina, tipo_entrega_id_tipo_entrega, direccion_envio, fecha_date, fecha_time, comentario) VALUES (
	4,
    2,
    2,
    2,
    1,
	-- direccion_envio porque tipo_entrega_id_tipo_entrega es 1, enviar
    "Calle HTML, 5, Barcelona, 08009",
    "2020/12/16",
    "14:02:30",
    'No mes gusta la mortadela con aceitunas'
);

-- Listar pedidos con nombres info básica en texto.
SELECT pedido.id_pedido, cliente.nombre_completo AS 'nombre_cliente', restaurante.nombre_restaurante AS 'nombre_restaurante', tipo_cocina.nombre AS 'tipo_cocina', tipo_entrega.nombre AS 'tipo_entrega', pedido.comentario
	FROM pedido INNER JOIN cliente ON cliente.id_cliente = pedido.cliente_id_cliente
    INNER JOIN usuario usuario_cliente ON usuario_cliente.id_usuario = cliente.usuario_id_usuario
    INNER JOIN tipo_entrega ON tipo_entrega.id_tipo_entrega = pedido.tipo_entrega_id_tipo_entrega
    INNER JOIN restaurante ON restaurante.id_restaurante = pedido.restaurante_id_restaurante
    INNER JOIN tipo_cocina ON tipo_cocina.id_tipo_cocina = pedido.tipo_cocina_id_tipo_cocina
    INNER JOIN usuario usuario_restaurante ON usuario_restaurante.id_usuario = restaurante.usuario_id_usuario
;

INSERT INTO pedido_paquete VALUES (1, 1, 1);
INSERT INTO pedido_paquete VALUES (1, 2, 2);
INSERT INTO pedido_paquete VALUES (1, 3, 3);

SELECT * FROM pedido;
SELECT LAST_INSERT_ID();