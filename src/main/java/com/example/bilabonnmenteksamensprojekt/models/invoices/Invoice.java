package com.example.bilabonnmenteksamensprojekt.models.invoices;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;

import java.sql.Date;
import java.time.LocalDate;

public class Invoice {

    private String invoiceId;
    private Customer customer;
    private LocalDate invoiceDate;
    private double invoicedAmount;
    private InvoiceItem[] invoicedItems;

    public Invoice(String invoiceId, Customer customer, LocalDate invoiceDate, double invoicedAmount, InvoiceItem[] invoicedItems) {
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

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
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
