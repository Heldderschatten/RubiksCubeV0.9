package org.example.rubikscubev09.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.paint.Color;
import org.example.rubikscubev09.data.ColorAdapter;
import org.example.rubikscubev09.data.Graph;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class GraphToJsonConverter {

    /**
     * Writes a Graph object to a JSON file.
     *
     * @param graph    The Graph object to be converted to JSON.
     * @param filePath The path to the file where the JSON will be saved.
     */
    public static void writeGraphToJson(Graph graph, String filePath) {
        // Create a Gson instance to serialize the Graph object.
        // 'setPrettyPrinting()' makes the JSON output more human-readable by formatting it nicely.
        Gson gson = new GsonBuilder().registerTypeAdapter(Color.class, new ColorAdapter()).setPrettyPrinting().create();

        // Use a FileWriter to write the JSON data to the specified file.
        try (FileWriter writer = new FileWriter(filePath)) {
            // Convert the Graph object to JSON and write it to the file.
            gson.toJson(graph, writer);

            // Confirm that the file has been successfully written.
            System.out.println("Graph successfully written to JSON file: " + filePath);
        } catch (IOException e) {
            // If an error occurs during writing, output an error message.
            System.err.println("Error writing to JSON file: " + e.getMessage());
        }
    }


    /**
     * Reads a JSON file and converts it into a Graph object.
     *
     * @param filePath The path to the JSON file to be read and deserialized.
     * @return The deserialized Graph object, or null if an error occurs.
     */
    public static Graph readGraphFromJson(String filePath) {
        // Create a simple Gson instance to deserialize the JSON data.
        Gson gson = new GsonBuilder().registerTypeAdapter(Color.class,new ColorAdapter()).create();

        // Use a FileReader to open and read the JSON file.
        try (FileReader reader = new FileReader(filePath)) {
            // Read the JSON data and convert it into a Graph object.
            Graph graph = gson.fromJson(reader, Graph.class);

            // Confirm that the file has been successfully read.
            System.out.println("Graph successfully read from JSON file: " + filePath);

            // Return the deserialized Graph object.
            return graph;
        } catch (IOException e) {
            // If an error occurs during reading, output an error message and return null.
            System.out.println("Error reading from JSON file: " + e.getMessage());
            return null;
        }
    }
}

