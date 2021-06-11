package br.com.zup.ot5.fase4.transacao.consulta_ultimas_compras;

import br.com.zup.ot5.fase4.transacao.consulta_transacoes.ConsultaTransacoesResponse;
import br.com.zup.ot5.fase4.transacao.model.Cartao;
import br.com.zup.ot5.fase4.transacao.model.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("cartoes/{idCartao}/ultimas-compras")
public class ConsultaUltimasComprasController {


    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public ResponseEntity<?> consultaUltimasCompras(@PathVariable String idCartao){

        Optional<Cartao> cartaoBuscadoPeloId = Optional.ofNullable(manager.find(Cartao.class, idCartao));

        if(cartaoBuscadoPeloId.isEmpty())
            return ResponseEntity.notFound().build();

        List<Transacao> ultimasDezTransacoes = manager
                                               .createQuery("SELECT t " +
                                                                   "FROM Transacao t " +
                                                                   "JOIN FETCH t.cartao c " +
                                                                   "WHERE c.id = :idCartao " +
                                                                   "ORDER BY t.efetivadaEm DESC ", Transacao.class)
                                               .setParameter("idCartao", idCartao)
                                               .setMaxResults(10)
                                               .getResultList();

        return ResponseEntity.ok(ultimasDezTransacoes);
    }


}
