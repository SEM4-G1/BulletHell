package dk.sdu.group.one.services;

import dk.sdu.group.one.data.EntityManager;

public interface PostProcessingService {
    /**
     *Runs once for each implementation on each game loop
     *
     * @param entityManager list of entities
     */
    void postProcess(EntityManager entityManager);
}
