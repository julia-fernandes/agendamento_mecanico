package com.julia.agendamento_mecanico.dto;

import com.julia.agendamento_mecanico.model.StatusAgendamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoRequestDTO(

        @NotBlank(message = "*Informe o nome do cliente")
        String nomeCliente,

        @NotBlank(message = "*Informe a placa do veículo")
        String placaVeiculo,

        @NotBlank(message = "*Informe o veículo")
        String veiculo,

        @NotNull(message = "*Informe a data do agendamento")
        LocalDate dataEntrada,

        @NotNull(message = "*Informe o horário do agendamento")
        LocalTime horaEntrada,

        @NotNull(message = "*Informe o status do agendamento")
        StatusAgendamento status,

        @NotNull(message = "*Informe se o agendamento está ativo")
        Boolean atividade
){}