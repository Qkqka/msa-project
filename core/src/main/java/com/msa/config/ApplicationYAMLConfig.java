package com.msa.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter @Setter
@ConfigurationProperties(prefix = "application")
public class ApplicationYAMLConfig {

    // GATEWAY========================================

    private Auth auth = new Auth();

    private Web web = new Web();

    @Getter @Setter
    public class Auth {
        private List<String> adminExceptUrlList;
    }

    @Getter @Setter
    public class Web {
        private Back back = new Back();

        @Getter @Setter
        public class Back {
            private String accessKey;
            private String accessValue;
        }
    }

    // API 서버========================================

    private Greeting greeting = new Greeting();

    private Session session = new Session();

    @Getter @Setter
    public class Greeting {
        private String message;
    }

    @Getter @Setter
    public class Session {
        private String key;
    }
}
