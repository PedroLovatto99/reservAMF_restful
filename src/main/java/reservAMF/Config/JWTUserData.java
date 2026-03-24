package reservAMF.Config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
