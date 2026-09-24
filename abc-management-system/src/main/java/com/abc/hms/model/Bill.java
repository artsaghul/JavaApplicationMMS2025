package com.abc.hms.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bill {
    private int billId;
    private int patientId;
    private Integer appointmentId;
    private BigDecimal amount;
    private LocalDate billDate;
    private String status; // PENDING, PAID

    private String patientName; // populated via join, optional

    public Bill() {}

    public Bill(int patientId, Integer appointmentId, BigDecimal amount) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.status = "PENDING";
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public Integer getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Integer appointmentId) { this.appointmentId = appointmentId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getBillDate() { return billDate; }
    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    @Override
    public String toString() {
        return String.format("[Bill #%d] %s | Amount: %.2f | Date: %s | Status: %s",
                billId, patientName != null ? patientName : ("Patient#" + patientId),
                amount, billDate, status);
    }
}
