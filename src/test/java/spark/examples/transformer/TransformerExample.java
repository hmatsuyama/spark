package spark.examples.transformer;

import static spark.Spark.get;

import java.util.HashMap;

import com.google.gson.Gson;

public class TransformerExample {

    public static void main(String args[]) {
    	
    	Gson gson = new Gson();
    	
        get("/hello", "application/json", (request, response) -> {
            //return new MyMessage("Hello World");
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("greet","こんにちは");
        	map.put("to","日本の皆さん");
        	return map;
        }, gson::toJson);
    }

}
