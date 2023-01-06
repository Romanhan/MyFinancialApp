package romanhan.controller;

import romanhan.exception.NotEnoughBalance;
import romanhan.model.User;

import javax.swing.*;
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

    public void withdrawal(int amount, Withdrawal withdrawalType, JFrame jFrame) {
        switch (withdrawalType) {
            case APARTMENT_LEASING:
                withdrawalBudget(amount, jFrame);
                apartmentLeasing += amount;
                totalExpensesForMonth += amount;
                break;
            case APARTMENT_BILL:
                withdrawalBudget(amount, jFrame);
                apartmentBill += amount;
                totalExpensesForMonth += amount;
                break;
            case CAR_LEASING:
                withdrawalBudget(amount, jFrame);
                carLeasing += amount;
                totalExpensesForMonth += amount;
                break;
            case CAR_CASCO:
                withdrawalBudget(amount, jFrame);
                carCasco += amount;
                totalExpensesForMonth += amount;
                break;
            case CAR_INSURANCE:
                withdrawalBudget(amount, jFrame);
                carInsurance += amount;
                totalExpensesForMonth += amount;
                break;
            case GAS:
                withdrawalBudget(amount, jFrame);
                gas += amount;
                totalExpensesForMonth += amount;
                break;
            case ELECTRICITY:
                withdrawalBudget(amount, jFrame);
                electricity += amount;
                totalExpensesForMonth += amount;
                break;
            case FOOD:
                withdrawalBudget(amount, jFrame);
                food += amount;
                totalExpensesForMonth += amount;
                break;
            case INTERNET:
                withdrawalBudget(amount, jFrame);
                internet += amount;
                totalExpensesForMonth += amount;
                break;
            case KINDERGARTEN:
                withdrawalBudget(amount, jFrame);
                kindergarten += amount;
                totalExpensesForMonth += amount;
                break;
            case PHONES:
                withdrawalBudget(amount, jFrame);
                phones += amount;
                totalExpensesForMonth += amount;
                break;
            case DEPOSIT:
                withdrawalBudget(amount, jFrame);
                deposit += amount;
                totalExpensesForMonth += amount;
                break;
        }
    }

    public void withdrawalBudget(int amount, JFrame jFrame) {
        try {
            if (user.getBudget() - amount < 0) {
                throw new NotEnoughBalance(jFrame);
            } else {
                user.setBudget(user.getBudget() - amount);
            }
        } catch (NotEnoughBalance ex) {
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
}
