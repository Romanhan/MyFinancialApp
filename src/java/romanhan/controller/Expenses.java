package romanhan.controller;

import romanhan.exception.NotEnoughBalanceException;
import romanhan.exception.NumberOnlyException;
import romanhan.model.User;
import romanhan.view.View;

import java.io.Serializable;

public class Expenses implements Serializable {
    private static final long serialVersionUID = 1L;
    private int apartmentLeasing;
    private int apartmentBill;
    private int carLeasing;
    private int carCasco;
    private int carInsurance;
    private int gas;
    private int electricity;
    private int food;
    private int internet;
    private int kindergarten;
    private int phones;
    private int deposit;
    private int totalExpensesForMonth;
    private final User user;

    //User getter and setter
    public void setBudget(int amount) {
        user.setBudget(amount);
    }

    public void deposit(int amount) {
        user.setBudget(user.getBudget() + amount);
    }

    public int getBudget() {
        return user.getBudget();
    }

    public void clearBudget() {
        user.clearBudget();
    }

    //Expenses constructor, getter and setter
    public Expenses(User user) {
        this.user = user;
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

    public int getTotalExpensesForMonth() {
        return totalExpensesForMonth;
    }

    public void withdrawal(int amount, Withdrawal withdrawalType) {
        switch (withdrawalType) {
            case APARTMENT_LEASING:
                withdrawalBudget(amount);
                apartmentLeasing += amount;
                totalExpensesForMonth += amount;
                break;
            case APARTMENT_BILL:
                withdrawalBudget(amount);
                apartmentBill += amount;
                totalExpensesForMonth += amount;
                break;
            case CAR_LEASING:
                withdrawalBudget(amount);
                carLeasing += amount;
                totalExpensesForMonth += amount;
                break;
            case CAR_CASCO:
                withdrawalBudget(amount);
                carCasco += amount;
                totalExpensesForMonth += amount;
                break;
            case CAR_INSURANCE:
                withdrawalBudget(amount);
                carInsurance += amount;
                totalExpensesForMonth += amount;
                break;
            case GAS:
                withdrawalBudget(amount);
                gas += amount;
                totalExpensesForMonth += amount;
                break;
            case ELECTRICITY:
                withdrawalBudget(amount);
                electricity += amount;
                totalExpensesForMonth += amount;
                break;
            case FOOD:
                withdrawalBudget(amount);
                food += amount;
                totalExpensesForMonth += amount;
                break;
            case INTERNET:
                withdrawalBudget(amount);
                internet += amount;
                totalExpensesForMonth += amount;
                break;
            case KINDERGARTEN:
                withdrawalBudget(amount);
                kindergarten += amount;
                totalExpensesForMonth += amount;
                break;
            case PHONES:
                withdrawalBudget(amount);
                phones += amount;
                totalExpensesForMonth += amount;
                break;
            case DEPOSIT:
                withdrawalBudget(amount);
                deposit += amount;
                totalExpensesForMonth += amount;
                break;
        }
    }

    public void withdrawalBudget(int amount) {
        try {
            if (user.getBudget() - amount < 0) {
                throw new NotEnoughBalanceException(View.jFrame);
            } else {
                user.setBudget(user.getBudget() - amount);
            }
        } catch (NotEnoughBalanceException ex) {
            throw new RuntimeException(ex);
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
        setTotalExpensesForMonth(0);
    }

    // Check if entered value is a number
    public static int checkEnteredValue(String value) throws NumberOnlyException {
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
