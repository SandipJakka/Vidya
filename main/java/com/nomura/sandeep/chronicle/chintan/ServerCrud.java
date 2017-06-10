package com.nomura.sandeep.chronicle.chintan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerCrud {

    private final Map<String, Integer> servers;
    private final String[] server_list;
    private final Set<Integer> activeIndices;
    private int counter = -1;

    public ServerCrud(int capacity) {
        servers = new HashMap<>(capacity);
        server_list = new String[capacity];
        activeIndices = new HashSet<>(capacity);
    }

    public ServerCrud() {
        this(1024);
    }

    public void insert(String server) {
        ++counter;
        servers.put(server, counter);
        server_list[counter] = server;
        activeIndices.add(counter);
    }

    public void delete(String server) {
        if (servers.containsKey(server)) {
            int index = servers.get(server);
            servers.remove(server);
            server_list[index] = "";
            activeIndices.remove(index);
            System.out.println("Deleted : " + server);
        } else {
            throw new RuntimeException("Delete failed ...");
        }
    }

    public String randomServer() {
        int index = rand(0, activeIndices.size() - 1);
        return server_list[index];
    }

    public static int rand(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }


    public static void main(String[] args) {
        ServerCrud serverCrud = new ServerCrud();
        serverCrud.insert("a");
        serverCrud.insert("b");
        serverCrud.insert("c");
        serverCrud.insert("e");
        serverCrud.insert("f");
        serverCrud.insert("g");
        serverCrud.insert("h");
        serverCrud.insert("i");
        serverCrud.insert("j");
        serverCrud.insert("k");

        System.out.println(serverCrud.randomServer());

        serverCrud.delete("b");

        System.out.println(serverCrud.randomServer());

        serverCrud.delete("c");

        System.out.println(serverCrud.randomServer());
        System.out.println(serverCrud.randomServer());
        System.out.println(serverCrud.randomServer());
        System.out.println(serverCrud.randomServer());
    }

}
