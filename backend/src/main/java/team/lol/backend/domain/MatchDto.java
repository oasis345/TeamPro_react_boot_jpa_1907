package team.lol.backend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * MatchDto is Detail of game
 */
@Component @Data @Lazy
public class MatchDto {

    @JsonProperty("participantIdentities")
    private List<PlayerDto> playersList;

    private int mapId;
    //private List<Object> teams;
    private List<PlayerDetailDto> participants;

    private Long gameDuration;
}