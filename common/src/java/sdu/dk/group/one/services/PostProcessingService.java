package java.sdu.dk.group.one.services;

import sdu.dk.group.one.data.Entitylist;

public interface PostProcessingService {
    /**
     *Runs once for each implementation on each game loop
     *
     * @param entitylist list of entities
     */
    void postProcess(Entitylist entitylist);
}
