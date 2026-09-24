package hospital.models;

import java.time.*;

public record Payment(int id, int invoiceId, double amount, LocalDate paymentDate, String method) {}
