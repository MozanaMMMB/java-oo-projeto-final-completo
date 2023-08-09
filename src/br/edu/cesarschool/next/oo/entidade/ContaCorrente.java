package br.edu.cesarschool.next.oo.entidade;

public class ContaCorrente extends RegistroIdentificavel {

	private static final long serialVersionUID = 1L;
	private String numero;
	private String nomeCorrentista;
	private double saldo;
	public ContaCorrente() {
		
	}
	public ContaCorrente(String numero, String nomeCorrentista, double saldo) {
		super();
		this.numero = numero;
		this.nomeCorrentista = nomeCorrentista;
		this.saldo = saldo;
	}
	public String getNomeCorrentista() {
		return nomeCorrentista;
	}
	public void setNomeCorrentista(String nomeCorrentista) {
		this.nomeCorrentista = nomeCorrentista;
	}
	public String getNumero() {
		return numero;
	}
	public double getSaldo() {
		return saldo;
	}
	public void creditar(double valor) {
		saldo = saldo + valor;
	}
	public void debitar(double valor) {
		saldo = saldo - valor;
	}
	/*
	 * Implementação do toString necessária a útil para 
	 * a tela utilizar, exibindo os resultados da 
	 * busca por número e do relatório geral 
	 */
	public String toString() {
		return "CONTA NÚMERO: " + numero + 
				"\n Correntista:" + nomeCorrentista +
				"\n Saldo      : " + saldo;
	}
	@Override
	public String obterChave() {
		return numero;
	}
}
