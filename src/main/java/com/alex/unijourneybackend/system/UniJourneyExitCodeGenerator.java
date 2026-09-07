package com.alex.unijourneybackend.system;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class UniJourneyExitCodeGenerator implements ExitCodeGenerator {

    @Override
    public int getExitCode() { return 0; }

}
