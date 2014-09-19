package br.com.login.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@ManagedBean
public class Ficha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5748867020539135351L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	@Column
	private String numero;
	@Column
	private String ano;
	@ManyToOne(cascade = CascadeType.PERSIST, optional = true)
	@JoinColumn(name = "cli_id", referencedColumnName = "cod")
	private Cliente cliente;
	@OneToMany(mappedBy = "ficha")
	private List<Contrato> listaContrato;

}
