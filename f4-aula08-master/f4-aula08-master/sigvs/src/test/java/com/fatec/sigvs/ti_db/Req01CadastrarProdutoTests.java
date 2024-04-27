package com.fatec.sigvs.ti_db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs.model.IProdutoRepository;
import com.fatec.sigvs.model.Produto;

@SpringBootTest
class Req01CadastrarProdutoTests {
	@Autowired
	IProdutoRepository repository;

	@Test
	void ct01_cadastrar_produto_com_sucesso() {
		repository.deleteAll();
		Produto produto1 = new Produto("eletrobomba 110v", "maquina de lavar", 22.30, 10);
		Produto produto2 = new Produto("Tirante Original Brastemp E Consul De 7 A 12 Kg 11cm", "lavar louça", 3.90, 20);
		Produto produto3 = new Produto("Termoatuador Lavadora Colormaq Electrolux GE", "maquina de lavar", 29.70, 40);
		repository.saveAll(Arrays.asList(produto1, produto2, produto3));
		assertEquals(3, repository.count());
	}

	@Test
	void ct02_cadastrar_produto_descricao_invalida() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("", "maquina de lavar", 22.30, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A descricao não deve estar em branco", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct03_cadastrar_produto_custo_invalido() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("eletrobomba 110v", "maquina de lavar", -1, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("O custo deve ser maior que zero", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct_04_cadastrar_categoria_invalida() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("electrolux 2024", "", 22.30, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A categoria não deve estar em branco", e.getMessage());
			assertNull(produto1);			
		}
	}

	@Test
	void ct_05_cadastrar_categoria_nula() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("electrolux 2024", null, 22.30, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A categoria não deve estar em branco", e.getMessage());
			assertNull(produto1);			
		}
	}

	@Test
	void ct_06_cadastrar_produto_qtd_invalida() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("electrolux 2024", "maquina de lavar", 22.30, -10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A quantidade deve ser maior que zero", e.getMessage());
			assertNull(produto1);			
		}
	}

	@Test
	void ct_07_cadastrar_produto_qtd_zero() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("electrolux 2024", "maquina de lavar", 22.30, 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A quantidade deve ser maior que zero", e.getMessage());
			assertNull(produto1);			
		}
	}

	@Test
	void ct_08_cadastrar_produto_custo_zero() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("electrolux 2024", "maquina de lavar", 0, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("O custo deve ser maior que zero", e.getMessage());
			assertNull(produto1);			
		}
	}

	@Test
	void ct_09_cadastrar_produto_custo_maximo() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("electrolux 2024", "maquina de lavar", 1.7976931348623157E308, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("O custo deve ser maior que zero", e.getMessage());
			assertNull(produto1);			
		}
	}

}
