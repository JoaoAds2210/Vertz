package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.Notificacao;
import com.example.projeto_integrador.model.StatusNotificacao;
import com.example.projeto_integrador.model.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByStatusAndDataAgendadaLessThanEqual(StatusNotificacao status, LocalDateTime data);

    List<Notificacao> findByPetId(Long petId);

    List<Notificacao> findByTipo(TipoNotificacao tipo);

    boolean existsByPetIdAndTipoAndDataAgendadaBetween(
            Long petId,
            TipoNotificacao tipo,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
