package io.project.app.gate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

//@Component
@Slf4j
public class GatewayCustomFilter extends AbstractGatewayFilterFactory<GatewayCustomFilter.Config> implements Ordered {
    private final ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFilterFactory;

    public GatewayCustomFilter(ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFilterFactory) {
        super(Config.class);
        this.modifyResponseBodyFilterFactory = modifyResponseBodyFilterFactory;
    }

    @Override
    public GatewayFilter apply(Config config) {
        final ModifyResponseBodyGatewayFilterFactory.Config modifyResponseBodyFilterFactoryConfig = new ModifyResponseBodyGatewayFilterFactory.Config();
        modifyResponseBodyFilterFactoryConfig.setRewriteFunction(String.class, String.class, (swe, bodyAsString) -> {
            var request = swe.getRequest();
            log.info("Url: {}", request.getURI().getPath());
            String ipAddress = request.getHeaders().getFirst("X-FORWARDED-FOR");
            if (!StringUtils.hasLength(ipAddress)) {
                var ips = request.getRemoteAddress();
                ipAddress = ips == null ? "" : ips.getHostString();
            }
            log.info("GATE: ipAddress: {}", ipAddress);

            var headers = request.getHeaders().entrySet();
            log.info("GATE:  Headers: {}", headers);

            var params = request.getQueryParams();
            log.info("GATE:  Params: {}", params);
            log.info("GATE:  Response: {}", bodyAsString);
            bodyAsString += " HIEU MODIFIED";
            log.warn("GATE: Response code: {}", swe.getResponse().getStatusCode());
            return Mono.just(bodyAsString);
        });
        return modifyResponseBodyFilterFactory.apply(modifyResponseBodyFilterFactoryConfig);
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public static class Config {
        // Put the configuration properties
    }
}
