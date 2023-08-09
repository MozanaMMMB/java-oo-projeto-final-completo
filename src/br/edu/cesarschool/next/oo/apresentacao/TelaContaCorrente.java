package br.edu.cesarschool.next.oo.apresentacao;

import java.util.List;
import java.util.Scanner;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;
import br.edu.cesarschool.next.oo.negocio.MediatorContaCorrente;

public class TelaContaCorrente {
	
	private static final Scanner ENTRADA = new Scanner(System.in);
	private MediatorContaCorrente mediatorCc = new MediatorContaCorrente();
	public void iniciarTela() {
		int opcao = 0;
		do {
			System.out.println("Escolha uma op��o: ");
			System.out.println("1- Incluir conta");
			System.out.println("2- Creditar");
			System.out.println("3- Debitar");
			System.out.println("4- Buscar conta");
			System.out.println("5- Gerar relat�rio geral de contas");
			System.out.println("6- Sair");
			opcao = ENTRADA.nextInt();
			if (opcao == 1) {
				incluir();
			} else if (opcao == 2) {
				creditar();
			} else if (opcao == 3) {
				debitar();
			} else if (opcao == 4) {
				consultarConta();
			} else if (opcao == 5) {				
				gerarRelatorioGeralContas();
			}
			System.out.println("\n Op��o " + opcao + " processada! \n");
		} while(opcao != 6);
		System.out.println("FIM DO PROGRAMA");
	}
	private void incluir() {
		String numero = null;
		String nomeCorrentista = null;
		double percentualBonus = 0;
		String ehContaPoupanca = "";
		ContaCorrente conta = null;
		System.out.println("Digite o n�mero: ");
		numero = ENTRADA.next();
		System.out.println("Digite o nome do correntista: ");
		nomeCorrentista = ENTRADA.next();
		System.out.println("� conta poupan�a (S/N)?");
		ehContaPoupanca = ENTRADA.next();
		if ("S".equalsIgnoreCase(ehContaPoupanca)) {
			System.out.println("Digite o percentual de b�nus: ");
			percentualBonus = ENTRADA.nextDouble();
			conta = new ContaPoupanca(numero, nomeCorrentista, 0, percentualBonus);
		} else {
			conta = new ContaCorrente(numero, nomeCorrentista, 0);
		}
		
		String mensagem = mediatorCc.incluir(conta);
		if (mensagem == null) {
			System.out.println("Conta inclu�da com sucesso!");
		} else {
			System.out.println(mensagem); 
		}
	}
	private void creditar() {
		String numero = null;
		double valor = 0;
		System.out.println("Digite o n�mero: ");
		numero =  ENTRADA.next();
		System.out.println("Digite o valor: ");
		valor = ENTRADA.nextDouble();
		String mensagem = mediatorCc.creditar(valor, numero);
		if (mensagem == null) {
			System.out.println("Cr�dito realizado com sucesso!");
		} else {
			System.out.println(mensagem); 
		}		
	}
	private void debitar() {
		String numero = null;
		double valor = 0;
		System.out.println("Digite o n�mero: ");
		numero =  ENTRADA.next();
		System.out.println("Digite o valor: ");
		valor = ENTRADA.nextDouble();
		String mensagem = mediatorCc.debitar(valor, numero);
		if (mensagem == null) {
			System.out.println("D�bito realizado com sucesso!");
		} else {
			System.out.println(mensagem); 
		}		
	}	
	private void consultarConta() {
		String numero = null;
		System.out.println("Digite o n�mero: ");
		numero = ENTRADA.next();
		ContaCorrente conta = mediatorCc.buscar(numero);
		if (conta == null) {
			System.out.println("Conta corrente n�o encontrada!");
		} else {
			System.out.println("Conta corrente encontrada");
			/*
			 * SOBRESCRITA e PRINC�PIO DA SUSBSTITUI��O! 
			 * O println, para objetos, chama o toString do tipo 
			 * do objeto. Portanto, se conta representar um 
			 * objeto do tipo ContaPoupanca, o toString l� 
			 * implementado � chamado. Se conta representa 
			 * objetos do tipo ContaCorrente mesmo, o toString
			 * de ContaCorrente � chamado.
			 *  
			 */			
			System.out.println(conta);
		}		
	}	
	private void gerarRelatorioGeralContas() {
		List<ContaCorrente> contas = mediatorCc.gerarRelatorioGeral();
		for (int i=0; i<contas.size(); i++) {
		//for (ContaCorrente conta : contas) {
			System.out.println(contas.get(i));
		}
	}
}
