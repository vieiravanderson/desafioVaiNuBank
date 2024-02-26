package br.com.desafiovainubank.vainaweb.backendt3;

public class LimiteInsuficienteException extends Exception{

public String getMessage() {
		
		return "Nao foi posssivel realizar a operacao. Limite de credito insuficiente.";
	}
}
