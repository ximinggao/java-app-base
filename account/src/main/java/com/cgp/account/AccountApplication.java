package com.cgp.account;

import com.cgp.common.ContextPropagationConfiguration;
import com.cgp.common.FilterConfiguration;
import com.cgp.common.OpenTelemetryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OpenTelemetryConfiguration.class, ContextPropagationConfiguration.class, FilterConfiguration.class})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
