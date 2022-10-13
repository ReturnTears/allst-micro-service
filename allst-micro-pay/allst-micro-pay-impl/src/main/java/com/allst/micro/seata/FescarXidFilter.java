package com.allst.micro.seata;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hutu
 * @since 2022-10-13 下午 09:57
 */
@Slf4j
public class FescarXidFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String xid = RootContext.getXID();
        String restXid = request.getHeader("Fescar-Xid");
        boolean bind = false;
        if (StringUtils.isBlank(xid) && StringUtils.isNotBlank(restXid)) {
            RootContext.bind(restXid);
            bind = true;
            if (logger.isDebugEnabled()) {
                logger.debug("bind[" + restXid + "] to RootContext");
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            if (bind) {
                String unbindXid = RootContext.unbind();
                if (logger.isDebugEnabled()) {
                    logger.debug("unbind[" + unbindXid + "] from RootContext");
                }
                if (!restXid.equalsIgnoreCase(unbindXid)) {
                    logger.warn("xid in change during http rest from " + restXid + " to " + unbindXid);
                    if (unbindXid != null) {
                        RootContext.bind(unbindXid);
                        logger.warn("bind [" + unbindXid + "] back to RootContext");
                    }
                }
            }
        }
    }

}
