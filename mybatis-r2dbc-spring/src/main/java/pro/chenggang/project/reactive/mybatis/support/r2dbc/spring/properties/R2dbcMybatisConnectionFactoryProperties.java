package pro.chenggang.project.reactive.mybatis.support.r2dbc.spring.properties;

import io.r2dbc.spi.ValidationDepth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ClassUtils;
import reactor.util.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

/**
 * The type R2dbc connection factory properties.
 *
 * @author Gang Cheng
 * @version 1.0.0
 */
@Getter
@Setter
@ToString
public class R2dbcMybatisConnectionFactoryProperties {

    /**
     * The configuration properties prefix.
     */
    public static final String PREFIX = "spring.r2dbc.mybatis";

    /**
     * Name of the connection factory
     */
    private String name;

    /**
     * Whether to generate a random connection factory name.
     */
    private boolean generateUniqueName = true;

    /**
     * R2dbc format Url
     */
    private String r2dbcUrl;

    /**
     * Login username of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    /**
     * r2dbc factory pull
     */
    private Pool pool = new Pool();

    /**
     * r2dbc connection factory metrics enabled
     */
    private Boolean enableMetrics = Boolean.FALSE;

    /**
     * r2dbc connection factory name based on configuration
     *
     * @return the connection factory name to use or {@code null}
     */
    public String determineConnectionFactoryName() {
        if (this.generateUniqueName && !hasText(this.name)) {
            this.name = UUID.randomUUID().toString();
        }
        return this.name;
    }

    /**
     * r2dbc connection url
     *
     * @return string
     */
    public String determineConnectionFactoryUrl() {
        if (!hasText(this.r2dbcUrl)) {
            return null;
        }
        String r2dbcUrl = this.r2dbcUrl;
        String encodedUsername;
        try {
            encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            //fallback to original username
            encodedUsername = username;
        }
        String encodedPassword;
        try {
            encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            //fallback to original password
            encodedPassword = password;
        }
        String credential = encodedUsername + (password == null || password.isEmpty() ? "" : ":" + encodedPassword);
        //only replace 'r2dbc:mysql:' with 'r2dbc:mariadb' when using 'MariadbConnectionFactory'
        boolean isMariadbConnectionfactoryPresent = ClassUtils.isPresent("org.mariadb.r2dbc.MariadbConnectionFactory", this.getClass().getClassLoader());
        if (isMariadbConnectionfactoryPresent && r2dbcUrl.startsWith("r2dbc:mysql:")) {
            r2dbcUrl = r2dbcUrl.replace("r2dbc:mysql:", "r2dbc:mariadb:");
        }
        r2dbcUrl = r2dbcUrl.replace("//", "//" + credential + "@");
        return r2dbcUrl;
    }

    /**
     * The type Pool.
     */
    @Getter
    @Setter
    @ToString
    public static class Pool {

        /**
         * r2dbc connection factory initial size
         * default 1
         */
        private Integer initialSize = 1;

        /**
         * r2dbc connection factory max size
         * default 10
         */
        private Integer maxSize = 10;

        /**
         * r2dbc connection factory max idle time
         */
        private Duration maxIdleTime = Duration.ofMinutes(30);

        /**
         * r2dbc connection factory max create connection time
         * ZERO indicates no-timeout
         */
        private Duration maxCreateConnectionTime = Duration.ZERO;
        /**
         * r2dbc connection factory max acquire time
         * ZERO indicates no-timeout
         */
        private Duration maxAcquireTime = Duration.ZERO;

        /**
         * r2dbc connection factory max lifetime
         * ZERO indicates no-lifetime
         */
        private Duration maxLifeTime = Duration.ZERO;
        /**
         * r2dbc connection factory validation query
         */
        @Nullable
        private String validationQuery;

        /**
         * r2dbc connection factory validation depth
         * LOCAL Perform a client-side only validation
         * REMOTE Perform a remote connection validations
         * {@link ValidationDepth}
         */
        private ValidationDepth validationDepth = ValidationDepth.REMOTE;

        /**
         * r2dbc connection factory acquire retry
         * ZERO indicates no-retry
         * default 1
         */
        private int acquireRetry = 1;

        /**
         * r2dbc connection factory background eviction interval
         * ZERO indicates no-timeout, negative marks unconfigured.
         */
        private Duration backgroundEvictionInterval = Duration.ofNanos(-1);

    }

}
