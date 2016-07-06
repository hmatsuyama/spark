package spark.examples.restful;

import static spark.Spark.*;

import java.util.LinkedHashMap;

import java.util.Map;

import com.google.gson.Gson;

public class RestExample {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
		Map<Integer, Person> personDb = new LinkedHashMap<Integer, Person>();
		Person person = new Person();
		person.firstName = "John";
		person.firstName = "Doe";
		person.age=66;
		person.memo="まあまあのおっさん";
		
		personDb.put(person.toString().hashCode(),person);

		get("/persons", "application/json", (req,res)->{
			return personDb;
		}, gson::toJson);
		
		post("/persons", "application/json", (req,res)->{
			Person personNew = new Person();
			personNew.firstName=req.queryParams("firstName");
			personNew.lastName=req.queryParams("lastName");
			personNew.age=Integer.parseInt(req.queryParams("age"));
			personNew.memo=req.queryParams("memo");
			personDb.put(personNew.toString().hashCode(),personNew);
			return personNew.toString().hashCode();
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
		
	}

}
