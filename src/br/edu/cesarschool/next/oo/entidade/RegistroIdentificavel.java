package br.edu.cesarschool.next.oo.entidade;

import java.io.Serializable;

public abstract class RegistroIdentificavel implements Serializable {
	private static final long serialVersionUID = 1L;
	public abstract String obterChave();
}
