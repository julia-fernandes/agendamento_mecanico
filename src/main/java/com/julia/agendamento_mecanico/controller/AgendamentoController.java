package com.julia.agendamento_mecanico.controller;

import com.julia.agendamento_mecanico.dto.AgendamentoRequestDTO;
import com.julia.agendamento_mecanico.dto.AgendamentoResponseDTO;
import com.julia.agendamento_mecanico.model.StatusAgendamento;
import com.julia.agendamento_mecanico.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public AgendamentoResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/placa")
    public AgendamentoResponseDTO buscarPorPlaca(@RequestParam String placaVeiculo){
        return service.buscarPorPlaca(placaVeiculo);
    }

    @GetMapping("/cliente")
    public List<AgendamentoResponseDTO> buscarPorCliente(@RequestParam String nomeCliente){
        return service.buscarPorCliente(nomeCliente);
    }

    @GetMapping
    public List<AgendamentoResponseDTO> listarAgendamentos(){
        return service.listarAgendamentos();
    }

    @GetMapping("/data")
    public List<AgendamentoResponseDTO> listarPorData(@RequestParam LocalDate dataEntrada){
        return service.listarPorData(dataEntrada);
    }

    @GetMapping("/status")
    public List<AgendamentoResponseDTO> listarPorStatus(@RequestParam StatusAgendamento status){
        return service.listarPorStatus(status);
    }

    @GetMapping("/atividade")
    public List<AgendamentoResponseDTO> listarPorAtividade(@RequestParam Boolean atividade){
        return service.listarPorAtividade(atividade);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@RequestBody @Valid AgendamentoRequestDTO dto){
        AgendamentoResponseDTO agendamento = service.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @PutMapping("/{id}")
    public AgendamentoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO dto){
        return service.alterar(id, dto);
    }

    @PatchMapping("/ativar/{id}")
    public AgendamentoResponseDTO ativar(@PathVariable Long id){
        return service.ativar(id);
    }

    @PatchMapping("/inativar/{id}")
    public AgendamentoResponseDTO inativar(@PathVariable Long id){
        return service.inativar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}