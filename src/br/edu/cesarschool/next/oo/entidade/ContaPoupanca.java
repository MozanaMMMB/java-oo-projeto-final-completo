package br.edu.cesarschool.next.oo.entidade;

public class ContaPoupanca extends ContaCorrente {

	private static final long serialVersionUID = 1L;
	private double percentualBonus;
	public ContaPoupanca() {
		
	}
	public ContaPoupanca(String numero, String nomeCorrentista, double saldo,
			double percentualBonus) {
		super(numero, nomeCorrentista, saldo);
		this.percentualBonus = percentualBonus;
	}
	
	public double getPercentualBonus() {
		return percentualBonus;
	}
	public void setPercentualBonus(double percentualBonus) {
		this.percentualBonus = percentualBonus;
	}
	public void creditar(double valor) {
		double valorComBonus = valor * (1 + percentualBonus/100);
		super.creditar(valorComBonus);
	}
	/*
	 * SOBRESCRITA
	 * Reuso do toString da superclasse. É comum esta abordagem
	 * em classes de entidades. A subclasse manda a superclasse 
	 */	
	public String toString() {
		return super.toString() + 
				"\n Perc bônus : " + percentualBonus;
	}
}
