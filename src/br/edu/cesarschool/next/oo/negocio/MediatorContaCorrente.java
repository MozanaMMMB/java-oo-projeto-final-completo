package br.edu.cesarschool.next.oo.negocio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

public class MediatorContaCorrente {
	private static final String MSG_CONTA_NAO_EXISTENTE = "Conta n�o existente";
	private DAOContaCorrente daoCc = new DAOContaCorrente();
	public String incluir(ContaCorrente conta) {
		if (conta == null) {
			return "Conta n�o informada"; 
		} else if (stringNulaOuVazia(conta.getNumero())) {
			return "N�mero n�o informado";
		} else if (stringNulaOuVazia(conta.getNomeCorrentista())) {
			return "Nome correntista n�o informado"; 
		} else if (conta.getNumero().length() < 5 || conta.getNumero().length() > 8) {
			return "Tamanho do n�mero inv�lido";
		} else if (conta.getNomeCorrentista().length() > 60) {
			return "Tamanho do nome do correntista inv�lido";		
		} 
		/* PRINC�PIO DA SUBSTITUI��O E CASTING 
		 * Aqui se verifica se conta representa um objeto
		 * do tipo ContaPoupanca. Se for o caso, faz-se
		 * um CASTING para ContaPoupanca a fim de se 
		 * validar o percentual de b�bus.
		 */
		if (conta instanceof ContaPoupanca) {
			ContaPoupanca cp = (ContaPoupanca)conta;
			if (cp.getPercentualBonus() < 0) {
				return "Percentual de b�nus inv�lido";
			}
		} 
		boolean ret = daoCc.incluir(conta);
		if (!ret) {
			return "Conta j� existente";
		} else {
			return null;
		}		
	}
	public String creditar(double valor, String numero) {
		String msg = validarCreditarDebitar(numero, valor);
		if (msg != null) {
			return msg;
		} else {
			ContaCorrente conta = daoCc.buscar(numero);
			if (conta == null) {
				return MSG_CONTA_NAO_EXISTENTE;
			} else {
				conta.creditar(valor);
				daoCc.alterar(conta);
				return null;
			}
		}	
	}
	public String debitar(double valor, String numero) {
		String msg = validarCreditarDebitar(numero, valor);
		if (msg != null) {
			return msg;
		} else {
			ContaCorrente conta = daoCc.buscar(numero);
			if (conta == null) {
				return MSG_CONTA_NAO_EXISTENTE;
			} else {
				if (conta.getSaldo() < valor) {
					return "Saldo insuficiente";
				} else {
					conta.debitar(valor);
					daoCc.alterar(conta);					
					return null;
				} 
			}
		}
	}
	private String validarCreditarDebitar(String numero, double valor) {
		if (stringNulaOuVazia(numero)) {
			return "N�mero n�o informado"; 
		} else if (valor < 0) {
			return "Valor negativo";
		} else {
			return null;
		}
	}
	public ContaCorrente buscar(String numero) {
		if (stringNulaOuVazia(numero)) {
			return null; 
		} else {		
			return daoCc.buscar(numero);
		}
	}
	public List<ContaCorrente> gerarRelatorioGeral() {
		ContaCorrente[] contas = daoCc.buscarTodos();
		List<ContaCorrente> listaContas = Arrays.asList(contas);
		Collections.sort(listaContas, new ComparadorContaCorrentePorSaldo()); 
		return listaContas;
		
	}
	private boolean stringNulaOuVazia(String valor) {
		return valor == null || valor.trim().equals("");
	}
}
