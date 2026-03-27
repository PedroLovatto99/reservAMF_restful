package reservAMF.DTO.Mapper;

import org.springframework.stereotype.Component;
import reservAMF.Models.SalaModel;
import reservAMF.DTO.Request.SalaRequest;
import reservAMF.DTO.Response.SalaResponse;

@Component
public class SalaMapper {

   public SalaModel toModel(SalaRequest salaRequest) {

       SalaModel salaModel = new SalaModel();

       salaModel.setNome(salaRequest.nome());
       salaModel.setBloco(salaRequest.bloco());
       salaModel.setTipo(salaRequest.tipo());
       salaModel.setAtiva(salaRequest.ativa());

       return salaModel;

   }

   public SalaResponse toResponse(SalaModel salaModel) {

     SalaResponse salaResponse = new SalaResponse(
        salaModel.getId(),
        salaModel.getNome(),
        salaModel.getBloco(),
        salaModel.getTipo(),
        salaModel.getAtiva()
     );

     return salaResponse;

   }

}
