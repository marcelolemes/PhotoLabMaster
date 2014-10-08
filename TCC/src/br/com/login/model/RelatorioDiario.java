package br.com.login.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by marcelo on 26/09/2014.
 */

@Entity
@ManagedBean
@Table(name = "relatorioDiario")
public class RelatorioDiario implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cod;
    @OneToOne
    @JoinColumn(name = "func", referencedColumnName = "cod")
    private User funcionario;
    @Column
    private int fotos;
    @Column
    private Timestamp dataRelatorio;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public User getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(User funcionario) {
        this.funcionario = funcionario;
    }

    public Timestamp getDataOperacao() {
        return dataRelatorio;
    }

    public void setDataOperacao(Timestamp dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public int getFotos() {
        return fotos;
    }

    public void setFotos(int fotos) {
        this.fotos = fotos;
    }
}
