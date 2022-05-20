package com.example.bilabonnmenteksamensprojekt.models.invoices;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;

import java.sql.Date;

public class Invoice {

    private String invoiceId;
    private Customer customer;
    private Date invoiceDate;
    private double invoicedAmount;
    private InvoiceItem[] invoicedItems;

    public Invoice(String invoiceId, Customer customer, Date invoiceDate, double invoicedAmount, InvoiceItem[] invoicedItems) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.invoicedAmount = invoicedAmount;
        this.invoicedItems = invoicedItems;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(double invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public InvoiceItem[] getInvoicedItems() {
        return invoicedItems;
    }

    public void setInvoicedItems(InvoiceItem[] invoicedItems) {
        this.invoicedItems = invoicedItems;
    }
}
