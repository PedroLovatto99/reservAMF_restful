package reservAMF.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservAMF.Enum.ReservaStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private SalaModel sala;

    @Column(nullable = false, length = 100)
    private String solicitante;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(nullable = false, length = 300)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservaStatus status;



}
