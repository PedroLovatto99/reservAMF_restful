package reservAMF.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "salas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private int bloco;

    @Column(nullable = false, length = 100)
    private String tipo;

    @Column(nullable = false)
    private Boolean ativa;

    @OneToMany(mappedBy = "sala")
    @JsonIgnore
    private List<ReservaModel> reservas;

}
