package romanhan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import romanhan.entity.Expenses;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static romanhan.dao.ExpensesDao.currentMonthAndYear;

class ExpensesDaoTest {

    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    protected void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void saveExpenses() {
        Expenses expenses = new Expenses();
        expenses.setId("November 2023");
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(expenses);

            session.getTransaction().commit();
        }
    }

    @Test
    void getExpensesById() {
        Expenses expenses = new Expenses();
        expenses.setId(currentMonthAndYear());
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(expenses);

            Expenses fromExpenses = session.get(Expenses.class, currentMonthAndYear());

            assertThat(expenses.getId()).isEqualTo(fromExpenses.getId());

            session.getTransaction().commit();
        }
    }

    @Test
    void getExpensesTable() {
        Expenses expenses = new Expenses();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(expenses);

            List<Expenses> fromExpenses = session.createQuery("from Expenses", Expenses.class).list();
            fromExpenses.forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}