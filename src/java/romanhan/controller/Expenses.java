package romanhan.controller;

import romanhan.exception.NotEnoughBalance;
import romanhan.model.User;

public class Expenses {
    private int apartmentLeasing;
    private int carLeasing;
    private int carInsurance;
    private int gas;
    private int bills;
    private int food;
    private int internet;
    private int kindergarten;
    private int phones;
    private int totalExpensesForMonth;
    private User user;

    public Expenses(User user) {
        this.user = user;
    }

    public void setApartmentLeasing(int apartmentLeasing) {
        this.apartmentLeasing = apartmentLeasing;
    }

    public void setCarLeasing(int carLeasing) {
        this.carLeasing = carLeasing;
    }

    public void setCarInsurance(int carInsurance) {
        this.carInsurance = carInsurance;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public void setBills(int bills) {
        this.bills = bills;
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

    public void setTotalExpensesForMonth(int totalExpensesForMonth) {
        this.totalExpensesForMonth = totalExpensesForMonth;
    }

    public int getApartmentLeasing() {
        return apartmentLeasing;
    }

    public int getCarLeasing() {
        return carLeasing;
    }

    public int getCarInsurance() {
        return carInsurance;
    }

    public int getGas() {
        return gas;
    }

    public int getBills() {
        return bills;
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
            case CAR_LEASING:
                withdrawalBudget(amount);
                carLeasing += amount;
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
            case BILLS:
                withdrawalBudget(amount);
                bills += amount;
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
        }
    }

    public void withdrawalBudget(int amount) {
        try {
            if (user.getBudget() - amount < 0) {
                throw new NotEnoughBalance();
            } else {
                user.setBudget(user.getBudget() - amount);
            }
        } catch (NotEnoughBalance e) {
            throw new RuntimeException(e);
        }
    }
}
