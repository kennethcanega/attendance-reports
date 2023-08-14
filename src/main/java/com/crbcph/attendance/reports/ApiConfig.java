package com.crbcph.attendance.reports;

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
}
