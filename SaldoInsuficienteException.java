package br.com.desafiovainubank.vainaweb.backendt3;

public class SaldoInsuficienteException extends Exception{

	public String getMessage() {
		
		return "Nao foi posssivel realizar a operacao. Saldo insuficiente";
	}
}
