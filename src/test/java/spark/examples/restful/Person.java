package spark.examples.restful;

public class Person {
	String firstName, lastName;
	int age;
	String memo;
	public String toString(){
		return firstName+" "+lastName+"("+age+") --"+memo+"--";
	}
}
