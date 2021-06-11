package br.com.zup.ot5.fase4.transacao.consulta_transacoes;

import br.com.zup.ot5.fase4.transacao.model.Cartao;
import br.com.zup.ot5.fase4.transacao.model.Transacao;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/transacoes")
public class ConsultaTransacaoController {


    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public Set<ConsultaTransacoesResponse> consultaTransacoes(){

          String emailDoUsuarioLogado = "luram.archanjo@zup.com.br";

          List<Cartao> cartaoAssociadoAoEmail =  manager
                     .createQuery("SELECT c " +
                                         "FROM Cartao c " +
                                         "WHERE c.email = :email", Cartao.class)
                     .setParameter("email", emailDoUsuarioLogado)
                     .getResultList();

          if(cartaoAssociadoAoEmail.isEmpty())
              return Collections.emptySet();

          Assert.state(cartaoAssociadoAoEmail.size() <= 1, "A consulta para obtenção do cartao associado ao usuario " +
                "logado nao pode retornar mais de 1 resultado!!");

          Set<Transacao> transacoesAssociadas = cartaoAssociadoAoEmail.get(0).obtemTransacoesAssociadas();

          return ConsultaTransacoesResponse.converteCollection(transacoesAssociadas);
    }


}
