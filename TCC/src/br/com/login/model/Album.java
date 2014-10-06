package br.com.login.model;

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
    private User userTratamento;

    @OneToOne (cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "func_mont", referencedColumnName = "cod")
    private User userMontagem;

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


    public User getUserTratamento() {
        return userTratamento;
    }

    public void setUserTratamento(User userAtual) {
        this.userTratamento = userAtual;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public User getUserMontagem() {
        return userMontagem;
    }

    public void setUserMontagem(User userMontagem) {
        this.userMontagem = userMontagem;
    }

    public int getQtdFotos() {
        return qtdFotos;
    }

    public void setQtdFotos(int qtdFotos) {
        this.qtdFotos = qtdFotos;
    }
}
