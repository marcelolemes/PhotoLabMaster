package br.com.login.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by marcelo on 26/09/2014.
 */

@Entity
@ManagedBean
public class Montagem implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cod;
    @Column
    private User funcionario;
    @Column
    private Album album;
    @Column
    private Date dataOperacao;

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

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }
}
