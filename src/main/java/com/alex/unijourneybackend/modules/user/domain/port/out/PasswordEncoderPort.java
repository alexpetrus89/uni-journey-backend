package com.alex.unijourneybackend.modules.user.domain.port.out;

import org.springframework.lang.NonNull;

public interface PasswordEncoderPort {

    /**
     * Encodes the raw password
     * @param rawPassword
     * @return
     */
    @NonNull
    String encode(@NonNull String rawPassword);

    /**
     * Checks if the raw password matches the encoded password
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    boolean matches(@NonNull String rawPassword, @NonNull String encodedPassword);

}
