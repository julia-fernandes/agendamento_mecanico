package com.julia.agendamento_mecanico.dto;

import com.julia.agendamento_mecanico.model.StatusAgendamento;

import java.time.LocalDate;

public record AgendamentoResponseDTO(

        Long id,
        String nomePessoa,
        String placaVeiculo,
        String veiculo,
        LocalDate dataEntrada,
        LocalDate horaEntrada,
        StatusAgendamento status,
        Boolean atividade
){}