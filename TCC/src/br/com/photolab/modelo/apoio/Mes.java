package br.com.photolab.modelo.apoio;

/**
 * Created by mega on 04/11/2014.
 */
public class Mes {
    private int numero;
    private String nome;


    public Mes(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }
    public Mes() {

        }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
