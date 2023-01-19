package romanhan.controller;

import org.junit.jupiter.api.Test;
import romanhan.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ExpensesTest {
    private final User user = new User();
    private final Expenses underTest = new Expenses(user);
    @Test
    void setAndGetBudget() {
        // given
        int enteredValue = 10;

        // when
        underTest.setBudget(enteredValue);

        // then
        int expectedValue = underTest.getBudget();
        assertThat(expectedValue).isEqualTo(enteredValue);
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
        assertThat(resultValue).isEqualTo(startValue + enteredValue);
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
        assertThat(underTest.getBudget()).isEqualTo(expected);
    }

    @Test
    void withdrawalApartmentLeasing() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.APARTMENT_LEASING);

        // then
        assertThat(underTest.getApartmentLeasing()).isEqualTo(enteredValue);
    }

    @Test
    void withdrawalApartmentBill() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.APARTMENT_BILL);

        // then
        assertThat(underTest.getApartmentBill()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalCarLeasing() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_LEASING);

        // then
        assertThat(underTest.getCarLeasing()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalCarCasco() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_CASCO);

        // then
        assertThat(underTest.getCarCasco()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalCarInsurance() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.CAR_INSURANCE);

        // then
        assertThat(underTest.getCarInsurance()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalGas() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.GAS);

        // then
        assertThat(underTest.getGas()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalElectricity() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.ELECTRICITY);

        // then
        assertThat(underTest.getElectricity()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalFood() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.FOOD);

        // then
        assertThat(underTest.getFood()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalInternet() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.INTERNET);

        // then
        assertThat(underTest.getInternet()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalKindergarten() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.KINDERGARTEN);

        // then
        assertThat(underTest.getKindergarten()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalPhones() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.PHONES);

        // then
        assertThat(underTest.getPhones()).isEqualTo(enteredValue);
    }
    @Test
    void withdrawalDeposit() {
        // given
        int enteredValue = 10;
        underTest.setBudget(enteredValue);

        // when
        underTest.withdrawal(enteredValue, Withdrawal.DEPOSIT);

        // then
        assertThat(underTest.getDeposit()).isEqualTo(enteredValue);
    }



    @Test
    void throwsExceptionIfBalanceIsSmallerThanWithdrawalAmount() {
        // given
        int budgetAmount = 10;
        int enteredValue = 20;
        underTest.setBudget(budgetAmount);

        // then
        assertThrows(RuntimeException.class, () -> {
            underTest.withdrawalBudget(enteredValue);
        });
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
        assertThat(budgetAmount).isGreaterThan(enteredValue);
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
        underTest.setTotalExpensesForMonth(value);
        underTest.setBudget(value);

        // when
        underTest.clearAllData();

        // then
        assertThat(underTest.getApartmentLeasing()).isEqualTo(0);
        assertThat(underTest.getApartmentBill()).isEqualTo(0);
        assertThat(underTest.getCarLeasing()).isEqualTo(0);
        assertThat(underTest.getCarCasco()).isEqualTo(0);
        assertThat(underTest.getCarInsurance()).isEqualTo(0);
        assertThat(underTest.getGas()).isEqualTo(0);
        assertThat(underTest.getElectricity()).isEqualTo(0);
        assertThat(underTest.getInternet()).isEqualTo(0);
        assertThat(underTest.getKindergarten()).isEqualTo(0);
        assertThat(underTest.getPhones()).isEqualTo(0);
        assertThat(underTest.getDeposit()).isEqualTo(0);
        assertThat(underTest.getFood()).isEqualTo(0);
        assertThat(underTest.getTotalExpensesForMonth()).isEqualTo(0);
        assertThat(underTest.getBudget()).isEqualTo(0);
    }

    @Test
    void ifEnteredValueIsEmptyShouldReturnZero() {
        // given
        String value = "";

        // then
        assertThat(Expenses.checkEnteredValue(value)).isEqualTo(0);
    }
    @Test
    void ifEnteredValueIsStringShouldReturnException() {
        // given
        String value = "sd";

        // then
        assertThrows(NumberFormatException.class, () -> {
            Expenses.checkEnteredValue(value);
        });
    }
    @Test
    void ifEnteredValueIsDigitShouldReturnIt() {
        // given
        String value = "1";

        // when
        int result = 1;

        // then
        assertThat(Expenses.checkEnteredValue(value)).isEqualTo(result);
    }

}