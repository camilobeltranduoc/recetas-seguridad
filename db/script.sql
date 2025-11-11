-- Script SQL para tabla de recetas
-- Este archivo es solo para evidencia académica, no se ejecuta en la aplicación

CREATE TABLE recetas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    tipo_cocina VARCHAR(50),
    pais VARCHAR(50),
    dificultad VARCHAR(20),
    ingredientes TEXT,
    pasos TEXT
);

INSERT INTO recetas (nombre, descripcion, tipo_cocina, pais, dificultad, ingredientes, pasos) VALUES
('Paella Valenciana', 'Plato tradicional español con arroz, mariscos y azafrán', 'Mediterránea', 'España', 'Media',
'400g arroz, 500g mariscos variados, 1 pimiento rojo, 200g judías verdes, Azafrán, Caldo de pescado',
'Sofreír el pimiento y las judías verdes en aceite de oliva|Añadir el arroz y tostar ligeramente|Incorporar el caldo caliente con azafrán|Agregar los mariscos y cocinar 18-20 minutos|Dejar reposar 5 minutos antes de servir'),

('Tacos al Pastor', 'Tacos mexicanos con carne de cerdo marinada y piña', 'Mexicana', 'México', 'Fácil',
'500g carne de cerdo, 2 chiles guajillo, 1 piña, Tortillas de maíz, Cilantro, Cebolla, Limón',
'Marinar la carne con los chiles y especias por 2 horas|Asar la carne en tiras finas|Cortar la piña en cubos pequeños|Calentar las tortillas|Armar los tacos con carne, piña, cilantro y cebolla|Servir con limón al lado'),

('Ramen Tradicional', 'Sopa japonesa con fideos, caldo y diversos toppings', 'Asiática', 'Japón', 'Difícil',
'200g fideos ramen, 1L caldo de huesos, 2 huevos, 200g carne de cerdo, Alga nori, Cebollín, Salsa de soja',
'Preparar el caldo de huesos cociendo por 8-12 horas|Marinar y cocinar la carne de cerdo|Cocinar los huevos y partirlos por la mitad|Cocer los fideos según instrucciones del paquete|Calentar el caldo y añadir salsa de soja al gusto|Servir los fideos en el caldo y decorar con carne, huevo, nori y cebollín'),

('Pasta Carbonara', 'Pasta italiana con huevo, queso y panceta', 'Italiana', 'Italia', 'Fácil',
'400g pasta, 200g panceta, 3 huevos, 100g queso parmesano, Pimienta negra',
'Cocinar la pasta al dente|Dorar la panceta en una sartén|Batir huevos con queso rallado|Mezclar pasta caliente con panceta|Añadir la mezcla de huevo removiendo rápido|Servir con pimienta negra molida'),

('Ceviche Peruano', 'Pescado marinado en limón con cebolla y ají', 'Latina', 'Perú', 'Media',
'500g pescado blanco, 10 limones, 1 cebolla morada, 1 ají, Cilantro, Camote, Choclo',
'Cortar el pescado en cubos pequeños|Exprimir los limones|Cortar la cebolla en juliana|Picar el ají y cilantro|Mezclar todo y dejar marinar 15 minutos|Servir con camote y choclo');
