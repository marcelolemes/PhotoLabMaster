package br.com.photolab.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album")
public class Album implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	@Column
	private String numero;
	@Column
	private int status;
	@Column
	private String obs;
    @Column
    private boolean ocupado;
    @Column
    private int qtdFotos;

    @OneToOne (cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "func_trat", referencedColumnName = "cod")
    private Usuario usuarioTratamento;

    @OneToOne (cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "func_mont", referencedColumnName = "cod")
    private Usuario usuarioMontagem;

	@ManyToOne(cascade = CascadeType.PERSIST, optional = true)
	@JoinColumn(name = "cont_id", referencedColumnName = "cod")
	private Contrato contrato;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


    public Usuario getUsuarioTratamento() {
        return usuarioTratamento;
    }

    public void setUsuarioTratamento(Usuario usuarioAtual) {
        this.usuarioTratamento = usuarioAtual;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Usuario getUsuarioMontagem() {
        return usuarioMontagem;
    }

    public void setUsuarioMontagem(Usuario usuarioMontagem) {
        this.usuarioMontagem = usuarioMontagem;
    }

    public int getQtdFotos() {
        return qtdFotos;
    }

    public void setQtdFotos(int qtdFotos) {
        this.qtdFotos = qtdFotos;
    }
}
