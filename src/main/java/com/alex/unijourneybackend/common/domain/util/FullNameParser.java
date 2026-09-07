package com.alex.unijourneybackend.common.domain.util;

import java.util.Arrays;

public class FullNameParser {

    public record ParsedName(String firstName, String lastName) {}

    public static ParsedName parse(String fullName) {
        String cleaned = fullName.trim().replaceAll("\\s+", " ");

        String[] parts = cleaned.split(" ");
        String firstName = parts[0];
        String lastName = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        return new ParsedName(firstName, lastName);
    }


}
