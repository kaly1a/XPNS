package com.example.xpns;

public class BillsModel {

    private String expenseDescription;
    private  String expenseDate;
    private  String expenseAmount;
    private  BillsModel() {}
    private  BillsModel(String expenseDescription, String expenseDate, String expenseAmount) {}
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
