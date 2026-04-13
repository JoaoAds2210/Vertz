package com.example.projeto_integrador.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medicacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nomeMedicacao;

    @Column(nullable = false, length = 80)
    private String dosagem;

    @Column(nullable = false, length = 80)
    private String frequencia;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamento_id", nullable = false)
    private Tratamento tratamento;
}
