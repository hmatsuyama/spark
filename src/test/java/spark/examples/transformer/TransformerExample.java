package spark.examples.transformer;

import static spark.Spark.get;

import java.util.HashMap;

public class TransformerExample {

    public static void main(String args[]) {
        get("/hello", "application/json", (request, response) -> {
            //return new MyMessage("Hello World");
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("greet","こんにちは");
        	map.put("to","世界の皆さん");
        	return map;
        }, new JsonTransformer());
    }

}
