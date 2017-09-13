package io;

import com.sun.security.ntlm.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * IFileWriter is a public interface for any class that takes a form as input and outputs a file. This means that there
 * may be MIDI writers, io.Log writers, LilyPond writers, MusicXML writers, ABC writers, and so on.
 */
public interface IFileWriter<T> {

    /**
     *
     * @param type
     * @param filename
     */
    void write(T type, String filename);

    /**
     * Writes
     * @param types
     * @param filename
     */
    default void write(Collection<T> types, String filename) {
        int index = 0;
        for(T type : types) {
            write(type,filename + "_" + index++);
        }

        /*
        Set<Server> servers = getServers();
        Map<String, String> serverData = new ConcurrentHashMap<>();

        servers.parallelStream().forEach((server) -> {
            serverData.put(server.getIdentifier(), server.fetchData());
        });
        */
    }

}
