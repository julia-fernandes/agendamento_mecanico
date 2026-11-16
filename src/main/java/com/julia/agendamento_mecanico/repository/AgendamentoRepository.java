package com.julia.agendamento_mecanico.repository;

import com.julia.agendamento_mecanico.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
