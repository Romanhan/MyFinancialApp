package romanhan.view;

import romanhan.controller.Expenses;
import romanhan.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static romanhan.controller.Withdrawal.*;

public class View {
    private Expenses expenses;
    private User user;
    private JFrame jFrame;

    private JButton jBCarLeasing;
    private JButton jBCarInsurance;
    private JButton jBGas;
    private JButton jBBills;
    private JButton jBFood;
    private JButton jBInternet;
    private JButton jBKindergarten;
    private JButton jBPhones;

    private JLabel jIncomeLabel;
    private JLabel jLApartment;
    private JLabel jLCarLeasing;
    private JLabel jLCarInsurance;
    private JLabel jLGas;
    private JLabel jLBills;
    private JLabel jLFood;
    private JLabel jLInternet;
    private JLabel jLKindergarten;
    private JLabel jLPhones;
    private JLabel jLTotalExpenses;

    Font titleFont = new Font("Times New Roman", Font.BOLD, 20);
    Font secondaryFont = new Font("Times New Roman", Font.PLAIN, 20);


    public View(Expenses expenses, User user) {
        this.expenses = expenses;
        this.user = user;
    }

    public void addComponentsToPane() {
        jFrame = new JFrame("My financial App");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 520);
        jFrame.setLayout(null);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try (FileOutputStream fileOut = new FileOutputStream("C:\\tmp\\userData.ser");
                     ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(user);
                    out.writeObject(expenses);
                    out.flush();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });

        JLabel dateLabel = new JLabel(getDate());
        dateLabel.setBounds(10, 10, 200, 25);
        dateLabel.setFont(titleFont);


        JLabel jLabelStart = new JLabel("Расходы:");
        jLabelStart.setBounds(10, 55, 200, 25);
        Font jLSFont = new Font("Times New Roman", Font.BOLD, 15);
        jLabelStart.setFont(jLSFont);


        jIncomeLabel = new JLabel();
        jIncomeLabel.setText("Бюджет на месяц " + user.getBudget() + "€");
        jIncomeLabel.setBounds(400, 10, 300, 25);
        jIncomeLabel.setFont(titleFont);

        JButton jbnButton = new JButton("Пополнить бюджет");
        jbnButton.setBounds(420, 40, 150, 30);
        jbnButton.setFocusable(false);
        jbnButton.addActionListener(new DepositBalanceListener());


        jLApartment = new JLabel("Лизинг за квартиру " + expenses.getApartmentLeasing() + "€");
        jLApartment.setBounds(10, 85, 300, 30);
        jLApartment.setFont(secondaryFont);
        JButton jBApartment = new JButton("Оплатить");
        jBApartment.setBounds(270, 85, 100, 30);
        jBApartment.setFocusable(false);
        jBApartment.addActionListener(new ApartmentListener());

        jLCarLeasing = new JLabel("Лизинг за машину " + expenses.getCarLeasing() + "€");
        jLCarLeasing.setBounds(10, 120, 300, 30);
        jLCarLeasing.setFont(secondaryFont);
        jBCarLeasing = new JButton("Оплатить");
        jBCarLeasing.setBounds(270, 120, 100, 30);
        jBCarLeasing.setFocusable(false);
        jBCarLeasing.addActionListener(new CarLeasingListener());

        jLCarInsurance = new JLabel("Страховка на машины " + expenses.getCarInsurance() + "€");
        jLCarInsurance.setBounds(10, 155, 300, 30);
        jLCarInsurance.setFont(secondaryFont);
        jBCarInsurance = new JButton("Оплатить");
        jBCarInsurance.setBounds(270, 155, 100, 30);
        jBCarInsurance.setFocusable(false);
        jBCarInsurance.addActionListener(new CarInsuranceListener());

        jLGas = new JLabel("Бензин " + expenses.getGas() + "€");
        jLGas.setBounds(10, 190, 300, 30);
        jLGas.setFont(secondaryFont);
        jBGas = new JButton("Оплатить");
        jBGas.setBounds(270, 190, 100, 30);
        jBGas.setFocusable(false);
        jBGas.addActionListener(new GasListener());

        jLBills = new JLabel("Счета " + expenses.getBills() + "€");
        jLBills.setBounds(10, 225, 300, 30);
        jLBills.setFont(secondaryFont);
        jBBills = new JButton("Оплатить");
        jBBills.setBounds(270, 225, 100, 30);
        jBBills.setFocusable(false);
        jBBills.addActionListener(new BillsListener());

        jLFood = new JLabel("Еда " + expenses.getFood() + "€");
        jLFood.setBounds(10, 260, 300, 30);
        jLFood.setFont(secondaryFont);
        jBFood = new JButton("Оплатить");
        jBFood.setBounds(270, 260, 100, 30);
        jBFood.setFocusable(false);
        jBFood.addActionListener(new FoodListener());

        jLInternet = new JLabel("Интернет " + expenses.getInternet() + "€");
        jLInternet.setBounds(10, 295, 300, 30);
        jLInternet.setFont(secondaryFont);
        jBInternet = new JButton("Оплатить");
        jBInternet.setBounds(270, 295, 100, 30);
        jBInternet.setFocusable(false);
        jBInternet.addActionListener(new InternetListener());

        jLKindergarten = new JLabel("Садик " + expenses.getKindergarten() + "€");
        jLKindergarten.setBounds(10, 330, 300, 30);
        jLKindergarten.setFont(secondaryFont);
        jBKindergarten = new JButton("Оплатить");
        jBKindergarten.setBounds(270, 330, 100, 30);
        jBKindergarten.setFocusable(false);
        jBKindergarten.addActionListener(new KindergartenListener());

        jLPhones = new JLabel("Телефоны " + expenses.getPhones() + "€");
        jLPhones.setBounds(10, 365, 300, 30);
        jLPhones.setFont(secondaryFont);
        jBPhones = new JButton("Оплатить");
        jBPhones.setBounds(270, 365, 100, 30);
        jBPhones.setFocusable(false);
        jBPhones.addActionListener(new PhonesListener());

        jLTotalExpenses = new JLabel("Расход всего за месяц " + expenses.getTotalExpensesForMonth() + "€");
        jLTotalExpenses.setBounds(10, 420, 300, 30);
        jLTotalExpenses.setFont(jLSFont);


        jFrame.add(dateLabel);
        jFrame.add(jLabelStart);
        jFrame.add(jIncomeLabel);

        jFrame.add(jLApartment);
        jFrame.add(jBApartment);

        jFrame.add(jLCarLeasing);
        jFrame.add(jBCarLeasing);

        jFrame.add(jLCarInsurance);
        jFrame.add(jBCarInsurance);

        jFrame.add(jLGas);
        jFrame.add(jBGas);

        jFrame.add(jLBills);
        jFrame.add(jBBills);

        jFrame.add(jLFood);
        jFrame.add(jBFood);

        jFrame.add(jLInternet);
        jFrame.add(jBInternet);

        jFrame.add(jLKindergarten);
        jFrame.add(jBKindergarten);

        jFrame.add(jLPhones);
        jFrame.add(jBPhones);

        jFrame.add(jLTotalExpenses);
        jFrame.add(jbnButton);

        jFrame.setVisible(true);
    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return simpleDateFormat.format(date);
    }

    private class DepositBalanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму добавить?"));
            user.deposit(enteredAmount);
            refreshBudget();
        }
    }

    private void refreshBudget() {
        jIncomeLabel.setText("Бюджет на месяц " + user.getBudget() + "€");
    }

    private class ApartmentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, APARTMENT_LEASING);
            jLApartment.setText("Лизинг за квартиру " + expenses.getApartmentLeasing() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private void refreshTotalExpenses() {
        jLTotalExpenses.setText("Расход всего за месяц " + expenses.getTotalExpensesForMonth() + "€");
    }

    private class CarLeasingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, CAR_LEASING);
            jLCarLeasing.setText("Лизинг за машину " + expenses.getCarLeasing() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class CarInsuranceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, CAR_INSURANCE);
            jLCarInsurance.setText("Страховка на машины " + expenses.getCarInsurance() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class GasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, GAS);
            jLGas.setText("Бензин " + expenses.getGas() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class BillsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, BILLS);
            jLBills.setText("Счета " + expenses.getBills() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class FoodListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, FOOD);
            jLFood.setText("Еда " + expenses.getFood() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class InternetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, INTERNET);
            jLInternet.setText("Интернет " + expenses.getInternet() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class KindergartenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, KINDERGARTEN);
            jLKindergarten.setText("Садик " + expenses.getKindergarten() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class PhonesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = Integer.parseInt(JOptionPane.showInputDialog("Какую сумму вычесть?"));
            expenses.withdrawal(enteredAmount, PHONES);
            jLPhones.setText("Телефоны " + expenses.getPhones() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }
}
