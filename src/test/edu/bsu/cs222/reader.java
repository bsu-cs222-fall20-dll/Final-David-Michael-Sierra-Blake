import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Reader{
    public static void main(String[] args) throws Exception{
        Object read = new JSONParser().parse(new FileReader("test-story.json"));

        JSONObject obj = (JSONObject) read;

        String Text = (String) obj.get("Text");
        System.out.println(Text);

        Map StartRoom = ((Map)obj.get("StartRoom"));
        Iterator<Map.Entry> itr1 = StartRoom.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

    }
}
