package pl.futurecollars.invoicing.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class Invoice {

  private long id;
  private LocalDate data;
  private Company buyer;
  private Company seller;
  private List<InvoiceEntry> entries;
}
