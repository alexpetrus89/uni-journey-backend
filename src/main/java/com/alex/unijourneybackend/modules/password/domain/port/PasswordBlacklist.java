package com.alex.unijourneybackend.modules.password.domain.port;

public interface PasswordBlacklist {

    boolean contains(String password);

}
