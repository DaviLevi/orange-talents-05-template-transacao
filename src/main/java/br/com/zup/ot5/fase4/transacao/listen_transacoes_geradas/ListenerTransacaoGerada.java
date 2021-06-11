package br.com.zup.ot5.fase4.transacao.listen_transacoes_geradas;

import br.com.zup.ot5.fase4.transacao.model.Cartao;
import br.com.zup.ot5.fase4.transacao.model.Estabelecimento;
import br.com.zup.ot5.fase4.transacao.model.Transacao;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Component
public class ListenerTransacaoGerada {

    @PersistenceContext
    private EntityManager manager;


    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void captaEPersiste(EventoTransacaoGerada eventoTransacaoGerada) {

          Optional<Cartao> cartaoEncontrado = Optional.ofNullable(manager.find(Cartao.class, eventoTransacaoGerada.getCartaoId()));

          if(cartaoEncontrado.isPresent()){ // se cartao existir, associa a nova transacao ao cartao existente

              Transacao transacaoGerada = eventoTransacaoGerada.converteParaTransacao(cartaoEncontrado.get());
              Estabelecimento estabelecimentoAssociado = eventoTransacaoGerada.converteParaEstabelecimento(transacaoGerada);
              transacaoGerada.associaUmEstebelecimento(estabelecimentoAssociado);
              manager.persist(transacaoGerada); // persistindo a transacao junto ao estabelecimento

          }else{ // senao, gera um novo cartao e associa a transacao ao cartao gerado

              Cartao cartaoAssociadoATransacao = eventoTransacaoGerada.converteParaCartao();
              manager.persist(cartaoAssociadoATransacao); // persistindo o cartao

              Transacao transacaoGerada = eventoTransacaoGerada.converteParaTransacao(cartaoAssociadoATransacao);
              Estabelecimento estabelecimentoAssociado = eventoTransacaoGerada.converteParaEstabelecimento(transacaoGerada);
              transacaoGerada.associaUmEstebelecimento(estabelecimentoAssociado);
              manager.persist(transacaoGerada); // persistindo a transacao junto ao estabelecimento
          }


    }

}

