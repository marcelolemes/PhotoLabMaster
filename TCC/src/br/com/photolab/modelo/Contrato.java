package br.com.photolab.modelo;

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
    private int qtdAlbum;
    @Column
    private String obs;
    @Column
    private boolean ocupado;
    @Column
    private Date dataBackup;
    @Column
    private int menosde50;
    @Column
    private int maisde50;
    @Column
    private int qtdFotos;
    @Column
    private int qtdPedidos;
    @Column
    private String tamanho;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "ficha_id", referencedColumnName = "cod")
    private Ficha ficha;

    @OneToMany(mappedBy = "contrato")
    private List<Album> listaAlbum;


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

    public int getQtdAlbum() {
        return qtdAlbum;
    }

    public void setQtdAlbum(int qtdAlbum) {
        this.qtdAlbum = qtdAlbum;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQtdFotos() {
        return qtdFotos;
    }

    public void setQtdFotos(int qtdFotos) {
        this.qtdFotos = qtdFotos;
    }

    public int getMenosde50() {
        return menosde50;
    }

    public void setMenosde50(int menosde50) {
        this.menosde50 = menosde50;
    }

    public int getMaisde50() {
        return maisde50;
    }

    public void setMaisde50(int maisde50) {
        this.maisde50 = maisde50;
    }

    public int getQtdPedidos() {
        return qtdPedidos;
    }

    public void setQtdPedidos(int qtdPedidos) {
        this.qtdPedidos = qtdPedidos;
    }
}

