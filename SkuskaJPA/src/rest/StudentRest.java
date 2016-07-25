package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import entity.Student;

@Path("/students")
public class StudentRest {
	private static List<Student> students;
	
	static {
		students = new ArrayList<>();
		students.add(new Student(1, "Janko", "Hrasko"));
		students.add(new Student(2, "Ferko", "Mrkva"));
		students.add(new Student(3, "Zuzka", "Petrzlenova"));
	}

	@GET
	@Produces("application/json")
	public List<Student> getStudents() {
		return students;
	}

	@GET
	@Path("/{index}")
	@Produces("application/json")
	public Student getStudent(@PathParam("index") int index) {
		return students.get(index - 1);
	}
	
	@POST
	@Consumes("application/json")
	public Response addStudent(Student student) {
		students.add(student);
		return Response.ok().build();
	}
}
