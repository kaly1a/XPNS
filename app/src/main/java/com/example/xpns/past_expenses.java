package com.example.xpns;

public class past_expenses {
    String expenseDescription, expenseDate, expenseAmount;

    public past_expenses(String expenseDescription, String expenseDate, String expenseAmount) {
        this.expenseDescription = expenseDescription;
        this.expenseDate = expenseDate;
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
