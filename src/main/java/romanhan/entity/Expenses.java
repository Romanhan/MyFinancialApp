package romanhan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import romanhan.exception.NotEnoughBalanceException;
import romanhan.exception.NumberOnlyException;
import romanhan.view.View;

import java.math.BigDecimal;

import static romanhan.dao.ExpensesDao.currentMonthAndYear;

@Entity
@Table(name = "my_financial_app")
public class Expenses {
    @Id
    @Column(name = "current_month")
    private String id = currentMonthAndYear();
    @Column(precision = 10, scale = 3, name = "user_budget")
    private BigDecimal budget;
    @Column(precision = 10, scale = 3, name = "apartment_leasing")
    private BigDecimal apartmentLeasing;
    @Column(precision = 10, scale = 3, name = "apartment_bill")
    private BigDecimal apartmentBill;
    @Column(precision = 10, scale = 3, name = "car_leasing")
    private BigDecimal carLeasing;
    @Column(precision = 10, scale = 3, name = "car_casco")
    private BigDecimal carCasco;
    @Column(precision = 10, scale = 3, name = "car_insurance")
    private BigDecimal carInsurance;
    @Column(precision = 10, scale = 3)
    private BigDecimal gas;
    @Column(precision = 10, scale = 3)
    private BigDecimal electricity;
    @Column(precision = 10, scale = 3)
    private BigDecimal internet;
    @Column(precision = 10, scale = 3)
    private BigDecimal kindergarten;
    @Column(precision = 10, scale = 3)
    private BigDecimal phones;
    @Column(precision = 10, scale = 3)
    private BigDecimal deposit;
    @Column(precision = 10, scale = 3)
    private BigDecimal food;
    @Column(precision = 10, scale = 3, name = "other_expenses")
    private BigDecimal otherExpenses;
    @Column(precision = 10, scale = 3, name = "total_expenses")
    private BigDecimal totalExpensesForMonth;

    private static Expenses instance = new Expenses();

    private Expenses() {
    }

    public static Expenses getExpenses() {
        return instance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void deposit(BigDecimal amount) {
        this.budget = budget.add(amount);
    }

    public void clearBudget() {
        setBudget(BigDecimal.ZERO);
    }

    public void setApartmentLeasing(BigDecimal apartmentLeasing) {
        this.apartmentLeasing = apartmentLeasing;
    }

    public void setApartmentBill(BigDecimal apartmentBill) {
        this.apartmentBill = apartmentBill;
    }

    public void setCarLeasing(BigDecimal carLeasing) {
        this.carLeasing = carLeasing;
    }

    public void setCarCasco(BigDecimal carCasco) {
        this.carCasco = carCasco;
    }

    public void setCarInsurance(BigDecimal carInsurance) {
        this.carInsurance = carInsurance;
    }

    public void setGas(BigDecimal gas) {
        this.gas = gas;
    }

    public void setElectricity(BigDecimal bills) {
        this.electricity = bills;
    }

    public void setFood(BigDecimal food) {
        this.food = food;
    }

    public void setInternet(BigDecimal internet) {
        this.internet = internet;
    }

    public void setKindergarten(BigDecimal kindergarten) {
        this.kindergarten = kindergarten;
    }

    public void setPhones(BigDecimal phones) {
        this.phones = phones;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public void setOtherExpenses(BigDecimal otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public void setTotalExpensesForMonth(BigDecimal totalExpensesForMonth) {
        this.totalExpensesForMonth = totalExpensesForMonth;
    }

    public BigDecimal getApartmentLeasing() {
        return apartmentLeasing;
    }

    public BigDecimal getApartmentBill() {
        return apartmentBill;
    }

    public BigDecimal getCarLeasing() {
        return carLeasing;
    }

    public BigDecimal getCarCasco() {
        return carCasco;
    }

    public BigDecimal getCarInsurance() {
        return carInsurance;
    }

    public BigDecimal getGas() {
        return gas;
    }

    public BigDecimal getElectricity() {
        return electricity;
    }

    public BigDecimal getFood() {
        return food;
    }

    public BigDecimal getInternet() {
        return internet;
    }

    public BigDecimal getKindergarten() {
        return kindergarten;
    }

    public BigDecimal getPhones() {
        return phones;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public BigDecimal getOtherExpenses() {
        return otherExpenses;
    }

    public BigDecimal getTotalExpensesForMonth() {
        return totalExpensesForMonth;
    }

    public void withdrawal(BigDecimal amount, Withdrawal withdrawalType) {
        switch (withdrawalType) {
            case APARTMENT_LEASING -> {
                withdrawalBudget(amount);
                apartmentLeasing = apartmentLeasing.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case APARTMENT_BILL -> {
                withdrawalBudget(amount);
                apartmentBill = apartmentBill.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case CAR_LEASING -> {
                withdrawalBudget(amount);
                carLeasing = carLeasing.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case CAR_CASCO -> {
                withdrawalBudget(amount);
                carCasco = carCasco.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case CAR_INSURANCE -> {
                withdrawalBudget(amount);
                carInsurance = carInsurance.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case GAS -> {
                withdrawalBudget(amount);
                gas = gas.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case ELECTRICITY -> {
                withdrawalBudget(amount);
                electricity = electricity.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case FOOD -> {
                withdrawalBudget(amount);
                food = food.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case INTERNET -> {
                withdrawalBudget(amount);
                internet = internet.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case KINDERGARTEN -> {
                withdrawalBudget(amount);
                kindergarten = kindergarten.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case PHONES -> {
                withdrawalBudget(amount);
                phones = phones.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case DEPOSIT -> {
                withdrawalBudget(amount);
                deposit = deposit.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
            case OTHER_EXPENSES -> {
                withdrawalBudget(amount);
                otherExpenses = otherExpenses.add(amount);
                totalExpensesForMonth = totalExpensesForMonth.add(amount);
            }
        }
    }

    public void withdrawalBudget(BigDecimal amount) {
        try {
            if (getBudget().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
                throw new NotEnoughBalanceException(View.jFrame);
            } else {
                setBudget(getBudget().subtract(amount));
            }
        } catch (NotEnoughBalanceException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearAllData() {
        setApartmentLeasing(BigDecimal.ZERO);
        setApartmentBill(BigDecimal.ZERO);
        setCarLeasing(BigDecimal.ZERO);
        setCarCasco(BigDecimal.ZERO);
        setCarInsurance(BigDecimal.ZERO);
        setGas(BigDecimal.ZERO);
        setElectricity(BigDecimal.ZERO);
        setInternet(BigDecimal.ZERO);
        setKindergarten(BigDecimal.ZERO);
        setPhones(BigDecimal.ZERO);
        setDeposit(BigDecimal.ZERO);
        setFood(BigDecimal.ZERO);
        setOtherExpenses(BigDecimal.ZERO);
        setTotalExpensesForMonth(BigDecimal.ZERO);
        clearBudget();
    }

    // Check if entered value is a number
    public static BigDecimal checkEnteredValue(String value) {
        BigDecimal resultValue;
        try {
            if (value.equals("")) {
                return BigDecimal.ZERO;
            } else {
                resultValue = new BigDecimal(value);
            }
        } catch (NumberFormatException ex) {
            throw new NumberOnlyException(View.jFrame);
        }
        return resultValue;
    }
}
