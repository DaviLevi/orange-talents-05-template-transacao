package br.com.zup.ot5.fase4.transacao.model;

import javax.persistence.*;

@Entity
public class Estabelecimento {

    @Id
    private String id;

    private String nome;

    private String cidade;

    private String endereco;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Transacao transacao;

    public Estabelecimento(String nome, String cidade, String endereco, Transacao transacao) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
        this.transacao = transacao;
    }

    public void associaATransacao(Transacao transacao){
        this.transacao = transacao;
    }

    @Deprecated public Estabelecimento() {}
}
