package pl.futurecollars.invoicing.service;

import java.util.List;
import java.util.Optional;
import pl.futurecollars.invoicing.db.DataBase;
import pl.futurecollars.invoicing.model.Invoice;

public class InvoiceService {

  private final DataBase database;

  public InvoiceService(DataBase database) {
    this.database = database;
  }

  public long save(Invoice invoice) {
    return database.save(invoice);
  }

  public Optional<Invoice> getById(long id) {
    return database.getById(id);
  }

  public List<Invoice> getAll() {
    return database.getAll();
  }

  public void update(long id, Invoice updatedInvoice) {
    database.update(id, updatedInvoice);
  }

  public void delete(long id) {
    database.delete(id);
  }
}
