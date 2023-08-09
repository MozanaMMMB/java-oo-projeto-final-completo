package br.edu.cesarschool.next.oo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.cesarschool.next.oo.entidade.RegistroIdentificavel;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class DAOGenericoTipoParametrizado<T extends RegistroIdentificavel> {
	private CadastroObjetos cadastro;
	private Class<?> tipo;
	public DAOGenericoTipoParametrizado(Class tipo) {
		this.tipo = tipo ;
		cadastro = new CadastroObjetos(tipo);
	}
	public boolean incluir(T reg) {
		T regBuscado = buscar(reg.obterChave()); 
		if (regBuscado != null) { 
			return false;
		} else {
			cadastro.incluir(reg, reg.obterChave());
			return true;
		}		 
	}
	public boolean alterar(T reg) {
		T regBuscado = buscar(reg.obterChave());
		if (regBuscado == null) {
			return false;
		} else {
			cadastro.alterar(reg, reg.obterChave());
			return true;
		}		
	}
	public T buscar(String codigo) {
		return (T)cadastro.buscar(codigo);
	}
	public List<T> buscarTodos() {		
		Serializable[] rets = cadastro.buscarTodos(tipo);
		List<T> ret = new ArrayList<T>();
		for(int i=0; i<rets.length; i++) {
			ret.add((T)rets[i]);
		}
		return ret;
	} 
}
