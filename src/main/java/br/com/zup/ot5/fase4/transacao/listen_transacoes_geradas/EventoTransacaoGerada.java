package br.com.zup.ot5.fase4.transacao.listen_transacoes_geradas;

import br.com.zup.ot5.fase4.transacao.model.Cartao;
import br.com.zup.ot5.fase4.transacao.model.Estabelecimento;
import br.com.zup.ot5.fase4.transacao.model.Transacao;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoTransacaoGerada {

    private String id;
    private BigDecimal valor;
    private EventoDescricaoEstabelecimento estabelecimento;
    private EventoDescricaoCartao cartao;
    private LocalDateTime efetivadaEm;

    public EventoTransacaoGerada(String id, BigDecimal valor, EventoDescricaoEstabelecimento estabelecimento, EventoDescricaoCartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public EventoTransacaoGerada() {
    }

    public Cartao converteParaCartao(){
        return this.cartao.converterParaCartao();
    }
    public Transacao converteParaTransacao(Cartao cartao){
        return new Transacao(id, valor, cartao, efetivadaEm);
    }
    public Estabelecimento converteParaEstabelecimento(Transacao transacaoGerada){
        return this.estabelecimento.converterParaEstabelecimento(transacaoGerada);
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EventoDescricaoEstabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public EventoDescricaoCartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public String getCartaoId(){
        return this.cartao.getId();
    }

    @Override
    public String toString() {
        return "EventoTransacaoGerada{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                '}';
    }
}

