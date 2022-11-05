package pl.futurecollars.invoicing.memory

import pl.futurecollars.invoicing.db.Database
import pl.futurecollars.invoicing.db.memory.InMemoryDataBase
import pl.futurecollars.invoicing.model.Invoice
import spock.lang.Specification

import static pl.futurecollars.invoicing.TestHelpers.invoice

class InMemoryDataBaseTest extends Specification {

    private Database database
    private List<Invoice> invoices

    def setup() {
        database = new InMemoryDataBase()

        invoices = (1..12).collect { invoice(it) }
    }

    def "should save invoices returning sequential id"() {
        when:
        def ids = invoices.collect({ database.save(it) })

        then:
        ids == (1..invoices.size()).collect()
        ids.forEach({ assert database.getById(it).isPresent() })
        ids.forEach({ assert database.getById(it).get().getId() == it })
        ids.forEach({ assert database.getById(it).get() == invoices.get(it - 1 as int) })
    }

    def "should get by id returns empty optional when there is no invoice with given id"() {
        expect:
        !database.getById(1).isPresent()
    }

    def "should get all returns empty collection if there were no invoices"() {
        expect:
        database.getAll().isEmpty()
    }

    def "should get all returns all invoices in the database, deleted invoice is not returned"() {
        given:
        invoices.forEach({ database.save(it) })

        expect:
        database.getAll().size() == invoices.size()
        database.getAll().forEach({ assert it == invoices.get(it.getId() - 1 as int) })

        when:
        database.delete(1)

        then:
        database.getAll().size() == invoices.size() - 1
        database.getAll().forEach({ assert it == invoices.get(it.getId() - 1 as int) })
        database.getAll().forEach({ assert it.getId() != 1 })
    }

    def "should delete all invoices"() {
        given:
        invoices.forEach({ database.save(it) })

        when:
        invoices.forEach({ database.delete(it.getId()) })

        then:
        database.getAll().isEmpty()
    }

    def "deleting not existing invoice is not causing any error"() {
        expect:
        database.delete(123)
    }

    def "it's possible to update the invoice"() {
        given:
        long id = database.save(invoices.get(0))

        when:
        database.update(id, invoices.get(1))

        then:
        database.getById(id).get() == invoices.get(1)
    }

    def "updating not existing invoice throws exception"() {
        when:
        database.update(213,invoices.get(1))

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "Invoice with id: 213 does not exist in database"
    }
}