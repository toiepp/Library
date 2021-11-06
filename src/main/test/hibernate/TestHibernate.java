package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.mikholskiy.models.Book;
import org.mikholskiy.models.BookCategory;
import org.mikholskiy.models.Client;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHibernate {
  @Test
  public void testAddingEntitiesIntoDatabase() {
    try (SessionFactory sessionFactory = (new Configuration())
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Book.class)
        .addAnnotatedClass(Client.class)
        .addAnnotatedClass(BookCategory.class)
        .buildSessionFactory(); Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();

//      DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

      Book book = new Book(
          "White Fang",
          "Jack London",
          (new GregorianCalendar(1946, Calendar.SEPTEMBER, 1)).getTime(),
          1);

      BookCategory bc1 = new BookCategory("Adventure");
      BookCategory bc2 = new BookCategory("Western");

      Client c1 = new Client(
          "Ivan", "Mikholskiy", 20
      );

      Client c2 = new Client(
          "Vladimir", "Degtyarev", 19
      );

      book.setCategories(bc1, bc2);
      book.setTenant(c1);

//      session.save(book);
//      session.save(c2);


      Book resultBook = session.get(Book.class, 1);
      assertEquals("White Fang", resultBook.getTitle());
      assertEquals(resultBook.getTenant().getFirstName(), "Ivan");

      Client resultClient = session.get(Client.class, 1);

      session.getTransaction().commit();
    }
  }
}