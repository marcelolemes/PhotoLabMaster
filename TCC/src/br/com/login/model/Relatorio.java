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
@Table(name = "relatorio")
public class Relatorio implements Serializable{

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
    private int fotos;
    @Column
    private Timestamp dataInicial;
    @Column
    private Timestamp dataFinal;
    @Column
    private long tempoOperacao;

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

    public Timestamp getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Timestamp dataOperacao) {
        this.dataFinal = dataOperacao;
    }

    public int getFotos() {
        return fotos;
    }

    public void setFotos(int fotos) {
        this.fotos = fotos;
    }

    public Timestamp getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Timestamp dataInicial) {
        this.dataInicial = dataInicial;
    }

    public long getTempoOperacao() {
        return tempoOperacao;
    }

    public void setTempoOperacao(long tempoOperacao) {
        this.tempoOperacao = tempoOperacao;
    }
}
