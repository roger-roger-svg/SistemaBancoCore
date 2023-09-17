package sistemabancario;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}
 * 
 * @author Roger Andrade
 * @date 18/08/2023
 */
public class GerenciadoraClientesTest1 {

	/**
	 * Teste básico da pesquisa de um cliente a partir do seu ID
	 * 
	 * @author Roger Andrade
	 * @date 18/08/2023
	 */
	@Test
	public void testPesquisaCliente(){
		/* ==== Montagem do cenário de teste === */
		// Criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Joao da Silva", 20, "joaodasilva@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Maria da Silva", 18, "mariadasilva@gmail.com", 2, true);
		
		// Inserindo os clientes criados na lista de clientes do banco.
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		GerenciadoraClientes genClientes = new GerenciadoraClientes(clientesDoBanco);
		
		/* ==== Execução do teste === */
		Cliente cliente = genClientes.pesquisaCliente(1);
		
		/* ==== Verificação e Avaliação do teste === */
		assertThat(cliente.getId(), is(1));
	}
	
	/**
	 * Teste basico da remocao do cliente a partir do ID
	 * @author Matheus Correia
	 * @date 25/08/2023
	 */
	@Test
	public void TestRemoveCliente() {
		/* ==== Montagem do cenário de teste === */
		Cliente cliente01 = new Cliente(1, "Joao da Silva", 20, "joaodasilva@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Maria da Silva", 18, "mariadasilva@gmail.com", 2, true);
	
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		/* ===== Preparação para a Execução ======*/
		GerenciadoraClientes genClientes = new GerenciadoraClientes(clientesDoBanco);
		
		/*===== Execucao da regra de negocio a ser testada ==== */
		boolean resultadoRemocaoCliente =  genClientes.removeCliente(2);
		
		/* ==== Verificação e Avaliação do teste === */
		assertThat(resultadoRemocaoCliente,is(true));
		assertThat(genClientes.getClientesDoBanco().size(),is(1));
		assertNull(genClientes.pesquisaCliente(2));
		
	}
	
}
