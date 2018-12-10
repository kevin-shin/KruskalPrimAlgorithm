package app;


import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String args[]) {
        // Create a preset Vertex set
        Graph graph = new Graph();

        ArrayList<Vertex> list = new ArrayList<>();
        Vertex olri = new Vertex("OLRI", 406, 293);
        Vertex lc = new Vertex("LC", 602, 431);
        Vertex neill = new Vertex("NEILL", 517, 268);
        Vertex kirk = new Vertex("KIRK", 723, 472);
        Vertex library = new Vertex("LIBRARY", 723, 397);
        Vertex oldMain = new Vertex("OLD MAIN", 733, 333);
        Vertex cc = new Vertex("CAMPUS CENTER", 883, 466);
        Vertex chapel = new Vertex("CHAPEL", 926, 332);
        Vertex weyerhaeuser = new Vertex("WEYERHAEUSER", 878, 262);
        Vertex carnegie = new Vertex("CARNEGIE", 760, 264);
        Vertex sevenMac = new Vertex("77 MAC", 854, 194);
        Vertex jwall = new Vertex("JWALL", 549, 207);
        Vertex studio = new Vertex("STUDIOS", 603, 246);
        Vertex music = new Vertex("MUSIC", 599, 151);
        Vertex kagin = new Vertex("KAGIN", 1094, 470);
        Vertex markim = new Vertex("MARKIM", 1033, 453);
        Vertex doty = new Vertex("DOTY", 1109, 344);
        Vertex turck = new Vertex("TURCK", 1050, 338);
        Vertex bigelow = new Vertex("BIGELOW", 1027, 272);
        Vertex thirtyMac = new Vertex("30 MAC", 1090, 274);
        Vertex wallace = new Vertex("WALLACE", 1143, 287);
        Vertex gdd = new Vertex("GDD", 1116, 112);
        Vertex stadium = new Vertex("STADIUM", 168, 487);
        Vertex language = new Vertex("LANGUAGE HOUSES", 223, 92);

        list.add(olri);
        list.add(lc);
        list.add(neill);
        list.add(kirk);
        list.add(library);
        list.add(oldMain);
        list.add(cc);
        list.add(chapel);
        list.add(weyerhaeuser);
        list.add(carnegie);
        list.add(sevenMac);
        list.add(jwall);
        list.add(studio);
        list.add(music);
        list.add(kagin);
        list.add(markim);
        list.add(doty);
        list.add(turck);
        list.add(bigelow);
        list.add(thirtyMac);
        list.add(wallace);
        list.add(gdd);
        list.add(stadium);
        list.add(language);

        for (Vertex vertex : list) {
            graph.addVertex(vertex);
        }

        Window window = new Window(graph);
    }
}

