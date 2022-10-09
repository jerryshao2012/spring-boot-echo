package com.ibm.client.engineering.echo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    // ----------------------------------------------------- Instance Variables

    /**
     * Logger settings
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    // ----------------------------------------------------- Private Methods

    private String getStringValue(final byte[] contentAsByteArray, final String characterEncoding) {
        try {
            if (contentAsByteArray != null)
                return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (final UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        }
        return "";
    }

    // ----------------------------------------------------- Protected Methods

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request     Http servlet request
     * @param response    Http servlet response
     * @param filterChain Filter Chain instance
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        final long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);

        if (logger.isDebugEnabled()) {
            final long timeTaken = System.currentTimeMillis() - startTime;

            final String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                    request.getCharacterEncoding());
            final String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                    response.getCharacterEncoding());

            logger.debug("Finished processing: method={}, requestURI={}, requestPayload={}, responseCode={}, responseBody={}, timeTaken={}",
                    request.getMethod(), request.getRequestURI(), requestBody,
                    response.getStatus(), responseBody, timeTaken);
        }

        responseWrapper.copyBodyToResponse();
    }

    // ----------------------------------------------------- Public Methods

}
