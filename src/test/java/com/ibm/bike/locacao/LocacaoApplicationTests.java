package com.ibm.bike.locacao;

import com.ibm.bike.locacao.model.Bicycle;
import com.ibm.bike.locacao.model.dto.BicycleDTO;
import com.ibm.bike.locacao.service.BicycleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static junit.framework.TestCase.*;

import java.util.Optional;

@SpringBootTest
class LocacaoApplicationTests {

	@Autowired
	private BicycleService service;

	@Test
	public void testInserir(){
		Bicycle bike = new Bicycle();
		bike.setModelo("Specialized");
		bike.setCor("Branco");

		BicycleDTO b = service.insert(bike);
		assertNotNull(b);

		Long id = b.getId();
		assertNotNull(id);

		Optional<BicycleDTO> op = service.getBikeById(id);
		assertTrue(op.isPresent());

		b = op.get();
		assertEquals("Specialized", b.getModelo());
		assertEquals("Branco", b.getCor());

		service.delete(id);
		assertFalse(service.getBikeById(id).isPresent());
	}

}
