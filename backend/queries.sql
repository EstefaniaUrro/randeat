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

INSERT INTO paquete VALUES (1, 'Entrante');
INSERT INTO paquete VALUES (2, 'Primer plato');
INSERT INTO paquete VALUES (3, 'Segundo plato');
INSERT INTO paquete VALUES (4, 'Postre');
INSERT INTO paquete VALUES (5, 'Menú');

INSERT INTO tipo_entrega VALUES (1, 'Enviar');
INSERT INTO tipo_entrega VALUES (2, 'Recoger');
INSERT INTO tipo_entrega VALUES (3, 'Comer ahí');

INSERT INTO usuario (correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	'bernatespinas@gmail.com',
    'tengo_hambre',
    '666666666',
    'Barcelona',
    'Carrer Marina 23'
);

INSERT INTO cliente (usuario_id_usuario, nombre_completo, codigo_postal_id_codigo_postal) VALUES (
	1,
	'Bernat Espinàs Añor',
	1
);

INSERT INTO tarjeta VALUES (1, 2349209342);

INSERT INTO cliente_tarjeta VALUES (1, 1);

INSERT INTO usuario (correo_electronico, contrasena, telefono, poblacion, direccion) VALUES (
	'gatosmuertos@gmail.com',
    'miau',
    '888889888',
    'Barcelona',
    'Carrer Marina 23'
);

INSERT INTO restaurante (usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal) VALUES (
	2,
    'cif campeador',
    'ES 1231231231231',
    'Cocido Gatileño',
    'Señor Michi',
    1
);
INSERT INTO restaurante (usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal) VALUES (
	2,
    'laksjdlaksjd',
    'ES 343434343434',
    'Natillas Pro',
    'Natialia',
    2
);
INSERT INTO restaurante (usuario_id_usuario, cif, iban, nombre_restaurante, nombre_propietario, codigo_postal_id_codigo_postal) VALUES (
	2,
    'lkj12lk3jl',
    'ES 909090909090',
    'Pizzas Melissa',
    'Melisa',
    1
);

INSERT INTO tipo_cocina VALUES (1, 'Chino');
INSERT INTO tipo_cocina VALUES (2, 'Pizza');

INSERT INTO restaurante_tipo_cocina VALUES (1, 1);
INSERT INTO restaurante_tipo_cocina VALUES (2, 1);
INSERT INTO restaurante_tipo_cocina VALUES (2, 2);
INSERT INTO restaurante_tipo_cocina VALUES (3, 2);

INSERT INTO restaurante_tipo_entrega VALUES (1, 2);
INSERT INTO restaurante_tipo_entrega VALUES (2, 1);
INSERT INTO restaurante_tipo_entrega VALUES (3, 1);
INSERT INTO restaurante_tipo_entrega VALUES (3, 3);

INSERT INTO restaurante_paquete VALUES (1, 1, 6.75);
INSERT INTO restaurante_paquete VALUES (1, 2, 12.75);

INSERT INTO pedido (cliente_id_cliente, restaurante_id_restaurante, tipo_cocina_id_tipo_cocina, tipo_entrega_id_tipo_entrega, aceptado, comentario) VALUES (
	1,
    1,
    2,
    1,
    0,
    'Crudo porfavor'
);

-- Listar pedidos con nombres info básica en texto.
SELECT pedido.id_pedido, cliente.nombre_completo AS 'nombre_cliente', restaurante.nombre_restaurante AS 'nombre_restaurante', tipo_cocina.nombre AS 'tipo_cocina', tipo_entrega.nombre AS 'tipo_entrega', pedido.comentario, pedido.aceptado
	FROM pedido INNER JOIN cliente ON cliente.id_cliente = pedido.cliente_id_cliente
    INNER JOIN usuario usuario_cliente ON usuario_cliente.id_usuario = cliente.usuario_id_usuario
    INNER JOIN tipo_entrega ON tipo_entrega.id_tipo_entrega = pedido.tipo_entrega_id_tipo_entrega
    INNER JOIN restaurante ON restaurante.id_restaurante = pedido.restaurante_id_restaurante
    INNER JOIN tipo_cocina ON tipo_cocina.id_tipo_cocina = pedido.tipo_cocina_id_tipo_cocina
    INNER JOIN usuario usuario_restaurante ON usuario_restaurante.id_usuario = restaurante.usuario_id_usuario
;

INSERT INTO pedido_paquete VALUES (1, 5);