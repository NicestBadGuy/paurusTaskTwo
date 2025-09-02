package si.paurus.assignment.tasktwo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
public final class TestEventBO {
    private final String matchId;
    private final Integer eventType;
    private final Integer eventSeq;
    private final OffsetDateTime createDate;
    @Setter
    private OffsetDateTime addedToResult;

    public TestEventBO(String matchId, Integer eventType, Integer eventSeq, OffsetDateTime createDate) {
        this.matchId = matchId;
        this.eventType = eventType;
        this.eventSeq = eventSeq;
        this.createDate = createDate;
        this.addedToResult = null;
    }

    public OffsetDateTime addedToResult() {
        return addedToResult;
    }

    public String matchId() {
        return matchId;
    }

    public Integer eventType() {
        return eventType;
    }

    public Integer eventSeq() {
        return eventSeq;
    }

    public OffsetDateTime createDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (TestEventBO) obj;
        return Objects.equals(this.matchId, that.matchId) &&
                Objects.equals(this.eventType, that.eventType) &&
                Objects.equals(this.eventSeq, that.eventSeq) &&
                Objects.equals(this.createDate, that.createDate) &&
                Objects.equals(this.addedToResult, that.addedToResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, eventType, eventSeq, createDate, addedToResult);
    }

    @Override
    public String toString() {
        return "EventBO[" +
                "matchId=" + matchId + ", " +
                "eventType=" + eventType + ", " +
                "eventSeq=" + eventSeq + ", " +
                "createDate=" + createDate + ", " +
                "addedToResult=" + addedToResult + ']';
    }

}
