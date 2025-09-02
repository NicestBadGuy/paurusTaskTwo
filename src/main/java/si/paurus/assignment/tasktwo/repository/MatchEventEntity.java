package si.paurus.assignment.tasktwo.repository;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(indexes = {@Index(name = "idx_match_id", columnList = "matchId")})
public class MatchEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dateInsert;

    private String matchId;

    private Integer marketId;

    private String outcomeId;

    private String specifiers;

    @PrePersist
    public void prePersist() {
        dateInsert = OffsetDateTime.now();
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(String outcomeId) {
        this.outcomeId = outcomeId;
    }

    public String getSpecifiers() {
        return specifiers;
    }

    public void setSpecifiers(String specifiers) {
        this.specifiers = specifiers;
    }

    public OffsetDateTime getDateInsert() {
        return dateInsert;
    }
}
