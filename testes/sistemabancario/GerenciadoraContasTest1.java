package sistemabancario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre contas, realizadas pela classe {@link GerenciadoraContas}
 * 
 * @author Matheus Correia
 * @date 25/08/2023
 */
public class GerenciadoraContasTest1 {
	
	/**
	 * Funcao para fazer o teste basico de transferencia bancaria  de um  valor de uma 
	 * conta de origem para uma conta destino
	 * 
	 * 
	 * @author Matheus Correia
	 * @date 25/08/2023
	 */
	
	@Test
	public void testTransfereValor() {
		
	
		ContaCorrente conta01 = new ContaCorrente(1, 200, true);
		ContaCorrente conta02 = new ContaCorrente(2, 0, true);
		
		List<ContaCorrente> contaDoBanco = new ArrayList<ContaCorrente>();
		
		contaDoBanco.add(conta01);
		contaDoBanco.add(conta02);
		
		/* ==== Preparacao para execucao ====*/
		GerenciadoraContas genContas = new GerenciadoraContas(contaDoBanco);
		
		/*===== Execucao da regra de negocio a ser testada ==== */
		boolean resultadoTransferencia = genContas.transfereValor(1,50, 2);
		
		/* ==== Execucao dos testes pelo JUnit para Analises e Verificações ===== */
		//assertThat(resultadoTransferencia, is(true));
		assertTrue(resultadoTransferencia);

		assertNotEquals(genContas.pesquisaConta(1), null);
		assertNotEquals(genContas.pesquisaConta(2), null);
		
		assertThat(conta01.getSaldo(), is(150.0));
		assertThat(conta02.getSaldo(), is(50.0));
		
		
		
	}
}
