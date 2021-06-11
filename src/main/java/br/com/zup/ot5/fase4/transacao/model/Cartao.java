package br.com.zup.ot5.fase4.transacao.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    private String id;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Transacao> transacoes = new HashSet<>();

    private String email;

    @Deprecated public Cartao() {

    }

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public void associaTransacao(Transacao transacao){
        this.transacoes.add(transacao);
    }

    public Set<Transacao> obtemTransacoesAssociadas(){
        return this.transacoes;
    }

}
