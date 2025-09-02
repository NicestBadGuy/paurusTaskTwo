package si.paurus.assignment.tasktwo.scedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataInsertionScheduler {

    @Scheduled(fixedRate = 1000) // TODO: think fixed delay? think implementation
    public void something() {
        System.out.println("scheduler works");
    }
}
