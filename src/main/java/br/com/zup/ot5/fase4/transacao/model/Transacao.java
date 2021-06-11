package br.com.zup.ot5.fase4.transacao.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    private String id;

    private BigDecimal valor;

    @OneToOne(mappedBy = "transacao", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Estabelecimento estabelecimento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cartao cartao;

    private LocalDateTime efetivadaEm;

    public Transacao(String id, BigDecimal valor, Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    @Deprecated public Transacao() {

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

    public void associaUmEstebelecimento(Estabelecimento estabelecimento){
        this.estabelecimento = estabelecimento;
        estabelecimento.associaATransacao(this);
    }


}
