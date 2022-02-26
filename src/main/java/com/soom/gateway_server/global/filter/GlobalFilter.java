package com.soom.gateway_server.global.filter;

import com.google.common.base.Strings;
import com.soom.gateway_server.global.data.FilterMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<FilterMetadata> {
    private static final Logger LOGGER = LogManager.getLogger(GlobalFilter.class);

    public GlobalFilter() {
        super(FilterMetadata.class);
    }

    @Override
    public GatewayFilter apply(FilterMetadata config) {
        return (exchange, chain) -> {
            final String uuid = UUID.randomUUID().toString();
            LOGGER.info(String.format("GlobalFilter START that id %s -> %s", uuid, exchange.getRequest().getPath()));
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    LOGGER.info(String.format("GlobalFilter END that id %s -> %s", uuid,  exchange.getResponse()))
            ));
        };
    }
}
