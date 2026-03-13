package com.reinaldo.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void crearProducto_ok() throws Exception {
		String json = """
				{
				"nombre":"Laptop",
				"precio":1500,
				"stock":10,
				"catgeoriaId":1
				}
				""";
		mockMvc.perform(post("/api/productos")
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	void crearProducto_error_validacion() throws Exception {
		String json = """
				{
				"precio":1500
				}
				""";
		mockMvc.perform(post("/api/productos")
				.content(json))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void obtenerProductos_ok() throws Exception {
		mockMvc.perform(get("/api/productos"))
		.andExpect(status().isOk());
	}
}
