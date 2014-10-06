package br.com.login.Dao;

import br.com.login.model.Album;
import br.com.login.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by marcelo on 27/09/2014.
 */
@Entity
public class Processos implements Serializable{
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
}
