package hospital.models;

import java.time.*;

public record InvoiceItem(int id, int invoiceId, String description, int quantity, double unitPrice) {}
