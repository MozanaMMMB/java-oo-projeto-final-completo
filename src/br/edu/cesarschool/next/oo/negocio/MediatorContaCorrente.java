package br.edu.cesarschool.next.oo.negocio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

public class MediatorContaCorrente {
	private static final String MSG_CONTA_NAO_EXISTENTE = "Conta não existente";
	private DAOContaCorrente daoCc = new DAOContaCorrente();
	public String incluir(ContaCorrente conta) {
		if (conta == null) {
			return "Conta não informada"; 
		} else if (stringNulaOuVazia(conta.getNumero())) {
			return "Número não informado";
		} else if (stringNulaOuVazia(conta.getNomeCorrentista())) {
			return "Nome correntista não informado"; 
		} else if (conta.getNumero().length() < 5 || conta.getNumero().length() > 8) {
			return "Tamanho do número inválido";
		} else if (conta.getNomeCorrentista().length() > 60) {
			return "Tamanho do nome do correntista inválido";		
		} 
		/* PRINCÍPIO DA SUBSTITUIÇÃO E CASTING 
		 * Aqui se verifica se conta representa um objeto
		 * do tipo ContaPoupanca. Se for o caso, faz-se
		 * um CASTING para ContaPoupanca a fim de se 
		 * validar o percentual de bôbus.
		 */
		if (conta instanceof ContaPoupanca) {
			ContaPoupanca cp = (ContaPoupanca)conta;
			if (cp.getPercentualBonus() < 0) {
				return "Percentual de bônus inválido";
			}
		} 
		boolean ret = daoCc.incluir(conta);
		if (!ret) {
			return "Conta já existente";
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
			return "Número não informado"; 
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
