package sistemabancario;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Matheus Correia
 * @date 22/09/2023
 */
public class GerenciadoraClientesTest3 {

	private GerenciadoraClientes gerClientes;

	private int idCliente01 = 1;
	private int idCliente02 = 2;

	@Before
	public void setUp() {
		//************* Montagem do cenário global **********//
		Cliente cliente01 = new Cliente(idCliente01, "Joao da Silva", 47, "joaodasilva@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 10, "mariadasilva@gmail.com", 1, true);

		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		//************* Desmontagem do cenário global **********//
		gerClientes.limpa();
	}

	/**
	 * Teste básico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Roger Andrade
	 * @date 01/09/2023
	 */
	
	@Test
	public void testPesquisaCliente() {
		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		/* ========== Verificações ========== */
		assertThat(cliente.getId(), is(idCliente01));

	}
	
	/**
	 * Teste básico da pesquisa de um cliente inexistente por um ID inexistente.
	 * 
	 * @author Roger Andrade
	 * @date 15/09/2023
	 */
	
	@Test
	public void testPesquisaClienteInexistente() {
		
		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(42);

		/* ========== Verificações ========== */
		assertNull(cliente);
	}
	

	/**
	 * Teste básico da remoção de um cliente a partir do seu ID.
	 * 
	 * @author Roger Andrade
	 * @date 01/09/2023
	 */
	
	@Test
	public void testRemoveCliente() {
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

		/* ========== Verificaçãos ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente02));

	}
	
	@Test
	public void testRemoveClienteInexistente() {

		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(10);

		/* ========== Verificaçãos ========== */
		assertThat(clienteRemovido, is(false));
		assertFalse(clienteRemovido);
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
	
	// Validação quando o cliente está no intervalo de idade permitida (18 - 65)
	@Test
	public void testClienteIdadePermitida1() throws IdadeNaoPermitidaException {
		Cliente cliente = new Cliente(3, "Daniel da Silva", 42, "danieldasilva@gmail.com", 3, true);
		
		boolean resultado = gerClientes.validaIdade(cliente.getIdade());
		
		assertTrue(resultado);
		
	}
	
	@Test
	public void testClienteIdadePermitida2() throws IdadeNaoPermitidaException {
		Cliente cliente = new Cliente(4, "Pedro da Silva", 18, "pedrodasilva@gmail.com", 4, true);
		
		boolean resultado = gerClientes.validaIdade(cliente.getIdade());
		
		assertTrue(resultado);
		
	}
	
	@Test
	public void testClienteIdadePermitida3() throws IdadeNaoPermitidaException {
		Cliente cliente = new Cliente(5, "Cleyton da Silva", 65, "cleytondasilva@gmail.com", 5, true);
		
		boolean resultado = gerClientes.validaIdade(cliente.getIdade());
		
		assertTrue(resultado);
		
	}
	
	// Validação quando o cliente NÃO está no intervalo de idade permitida (18 - 65)
	@Test
	public void testClienteIdadePermitida4() throws IdadeNaoPermitidaException {
		Cliente cliente = new Cliente(6, "Victor", 17, "victor@gmail.com", 6, true);
		
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	
	}
	
	@Test
	public void testClienteIdadePermitida5() throws IdadeNaoPermitidaException {
		Cliente cliente = new Cliente(7, "Matheus", 66, "matheus@gmail.com", 7, true);
		
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	
	}

}


