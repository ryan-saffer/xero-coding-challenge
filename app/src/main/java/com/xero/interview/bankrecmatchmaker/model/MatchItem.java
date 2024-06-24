package com.xero.interview.bankrecmatchmaker.model;

public class MatchItem {

    private final String id;
    private final String paidTo;
    private final String transactionDate;
    private final float total;
    private final String docType;


    public MatchItem(String id, String paidTo, String transactionDate, float total, String docType) {
        this.id = id;
        this.paidTo = paidTo;
        this.transactionDate = transactionDate;
        this.total = total;
        this.docType = docType;
    }

    public String getId() {
        return id;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public float getTotal() {
        return total;
    }

    public String getDocType() {
        return docType;
    }

}
