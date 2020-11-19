USE pistaccio;

DELETE FROM pedido;
DELETE FROM cliente_tarjeta;

SELECT * FROM paquete;
INSERT INTO paquete VALUES (1, 'Entrante');
INSERT INTO paquete VALUES (2, 'Primer plato');
INSERT INTO paquete VALUES (3, 'Segundo plato');
INSERT INTO paquete VALUES (4, 'Postre');
INSERT INTO paquete VALUES (5, 'Menú');

SELECT * FROM tipo_entrega;
INSERT INTO tipo_entrega VALUES (1, 'Enviar');
INSERT INTO tipo_entrega VALUES (2, 'Recoger');
INSERT INTO tipo_entrega VALUES (3, 'Comer ahí');

SELECT * FROM usuario;

INSERT INTO usuario (correo_electronico, contrasena, nombre, direccion, poblacion, telefono) VALUES ('bernatespinas@gmail.com', 'tengo_hambre', 'Bernat Espinàs', 'Carrer Marina 23', 'Barcelona', '888888888');
SELECT * FROM cliente;
INSERT INTO cliente VALUES (1, 1);

SELECT * FROM tarjeta;
INSERT INTO tarjeta VALUES (1, 2349209342);

SELECT * FROM cliente_tarjeta;
INSERT INTO cliente_tarjeta VALUES (1, 1);

INSERT INTO usuario (correo_electronico, contrasena, nombre, direccion, poblacion, telefono) VALUES ('gatosmuertos@gmail.com', 'miau', 'Cocido Gatileño', 'Carrer Marina 23', 'Barcelona', '888889888');
SELECT * FROM restaurante;
INSERT INTO restaurante VALUES (1, 'ES 1231231231231', 2);

SELECT * FROM tipo_cocina;
INSERT INTO tipo_cocina VALUES (1, 'Chino');
INSERT INTO tipo_cocina VALUES (2, 'Pizza');

SELECT * FROM restaurante_tipo_cocina;
INSERT INTO restaurante_tipo_cocina VALUES (1, 1);

SELECT * FROM restaurante_tipo_entrega;
INSERT INTO restaurante_tipo_entrega VALUES (1, 2);

SELECT * FROM restaurante_paquete;
INSERT INTO restaurante_paquete VALUES (1, 1, 6.75);
INSERT INTO restaurante_paquete VALUES (1, 2, 12.75);

SELECT * FROM pedido;
INSERT INTO pedido VALUES (1, 1, 1, 2, 'Crudo porfavor', 0, 1);

-- Listar pedidos con nombres en vez de IDs.
SELECT pedido.id_pedido, usuario_cliente.nombre AS 'nombre_usuario', usuario_restaurante.nombre AS 'nombre_restaurante', tipo_entrega.nombre, pedido.comentario, pedido.aceptado, tipo_cocina.nombre AS 'tipo_cocina'
	FROM pedido INNER JOIN cliente ON cliente.id_cliente = pedido.cliente_id_cliente
    INNER JOIN usuario usuario_cliente ON usuario_cliente.id_usuario = cliente.usuario_id_usuario
    INNER JOIN tipo_entrega ON tipo_entrega.id_tipo_entrega = pedido.tipo_entrega_id_tipo_entrega
    INNER JOIN restaurante ON restaurante.id_restaurante = pedido.restaurante_id_restaurante
    INNER JOIN tipo_cocina ON tipo_cocina.id_tipo_cocina = pedido.tipo_cocina_id_tipo_cocina
    INNER JOIN usuario usuario_restaurante ON usuario_restaurante.id_usuario = restaurante.usuario_id_usuario
;

SELECT * FROM pedido_paquete;
INSERT INTO pedido_paquete VALUES (1, 5);