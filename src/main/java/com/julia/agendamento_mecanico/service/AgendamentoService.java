package com.julia.agendamento_mecanico.service;

import com.julia.agendamento_mecanico.dto.AgendamentoRequestDTO;
import com.julia.agendamento_mecanico.dto.AgendamentoResponseDTO;
import com.julia.agendamento_mecanico.exception.RecursoNaoEncontradoException;
import com.julia.agendamento_mecanico.exception.RegraDeNegocioException;
import com.julia.agendamento_mecanico.model.Agendamento;
import com.julia.agendamento_mecanico.model.StatusAgendamento;
import com.julia.agendamento_mecanico.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {

    public final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository){
        this.repository = repository;
    }

    private List<AgendamentoResponseDTO> listResponse(List<Agendamento> agendamentos){
        return agendamentos.stream().map(this::toResponse).toList();
    }

    private AgendamentoResponseDTO toResponse(Agendamento agendamento){
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getNomeCliente(),
                agendamento.getPlacaVeiculo(),
                agendamento.getVeiculo(),
                agendamento.getDataEntrada(),
                agendamento.getHoraEntrada(),
                agendamento.getStatus(),
                agendamento.getAtividade()
        );
    }

    private Agendamento toEntity(AgendamentoRequestDTO dto){
        Agendamento agendamento = new Agendamento();
        agendamento.setNomeCliente(dto.nomeCliente());
        agendamento.setPlacaVeiculo(dto.placaVeiculo());
        agendamento.setVeiculo(dto.veiculo());
        agendamento.setDataEntrada(dto.dataEntrada());
        agendamento.setHoraEntrada(dto.horaEntrada());
        agendamento.setStatus(dto.status());
        agendamento.setAtividade(dto.atividade());

        return agendamento;
    }

    private Agendamento buscarEntidade(Long id){

        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado."));
    }

    public AgendamentoResponseDTO buscarPorId(Long id){

        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado."));

        return toResponse(agendamento);
    }

    public AgendamentoResponseDTO buscarPorPlaca(String placaVeiculo){
        Agendamento agendamento = repository.findByPlacaVeiculoIgnoreCase(placaVeiculo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado."));

        return toResponse(agendamento);
    }

    public List<AgendamentoResponseDTO> buscarPorCliente(String nomeCliente){
        List<Agendamento> agendamentos = repository.findByNomeClienteContainingIgnoreCase(nomeCliente);

        return listResponse(agendamentos);
    }

    public List<AgendamentoResponseDTO> listarAgendamentos(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public List<AgendamentoResponseDTO> listarPorData(LocalDate dataEntrada){
        List<Agendamento> agendamentos = repository.findByDataEntrada(dataEntrada);

        return listResponse(agendamentos);
    }

    public List<AgendamentoResponseDTO> listarPorStatus(StatusAgendamento status){
        List<Agendamento> agendamentos = repository.findByStatus(status);

        return listResponse(agendamentos);
    }

    public List<AgendamentoResponseDTO> listarPorAtividade(Boolean atividade){
        List<Agendamento> agendamentos = repository.findByAtividade(atividade);

        return listResponse(agendamentos);
    }

    public AgendamentoResponseDTO cadastrar(AgendamentoRequestDTO dto){
        Agendamento agendamento = toEntity(dto);
        Agendamento salvo = repository.save(agendamento);
        return toResponse(salvo);
    }

    public AgendamentoResponseDTO alterar(Long id, AgendamentoRequestDTO dto){
        Agendamento agendamento = buscarEntidade(id);

        agendamento.setNomeCliente(dto.nomeCliente());
        agendamento.setPlacaVeiculo(dto.placaVeiculo());
        agendamento.setVeiculo(dto.veiculo());
        agendamento.setDataEntrada(dto.dataEntrada());
        agendamento.setHoraEntrada(dto.horaEntrada());
        agendamento.setStatus(dto.status());
        agendamento.setAtividade(dto.atividade());

        if(agendamento.getStatus() == StatusAgendamento.FINALIZADO){
            agendamento.setAtividade(false);
        }

        Agendamento salvo = repository.save(agendamento);
        return toResponse(salvo);
    }

    public AgendamentoResponseDTO ativar(Long id){

        Agendamento agendamento = buscarEntidade(id);

        if (agendamento.getAtividade() == true) {
            throw new RegraDeNegocioException("Agendamento já ativo.");
        }
        else{
            agendamento.setAtividade(true);
            Agendamento salvo = repository.save(agendamento);
            return toResponse(salvo);
        }
    }

    public AgendamentoResponseDTO inativar(Long id){

        Agendamento agendamento = buscarEntidade(id);

        if (agendamento.getAtividade() == false) {
            throw new RegraDeNegocioException("Agendamento já inativo.");
        }

        if(agendamento.getStatus() == StatusAgendamento.EM_ANDAMENTO){
            throw new RegraDeNegocioException("Não é possível inativar um agendamento em andamento.");
        }
        else{
            agendamento.setAtividade(false);
            Agendamento salvo = repository.save(agendamento);
            return toResponse(salvo);
        }
    }

    public void deletar(Long id){
        Agendamento agendamento = buscarEntidade(id);

        if (agendamento.getAtividade() == true || agendamento.getStatus() == StatusAgendamento.EM_ANDAMENTO) {
            throw new RegraDeNegocioException("Não é possível deletar agendamentos ativos ou em andamento.");
        }
        else{
            repository.delete(agendamento);
        }
    }
}