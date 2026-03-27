package reservAMF.DTO.Mapper;

import org.springframework.stereotype.Component;
import reservAMF.Models.ReservaModel;
import reservAMF.DTO.Request.ReservaRequest;
import reservAMF.DTO.Response.ReservaResponse;
import reservAMF.Enum.ReservaStatus;
import reservAMF.Models.SalaModel;

@Component
public class ReservaMapper {

    public ReservaModel toModel(ReservaRequest reservaRequest, SalaModel sala) {
        ReservaModel reservaModel = new ReservaModel();

        reservaModel.setSala(sala);
        reservaModel.setSolicitante(reservaRequest.solicitante());
        reservaModel.setDataHoraInicio(reservaRequest.dataHoraInicio());
        reservaModel.setDataHoraFim(reservaRequest.dataHoraFim());
        reservaModel.setMotivo(reservaRequest.motivo());
        reservaModel.setStatus(ReservaStatus.AGENDADA);

        return reservaModel;

    }

    public ReservaResponse toResponse(ReservaModel reservaModel) {

        ReservaResponse reservaResponse = new ReservaResponse(
        reservaModel.getId(),
        reservaModel.getSala().getId(),
        reservaModel.getSolicitante(),
        reservaModel.getDataHoraInicio(),
        reservaModel.getDataHoraFim(),
        reservaModel.getMotivo(),
        reservaModel.getStatus()
        );

        return reservaResponse;
    }

    public void atualizarModel(ReservaRequest reservaRequest, ReservaModel reservaExistente, SalaModel sala) {

        reservaExistente.setSala(sala);
        reservaExistente.setSolicitante(reservaRequest.solicitante());
        reservaExistente.setDataHoraInicio(reservaRequest.dataHoraInicio());
        reservaExistente.setDataHoraFim(reservaRequest.dataHoraFim());
        reservaExistente.setMotivo(reservaRequest.motivo());

    }


}
