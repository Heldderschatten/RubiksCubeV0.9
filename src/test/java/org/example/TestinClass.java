package org.example;

import org.example.rubikscubev09.Files.FilesReader;
import org.example.rubikscubev09.Files.GraphToJsonConverter;
import org.example.rubikscubev09.cubes.Controller_Cube_2by2;
import org.example.rubikscubev09.data.Graph;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestinClass {
    /*@Test
    void testFilReder(){
        Controller_Cube_2by2 controllerCube2by2 =  new Controller_Cube_2by2();
        controllerCube2by2.start();
        Graph g = controllerCube2by2.getG();
        FilesReader filesReader =  new FilesReader();
        String filename = "2by2File.json";
        Graph g1  = filesReader.readFiels( System.getProperty("user.dir")+ System.getProperty("file.separator") + filename);
        assertEquals(g,g1);
    }*/
    /*@Test
    void fileCreate(){
       /* Controller_Cube_2by2 controllerCube2by2 = new Controller_Cube_2by2();
        controllerCube2by2.start();
        String filepath = System.getProperty("user.dir") + "test2.json";
        GraphToJsonConverter.writeGraphToJson(controllerCube2by2.getG(),filepath);
        System.out.println("Something");
        assertEquals("1","1");
    }*/
    /*@Test
    void testJsonFile(){
        Controller_Cube_2by2 controllerCube2by2 = new Controller_Cube_2by2();
        controllerCube2by2.start();
        String filepath = System.getProperty("user.dir") +"\\"+ "test2.json";
        GraphToJsonConverter.writeGraphToJson(controllerCube2by2.getG(),filepath);
        Graph g2 =  GraphToJsonConverter.readGraphFromJson(filepath);
        assertEquals(controllerCube2by2.getG().toString(),g2.toString());
    }*/
}
