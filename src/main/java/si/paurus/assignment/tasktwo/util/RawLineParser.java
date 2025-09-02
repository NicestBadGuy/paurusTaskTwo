package si.paurus.assignment.tasktwo.util;

import si.paurus.assignment.tasktwo.model.NewEventBO;

import java.util.regex.Pattern;

public class RawLineParser {

    private static final Pattern LINE = Pattern
            .compile("^'(?<matchId>[^']+)'\\|(?<eventId>\\d+)\\|'(?<notEventSeq>[^']+)'\\|(?:'(?<attrs>[^']*)')?$");
    private static final Pattern TEST_LINE = Pattern
            .compile("");

    public static NewEventBO parse(String rawLine) {
        var matcher = LINE.matcher(rawLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Couldn't parse " + rawLine);
        }
        var matchId = matcher.group("matchId");
        var eventId = Long.parseLong(matcher.group("eventId"));
        var notEventSequence = (matcher.group("notEventSeq"));
        var attrs = matcher.group("attrs");

        return new NewEventBO(matchId, eventId, notEventSequence, attrs);
    }

    public static NewEventBO parseTest() {return null;}
}
