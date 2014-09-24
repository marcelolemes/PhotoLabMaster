package br.com.login.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@ManagedBean
@Table(name = "contrato")
public class Contrato implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cod;
    @Column
    private String curso;
    @Column
    private String numeroContrato;
    @Column
    private String cidade;
    @Column
    private Date dataEntrada;
    @Column
    private Date dataPrazo;
    @Column
    private Date dataEntrega;
    @Column
    private String entidade;
    @Column
    private int media;
    @Column
    private String caminho;
    @Column
    private int status;
    @Column
    private int urgencia;
    @Column
    private String obs;
    @Column
    private Date dataBackup;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "ficha_id", referencedColumnName = "cod")
    private Ficha ficha;

    @OneToMany(mappedBy = "contrato")
    private List<Album> listaAlbum;

    public List<Album> getListaAlbum() {
        return listaAlbum;
    }

    public void setListaAlbum(List<Album> listaAlbum) {
        this.listaAlbum = listaAlbum;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDataBackup() {
        return dataBackup;
    }

    public void setDataBackup(Date dataBackup) {
        this.dataBackup = dataBackup;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

}

