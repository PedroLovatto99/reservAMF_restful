package reservAMF.DTO.Request;

import jakarta.validation.constraints.NotNull;
import reservAMF.Enum.ReservaStatus;

public record AlterarStatusRequest(@NotNull ReservaStatus status) {
}
