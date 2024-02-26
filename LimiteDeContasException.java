package br.com.desafiovainubank.vainaweb.backendt3;

public class LimiteDeContasException extends Exception{

	public String getMessage() {
		return "Uma pessoa nao pode ter mais de uma conta do mesmo tipo";
	}
}
