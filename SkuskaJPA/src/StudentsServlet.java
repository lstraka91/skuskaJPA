
import entity.Student;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    private Map<Integer, Student> students;
    
    @Override
    public void init() throws ServletException {
        super.init();

//        GameServiceHibernateImpl gshimpl = new GameServiceHibernateImpl();
//        try {
//        	Game game = new Game();
//        	game.setGameName("hra");
//        	game.setAuthor("autor");
//        	game.setGameURL("www");
//        	game.setDateCreated(new Date());
//			gshimpl.add(game);
//		} catch (GameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        students = new HashMap<Integer, Student>();
        students.put(1, new Student(1, "Janko", "Hrasko"));
        students.put(2, new Student(2, "Ferko", "Mrkva"));
        students.put(3, new Student(3, "Zuzka", "Petrzlenova"));
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    	String action = request.getParameter("action");
        if ("view".equals(action) && request.getParameter("id") != null) {
        	Student student = students.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("student", student);
            request.getRequestDispatcher("/WEB-INF/jsp/view_student.jsp").forward(request, response);
        } else if ("add".equals(action)) {
            	request.getRequestDispatcher("/WEB-INF/jsp/add_student.jsp").forward(request, response);
        } else if ("edit".equals(action) && request.getParameter("id") != null) {
        	Student student = students.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("student", student);
        	request.getRequestDispatcher("/WEB-INF/jsp/edit_student.jsp").forward(request, response);
        } else if ("insert".equals(action)) {
        	int id = students.size() + 1;
        	Student student = new Student(id, request.getParameter("firstName"), request.getParameter("lastName"));
        	students.put(id, student);
        	forwardToList(request, response);
        } else if ("save".equals(action)) {
        	Student student = students.get(Integer.parseInt(request.getParameter("id")));
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            forwardToList(request, response);
        } else {
            forwardToList(request, response);
        }
    }

	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("students", students.values());
		request.getRequestDispatcher("/WEB-INF/jsp/list_students.jsp").forward(request, response);
	}
}
