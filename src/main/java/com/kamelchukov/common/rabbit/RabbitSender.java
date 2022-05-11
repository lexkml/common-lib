package com.kamelchukov.common.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamelchukov.common.model.dto.messageDto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RabbitSender {

    private RabbitTemplate template;
    private Queue queue;
    private HttpServletRequest request;
    private ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    @SneakyThrows
    public void sendMessage() {
        var messageDto = createMessage();
        logger.info("Sending message via RabbitMQ: " + messageDto);
        template.convertAndSend(queue.getName(), mapper.writeValueAsString(messageDto));
        logger.info("Message was sent successfully");
    }

    private MessageDto createMessage() {
        return MessageDto.builder()
                .username(getKeycloakUserName())
                .path(getPath())
                .time(LocalDateTime.now())
                .build();
    }

    private String getPath() {
        return request.getRequestURI();
    }

    private String getKeycloakUserName() {
        var authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
        return principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
    }
}
