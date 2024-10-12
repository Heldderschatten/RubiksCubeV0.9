package org.example.rubikscubev09.data;

import com.google.gson.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

// Adapter for serializing and deserializing JavaFX Color
public class ColorAdapter implements JsonSerializer<Color>, JsonDeserializer<Color> {

    @Override
    public JsonElement serialize(Color color, Type type, JsonSerializationContext context) {
        // Convert Color object to JSON string
        return new JsonPrimitive(String.format("#%02X%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255),
                (int) (color.getOpacity() * 255)));
    }

    @Override
    public Color deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        // Convert JSON string back to Color object
        return Color.web(json.getAsString());
    }
}
