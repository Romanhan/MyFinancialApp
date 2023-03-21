package romanhan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import romanhan.exception.NotEnoughBalanceException;
import romanhan.exception.NumberOnlyException;
import romanhan.view.View;

import static romanhan.dao.ExpensesDao.currentMonthAndYear;

@Entity
@Table(name = "my_financial_app")
public class Expenses {
    @Id
    @Column(name = "current_month")
    private String id = currentMonthAndYear();
    @Column(name = "user_budget")
    private int budget;
    @Column(name = "apartment_leasing")
    private int apartmentLeasing;
    @Column(name = "apartment_bill")
    private int apartmentBill;
    @Column(name = "car_leasing")
    private int carLeasing;
    @Column(name = "car_casco")
    private int carCasco;
    @Column(name = "car_insurance")
    private int carInsurance;
    private int gas;
    private int electricity;
    private int internet;
    private int kindergarten;
    private int phones;
    private int deposit;
    private int food;
    @Column(name = "other_expenses")
    private int otherExpenses;
    @Column(name = "total_expenses")
    private int totalExpensesForMonth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void deposit(int amount) {
        this.budget += amount;
    }

    public void clearBudget() {
        setBudget(0);
    }

    public Expenses() {
    }

    public void setApartmentLeasing(int apartmentLeasing) {
        this.apartmentLeasing = apartmentLeasing;
    }

    public void setApartmentBill(int apartmentBill) {
        this.apartmentBill = apartmentBill;
    }

    public void setCarLeasing(int carLeasing) {
        this.carLeasing = carLeasing;
    }

    public void setCarCasco(int carCasco) {
        this.carCasco = carCasco;
    }

    public void setCarInsurance(int carInsurance) {
        this.carInsurance = carInsurance;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public void setElectricity(int bills) {
        this.electricity = bills;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public void setKindergarten(int kindergarten) {
        this.kindergarten = kindergarten;
    }

    public void setPhones(int phones) {
        this.phones = phones;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void setOtherExpenses(int otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public void setTotalExpensesForMonth(int totalExpensesForMonth) {
        this.totalExpensesForMonth = totalExpensesForMonth;
    }

    public int getApartmentLeasing() {
        return apartmentLeasing;
    }

    public int getApartmentBill() {
        return apartmentBill;
    }

    public int getCarLeasing() {
        return carLeasing;
    }

    public int getCarCasco() {
        return carCasco;
    }

    public int getCarInsurance() {
        return carInsurance;
    }

    public int getGas() {
        return gas;
    }

    public int getElectricity() {
        return electricity;
    }

    public int getFood() {
        return food;
    }

    public int getInternet() {
        return internet;
    }

    public int getKindergarten() {
        return kindergarten;
    }

    public int getPhones() {
        return phones;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getOtherExpenses() {
        return otherExpenses;
    }

    public int getTotalExpensesForMonth() {
        return totalExpensesForMonth;
    }

    public void withdrawal(int amount, Withdrawal withdrawalType) {
        switch (withdrawalType) {
            case APARTMENT_LEASING -> {
                withdrawalBudget(amount);
                apartmentLeasing += amount;
                totalExpensesForMonth += amount;
            }
            case APARTMENT_BILL -> {
                withdrawalBudget(amount);
                apartmentBill += amount;
                totalExpensesForMonth += amount;
            }
            case CAR_LEASING -> {
                withdrawalBudget(amount);
                carLeasing += amount;
                totalExpensesForMonth += amount;
            }
            case CAR_CASCO -> {
                withdrawalBudget(amount);
                carCasco += amount;
                totalExpensesForMonth += amount;
            }
            case CAR_INSURANCE -> {
                withdrawalBudget(amount);
                carInsurance += amount;
                totalExpensesForMonth += amount;
            }
            case GAS -> {
                withdrawalBudget(amount);
                gas += amount;
                totalExpensesForMonth += amount;
            }
            case ELECTRICITY -> {
                withdrawalBudget(amount);
                electricity += amount;
                totalExpensesForMonth += amount;
            }
            case FOOD -> {
                withdrawalBudget(amount);
                food += amount;
                totalExpensesForMonth += amount;
            }
            case INTERNET -> {
                withdrawalBudget(amount);
                internet += amount;
                totalExpensesForMonth += amount;
            }
            case KINDERGARTEN -> {
                withdrawalBudget(amount);
                kindergarten += amount;
                totalExpensesForMonth += amount;
            }
            case PHONES -> {
                withdrawalBudget(amount);
                phones += amount;
                totalExpensesForMonth += amount;
            }
            case DEPOSIT -> {
                withdrawalBudget(amount);
                deposit += amount;
                totalExpensesForMonth += amount;
            }
            case OTHER_EXPENSES -> {
                withdrawalBudget(amount);
                otherExpenses += amount;
                totalExpensesForMonth += amount;
            }
        }
    }

    public void withdrawalBudget(int amount) {
        try {
            if (getBudget() - amount < 0) {
                throw new NotEnoughBalanceException(View.jFrame);
            } else {
                setBudget(getBudget() - amount);
            }
        } catch (NotEnoughBalanceException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearAllData() {
        setApartmentLeasing(0);
        setApartmentBill(0);
        setCarLeasing(0);
        setCarCasco(0);
        setCarInsurance(0);
        setGas(0);
        setElectricity(0);
        setInternet(0);
        setKindergarten(0);
        setPhones(0);
        setDeposit(0);
        setFood(0);
        setOtherExpenses(0);
        setTotalExpensesForMonth(0);
        clearBudget();
    }

    // Check if entered value is a number
    public static int checkEnteredValue(String value) {
        int resultValue;
        try {
            if (value.equals("")) {
                return 0;
            } else {
                resultValue = Integer.parseInt(value);
            }
        } catch (NumberFormatException ex) {
            throw new NumberOnlyException(View.jFrame);
        }
        return resultValue;
    }
}
