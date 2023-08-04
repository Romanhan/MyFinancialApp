package romanhan.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import romanhan.entity.Expenses;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static romanhan.dao.ExpensesDao.currentMonthAndYear;

class ExpensesDaoTest {

    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() {
        // A SessionFactory is set up once for an application!
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Expenses.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
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
        Expenses expenses = Expenses.getExpenses();
        expenses.setId("November 2023");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(expenses);
            session.getTransaction().commit();

            Expenses fromExpenses = session.get(Expenses.class, expenses.getId());

            assertEquals(fromExpenses.getId(), expenses.getId());
        }
    }

    @Test
    void getExpensesById() {
        Expenses expenses = Expenses.getExpenses();
        expenses.setId(currentMonthAndYear());
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(expenses);

            Expenses fromExpenses = session.get(Expenses.class, currentMonthAndYear());

            assertEquals(fromExpenses.getId(), expenses.getId());

            session.getTransaction().commit();
        }
    }

    @Test
    void getExpensesTable() {
        Expenses expenses = Expenses.getExpenses();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(expenses);

            List<Expenses> fromExpenses = session.createQuery("from Expenses", Expenses.class).list();
            assertFalse(fromExpenses.isEmpty());

            session.getTransaction().commit();
        }
    }
}