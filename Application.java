package br.com.desafiovainubank.vainaweb.backendt3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;

public class Application {

	static Set<ContaPoupanca> contasPoupanca;
	static Set<ContaCorrente> contasCorrente;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		contasPoupanca = new HashSet<ContaPoupanca>();
		
		contasCorrente = new HashSet<ContaCorrente>();
		
		menu();
	}
	
	public static void menu() {
		System.out.println("\n");
		System.out.println(" 1 - Criar Conta Poupanca ");
        System.out.println(" 2 - Criar Conta Corrente ");
        System.out.println(" 3 - Mostrar todas as contas ");
        System.out.println(" 4 - Buscar Conta Poupanca ");
        System.out.println(" 5 - Buscar Conta Corrente ");
        System.out.println(" 6 - Depositar em Conta Poupanca ");
        System.out.println(" 7 - Depositar em Conta Corrente ");
        System.out.println(" 8 - Sacar de uma Conta Poupanca ");
        System.out.println(" 9 - Sacar de uma Conta Corrente ");
        System.out.println(" 10 - Transferir de uma Conta Poupanca ");
        System.out.println(" 11 - Transferir de uma Conta Corrente ");
        System.out.println(" 12 - Excluir uma Conta Poupanca ");
        System.out.println(" 13 - Excluir uma Conta Corrente ");
        System.out.println(" 14 - Editar uma Conta Poupanca ");
        System.out.println(" 15 - Editar uma Conta Corrente ");
        System.out.println(" 16 - Sair ");
        
        int opcao = 0;
        try {
        	opcao = sc.nextInt();
        } catch(InputMismatchException e){
        }
        
        sc.nextLine();
        
        switch(opcao) {
        case 1:
        	try {
				criarContaPoupanca();
			} catch (LimiteDeContasException e) {
				System.out.println(e.getMessage());
				menu();
			}
        	break;
        	
        case 2:
        	try {
				criarContaCorrente();
			} catch (LimiteDeContasException e) {
				System.out.println(e.getMessage());
				menu();
			}
        	break;
        	
        case 3:
        	listarContas();
        	break;
        case 4:
        	int numeroContaPoupanca = 0;
        	try {
        		System.out.println("Informe o numero da conta que deseja buscar: ");
        		numeroContaPoupanca = sc.nextInt();
        	} catch(InputMismatchException e) {
        	}
        	sc.nextLine();
        	
        	if(buscarContaPoupanca(numeroContaPoupanca) == null) {
        		System.out.println("Conta nao encontrada");
        	} else {
        		System.out.println(buscarContaPoupanca(numeroContaPoupanca));
        	}
        	menu();
        	break;
        case 5:
        	int numeroContaCorrente = 0;
        	try {
        		System.out.println("Informe o numero da conta que deseja buscar: ");
        		numeroContaCorrente = sc.nextInt();
        	} catch(InputMismatchException e) {
        	}
        	sc.nextLine();
        	
        	if(buscarContaCorrente(numeroContaCorrente) == null){
        		System.out.println("Conta nao encontrada");
        	} else {
        		System.out.println(buscarContaCorrente(numeroContaCorrente));
        	}
        	menu();
        	break;
        case 6:
        	depositarContaPoupanca();
        	break;
        case 7:
        	depositarContaCorrente();
        	break;
        case 8:
        	sacarContaPoupanca();
        	break;
        case 9:
        	sacarContaCorrente();
        	break;
        case 10:
        	transferirDeContaPoupanca();
        	break;
        case 11:
        	transferirDeContaCorrente();
        	break;
        case 12:
        	excluirContaPoupanca();
        	break;
        case 13:
        	excluirContaCorrente();
        	break;
        case 14:
        	editarContaPoupanca();
        	break;
        case 15:
        	editarContaCorrente();
        	break;
        case 16:
        	System.out.println("FIM");
        	System.exit(0);
        default:
        	System.out.println("Opcao invalida");
        	menu();
        }
        
        
	}
	

	public static void criarContaPoupanca()  throws LimiteDeContasException{
		
		
		System.out.println("Insira o nome do titular: ");
		String nome = sc.nextLine();
		System.out.println("Insira o CPF: ");
		String cpf = sc.nextLine();
		double saldo = 0;
		boolean erroSaldo = true;
		do {
			try {
				System.out.println("Insira o saldo: R$");
				saldo = sc.nextDouble();
				erroSaldo = false;
			} catch(InputMismatchException e){
				System.out.println("Erro. O saldo deve conter apenas numeros.");
			}
			sc.nextLine();
		}while(erroSaldo);
		
		Random numero = new Random();
		int numeroConta = numero.nextInt(999);
		
		boolean erroData = true;
		Date data = null;
		do {
			System.out.println("Insira a data de nascimento (dia/mes/ano): ");
			String dataAniversario = sc.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
			
			try {
				data = sdf.parse(dataAniversario);
				erroData = false;
			} catch (ParseException e) {
				System.out.println("Favor inserir a data no formato dd/mm/aaaa");
			}
		}while(erroData);
		
		
		ContaPoupanca novaConta = new ContaPoupanca(nome, cpf, saldo, numeroConta, data);
		

		for(ContaPoupanca conta: contasPoupanca) {
			if(novaConta.equals(conta)) {
				throw new LimiteDeContasException();
			}
				
		}
		
		contasPoupanca.add(novaConta);
		System.out.println("Conta criada com sucesso");
		System.out.println("O numero da sua nova conta: " + novaConta.getNumeroConta());
		
		menu();
			
		
	}
	
	public static void  criarContaCorrente() throws LimiteDeContasException{
		
	
		System.out.println("Insira o nome do titular: ");
		String nome = sc.nextLine();
		System.out.println("Insira o CPF: ");
		String cpf = sc.nextLine();
		double saldo = 0;
		boolean erroSaldo = true;
		do {
			try {
				System.out.println("Insira o saldo: R$");
				saldo = sc.nextDouble();
				erroSaldo = false;
			} catch(InputMismatchException e){
				System.out.println("Erro. O saldo deve conter apenas numeros.");
			}
			sc.nextLine();
		}while(erroSaldo);
		
		double limite = 0;
		boolean erroLimite = true;
		do {
			try {
				System.out.println("Insira o limite: R$");
				limite = sc.nextDouble();
				erroLimite = false;
			} catch(InputMismatchException e){
				System.out.println("Erro. O limite deve conter apenas numeros.");
			}
			sc.nextLine();
		}while(erroLimite);
		
		Random numero = new Random();
		int numeroConta = numero.nextInt(999);
		
		ContaCorrente novaConta = new ContaCorrente(nome, cpf, saldo, numeroConta, limite);
		
		for(ContaCorrente conta: contasCorrente) {
			if(novaConta.equals(conta)) {
				throw new LimiteDeContasException();
			}
				
		}
		
		contasCorrente.add(novaConta);
		System.out.println("Conta criada com sucesso");
		System.out.println("O numero da sua nova conta: " + novaConta.getNumeroConta());
		
		menu();
		
	}
	
	public static void listarContas() {
		System.out.println(contasCorrente);
    	System.out.println(contasPoupanca);
    	menu();
	}
	
	public static ContaPoupanca buscarContaPoupanca(int numeroConta) {
		ContaPoupanca contaBuscar = null;
		if(contasPoupanca.size() > 0) {
			for(ContaPoupanca conta : contasPoupanca) {
				if(conta.getNumeroConta() == numeroConta) {
					contaBuscar = conta;
				}
			
			}
		} 
		return contaBuscar;
		
		
	}
	
	public static ContaCorrente buscarContaCorrente(int numeroConta) {
		ContaCorrente contaBuscar = null;
		if(contasCorrente.size() > 0) {
			for(ContaCorrente conta : contasCorrente) {
				if(conta.getNumeroConta() == numeroConta) {
					return conta;
				}
			}
		}
		return contaBuscar;
		
	}
	
	public static void depositarContaPoupanca() {
		int numeroContaPoupanca = 0;
		try {
			System.out.println("Informe o numero da conta: ");
			numeroContaPoupanca = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		
		ContaPoupanca conta = buscarContaPoupanca(numeroContaPoupanca);
		if(conta != null) {
			System.out.println("Informe o valor a ser depositado: R$");
			double valorDeposito = sc.nextDouble();
			conta.realizarDeposito(valorDeposito);
			System.out.println("Deposito de R$" + valorDeposito + " realizado com sucesso");
		} else {
			System.out.println("Conta nao encontrada");
		}
		
		menu();
	}
	
	public static void depositarContaCorrente() {
		int numeroContaCorrente = 0;
		try {
			System.out.println("Informe o numero da conta: ");
			numeroContaCorrente = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		
		ContaCorrente conta = buscarContaCorrente(numeroContaCorrente);
		if(conta != null) {
			System.out.println("Informe o valor a ser depositado: R$");
			double valorDeposito = sc.nextDouble();
			conta.realizarDeposito(valorDeposito);
			System.out.println("Deposito de R$" + valorDeposito + " realizado com sucesso");
		} else {
			System.out.println("Conta nao encontrada");
		}
		
		menu();
	}
	
	private static void sacarContaPoupanca() {
		int numeroContaPoupanca = 0;
		try {
			System.out.println("Informe o numero da conta: ");
			numeroContaPoupanca = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		
		ContaPoupanca conta = buscarContaPoupanca(numeroContaPoupanca);
		if(conta != null) {
			System.out.println("Informe o valor a ser sacado: R$");
			double valorSaque = sc.nextDouble();
			try {
				conta.realizarSaque(valorSaque);
			} catch (SaldoInsuficienteException e) {
				System.out.println(e.getMessage());
			}
			
		} else {
			System.out.println("Conta nao encontrada");
		}
		
		menu();
		
	}
	
	private static void sacarContaCorrente() {
		int numeroContaCorrente = 0;
		try {
			System.out.println("Informe o numero da conta: ");
			numeroContaCorrente = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		
		ContaCorrente conta = buscarContaCorrente(numeroContaCorrente);
		if(conta != null) {
			System.out.println("Informe o valor a ser sacado: R$");
			double valorSaque = sc.nextDouble();
			try {
				conta.realizarSaque(valorSaque);
			} catch (SaldoInsuficienteException e) {
				System.out.println(e.getMessage());
				try {
					conta.usarLimiteCredito();
				} catch (LimiteInsuficienteException e1) {
					System.out.println(e1.getMessage());
				}
			}
			
		} else {
			System.out.println("Conta nao encontrada");
		}
		
		menu();
	}
	
	public static void transferirDeContaPoupanca() {
		int numContaRemetente = 0;
		int numContaDestino = 0;
		System.out.println("Numero da conta que vai enviar a transferencia: ");
		try {
			numContaRemetente = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		ContaPoupanca conta = buscarContaPoupanca(numContaRemetente);
		if(conta!= null) {
			double valorASerTransferido = 0;
			int opcaoTipoConta = 0;
			do {
				System.out.println("Informe para qual tipo de conta o dinheiro sera transferido: ");
				System.out.println("[1] Conta Poupanca");
				System.out.println("[2] Conta Corrente");
				try {
					opcaoTipoConta = sc.nextInt();
				} catch(InputMismatchException e) {
					System.out.println("Opcao invalida!");
				}
				sc.nextLine();
			} while(opcaoTipoConta != 1 && opcaoTipoConta != 2);
			
			if(opcaoTipoConta == 1) {
				try {
					System.out.println("Numero da conta destino: ");
					numContaDestino = sc.nextInt();
				} catch(InputMismatchException e) {
				}
				sc.nextLine();
				ContaPoupanca contaDestino = buscarContaPoupanca(numContaDestino);
				if(contaDestino != null) {
					do {
						try {
							System.out.println("Informe o valor a ser transferido: R$");
							valorASerTransferido = sc.nextDouble();
						} catch(InputMismatchException e) {
							System.out.println("Valor invalido");
						}
						sc.nextLine();
					} while(valorASerTransferido <= 0);
					
					try {
						conta.realizarTransferencia(contaDestino, valorASerTransferido);
						
					} catch (SaldoInsuficienteException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("A conta informada nao existe");
				}
				menu();
			} else if(opcaoTipoConta == 2) {
				try {
					System.out.println("Numero da conta destino: ");
					numContaDestino = sc.nextInt();
				} catch(InputMismatchException e) {
				}
				sc.nextLine();
				ContaCorrente contaDestino = buscarContaCorrente(numContaDestino);
				if(contaDestino != null) {
					do {
						try {
							System.out.println("Informe o valor a ser transferido: R$");
							valorASerTransferido = sc.nextDouble();
						} catch(InputMismatchException e) {
							System.out.println("Valor invalido");
						}
						sc.nextLine();
					}while(valorASerTransferido <= 0);
					
					try {
						conta.realizarTransferencia(contaDestino, valorASerTransferido);
						menu();
					} catch (SaldoInsuficienteException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("A conta informada nao existe");
				}
				menu();
			}
			
		} else {
			System.out.println("A conta informada nao existe");
		}
		menu();
	}	
	
	private static void transferirDeContaCorrente() {
		int numContaRemetente = 0;
		int numContaDestino = 0;
		System.out.println("Numero da conta que vai enviar a transferencia: ");
		try {
			numContaRemetente = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		ContaCorrente conta = buscarContaCorrente(numContaRemetente);
		if(conta!= null) {
			double valorASerTransferido = 0;
			int opcaoTipoConta = 0;
			do {
				System.out.println("Informe para qual tipo de conta o dinheiro sera transferido: ");
				System.out.println("[1] Conta Poupanca");
				System.out.println("[2] Conta Corrente");
				try {
					opcaoTipoConta = sc.nextInt();
				} catch(InputMismatchException e) {
					System.out.println("Opcao invalda!");
				}
				sc.nextLine();
			} while(opcaoTipoConta != 1 && opcaoTipoConta != 2);
			if(opcaoTipoConta == 1) {
				try {
					System.out.println("Numero da conta destino: ");
					numContaDestino = sc.nextInt();
				} catch(InputMismatchException e) {
				}
				sc.nextLine();
				ContaPoupanca contaDestino = buscarContaPoupanca(numContaDestino);
				if(contaDestino != null) {
					do {
						try {
							System.out.println("Informe o valor a ser transferido: R$");
							valorASerTransferido = sc.nextDouble();
						} catch(InputMismatchException e) {
							System.out.println("Valor invalido");
						}
						sc.nextLine();
					} while(valorASerTransferido <= 0);
					try {
						conta.realizarTransferencia(contaDestino, valorASerTransferido);
						
					} catch (SaldoInsuficienteException e) {
						System.out.println(e.getMessage());
						try {
							conta.usarLimiteCredito();
						} catch (LimiteInsuficienteException e1) {
							System.out.println(e1.getMessage());
						}
					}
				} else {
					System.out.println("A conta informada nao existe");
				}
				menu();
			} else if(opcaoTipoConta == 2) {
				try {
					System.out.println("Numero da conta destino: ");
					numContaDestino = sc.nextInt();
				} catch(InputMismatchException e) {
				}
				sc.nextLine();
				ContaCorrente contaDestino = buscarContaCorrente(numContaDestino);
				if(contaDestino != null) {
					do {
						try {
							System.out.println("Informe o valor a ser transferido: R$");
							valorASerTransferido = sc.nextDouble();
						} catch(InputMismatchException e) {
							System.out.println("Valor invalido");
						}
						sc.nextLine();
					} while(valorASerTransferido <= 0);
					try {
						conta.realizarTransferencia(contaDestino, valorASerTransferido);
						menu();
					} catch (SaldoInsuficienteException e) {
						System.out.println(e.getMessage());
						try {
							conta.usarLimiteCredito();
						} catch (LimiteInsuficienteException e1) {
							System.out.println(e1.getMessage());
						}
					}
				} else {
					System.out.println("A conta informada nao existe");
				}
				menu();
			}
			
		} else {
			System.out.println("A conta informada nao existe");
		}
		menu();
	}
	
	private static void excluirContaPoupanca() {
		int numConta = 0;
		System.out.println("Numero da conta que vai ser deletada: ");
		try {
			numConta = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		ContaPoupanca conta = buscarContaPoupanca(numConta);
		if(conta != null) {
			contasPoupanca.remove(conta);
			System.out.println("Conta deletada com sucesso!");
		} else {
			System.out.println("Conta nao encontrada no sistema");
		}
		menu();
	}
	private static void excluirContaCorrente() {
		int numConta = 0;
		System.out.println("Numero da conta que vai ser deletada: ");
		try {
			numConta = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		ContaCorrente conta = buscarContaCorrente(numConta);
		if(conta != null) {
			contasCorrente.remove(conta);
			System.out.println("Conta deletada com sucesso!");
		} else {
			System.out.println("Conta nao encontrada no sistema");
		}
		menu();
		
	}
	
	private static void editarContaPoupanca() {
		int numConta = 0;
		System.out.println("Numero da conta que vai ser editada: ");
		try {
			numConta = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		ContaPoupanca conta = buscarContaPoupanca(numConta);
		if(conta != null) {
			System.out.println("Insira as novas informacoes: ");
			System.out.println("Insira o nome do titular: ");
			String nome = sc.nextLine();
			System.out.println("Insira o CPF: ");
			String cpf = sc.nextLine();
			double saldo = 0;
			boolean erroSaldo = true;
			do {
				try {
					System.out.println("Insira o saldo: R$");
					saldo = sc.nextDouble();
					erroSaldo = false;
				} catch(InputMismatchException e){
					System.out.println("Erro. O saldo deve conter apenas numeros.");
				}
				sc.nextLine();
			}while(erroSaldo);
			
			boolean erroData = true;
			Date data = null;
			do {
				System.out.println("Insira a data de nascimento (dia/mes/ano): ");
				String dataAniversario = sc.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
				
				try {
					data = sdf.parse(dataAniversario);
					erroData = false;
				} catch (ParseException e) {
					System.out.println("Favor inserir a data no formato dd/mm/aaaa");
				}
			}while(erroData);
			
			conta.setNome(nome);
			conta.setCpf(cpf);
			conta.setSaldo(saldo);
			conta.setAniversario(data);
			
			System.out.println("Informacoes atualizadas com sucesso");
			
		} else {
			System.out.println("Conta nao encontrada no sistema");
		}
		menu();
	}

	private static void editarContaCorrente() {
		int numConta = 0;
		System.out.println("Numero da conta que vai ser editada: ");
		try {
			numConta = sc.nextInt();
		} catch(InputMismatchException e) {
		}
		sc.nextLine();
		ContaCorrente conta = buscarContaCorrente(numConta);
		if(conta != null) {
			System.out.println("Insira as novas informacoes: ");
			System.out.println("Insira o nome do titular: ");
			String nome = sc.nextLine();
			System.out.println("Insira o CPF: ");
			String cpf = sc.nextLine();
			double saldo = 0;
			boolean erroSaldo = true;
			do {
				try {
					System.out.println("Insira o saldo: R$");
					saldo = sc.nextDouble();
					erroSaldo = false;
				} catch(InputMismatchException e){
					System.out.println("Erro. O saldo deve conter apenas numeros.");
				}
				sc.nextLine();
			}while(erroSaldo);
			
			double limite = 0;
			boolean erroLimite = true;
			do {
				try {
					System.out.println("Insira o limite: R$");
					limite = sc.nextDouble();
					erroLimite = false;
				} catch(InputMismatchException e){
					System.out.println("Erro. O limite deve conter apenas numeros.");
				}
				sc.nextLine();
			}while(erroLimite);
			conta.setNome(nome);
			conta.setCpf(cpf);
			conta.setSaldo(saldo);
			conta.setLimiteCredito(limite);
			
			System.out.println("Informacoes atualizadas com sucesso");
		} else {
			System.out.println("Conta nao encontrada no sistema");
		}
		menu();
	}

		
}
	

