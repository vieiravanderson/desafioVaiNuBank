package br.com.desafiovainubank.vainaweb.backendt3;

import java.util.Objects;

public class Conta {

	protected String nome;
	protected String cpf;
	protected double saldo;
	protected int numeroConta;
	
	public Conta(String nome, String cpf, double saldo, int numeroConta) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
	}


	public Conta() {

	}

	

	@Override
	public boolean equals(Object obj){
		if (this.cpf == obj)
			return true;
		if (obj == null)
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(cpf, other.cpf) || Objects.equals(numeroConta, other.numeroConta);
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	

	public int getNumeroConta() {
		return numeroConta;
	}


	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}


	@Override
	public String toString() {
		return "Conta [Nome=" + nome + ", cpf=" + cpf + ", saldo="
				+ saldo + "]";
	}
	
	public boolean verificarPossibilidadeOperacao(double valor) {
		if(this.saldo >= valor) {
			return true;
		} else {
			return false;
		}
	}

	public void realizarSaque(double valor) throws SaldoInsuficienteException {
		if(verificarPossibilidadeOperacao(valor)) {
			this.saldo -= valor;
			System.out.println("Saque de R$" + valor + " realizado com sucesso");
			System.out.println("Novo valor em conta: R$" + this.saldo);
		}
		else{
			throw new SaldoInsuficienteException();
		}

	}
	
	public void realizarDeposito(double valor) {
		this.saldo += valor;
	}
	
	public void realizarTransferencia(Conta conta, double valor) throws SaldoInsuficienteException{
		if(verificarPossibilidadeOperacao(valor)) {
			System.out.println("Transferencia de R$" + valor + " relizada com sucesso.");
			conta.saldo += valor;
			this.saldo -= valor;
			System.out.println("Novo valor em conta: R$" + this.saldo);
		} else {
			throw new SaldoInsuficienteException();
		}
	}
}
