package pro.chenggang.project.reactive.mybatis.support.r2dbc.suite.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * r2dbc mybatis properties
 * @author Gang Cheng
 */
@Getter
@Setter
@ToString
public class R2dbcMybatisProperties {

    private boolean mapUnderscoreToCamelCase;
    private String[] mapperLocations;
    private String typeAliasesPackage;

    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<>();
        if (this.mapperLocations != null) {
            for (String mapperLocation : this.mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException ignore) {
                    //ignore
                }
            }
        }
        return resources.toArray(new Resource[0]);
    }
}
