package br.com.desafiovainubank.vainaweb.backendt3;

import java.util.Scanner;

public class ContaCorrente extends Conta{
	
	Scanner sc = new Scanner(System.in);
	private double limiteCredito;

	public ContaCorrente(String nome, String cpf, double saldo, int numeroConta, double limiteCredito) {
		super(nome, cpf, saldo, numeroConta);
		this.limiteCredito = limiteCredito;
	}

	public ContaCorrente() {
		super();
	}


	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	public String toString() {
		return "\n" + "Tipo de conta: Conta Corrente " + "\n" +  "Limite de credito: " + limiteCredito + "\n" + "Numero da conta: " + numeroConta
				+ "\n" + "Nome do titular: " + nome + "\n" + "CPF: " + cpf + "\n" + "Saldo: R$" + saldo + "\n";
	}

	private boolean verificarLimiteCredito() {
		if(this.limiteCredito > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void usarLimiteCredito() throws LimiteInsuficienteException {
		System.out.println("Deseja utilizar o limite especial? ");
		int opcao;
		do {
			System.out.println("[1]Sim");
			System.out.println("[2]Nao");
			opcao = sc.nextInt();
		}while(opcao != 1 && opcao != 2);
		if(opcao == 1) {
			if(verificarLimiteCredito()) {
				System.out.println("Limite de R$" + this.limiteCredito + " adicionado ao saldo com sucesso");
				this.saldo += this.limiteCredito;
				System.out.println("Novo valor em conta: R$" + this.saldo);
				this.limiteCredito = 0;
			} else {
				throw new LimiteInsuficienteException();
			}
		} else {
			this.saldo = saldo;
		}
		
		
		
	}

	
}
