package com.pruebatecnica.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.backend.controllers.BrandController;
import com.pruebatecnica.backend.models.Brand;
import com.pruebatecnica.backend.services.interfaces.IBrandService;

@WebMvcTest(BrandController.class)
class BrandControllerTest {
	
	@Autowired
	private MockMvc movkMvc;
	
	@MockBean
	private IBrandService service;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetBrand() throws Exception {
		var findById = movkMvc.perform(get("/api/brand/1").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
						.andReturn();
		System.out.println(findById.getResponse().getStatus());
		if (findById.getResponse().getStatus() != MockHttpServletResponse.SC_OK) {
			fail("Error al llamar Brand por Id");
		}
		
		var dataBrand = objectMapper.readValue(findById.getResponse().getContentAsString(), Brand.class);
		
		assertThat(dataBrand.getId()).isNotEqualTo(0); 
	}
	
	@Test
	void testGetBrandService() throws Exception {
		Optional<Brand> brandO = service.getById(1);
		if (brandO.isEmpty()) {
			fail("La marca no existe");
		}
		
		assertThat(brandO.isPresent()).isEqualTo(true);
	}

}
