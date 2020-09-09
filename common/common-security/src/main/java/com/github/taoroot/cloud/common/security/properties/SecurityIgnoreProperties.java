package com.github.taoroot.cloud.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.security.ignore")
public class SecurityIgnoreProperties {

    private List<List<String>> urls = new ArrayList<>();
}
