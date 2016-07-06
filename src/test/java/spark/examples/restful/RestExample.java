package spark.examples.restful;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RestExample {

	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<Integer, Person> personDb = new LinkedHashMap<Integer, Person>();
		Person person = new Person();
		person.firstName = "John";
		person.lastName = "Doe";
		person.age=66;
		person.memo="まあまあのおっさん";
		
		personDb.put(person.hashCode(),person);
		
	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", "*");
	        response.type("application/json;charset=utf-8");
	    });
		

		get("/persons", "application/json", (req,res)->{
			JsonArray arr = new JsonArray();
			for(int id: personDb.keySet()){
				JsonObject per = gson.fromJson(gson.toJson(personDb.get(id)), JsonElement.class);
				per.addProperty("id", id);
				arr.add(per);
				}
			String str = gson.toJson(arr);
			System.out.println(str);
			return str;
		});
		
		get("/persons/:id", "application/json", (req,res)->{
			int id=Integer.parseInt(req.params(":id"));
			Person targetPerson = personDb.get(id);
			return targetPerson;
		}, gson::toJson);
		
		post("/persons", "application/json", (req,res)->{
			res.type("application/json");
			Person personNew = new Person();
			System.out.println(req.queryParams().toString());
			personNew.firstName=req.queryParams("firstName");
			personNew.lastName=req.queryParams("lastName");
			personNew.age=Integer.parseInt(req.queryParams("age"));
			personNew.memo=req.queryParams("memo");
			personDb.put(personNew.hashCode(),personNew);
			return personNew.hashCode();
		}, gson::toJson);
		
		put("/persons/:id", "application/json", (req,res)->{
			int id=Integer.parseInt(req.params(":id"));
			//TODO PUTする。
			return null;
		}, gson::toJson);
		
		patch("/persons/:id", "application/json", (req,res)->{
			int id=Integer.parseInt(req.params(":id"));
			//TODO patchする。
			return null;
		}, gson::toJson);
		
		delete("/persons/:id", "application/json", (req,res)->{
			int id=Integer.parseInt(req.params(":id"));
			//TODO deleteする。
			personDb.remove(id);
			return null;
		}, gson::toJson);
		
		get("/test", (req,res)->{
			res.status(303);
			res.header("Location", "http://www.orb-japan.co.jp");
			return -1;
		}, gson::toJson);
		
	}

}
