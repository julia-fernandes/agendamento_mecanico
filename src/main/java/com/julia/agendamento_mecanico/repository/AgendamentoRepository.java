package com.julia.agendamento_mecanico.repository;

import com.julia.agendamento_mecanico.model.Agendamento;
import com.julia.agendamento_mecanico.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByNomeClienteContainingIgnoreCase(String nomeCliente);

    Optional<Agendamento> findByPlacaVeiculoIgnoreCase(String placaVeiculo);

    List<Agendamento> findByDataEntrada(LocalDate dataEntrada);

    List<Agendamento> findByStatus(StatusAgendamento status);

    List<Agendamento> findByAtividade(Boolean atividade);
}
