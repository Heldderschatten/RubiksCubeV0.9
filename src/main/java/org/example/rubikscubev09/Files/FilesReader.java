package org.example.rubikscubev09.Files;

import javafx.scene.paint.Color;
import org.example.rubikscubev09.data.Graph;
import org.example.rubikscubev09.data.Node;
import org.example.rubikscubev09.data.Point;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FilesReader {
    public void makeSafe(Graph graph, String filename) {
        System.out.println("FilesReader.makeSafe");
        String Path = System.getProperty("user.dir");
        Path += System.getProperty("file.separator") + filename;
        File file = new File(Path);
        if (file.exists()) {
            file.delete();
        }
        if (generateFile(Path)) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(graph.toString());
                System.out.println(file.getAbsoluteFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean generateFile(String Path) {
        System.out.println("FilesReader.generateFile");
        File file = new File(Path);
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public Graph readFiels(String path) {
        System.out.println("FilesReader.readFiels");
        Path filePath = Paths.get(path);
        System.out.println(filePath);
        int n = 0;
        List<String> all;
        try (BufferedReader fileReader = Files.newBufferedReader(filePath)) {
            all = Files.readAllLines(filePath);
            for (String s : all) {
                //System.out.println(s);
            }
        } catch (FileSystemNotFoundException io) {
            System.out.println(io);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graph g = getGraphFromFile(all);
        return g;
    }

    public Graph getGraphFromFile(List<String> jasonFileStrings) {
        System.out.println("FilesReader.getGraphFromFile");
        //Parameters
        int n = -1;
        int numberOfNodes = -1;
        int[][] adjacencylists = null;
        Node[] nodes = null;


        String[] jsonFileStringArry = new String[jasonFileStrings.size()];
        jasonFileStrings.toArray(jsonFileStringArry);

        //Extract n
        int iN = findLine("\"n\"", jsonFileStringArry);
        System.out.println("IN : " + iN);
        String work = jsonFileStringArry[iN].toString();
        work = work.replace("\"n\" : ", "");
        work = work.replace(",", "");
        work = work.trim();
        //System.out.println("Work: " + work);
        n = Integer.parseInt(work);

        //Extract numberOfnodes:
        int iNumberOfNodes = findLine("\"numberOfNodes\"", jsonFileStringArry);
        //System.out.println("InumberOfNodes : " + iNumberOfNodes);
        work = jsonFileStringArry[iNumberOfNodes].toString();
        work = work.replace("\"numberOfNodes\" : ", "");
        work = work.replace(",", "");
        work = work.trim();
        numberOfNodes = Integer.parseInt(work);

        //Ectract adjacencylists
        adjacencylists = new int[n][n];
        int iadjacencylists = findLine("\"adjacencylists\" : ", jsonFileStringArry);
        System.out.println("adjacencylists : " + iadjacencylists);

        work = jsonFileStringArry[iadjacencylists].toString();
        work = work.replace("\"adjacencylists\" : ", "");
        work = work.replace("[", "");
        work = work.trim();
        //System.out.println(work);
        String[] works = work.split("],");
        int i = 0;
        int j = 0;
        for (String s : works) {
            //System.out.println("i: " + i);
            s = s.replace("]", "");
            String[] sW = s.split(",");
            j = 0;
            for (String stringNumber : sW) {
                adjacencylists[i][j] = Integer.parseInt(stringNumber);
                j++;
                //System.out.println("j: " +j);
            }
            i++;
        }


        //Extract Nodes:
        int iNodes = findLine("\"nodes\"", jsonFileStringArry);
        System.out.println("nodes : " + iNodes);
        nodes = new Node[n];
        int counterOfNodes = 0;
        int counterOfSquareBrackets = 0;
        int counterLines = iNodes - 1;
        boolean endFound = false;
        do {
            counterLines++;
            char[] charArray = jsonFileStringArry[counterLines].toCharArray();
            for (char c : charArray) {
                if (c == '[') {
                    counterOfSquareBrackets++;
                }
                if (c == ']') {
                    counterOfSquareBrackets--;
                }
            }
        } while (counterOfSquareBrackets != 0);
        System.out.println(counterLines);
        String comparedString = "";
        for (int k = iNodes + 2; k < counterLines; k++) {
            comparedString += jsonFileStringArry[k];
        }
        String[] split = comparedString.split("\"Node\" : ");
        int counter = 0;
        for (String s : split) {
            s = s.replace("{", "");
            s = s.replace("}", "");
            s = s.replace("\t", "");
            s = s.replace(" ", "");
            s = s.replace("\"Point\":", "");
            s = s.trim();
            String[] split2 = s.split(",");

            String name = "";
            Color color = Color.WHITE;
            for (String s2 : split2) {
                s2 = s2.replace("\"", "");
                if (s2.contains("name")) {
                    s2 = s2.replace("name:", "");
                    name = s2;
                }
                if (s2.contains("color")) {
                    s2 = s2.replace("color:", "");
                    color = Color.valueOf(s2);
                }
                System.out.println(s2);
            }
            nodes[counter] = new Node(new Point(name,color));
            counter++;
        }
        Graph g =  new Graph(n, numberOfNodes,adjacencylists,nodes);

        //testausgabe:
        /*System.out.printf("testausgabe:\nn: %2d \n" +
                "numberOfNodes: %2d \n" +
                "adjacencylists %s \n" +
                "Nodes: %s\n", n, numberOfNodes, adjacencylists, nodes);
*/
        return g;
    }

    private int findLine(String search, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains(search)) {
                return i;
            }
        }
        boolean found = false;
        return -1;
    }
}
