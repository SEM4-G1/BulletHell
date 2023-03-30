package sdu.dk.group.one;

import java.sdu.dk.group.one.Entitylist;


public interface GamePluginService {
    /**
     *Runs once for each implementation on each game loop
     *
     * @param entitylist list of entities
     */
    void process(Entitylist entitylist);

    /**
     * Runs on creation of implementation
     *
     * @param entitylist list of entities
     */
    void start(Entitylist entitylist);

    /**
     * Runs on destruction of implementation
     *
     * @param entitylist
     */
    void stop(Entitylist entitylist);
}
