package si.paurus.assignment.tasktwo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import si.paurus.assignment.tasktwo.repository.MatchEventEntity;
import si.paurus.assignment.tasktwo.repository.MatchEventJpaRepo;
import si.paurus.assignment.tasktwo.util.RawLineMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DataInsertionService {
    private final int batchSize;
    private final List<MatchEventEntity> buffer;

    private final MatchEventJpaRepo matchEventJpaRepo;

    public DataInsertionService(
            @Value("${insertion.batch-size:1000}") int batchSize,
            MatchEventJpaRepo matchEventJpaRepo) {
        this.batchSize = batchSize;
        this.matchEventJpaRepo = matchEventJpaRepo;
        buffer = new ArrayList<>(batchSize);
    }


    public void startDataInsertion(Path filePath) throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            Iterator<String> it = lines.iterator();
            while (it.hasNext()) {
                RawLineMapper.toEntity(it.next()).ifPresent(buffer::add);
                if (buffer.size() == batchSize) {
                    matchEventJpaRepo.saveAll(buffer);
                    buffer.clear();
                }
            }

            if (!buffer.isEmpty()) {
                matchEventJpaRepo.saveAll(buffer);
                buffer.clear();
            }
        }
    }
}
