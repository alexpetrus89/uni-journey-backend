package com.alex.unijourneybackend.modules.password.infrastructure.blacklist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.password.domain.config.PasswordProperties;
import com.alex.unijourneybackend.modules.password.domain.port.PasswordBlacklist;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

@Component
public class BloomFilterPasswordBlacklist implements PasswordBlacklist {

    private final BloomFilter<CharSequence> filter;

    public BloomFilterPasswordBlacklist(
        PasswordProperties properties,
        ResourceLoader loader
    ) throws IOException
    {

        var blacklist = properties.getBlacklist();

        filter = BloomFilter.create(
            Funnels.stringFunnel(StandardCharsets.UTF_8),
            blacklist.getExpectedSize(),
            blacklist.getFalsePositiveRate()
        );

        if (!blacklist.isEnabled()) return;

        String filePath = blacklist.getFile();
        if (filePath == null) return;

        Resource resource = loader.getResource(filePath);

        try (
            InputStream is = resource.getInputStream();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
            )
        ) {

            String line;

            while ((line = reader.readLine()) != null)
                filter.put(line.trim().toLowerCase());
        }
    }


    @Override
    public boolean contains(String password) {
        return filter.mightContain(password.toLowerCase());
    }


}
