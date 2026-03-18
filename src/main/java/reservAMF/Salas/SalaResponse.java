package reservAMF.Salas;

public record SalaResponse(
    Long id,
    String nome,
    int bloco,
    String tipo,
    Boolean ativa

) {}
