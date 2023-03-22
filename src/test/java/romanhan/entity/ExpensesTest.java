package romanhan.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpensesTest {
    private final Expenses underTest = new Expenses();
    @Test
    void setAndGetBudget() {
        // given
        int enteredValue = 10;

        // when
        underTest.setBudget(enteredValue);

        // then
        int expectedValue = underTest.getBudget();
        assertEquals(expectedValue, enteredValue);
    }

    @Test
    void checkIfDepositing() {
        // given
        int enteredValue = 10;
        int startValue = underTest.getBudget();

        // when
        underTest.deposit(enteredValue);

        // then
        int resultValue = underTest.getBudget();
        assertEquals(resultValue, startValue + enteredValue);
    }

    @Test
    void clearBudget() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.clearBudget();

        // then
        int expected = 0;
        assertEquals(underTest.getBudget(), expected);
    }

    @Test
    void withdrawalApartmentLeasing() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.APARTMENT_LEASING);

        // then
        assertEquals(underTest.getApartmentLeasing(), enteredValue);
    }

    @Test
    void withdrawalApartmentBill() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.APARTMENT_BILL);

        // then
        assertEquals(underTest.getApartmentBill(), enteredValue);
    }
    @Test
    void withdrawalCarLeasing() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_LEASING);

        // then
        assertEquals(underTest.getCarLeasing(), enteredValue);
    }
    @Test
    void withdrawalCarCasco() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_CASCO);

        // then
        assertEquals(underTest.getCarCasco(), enteredValue);
    }
    @Test
    void withdrawalCarInsurance() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_INSURANCE);

        // then
        assertEquals(underTest.getCarInsurance(), enteredValue);
    }
    @Test
    void withdrawalGas() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.GAS);

        // then
        assertEquals(underTest.getGas(), enteredValue);
    }
    @Test
    void withdrawalElectricity() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.ELECTRICITY);

        // then
        assertEquals(underTest.getElectricity(), enteredValue);
    }
    @Test
    void withdrawalFood() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.FOOD);

        // then
        assertEquals(underTest.getFood(), enteredValue);
    }
    @Test
    void withdrawalInternet() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.INTERNET);

        // then
        assertEquals(underTest.getInternet(), enteredValue);
    }
    @Test
    void withdrawalKindergarten() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.KINDERGARTEN);

        // then
        assertEquals(underTest.getKindergarten(), enteredValue);
    }
    @Test
    void withdrawalPhones() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.PHONES);

        // then
        assertEquals(underTest.getPhones(), enteredValue);
    }
    @Test
    void withdrawalDeposit() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.DEPOSIT);

        // then
        assertEquals(underTest.getDeposit(), enteredValue);
    }

    @Test
    void withdrawalOtherExpenses() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.OTHER_EXPENSES);

        // then
        assertEquals(underTest.getOtherExpenses(), enteredValue);
    }



    @Test
    void throwsExceptionIfBalanceIsSmallerThanWithdrawalAmount() {
        // given
        int budgetAmount = 10;
        int enteredValue = 20;
        underTest.setBudget(budgetAmount);

        // then
        assertThrows(RuntimeException.class, () -> underTest.withdrawalBudget(enteredValue));
    }
    @Test
    void withdrawalIfBudgetAmountIsEnough() {
        // given
        int budgetAmount = 20;
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawalBudget(enteredValue);

        // then
        Assertions.assertTrue(budgetAmount > enteredValue);
    }

    @Test
    void clearAllData() {
        // given
        int value = 10;
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
        assertEquals(underTest.getApartmentLeasing(), 0);
        assertEquals(underTest.getApartmentBill(), 0);
        assertEquals(underTest.getCarLeasing(), 0);
        assertEquals(underTest.getCarCasco(), 0);
        assertEquals(underTest.getCarInsurance(), 0);
        assertEquals(underTest.getGas(), 0);
        assertEquals(underTest.getElectricity(), 0);
        assertEquals(underTest.getInternet(), 0);
        assertEquals(underTest.getKindergarten(), 0);
        assertEquals(underTest.getPhones(), 0);
        assertEquals(underTest.getDeposit(), 0);
        assertEquals(underTest.getFood(), 0);
        assertEquals(underTest.getOtherExpenses(), 0);
        assertEquals(underTest.getTotalExpensesForMonth(), 0);
        assertEquals(underTest.getBudget(), 0);
    }

    @Test
    void ifEnteredValueIsEmptyShouldReturnZero() {
        // given
        String value = "";

        // then
        assertEquals(Expenses.checkEnteredValue(value), 0);
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
        int result = 1;

        // then
        assertEquals(Expenses.checkEnteredValue(value), result);
    }

}