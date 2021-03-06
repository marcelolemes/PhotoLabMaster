package br.com.photolab.modelo;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@ManagedBean
@Table(name = "usuario")
public class Usuario implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cod;
    @Column
    private int nivelacesso;
    @Column
    private int setor;
    @Column
    private String apelido;
    @Column
    private String senha;
   @Column
    private Timestamp ultimoacesso;
    @Column
    private boolean logado;
    @Column
    private String auxiliar;

    @OneToOne
    @JoinColumn(name = "album_atual", referencedColumnName = "cod")
    private Album albumAtual;

    public Timestamp getUltimoacesso() {
        return ultimoacesso;
    }

    public void setUltimoacesso(Timestamp ultimoacesso) {
        this.ultimoacesso = ultimoacesso;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getNivelAcesso() {
        return nivelacesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelacesso = nivelAcesso;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public Album getAlbumAtual() {
        return albumAtual;
    }

    public void setAlbumAtual(Album albumAtual) {
        this.albumAtual = albumAtual;
    }

    public int getNivelacesso() {
        return nivelacesso;
    }

    public void setNivelacesso(int nivelacesso) {
        this.nivelacesso = nivelacesso;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
}
