package com.example.xpns;

public class UserExpense {

    private String expenseName, expenseDate,expenseTime, expenseDescription;
    private String expenseAmount;
    private String userID;
    private String splitID;

    public UserExpense() {

    }

    public UserExpense(String splitID,String userID, String expenseDate, String expenseTime, String expenseDescription,String expenseAmount) {
//        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseTime = expenseTime;
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

    public String getExpenseTime() {
        return expenseTime;
    }

    public void setExpenseTime(String expenseTime) {
        this.expenseTime = expenseTime;
    }

}
