
import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/OfficeEmployeeServlet")
public class OfficeEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OfficeEmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAoneToMany0110_02");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Office oc = new Office();
          
        oc.setOfficeCode("8");
        oc.setCity("Taipei");
        oc.setAddressLine1("Kung Yuan");
        oc.setCountry("Taiwan");
        oc.setPhone("02-23826015");
        oc.setPostalCode("100");
        oc.setState("TW");
        oc.setTerritory("TW");
        
        Employee repto=new Employee();
        repto.setEmployeeNumber(1056);
        
        Employee e1=new Employee();
        e1.setEmployeeNumber(1801);
        e1.setFirstName("Amy");
        e1.setLastName("Chen");
        e1.setEmail("amy@demo.com");
        e1.setExtension("0x12345");
        e1.setOffice(oc);
        e1.setJobTitle("Sales");
        e1.setEmployee(repto);
        Employee e2=new Employee();
        e2.setEmployeeNumber(1802);
        e2.setFirstName("Mary");
        e2.setLastName("Wang");
        e2.setEmail("Wang@demo.com");
        e2.setExtension("0x12345");
        e2.setOffice(oc);
        e2.setJobTitle("Sales");
        e2.setEmployee(repto);
        ArrayList<Employee> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        oc.setEmployees(list);          
        em.persist(oc);

        em.getTransaction().commit();
        em.close();
        emf.close();

		response.getWriter().append("Office Employee Saved  ");		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
