package romanhan.view;

import romanhan.database.H2Database;
import romanhan.controller.Expenses;
import romanhan.exception.NumberOnlyException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import static romanhan.controller.Expenses.checkEnteredValue;
import static romanhan.controller.Withdrawal.*;
import static romanhan.database.H2Database.*;

public class View {
    private final Expenses expenses;
    public static JFrame jFrame;

    private JLabel jIncomeLabel;
    private JLabel jLApartment;
    private JLabel jLApartmentBill;
    private JLabel jLCarLeasing;
    private JLabel jLCarInsurance;
    private JLabel jLCarCasco;
    private JLabel jLGas;
    private JLabel jLElectricity;
    private JLabel jLFood;
    private JLabel jLInternet;
    private JLabel jLKindergarten;
    private JLabel jLPhones;
    private JLabel jLDeposit;
    private JLabel jLOtherExpenses;
    private JLabel jLTotalExpenses;

    private final JMenuBar jMenuBar = new JMenuBar();

    Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
    Font secondaryFont = new Font("Segoe UI", Font.PLAIN, 20);

    public View(Expenses expenses) {
        this.expenses = expenses;
    }

    public void addComponentsToPane() {
        jFrame = new JFrame("My financial App");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(750, 685);
        jFrame.setLayout(null);
        jFrame.setResizable(false); //Disable ability to resize frame
        jFrame.setLocationRelativeTo(null); //Set JFrame to appear on centered of the screen
        jFrame.addWindowListener(new WindowAdapter() { //When user clicks Exit button, program checks if file paths exists and saves data to file
            @Override
            public void windowClosing(WindowEvent e) { //Check if file path exists

                //Work with file

                /*Path filePath = Paths.get("C:\\MyFinancialApp\\");
                if (!Files.exists(filePath)) {
                    try {
                        Files.createDirectory(filePath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                try (FileOutputStream fileOut = new FileOutputStream("C:\\MyFinancialApp\\" + currentMonthAndYear() + ".ser"); // Save data to file
                     ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(user);
                    out.writeObject(expenses);
                    out.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }*/

                //Work with databases

                //MySQLDatabase.saveToDatabase(expenses);
                H2Database.saveToDatabase(expenses);
            }
        });
        //Adding menu bar
        JMenu jMenu = new JMenu("Menu");
        JMenuItem jMIHistory = new JMenuItem("Смотреть историю затрат");

        jMIHistory.addActionListener(new JMIHistoryListener());

        JMenuItem jMIClear = new JMenuItem("Стереть все данные");
        jMIClear.addActionListener(new JMIClearListener());

        jMenu.add(jMIHistory);
        jMenu.add(jMIClear);
        jMenuBar.add(jMenu);

        //Creating labels and buttons
        JLabel dateLabel = new JLabel(currentMonthAndYear());
        dateLabel.setBounds(10, 10, 200, 25);
        dateLabel.setFont(titleFont);

        JLabel jLabelStart = new JLabel("Расходы:");
        jLabelStart.setBounds(10, 55, 200, 25);
        Font jLSFont = new Font("Times New Roman", Font.BOLD, 15);
        jLabelStart.setFont(jLSFont);

        jIncomeLabel = new JLabel();
        jIncomeLabel.setText("Бюджет на месяц " + expenses.getBudget() + "€");
        jIncomeLabel.setBounds(450, 10, 300, 25);
        jIncomeLabel.setFont(titleFont);

        JButton jbnButton = new JButton("Пополнить бюджет");
        jbnButton.setBounds(470, 40, 150, 30);
        jbnButton.setFocusable(false);
        jbnButton.addActionListener(new DepositBalanceListener());

        jLApartment = new JLabel("Лизинг за квартиру " + expenses.getApartmentLeasing() + "€");
        jLApartment.setBounds(10, 85, 300, 30);
        jLApartment.setFont(secondaryFont);
        JButton jBApartment = new JButton("Оплатить");
        jBApartment.setBounds(270, 85, 100, 30);
        jBApartment.setFocusable(false);
        jBApartment.addActionListener(new ApartmentListener());

        jLApartmentBill = new JLabel("Счёт за квартиру " + expenses.getApartmentBill() + "€");
        jLApartmentBill.setBounds(10, 120, 300, 30);
        jLApartmentBill.setFont(secondaryFont);
        JButton jBApartmentBill = new JButton("Оплатить");
        jBApartmentBill.setBounds(270, 120, 100, 30);
        jBApartmentBill.setFocusable(false);
        jBApartmentBill.addActionListener(new ApartmentBillListener());

        jLCarLeasing = new JLabel("Лизинг за машину " + expenses.getCarLeasing() + "€");
        jLCarLeasing.setBounds(10, 155, 300, 30);
        jLCarLeasing.setFont(secondaryFont);
        JButton jBCarLeasing = new JButton("Оплатить");
        jBCarLeasing.setBounds(270, 155, 100, 30);
        jBCarLeasing.setFocusable(false);
        jBCarLeasing.addActionListener(new CarLeasingListener());

        jLCarCasco = new JLabel("Каско машины " + expenses.getCarCasco() + "€");
        jLCarCasco.setBounds(10, 190, 300, 30);
        jLCarCasco.setFont(secondaryFont);
        JButton jBCarCasco = new JButton("Оплатить");
        jBCarCasco.setBounds(270, 190, 100, 30);
        jBCarCasco.setFocusable(false);
        jBCarCasco.addActionListener(new CarCascoListener());

        jLCarInsurance = new JLabel("Страховка машин " + expenses.getCarInsurance() + "€");
        jLCarInsurance.setBounds(10, 225, 300, 30);
        jLCarInsurance.setFont(secondaryFont);
        JButton jBCarInsurance = new JButton("Оплатить");
        jBCarInsurance.setBounds(270, 225, 100, 30);
        jBCarInsurance.setFocusable(false);
        jBCarInsurance.addActionListener(new CarInsuranceListener());

        jLGas = new JLabel("Бензин " + expenses.getGas() + "€");
        jLGas.setBounds(10, 260, 300, 30);
        jLGas.setFont(secondaryFont);
        JButton jBGas = new JButton("Оплатить");
        jBGas.setBounds(270, 260, 100, 30);
        jBGas.setFocusable(false);
        jBGas.addActionListener(new GasListener());

        jLElectricity = new JLabel("Электричество " + expenses.getElectricity() + "€");
        jLElectricity.setBounds(10, 295, 300, 30);
        jLElectricity.setFont(secondaryFont);
        JButton jBElectricity = new JButton("Оплатить");
        jBElectricity.setBounds(270, 295, 100, 30);
        jBElectricity.setFocusable(false);
        jBElectricity.addActionListener(new ElectricityListener());

        jLInternet = new JLabel("Интернет " + expenses.getInternet() + "€");
        jLInternet.setBounds(10, 330, 300, 30);
        jLInternet.setFont(secondaryFont);
        JButton jBInternet = new JButton("Оплатить");
        jBInternet.setBounds(270, 330, 100, 30);
        jBInternet.setFocusable(false);
        jBInternet.addActionListener(new InternetListener());

        jLKindergarten = new JLabel("Садик " + expenses.getKindergarten() + "€");
        jLKindergarten.setBounds(10, 365, 300, 30);
        jLKindergarten.setFont(secondaryFont);
        JButton jBKindergarten = new JButton("Оплатить");
        jBKindergarten.setBounds(270, 365, 100, 30);
        jBKindergarten.setFocusable(false);
        jBKindergarten.addActionListener(new KindergartenListener());

        jLPhones = new JLabel("Телефоны " + expenses.getPhones() + "€");
        jLPhones.setBounds(10, 400, 300, 30);
        jLPhones.setFont(secondaryFont);
        JButton jBPhones = new JButton("Оплатить");
        jBPhones.setBounds(270, 400, 100, 30);
        jBPhones.setFocusable(false);
        jBPhones.addActionListener(new PhonesListener());

        jLDeposit = new JLabel("Никитин депозит " + expenses.getDeposit() + "€");
        jLDeposit.setBounds(10, 435, 300, 30);
        jLDeposit.setFont(secondaryFont);
        JButton jBDeposit = new JButton("Оплатить");
        jBDeposit.setBounds(270, 435, 100, 30);
        jBDeposit.setFocusable(false);
        jBDeposit.addActionListener(new DepositListener());

        jLFood = new JLabel("Еда " + expenses.getFood() + "€");
        jLFood.setBounds(10, 470, 300, 30);
        jLFood.setFont(secondaryFont);
        JButton jBFood = new JButton("Оплатить");
        jBFood.setBounds(270, 470, 100, 30);
        jBFood.setFocusable(false);
        jBFood.addActionListener(new FoodListener());

        jLOtherExpenses = new JLabel("Другие расходы " + expenses.getOtherExpenses() + "€");
        jLOtherExpenses.setBounds(10, 505, 300, 30);
        jLOtherExpenses.setFont(secondaryFont);
        JButton jBOtherExpenses = new JButton("Оплатить");
        jBOtherExpenses.setBounds(270, 505, 100, 30);
        jBOtherExpenses.setFocusable(false);
        jBOtherExpenses.addActionListener(new OtherExpensesListener());

        jLTotalExpenses = new JLabel("Расход всего за месяц " + expenses.getTotalExpensesForMonth() + "€");
        jLTotalExpenses.setBounds(10, 575, 300, 30);
        jLTotalExpenses.setFont(jLSFont);

        //Adding labels and buttons to the frame
        jFrame.setJMenuBar(jMenuBar); //Add JMenuBar to frame
        jFrame.add(dateLabel);
        jFrame.add(jLabelStart);
        jFrame.add(jIncomeLabel);

        jFrame.add(jLApartment);
        jFrame.add(jBApartment);

        jFrame.add(jLApartmentBill);
        jFrame.add(jBApartmentBill);

        jFrame.add(jLCarLeasing);
        jFrame.add(jBCarLeasing);

        jFrame.add(jLCarCasco);
        jFrame.add(jBCarCasco);

        jFrame.add(jLCarInsurance);
        jFrame.add(jBCarInsurance);

        jFrame.add(jLGas);
        jFrame.add(jBGas);

        jFrame.add(jLElectricity);
        jFrame.add(jBElectricity);

        jFrame.add(jLInternet);
        jFrame.add(jBInternet);

        jFrame.add(jLKindergarten);
        jFrame.add(jBKindergarten);

        jFrame.add(jLPhones);
        jFrame.add(jBPhones);

        jFrame.add(jLDeposit);
        jFrame.add(jBDeposit);

        jFrame.add(jLFood);
        jFrame.add(jBFood);

        jFrame.add(jLOtherExpenses);
        jFrame.add(jBOtherExpenses);

        jFrame.add(jLTotalExpenses);
        jFrame.add(jbnButton);

        jFrame.setVisible(true);
    }

    //Add balance
    private class DepositBalanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму добавить?"));
            expenses.deposit(enteredAmount);
            refreshBudget();
        }
    }

    //Refresh data after updating amount
    private void refreshBudget() {
        jIncomeLabel.setText("Бюджет на месяц " + expenses.getBudget() + "€");
    }

    private void refreshTotalExpenses() {
        jLTotalExpenses.setText("Расход всего за месяц " + expenses.getTotalExpensesForMonth() + "€");
    }

    private void refreshAllExpenses() {
        jLApartment.setText("Лизинг за квартиру " + expenses.getApartmentLeasing() + "€");
        jLApartmentBill.setText("Счёт за квартиру " + expenses.getApartmentBill() + "€");
        jLCarLeasing.setText("Лизинг за машину " + expenses.getCarLeasing() + "€");
        jLCarCasco.setText("Каско машины " + expenses.getCarLeasing() + "€");
        jLCarInsurance.setText("Страховка машины " + expenses.getCarInsurance() + "€");
        jLGas.setText("Бензин " + expenses.getGas() + "€");
        jLElectricity.setText("Электричество " + expenses.getElectricity() + "€");
        jLInternet.setText("Интернет " + expenses.getInternet() + "€");
        jLKindergarten.setText("Садик " + expenses.getKindergarten() + "€");
        jLPhones.setText("Телефоны " + expenses.getPhones() + "€");
        jLDeposit.setText("Никитин депозит " + expenses.getDeposit() + "€");
        jLFood.setText("Еда " + expenses.getFood() + "€");
        jLOtherExpenses.setText("Другие расходы " + expenses.getOtherExpenses() + "€");
    }

    //Listeners for buttons
    private class ApartmentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, APARTMENT_LEASING);
            jLApartment.setText("Лизинг за квартиру " + expenses.getApartmentLeasing() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class ApartmentBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, APARTMENT_BILL);
            jLApartmentBill.setText("Счёт за квартиру " + expenses.getApartmentBill() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class CarLeasingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, CAR_LEASING);
            jLCarLeasing.setText("Лизинг за машину " + expenses.getCarLeasing() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class CarCascoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, CAR_CASCO);
            jLCarCasco.setText("Каско машины " + expenses.getCarCasco() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class CarInsuranceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, CAR_INSURANCE);
            jLCarInsurance.setText("Страховка машин " + expenses.getCarInsurance() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class GasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, GAS);
            jLGas.setText("Бензин " + expenses.getGas() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class ElectricityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, ELECTRICITY);
            jLElectricity.setText("Электричество " + expenses.getElectricity() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class InternetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, INTERNET);
            jLInternet.setText("Интернет " + expenses.getInternet() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class KindergartenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, KINDERGARTEN);
            jLKindergarten.setText("Садик " + expenses.getKindergarten() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class PhonesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, PHONES);
            jLPhones.setText("Телефоны " + expenses.getPhones() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount;
            enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, DEPOSIT);
            jLDeposit.setText("Никитин депозит " + expenses.getDeposit() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class FoodListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, FOOD);
            jLFood.setText("Еда " + expenses.getFood() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    private class OtherExpensesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int enteredAmount = checkEnteredValue(JOptionPane.showInputDialog(jFrame, "Какую сумму вычесть?"));
            if (enteredAmount < 0) {
                throw new NumberOnlyException(jFrame);
            }
            expenses.withdrawal(enteredAmount, OTHER_EXPENSES);
            jLOtherExpenses.setText("Другие расходы " + expenses.getOtherExpenses() + "€");
            refreshTotalExpenses();
            refreshBudget();
        }
    }

    //JMenu Listeners
    private class JMIClearListener implements ActionListener { //JMenu button clears all user data
        @Override
        public void actionPerformed(ActionEvent e) {
            expenses.clearAllData();
            refreshBudget();
            refreshTotalExpenses();
            refreshAllExpenses();
        }
    }

    //For future updates
    private class JMIHistoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] columnNames = {"Дата", "Лизинг квартиры", "Счета за квартиру",
                    "Лизинг машины", "Каско машины", "Страховка машины", "Бензин", "Электричество", "Интернет", "Садик",
                    "Телефоны", "Никитин депозит", "Еда", "Другие расходы", "Всего потрачено"};

            JFrame jFrame = new JFrame("Database Search Result");
            jFrame.setLayout(new BorderLayout());
            jFrame.setBounds(300, 220, 1400, 400);

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(columnNames);

            JTable jTable = new JTable();
            jTable.setModel(tableModel);

            // Set column width
            jTable.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable.getColumnModel().getColumn(1).setPreferredWidth(140);
            jTable.getColumnModel().getColumn(2).setPreferredWidth(145);
            jTable.getColumnModel().getColumn(3).setPreferredWidth(125);
            jTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            jTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            jTable.getColumnModel().getColumn(6).setPreferredWidth(70);
            jTable.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTable.getColumnModel().getColumn(8).setPreferredWidth(80);
            jTable.getColumnModel().getColumn(9).setPreferredWidth(60);
            jTable.getColumnModel().getColumn(10).setPreferredWidth(90);
            jTable.getColumnModel().getColumn(11).setPreferredWidth(140);
            jTable.getColumnModel().getColumn(12).setPreferredWidth(60);
            jTable.getColumnModel().getColumn(13).setPreferredWidth(130);
            jTable.getColumnModel().getColumn(14).setPreferredWidth(150);

            JScrollPane jScrollPane = new JScrollPane(jTable);
            jScrollPane.setBounds(jTable.getBounds());

            jFrame.add(jScrollPane);
            jFrame.setVisible(true);

            readTableDataFromDatabase(tableModel);
        }
    }
}
