package com.example.projeto_integrador.scheduler;

import com.example.projeto_integrador.Repositories.NotificacaoRepository;
import com.example.projeto_integrador.Repositories.VacinaRepository;
import com.example.projeto_integrador.model.Notificacao;
import com.example.projeto_integrador.model.StatusNotificacao;
import com.example.projeto_integrador.model.TipoNotificacao;
import com.example.projeto_integrador.model.Vacina;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoScheduler {

    private final VacinaRepository vacinaRepository;
    private final NotificacaoRepository notificacaoRepository;

    @Scheduled(cron = "0 0 8 * * *")
    public void verificarVacinasAtrasadas() {

        log.info("Verificando vacinas atrasadas...");

        List<Vacina> atrasadas = vacinaRepository
                .findByDataProximaDoseLessThanEqual(LocalDate.now());

        int contador = 0;

        for (Vacina vacina : atrasadas) {

            boolean jaNotificadoHoje = notificacaoRepository
                    .existsByPetIdAndTipoAndDataAgendadaBetween(
                            vacina.getProntuario().getPet().getId(),
                            TipoNotificacao.VACINA,
                            LocalDate.now().atStartOfDay(),
                            LocalDate.now().atTime(23, 59, 59)
                    );

            if (!jaNotificadoHoje) {
                Notificacao notificacao = Notificacao.builder()
                        .titulo("Vacina atrasada: " + vacina.getNome())
                        .mensagem("A vacina " + vacina.getNome() + " do pet "
                                + vacina.getProntuario().getPet().getNome()
                                + " está atrasada desde "
                                + vacina.getDataProximaDose())
                        .dataAgendada(LocalDateTime.now())
                        .tipo(TipoNotificacao.VACINA)
                        .status(StatusNotificacao.PENDENTE)
                        .pet(vacina.getProntuario().getPet())
                        .build();

                notificacaoRepository.save(notificacao);
                contador++;
            }
        }

        log.info("{} notificações de vacina geradas.", contador);
    }
}