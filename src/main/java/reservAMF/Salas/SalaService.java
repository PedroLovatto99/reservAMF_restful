package reservAMF.Salas;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaService {

    private SalaRepository salaRepo;
    private SalaMapper salaMapper;

    public SalaService(SalaRepository salaRepo, SalaMapper salaMapper) {
        this.salaRepo = salaRepo;
        this.salaMapper = salaMapper;
    }

    public List<SalaResponse> listarSalas() {
        List<SalaModel> salas = salaRepo.findAll();
        return salas.stream()
                .map(salaMapper::toResponse)
                .collect(Collectors.toList());

    }

    public SalaResponse criarSala(SalaRequest salaRequest) {
        SalaModel sala = salaMapper.toModel(salaRequest);
        sala = salaRepo.save(sala);
        return salaMapper.toResponse(sala);
    }


    public SalaResponse listarSalaPorId(Long id) {

        Optional<SalaModel> sala = salaRepo.findById(id);
        return sala.map(salaMapper::toResponse).orElse(null);
    }


    public SalaResponse editarSala(Long id, SalaRequest salaRequest) {
        Optional<SalaModel> sala = salaRepo.findById(id);
        if(sala.isPresent()) {
            SalaModel salaAtualizada = salaMapper.toModel(salaRequest);
            salaAtualizada.setId(id);
            SalaModel salaSalva = salaRepo.save(salaAtualizada);
            return salaMapper.toResponse(salaSalva);
        }
        return null;

    }

    public void deletarSala(Long id) {
        Optional<SalaModel> sala = salaRepo.findById(id);
        if(sala.isPresent()) {
            salaRepo.deleteById(id);
        }


    }




}
