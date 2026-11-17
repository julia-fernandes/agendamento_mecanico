package com.julia.agendamento_mecanico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_AGENDAMENTO")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false, unique = true)
    private String placaVeiculo;

    @Column(nullable = false)
    private String veiculo;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private LocalTime horaEntrada;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.ABERTO;

    @Column(nullable = false)
    private Boolean atividade = true;
}