package com.ibm.client.engineering.echo.controller;

import com.ibm.client.engineering.echo.dto.JsonPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class EchoController {

    // ----------------------------------------------------- Instance Variables

    /**
     * Logger settings
     */
    private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @Autowired
    private HttpServletRequest request;

    // ----------------------------------------------------- Private Methods

    private static String cookieToString(final Cookie cookie) {
        final StringBuffer result = new StringBuffer();

        result.append(cookie.getName() + "=" + cookie.getValue());

        result.append("; Max-Age=" + cookie.getMaxAge());

        if (cookie.getDomain() != null) {
            result.append("; Domain=" + cookie.getDomain());
        }

        if (cookie.getPath() != null) {
            result.append("; Path=" + cookie.getPath());
        }

        if (cookie.getSecure()) {
            result.append("; Secure");
        }

        if (cookie.isHttpOnly()) {
            result.append("; HttpOnly");
        }

        return result.toString();
    }

    // ----------------------------------------------------- Protected Methods

    // ----------------------------------------------------- Public Methods

    @RequestMapping(value = "/echo/**", consumes = MediaType.ALL_VALUE, produces =
            MediaType.TEXT_HTML_VALUE, method = {RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS,
            RequestMethod.HEAD, RequestMethod.PATCH})
    @ResponseStatus(NOT_FOUND)
    public String echo(final @RequestBody(required = false) byte[] rawBody, final Model model) {
        model.addAttribute(JsonPayload.PATH, request.getServletPath());
        logger.info("path: {}", request.getServletPath());

        model.addAttribute(JsonPayload.PROTOCOL, request.getProtocol());
        logger.info("protocol: {}", request.getProtocol());

        model.addAttribute(JsonPayload.METHOD, request.getMethod());
        logger.info("method: {}", request.getMethod());

        final Map<String, String> headers = Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), request::getHeader));
        model.addAttribute(JsonPayload.HEADERS, headers);
        logger.info("headers: {}", headers);

        final String[] cookies = request.getCookies() == null ? null :
                Arrays.stream(request.getCookies()).map(EchoController::cookieToString).toArray(String[]::new);
        model.addAttribute(JsonPayload.COOKIES, cookies);
        logger.info("cookies: {}", (Object[]) cookies);

        final Map<String, String> parameters = request.getParameterMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.join("; ", e.getValue())));
        model.addAttribute(JsonPayload.PARAMETERS, parameters);
        logger.info("parameters: {}", parameters);

        final String body = rawBody != null ? new String(rawBody) : null;
        model.addAttribute(JsonPayload.BODY, body);
        logger.info("body: {}", body);

        return "echo";
    }

    @RequestMapping(value = "/echoJson", consumes = MediaType.ALL_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS,
            RequestMethod.HEAD, RequestMethod.PATCH})
    public ResponseEntity<JsonPayload> echoBack(final @RequestBody(required = false) byte[] rawBody) {
        final JsonPayload response = new JsonPayload();
        response.set(JsonPayload.PATH, request.getServletPath());
        logger.info("path: {}", request.getServletPath());

        response.set(JsonPayload.PROTOCOL, request.getProtocol());
        logger.info("protocol: {}", request.getProtocol());

        response.set(JsonPayload.METHOD, request.getMethod());
        logger.info("method: {}", request.getMethod());

        final Map<String, String> headers = Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), request::getHeader));
        response.set(JsonPayload.HEADERS, headers);
        logger.info("headers: {}", headers);

        final String[] cookies = request.getCookies() == null ? null :
                Arrays.stream(request.getCookies()).map(EchoController::cookieToString).toArray(String[]::new);
        response.set(JsonPayload.COOKIES, cookies);
        logger.info("cookies: {}", (Object[]) cookies);

        final Map<String, String> parameters = request.getParameterMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.join("; ", e.getValue())));
        response.set(JsonPayload.PARAMETERS, parameters);
        logger.info("parameters: {}", parameters);

        final String body = rawBody != null ? new String(rawBody) : null;
        response.set(JsonPayload.BODY, body);
        logger.info("body: {}", body);

        return ResponseEntity.status(NOT_FOUND).body(response);
    }
}