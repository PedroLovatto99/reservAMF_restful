package reservAMF.DTO.Response;

import java.io.Serializable;

public record SalaResponse (
    Long id,
    String nome,
    int bloco,
    String tipo,
    Boolean ativa

) implements Serializable {}
