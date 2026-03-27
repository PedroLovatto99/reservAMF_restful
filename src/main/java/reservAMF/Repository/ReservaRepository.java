package reservAMF.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import reservAMF.Models.ReservaModel;
import reservAMF.Enum.ReservaStatus;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {

    @EntityGraph(attributePaths = "sala")
    Page<ReservaModel> findAll(Pageable pageable);

    boolean existsBySalaIdAndStatusNotAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
            Long salaId,
            ReservaStatus status,
            LocalDateTime fimNovaReserva,
            LocalDateTime inicioNovaReserva
    );


}
