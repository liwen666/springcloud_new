package jrx.data.hub.compile.config;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:22
 */

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ConfigurationProperties(
        prefix = "jrx.data.hub.maven"
)
@Builder
/**
 */
public class MavenParamConfig {

    private String mavenHome;
    private String mavenSetting;
}