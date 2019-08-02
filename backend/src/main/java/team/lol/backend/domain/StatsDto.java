package team.lol.backend.domain;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * StatsDto
 */
@Data @Component @Lazy
public class StatsDto {

    private int kills;
    private int assists;
    private int deaths;
    private boolean win;

}