package br.com.zup.ot5.fase4.transacao.listen_transacoes_geradas;

import br.com.zup.ot5.fase4.transacao.model.Estabelecimento;
import br.com.zup.ot5.fase4.transacao.model.Transacao;
import com.fasterxml.jackson.annotation.JsonCreator;

public class EventoDescricaoEstabelecimento{

    private String nome;
    private String cidade;
    private String endereco;

    public EventoDescricaoEstabelecimento() {
    }

    public EventoDescricaoEstabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "EventoDescricaoEstabelecimento{" +
                "nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public Estabelecimento converterParaEstabelecimento(Transacao transacao){
        return new Estabelecimento(this.nome, this.cidade, this.endereco, transacao);
    }
}
