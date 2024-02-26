package br.com.desafiovainubank.vainaweb.backendt3;

import java.util.Date;

public class ContaPoupanca extends Conta{

	private Date aniversario;
	
	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String nome, String cpf, double saldo, int numeroConta, Date aniversario) {
		super(nome, cpf, saldo, numeroConta);
		this.aniversario = aniversario;
	}



	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	
	
	public Date getaniversario() {
		return aniversario;
	}

	@Override
	public String toString() {
		return "\n" + "Tipo de conta: Conta Poupanca " + "\n" +  "Numero da conta: " + numeroConta + "\n" + "Nome do titular: " + nome + 
				"\n" + "CPF: " + cpf + "\n" + "Saldo: R$" + saldo + "\n" + "Data de nascimento: " + aniversario + "\n";
	}
	
	private double calcularJurosSobreSaque(double valor) {
		return valor *= 0.02;
	}
	
	@Override
	public void realizarSaque(double valor) throws SaldoInsuficienteException {
		
		if(verificarPossibilidadeOperacao(valor)) {
			
			this.saldo -= (valor + calcularJurosSobreSaque(valor));
			System.out.println("Saque de R$" + valor + " realizado com sucesso");
			System.out.println("Com juros  de 2%. Novo valor em conta: R$" + this.saldo);
		}
		else{
			throw new SaldoInsuficienteException();
		}

	}

	
	
}

	

