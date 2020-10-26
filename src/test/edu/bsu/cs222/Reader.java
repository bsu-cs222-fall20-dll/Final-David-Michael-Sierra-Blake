package edu.bsu.cs222;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Reader{
    public static void main(String[] args) throws Exception{
        Object read = new JsonParser().parse(new FileReader("test-story.json"));

        JsonObject obj = (JsonObject) read;

        String Text = obj.getAsJsonObject("Text").toString();
        System.out.println(Text);

        Map StartRoom = ((Map)obj.get("StartRoom"));
        Iterator<Map.Entry> itr1 = StartRoom.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

    }
}
