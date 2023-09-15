package sistemabancario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestaSistemaBancario{

	static GerenciadoraClientes gerClientes;
	static GerenciadoraContas gerContas;
	
	public static void main(String[] args) {
		
		inicializaSistemaBancario(); // criando algumas contas e clientes ficticios
		
		try (Scanner sc = new Scanner(System.in)) {
			boolean continua = true;
			
			while (continua){
				
				printMenu();
				
				int opcao = sc.nextInt();
				
				switch (opcao) {
				// Consultar por um cliente
				case 1:
					System.out.print("Digite o ID do cliente: ");
					int idCliente = sc.nextInt();
					Cliente cliente = gerClientes.pesquisaCliente(idCliente);
					
					if(cliente != null)
						System.out.println(cliente.toString());
					else
						System.out.println("Cliente nao encontrado!");
					
					pulalinha();
					break;

				// Consultar por uma conta corrente
				case 2:
					System.out.print("Digite o ID da conta: ");
					int idConta = sc.nextInt();
					ContaCorrente conta = gerContas.pesquisaConta(idConta);
					
					if(conta != null)
						System.out.println(conta.toString());
					else
						System.out.println("Conta nao encontrado!");
					
					pulalinha();
					break;

				// Ativar um cliente
				case 3:
					
					System.out.print("Digite o ID do cliente: ");
					int idCliente2 = sc.nextInt();
					Cliente cliente2 = gerClientes.pesquisaCliente(idCliente2);
					
					if(cliente2 != null){
						cliente2.setAtivo(true);
						System.out.println("Cliente ativado com sucesso!");
					}
					else
						System.out.println("Cliente nao encontrado!");
				
					pulalinha();
					break;
					
				// Desativar um cliente
				case 4:
					
					System.out.print("Digite o ID do cliente: ");
					int idCliente3 = sc.nextInt();
					Cliente cliente3 = gerClientes.pesquisaCliente(idCliente3);
					
					if(cliente3 != null){
						cliente3.setAtivo(false);
						System.out.println("Cliente desativado com sucesso!");
					}
					else
						System.out.println("Cliente nao encontrado!");
					
					pulalinha();
					break;
				
				// Sair
				case 5:
					continua = false;
					System.out.println("################# Sistema encerrado #################");
					break;
					
				default:
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					break;
					
				} 
				
			}
		}
	
		
	}

	private static void pulalinha() {
		System.out.println("\n");
	}

	/**
	 * Imprime menu de opcoes do nosso sistema bancario
	 */
	private static void printMenu() {
		
		System.out.println("O que voce deseja fazer? \n");
		System.out.println("1) Consultar por um cliente");
		System.out.println("2) Consultar por uma conta corrente");
		System.out.println("3) Ativar um cliente");
		System.out.println("4) Desativar um cliente");
		System.out.println("5) Sair");
		System.out.println();
		
	}

	/**
	 * Metodo que cria e insere algumas contas e clientes no sistema do banco,
	 * apenas para realizacao de testes manuais atraves do metodo main acima.
	 */
	private static void inicializaSistemaBancario() {
		//Criar listas
		List<ContaCorrente> contasDoBanco = new ArrayList<ContaCorrente>();
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		
		//Criando e inserindo contas
		ContaCorrente conta1 = new ContaCorrente(1, 0, false);
		ContaCorrente conta2 = new ContaCorrente(2, 1000, true);
		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		
		//Criando e inserindo clientes
		Cliente clientea = new Cliente(1,"Joao da Silva", 20, "joaodasilva@gmail.com",conta2.getId(),true);
		Cliente clienteb = new Cliente(2,"Maria da Silva", 18, "mariadasilva@gmail.com",conta1.getId(),false);
		clientesDoBanco.add(clientea);
		clientesDoBanco.add(clienteb);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		gerContas = new GerenciadoraContas(contasDoBanco);

	}
}