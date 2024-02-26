package per.itachi.java.mq.kafka.joint.controller.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContextLoggingFilter implements Filter {

    private static final String CONTEXT_REQUEST_ID = "x-request-id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        setup();
        try {
            chain.doFilter(request, response);
        }
        finally {
            teardown();
        }
    }

    private void setup() {
        MDC.put(CONTEXT_REQUEST_ID, UUID.randomUUID().toString());
    }

    private void teardown() {
        MDC.remove(CONTEXT_REQUEST_ID);
    }
}
