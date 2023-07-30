
/* Copyright (c) 2023 Rodrigo Bento */

package rbento.starter;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "starter")
public class Config {

    private String host;
    private Integer port;
    private List<String> strings;
    private Map<String, String> maps;

    @PostConstruct
    public void init() {
        log.info("host: {}", host);
        log.info("port: {}", port);
        log.info("strings: {}", strings);
        log.info("maps: {}", maps);
    }
}
