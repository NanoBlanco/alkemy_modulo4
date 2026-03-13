CREATE TABLE categorias (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL
);

CREATE TABLE productos (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	precio INT NOT NULL,
	stock INT NOT NULL,
	categoria_id BIGINT,
	CONSTRAINT fk_categoria
	FOREIGN KEY (categoria_id)
	REFERENCES categorias(id)
);