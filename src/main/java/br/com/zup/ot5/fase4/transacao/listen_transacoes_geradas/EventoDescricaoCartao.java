package br.com.zup.ot5.fase4.transacao.listen_transacoes_geradas;

import br.com.zup.ot5.fase4.transacao.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

public class EventoDescricaoCartao{

    private String id;
    private String email;

    public EventoDescricaoCartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public EventoDescricaoCartao() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "EventoDescricaoCartao{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Cartao converterParaCartao(){
        return new Cartao(this.id, this.email);
    }
}
