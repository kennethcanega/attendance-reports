package com.crbcph.attendance.component.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiConfig {
    @Value("${api.ushering.url}")
    private String url;
    @Value("${client.application.id}")
    private String clientId;
    @Value("${client.application.secret}")
    private String clientSecret;
}
