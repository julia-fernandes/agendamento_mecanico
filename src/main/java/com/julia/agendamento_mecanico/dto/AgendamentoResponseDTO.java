package com.julia.agendamento_mecanico.dto;

import com.julia.agendamento_mecanico.model.StatusAgendamento;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoResponseDTO(

        Long id,
        String nomeCliente,
        String placaVeiculo,
        String veiculo,
        LocalDate dataEntrada,
        LocalTime horaEntrada,
        StatusAgendamento status,
        Boolean atividade
){}