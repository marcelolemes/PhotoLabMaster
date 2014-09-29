package br.com.login.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by marcelo on 26/09/2014.
 */

@Entity
@ManagedBean
public class RelatorioMontagem implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cod;
    @OneToOne
    @JoinColumn(name = "func", referencedColumnName = "cod")
    private User funcionario;
    @OneToOne
    @JoinColumn(name = "album", referencedColumnName = "cod")
    private Album album;
    @Column
    private Timestamp dataOperacao;

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Timestamp getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Timestamp dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
