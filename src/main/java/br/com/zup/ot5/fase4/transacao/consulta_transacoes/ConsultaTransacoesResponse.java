package br.com.zup.ot5.fase4.transacao.consulta_transacoes;

import br.com.zup.ot5.fase4.transacao.model.Cartao;
import br.com.zup.ot5.fase4.transacao.model.Estabelecimento;
import br.com.zup.ot5.fase4.transacao.model.Transacao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsultaTransacoesResponse {

    private String id;
    private BigDecimal valor;
    private LocalDateTime efetivadaEm;

    public ConsultaTransacoesResponse(Transacao transacao){
        this.id = transacao.getId();
        this.valor = transacao.getValor();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public static Set<ConsultaTransacoesResponse> converteCollection(Set<Transacao> transacoes){
        return transacoes.stream().map(ConsultaTransacoesResponse::new).collect(Collectors.toSet());
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
