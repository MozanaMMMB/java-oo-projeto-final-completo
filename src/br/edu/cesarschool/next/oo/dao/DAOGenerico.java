package br.edu.cesarschool.next.oo.dao;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.entidade.RegistroIdentificavel;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class DAOGenerico {
	private CadastroObjetos cadastro;
	private Class<?> tipo;
	public DAOGenerico(Class<?> tipo) {
		this.tipo = tipo;
		cadastro = new CadastroObjetos(tipo);
	}
	public boolean incluir(RegistroIdentificavel reg) {
		RegistroIdentificavel regBuscado = buscar(reg.obterChave()); 
		if (regBuscado != null) { 
			return false;
		} else {
			cadastro.incluir(reg, reg.obterChave());
			return true;
		}		 
	}
	public boolean alterar(RegistroIdentificavel reg) {
		RegistroIdentificavel regBuscado = buscar(reg.obterChave());
		if (regBuscado == null) {
			return false;
		} else {
			cadastro.alterar(reg, reg.obterChave());
			return true;
		}		
	}
	public RegistroIdentificavel buscar(String codigo) {
		return (RegistroIdentificavel)cadastro.buscar(codigo);
	}
	public RegistroIdentificavel[] buscarTodos() {
		Serializable[] rets = cadastro.buscarTodos(tipo);
		RegistroIdentificavel[] contas = new RegistroIdentificavel[rets.length];
		for(int i=0; i<rets.length; i++) {
			contas[i] = (RegistroIdentificavel)rets[i];
		}
		return contas;
	} 
}
