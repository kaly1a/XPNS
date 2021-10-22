package com.example.xpns;

public class UserExpense {

    private String expenseName, expenseDate, expenseDescription;
    private String expenseAmount;
    private String userID;
    private String splitID;

    public UserExpense() {

    }

    public UserExpense(String splitID,String userID, String expenseDate, String expenseDescription,String expenseAmount) {
//        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseDescription = expenseDescription;
        this.expenseAmount = expenseAmount;
        this.userID = userID;
        this.splitID = splitID;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSplitID() {
        return splitID;
    }

    public void setSplitID(String splitID) {
        this.splitID = splitID;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(String expenseAmount) {
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

}
