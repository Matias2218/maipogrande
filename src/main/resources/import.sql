--Cliente Externo
insert into CLIENTES (NOMBRE_CLI,APELLIDOS_CLI,RUT_CLI,EMAIL_CLI,FECHA_CREACION_CLI,CONTRASEÑA_CLI,TELEFONO_CLI,TIPO_CLI) values ('Matias','Maldonado Oviedo','19504450-3','maldo1514@gmail.com',TO_DATE(SYSDATE),'$2a$10$ufvW1wMnnbEwWgzVK5VAtu4GLEdfwbIDTiGgODjz0BNdWATNbRLJ.','+56978851234','E');
--Cliente Interno
INSERT INTO CLIENTES (APELLIDOS_CLI, CONTRASEÑA_CLI,EMAIL_CLI,FECHA_CREACION_CLI,NOMBRE_CLI,RUT_CLI,TELEFONO_CLI,TIPO_CLI) VALUES ('Riveros Henriquez', '$2a$10$lqeUFIKH7INmZ3CbMz/0Ne6LhTy7bKNvYqtOjssxo/Bbvofcd1M6O', 'hugo@gmail.com', to_date(sysdate), 'Hugo', '19498763-3', '+56945781245', 'I');
INSERT INTO CLIENTES (APELLIDOS_CLI, CONTRASEÑA_CLI,EMAIL_CLI,FECHA_CREACION_CLI,NOMBRE_CLI,RUT_CLI,TELEFONO_CLI,TIPO_CLI) VALUES ('Sepulveda De La Cruz', '$2a$10$lqeUFIKH7INmZ3CbMz/0Ne6LhTy7bKNvYqtOjssxo/Bbvofcd1M6O', 'rog@gmail.com', to_date(sysdate), 'Rogelio', '19498763-4', '+56945781245', 'I');

--Contratos
insert into CONTRATOS (FECHA_INICIO_CONTRA,FECHA_TERMINO_CONTRA,PDF_CONTRA,TIPO_CONTRA) VALUES (TO_DATE(SYSDATE),TO_DATE(SYSDATE),NULL,'A');
insert into CONTRATOS (FECHA_INICIO_CONTRA,FECHA_TERMINO_CONTRA,PDF_CONTRA,TIPO_CONTRA) VALUES (TO_DATE(SYSDATE),TO_DATE(SYSDATE),NULL,'A');
--PRODUCTORES
insert into PRODUCTORES (NOMBRE_PROD,APELLIDO_PROD,RUT_PROD,EMAIL_PROD,FECHA_CREACION_PROD,CONTRASEÑA_PROD,TELEFONO_PROD,ID_CONTRA) values ('PANCHITO SA',NULL,'72342121-2','panchito@gmail.com',TO_DATE(SYSDATE),'$2a$10$ud7PJvIuUQRi9j3UGl7yHuYtMrmGCt11elodnq1rd.AuKd.I5qGlW','+56987464127',1);
--TRANSPORTISTAS
insert into TRANSPORTISTAS (NOMBRE_TRAN,APELLIDOS_TRAN,RUT_TRAN,EMAIL_TRAN,CAPACIDAD_CARGA, CONTRASEÑA_TRAN, FECHA_CREACION, PATENTE, REFRIGERACION, TAMAÑO, TELEFONO_TRAN, ID_CONTRA) VALUES ('Leonardo', 'Barrueto', '194987633', 'reload_13@live.cl', 3.12, '$2a$10$1T7D5O4zlAN7gHOmGCJsHeZKAhTR4.HkYpD4dPlcLqwGkif3gk1Ku', TO_DATE(SYSDATE), 'dgrt23', '1', 'Grande', '+56944940903', 2);
--ADMNISTRADOR
insert into ADMINISTRADORES(NOMBRE_ADM,APELLIDOS_ADM,RUT_ADM,EMAIL_ADM,FECHA_CREACION_ADM,TELEFONO_ADM,CONTRASEÑA_ADM) values ('ADMIN','ADMIN ADMIN','19987541-2','admin@gmail.com',to_date(SYSDATE),'+56974123458','$2a$10$gRJG7Pnu23zRvkaVGlaX2eHyaF7ujbfWdrWslijzc1g.2kzqe9MQW');
--CONSULTOR
insert into CONSULTORES(APELLIDOS_CON,CONTRASEÑA_CON,EMAIL_CON,FECHA_CREACION_CON,NOMBRE_CON,RUT_CON,TELEFONO_CON,ID_ADM) VALUES ('Rodriguez','$2a$10$cVcZnc6fQrIQxNJ0p7SiyOHOR/TfYvYyDgcXZkPWfia6nKRdNpJ3m','vrodriguez@gmail.com',to_date(sysdate),'Victoria','19847967-4','+56914213574',1);
--PRODUCTOS
insert into PRODUCTOS(CALIDAD_PRODU,FECHA_INGRESO_PRODU,IMAGEN_PRODU,NOMBRE_PRODU,UNIDAD_MASA_PRODU,PRECIO_PRODU,STOCK_PRODU,TIPO_COMERCIALIZACION_PRODU,ID_PROD) values (5,TO_DATE(SYSDATE),EMPTY_BLOB(),'Manzana','KG',50,5000,'E',1);
--SOLICITUDES
insert into SOLICITUDES(DESCRIPCION_SOL,DIRECCION_DESTINO_SOL,ESTADO_SOL,FECHA_EMISION_SOL,FECHALIMITE_SOL,PAIS_DESTINO_SOL,ID_CLI) VALUES ('Test','test 246 calle yerbas','A',SYSDATE,SYSDATE,'AR',1);
--PRODUCTOS SOLICITADOS
insert into PRODUCTOS_SOLICITADOS(CANTIDAD_PRODS,NOMBRE_PRODS,UNIDAD_PRODS,ID_SOL) VALUES (50,'Platanos','T',1);
insert into PRODUCTOS_SOLICITADOS(CANTIDAD_PRODS,NOMBRE_PRODS,UNIDAD_PRODS,ID_SOL) VALUES (300,'Manzanas','KG',1);
--VENTAS
insert into VENTAS(PAIS_ORIGEN,ESTADO_VENTA,TIPO_VENTA,ID_SOL,ID_ADM) values ('Chile','P','E',1,1);

