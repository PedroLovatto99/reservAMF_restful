package reservAMF.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reservAMF.DTO.Mapper.ReservaMapper;
import reservAMF.DTO.Request.ReservaRequest;
import reservAMF.DTO.Response.ReservaResponse;
import reservAMF.Enum.ReservaStatus;
import reservAMF.Models.ReservaModel;
import reservAMF.Repository.ReservaRepository;
import reservAMF.DTO.Mapper.SalaMapper;
import reservAMF.Models.SalaModel;
import reservAMF.Repository.SalaRepository;
import reservAMF.DTO.Request.AlterarStatusRequest;

import java.util.Optional;

@Service
public class ReservaService {

    SalaRepository salaRepository;
    SalaMapper salaMapper;
    ReservaRepository reservaRepo;
    ReservaMapper reservaMapper;

    public ReservaService(SalaRepository salaRepository, SalaMapper salaMapper, ReservaRepository reservaRepo, ReservaMapper reservaMapper) {
        this.salaRepository = salaRepository;
        this.salaMapper = salaMapper;
        this.reservaRepo = reservaRepo;
        this.reservaMapper = reservaMapper;
    }

    @Cacheable("lista_reservas")
    public Page<ReservaResponse> listarReservas(Pageable paginacao) {
        Page<ReservaModel> paginaReservas = reservaRepo.findAll(paginacao);
            return paginaReservas.map(reservaMapper::toResponse);
    }


    public ReservaResponse criarReserva(ReservaRequest reservaRequest) {

        boolean conflito = reservaRepo.existsBySalaIdAndStatusNotAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                reservaRequest.sala(),
                ReservaStatus.CANCELADA,
                reservaRequest.dataHoraFim(),
                reservaRequest.dataHoraInicio()
        );

        if (conflito) {
            throw new IllegalArgumentException("Essa sala já possui uma reserva nesse mesmo horário");
        }


        SalaModel sala = salaRepository.findById(reservaRequest.sala())
                .orElse(null);

        if(sala != null) {
            ReservaModel reserva = reservaMapper.toModel(reservaRequest, sala);
            reserva = reservaRepo.save(reserva);
            return reservaMapper.toResponse(reserva);
        } else {
            return null;
        }

    }

    public ReservaResponse listarReservaPorId(Long id) {
        Optional<ReservaModel> reserva = reservaRepo.findById(id);
        return reserva.map(reservaMapper::toResponse).orElse(null);
    }

    public ReservaResponse editarReserva(Long id, ReservaRequest reservaRequest) {

        ReservaModel reserva = reservaRepo.findById(id)
                .orElse(null);

        SalaModel sala = salaRepository.findById(reservaRequest.sala())
                .orElse(null);

        if(reserva != null && sala != null) {
            reservaMapper.atualizarModel(reservaRequest, reserva, sala);
            ReservaModel reservaSalva = reservaRepo.save(reserva);
            return reservaMapper.toResponse(reservaSalva);
        } else {
            return null;
        }

    }

    public ReservaResponse alterarStatus(Long id, AlterarStatusRequest alterarStatus) {
        ReservaModel reserva = reservaRepo.findById(id)
                .orElse(null);

        if(reserva != null) {
            reserva.setStatus(alterarStatus.status());
            ReservaModel reservaStatusAtualizado = reservaRepo.save(reserva);
            return reservaMapper.toResponse(reservaStatusAtualizado);
        } else {
            return null;
        }
    }

    public void deletarReserva(Long id) {
        Optional<ReservaModel> reserva = reservaRepo.findById(id);
        if(reserva.isPresent()) {
            reservaRepo.delete(reserva.get());
        }

    }



}
