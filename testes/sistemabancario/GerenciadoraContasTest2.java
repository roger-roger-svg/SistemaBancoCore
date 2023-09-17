package sistemabancario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Funcao para fazer o teste basico de transferencia bancaria  de um  valor insuficiente de uma 
 * conta de origem para uma conta destino
 * 
 * 
 * @author Roger Andrade
 * @date 15/09/2023
 */

public class GerenciadoraContasTest2 {
	
	private GerenciadoraContas gerContas;
	
	private int idConta01 = 1;
	private int idConta02 = 2;
	
	private ContaCorrente conta01;
	private ContaCorrente conta02;
	
	@Before
	public void setUp() {
		conta01 = new ContaCorrente(idConta01, 0, true);
		conta02 = new ContaCorrente(idConta02, 0, true);
		
		List<ContaCorrente> contaDoBanco = new ArrayList<ContaCorrente>();
		contaDoBanco.add(conta01);
		contaDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contaDoBanco);
		
	}
	
	@After
	public void tearDown() {
		/**** Desmontagem do cenário global ****/
		gerContas.limpa();
	}
	
	@Test
	public void testTransfereValor1() {
		/*===== Execucao da regra de negocio a ser testada ==== */
		conta01.setSaldo(200);
		conta02.setSaldo(0);
		
		boolean resultadoTransferencia = gerContas.transfereValor(1,50, 2);
		
		/* ==== Execucao dos testes pelo JUnit para Analises e Verificações ===== */
		//assertThat(resultadoTransferencia, is(true));
		assertTrue(resultadoTransferencia);

		// Verificação se as contas existem.
		assertNotEquals(gerContas.pesquisaConta(1), null);
		assertNotEquals(gerContas.pesquisaConta(2), null);
		
		assertThat(conta01.getSaldo(), is(150.0));
		assertThat(conta02.getSaldo(), is(50.0));	
		
	}
	
	@Test
	public void testTransfereValor2() {
		/*===== Execucao da regra de negocio a ser testada ==== */
		conta01.setSaldo(100);
		conta02.setSaldo(0);
		
		boolean resultadoTransferencia = gerContas.transfereValor(1,200, 2);
		
		/* ==== Execucao dos testes pelo JUnit para Analises e Verificações ===== */
		assertTrue(resultadoTransferencia);
		
		assertThat(conta01.getSaldo(), is(-100.0));
		assertThat(conta02.getSaldo(), is(200.0));
		
	}
	
	@Test
	public void testTransfereValor3() {
		/*===== Execucao da regra de negocio a ser testada ==== */
		conta01.setSaldo(-100);
		conta02.setSaldo(0);
		
		boolean resultadoTransferencia = gerContas.transfereValor(1,200, 2);
		
		/* ==== Execucao dos testes pelo JUnit para Analises e Verificações ===== */
		//assertThat(resultadoTransferencia, is(true));
		assertTrue(resultadoTransferencia);
		
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(200.0));
		
	}
	
	@Test
	public void testTransfereValor4() {
		/*===== Execucao da regra de negocio a ser testada ==== */
		conta01.setSaldo(-100);
		conta02.setSaldo(-100);
		
		boolean resultadoTransferencia = gerContas.transfereValor(1,200, 2);
		
		/* ==== Execucao dos testes pelo JUnit para Analises e Verificações ===== */
		//assertThat(resultadoTransferencia, is(true));
		assertTrue(resultadoTransferencia);
		
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(100.0));
		
	}
}
