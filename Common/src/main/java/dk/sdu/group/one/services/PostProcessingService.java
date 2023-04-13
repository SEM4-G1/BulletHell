package dk.sdu.group.one.services;

import dk.sdu.group.one.data.EntityList;
public interface PostProcessingService {
    /**
     *Runs once for each implementation on each game loop
     *
     * @param entitylist list of entities
     */
    void postProcess(EntityList entitylist);
}
