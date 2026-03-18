package reservAMF.Salas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaRequest(

     @NotNull @Size(max = 100)
     String nome,

     @NotNull
     int bloco,

     @NotNull @Size(max = 100)
     String tipo,

     @NotNull
     Boolean ativa

) {}
