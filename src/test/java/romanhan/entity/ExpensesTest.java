package romanhan.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpensesTest {
    private final Expenses underTest = new Expenses();

    @Test
    @BeforeEach
    void setUp() {
        underTest.clearAllData();
    }


    @Test
    void setAndGetBudget() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);

        // when
        underTest.setBudget(enteredValue);

        // then
        BigDecimal expectedValue = underTest.getBudget();
        assertEquals(expectedValue, enteredValue);
    }

    @Test
    void checkIfDepositing() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        BigDecimal startValue = underTest.getBudget();

        // when
        underTest.deposit(enteredValue);

        // then
        BigDecimal resultValue = underTest.getBudget();
        assertEquals(resultValue, startValue.add(enteredValue));
    }

    @Test
    void clearBudget() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.clearBudget();

        // then
        BigDecimal expected = new BigDecimal(0);
        assertEquals(underTest.getBudget(), expected);
    }

    @Test
    void withdrawalApartmentLeasing() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.APARTMENT_LEASING);

        // then
        assertEquals(underTest.getApartmentLeasing(), enteredValue);
    }

    @Test
    void withdrawalApartmentBill() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.APARTMENT_BILL);

        // then
        assertEquals(underTest.getApartmentBill(), enteredValue);
    }
    @Test
    void withdrawalCarLeasing() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_LEASING);

        // then
        assertEquals(underTest.getCarLeasing(), enteredValue);
    }
    @Test
    void withdrawalCarCasco() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_CASCO);

        // then
        assertEquals(underTest.getCarCasco(), enteredValue);
    }
    @Test
    void withdrawalCarInsurance() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_INSURANCE);

        // then
        assertEquals(underTest.getCarInsurance(), enteredValue);
    }
    @Test
    void withdrawalGas() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.GAS);

        // then
        assertEquals(underTest.getGas(), enteredValue);
    }
    @Test
    void withdrawalElectricity() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.ELECTRICITY);

        // then
        assertEquals(underTest.getElectricity(), enteredValue);
    }
    @Test
    void withdrawalFood() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.FOOD);

        // then
        assertEquals(underTest.getFood(), enteredValue);
    }
    @Test
    void withdrawalInternet() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.INTERNET);

        // then
        assertEquals(underTest.getInternet(), enteredValue);
    }
    @Test
    void withdrawalKindergarten() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.KINDERGARTEN);

        // then
        assertEquals(underTest.getKindergarten(), enteredValue);
    }
    @Test
    void withdrawalPhones() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.PHONES);

        // then
        assertEquals(underTest.getPhones(), enteredValue);
    }
    @Test
    void withdrawalDeposit() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.DEPOSIT);

        // then
        assertEquals(underTest.getDeposit(), enteredValue);
    }

    @Test
    void withdrawalOtherExpenses() {
        // given
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.OTHER_EXPENSES);

        // then
        assertEquals(underTest.getOtherExpenses(), enteredValue);
    }



    @Test
    void throwsExceptionIfBalanceIsSmallerThanWithdrawalAmount() {
        // given
        BigDecimal budgetAmount = new BigDecimal(10);
        BigDecimal enteredValue = new BigDecimal(20);
        underTest.setBudget(budgetAmount);

        // then
        assertThrows(RuntimeException.class, () -> underTest.withdrawalBudget(enteredValue));
    }
    @Test
    void withdrawalIfBudgetAmountIsEnough() {
        // given
        BigDecimal budgetAmount = new BigDecimal(20);
        BigDecimal enteredValue = new BigDecimal(10);
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawalBudget(enteredValue);

        // then
        Assertions.assertTrue(budgetAmount.compareTo(enteredValue) > 0);
    }

    @Test
    void clearAllData() {
        // given
        BigDecimal value = new BigDecimal(10);
        underTest.setApartmentLeasing(value);
        underTest.setApartmentBill(value);
        underTest.setCarLeasing(value);
        underTest.setCarCasco(value);
        underTest.setCarInsurance(value);
        underTest.setGas(value);
        underTest.setElectricity(value);
        underTest.setInternet(value);
        underTest.setKindergarten(value);
        underTest.setPhones(value);
        underTest.setDeposit(value);
        underTest.setFood(value);
        underTest.setOtherExpenses(value);
        underTest.setTotalExpensesForMonth(value);
        underTest.setBudget(value);

        // when
        underTest.clearAllData();

        // then
        assertEquals(underTest.getApartmentLeasing(), BigDecimal.ZERO);
        assertEquals(underTest.getApartmentBill(), BigDecimal.ZERO);
        assertEquals(underTest.getCarLeasing(), BigDecimal.ZERO);
        assertEquals(underTest.getCarCasco(), BigDecimal.ZERO);
        assertEquals(underTest.getCarInsurance(), BigDecimal.ZERO);
        assertEquals(underTest.getGas(), BigDecimal.ZERO);
        assertEquals(underTest.getElectricity(), BigDecimal.ZERO);
        assertEquals(underTest.getInternet(), BigDecimal.ZERO);
        assertEquals(underTest.getKindergarten(), BigDecimal.ZERO);
        assertEquals(underTest.getPhones(), BigDecimal.ZERO);
        assertEquals(underTest.getDeposit(), BigDecimal.ZERO);
        assertEquals(underTest.getFood(), BigDecimal.ZERO);
        assertEquals(underTest.getOtherExpenses(), BigDecimal.ZERO);
        assertEquals(underTest.getTotalExpensesForMonth(), BigDecimal.ZERO);
        assertEquals(underTest.getBudget(), BigDecimal.ZERO);
    }

    @Test
    void ifEnteredValueIsEmptyShouldReturnZero() {
        // given
        String value = "";

        // then
        assertEquals(Expenses.checkEnteredValue(value), BigDecimal.ZERO);
    }
    @Test
    void ifEnteredValueIsStringShouldReturnException() {
        // given
        String value = "sd";

        // then
        assertThrows(NumberFormatException.class, () -> Expenses.checkEnteredValue(value));
    }
    @Test
    void ifEnteredValueIsDigitShouldReturnIt() {
        // given
        String value = "1";

        // when
        BigDecimal result = new BigDecimal(1);

        // then
        assertEquals(Expenses.checkEnteredValue(value), result);
    }

}