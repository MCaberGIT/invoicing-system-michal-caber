package pl.futurecollars.invoicing.utils

import pl.futurecollars.invoicing.TestHelpers
import pl.futurecollars.invoicing.model.Invoice
import spock.lang.Specification


class JsonServiceTest extends Specification {

    JsonService jsonService = new JsonService()

    def "convert object to json "() {
        given:
        def invoiceInJson = "{\"id\":0,\"date\":\"2022-11-19\",\"buyer\":{\"taxIdentificationNumber\":\"1111111111\",\"address\":\"ul. Bukowińska 24d/1 02-703 Warszawa, Polska\",\"name\":\"iCode Trust 1 Sp. z o.o\"},\"seller\":{\"taxIdentificationNumber\":\"1111111111\",\"address\":\"ul. Bukowińska 24d/1 02-703 Warszawa, Polska\",\"name\":\"iCode Trust 1 Sp. z o.o\"},\"entries\":[{\"description\":\"Programming course 1\",\"price\":1000,\"vatValue\":80.0,\"vatRate\":\"VAT_8\"}]}\n"
        when:
        def result = jsonService.toObject(invoiceInJson, Invoice.class)
        then:
        result.id == 0
        result.buyer.name == "iCode Trust 1 Sp. z o.o"
    }

    def "should throw exception whe json is invalid"() {
        given:
        def invoiceInJson = ""
        when:
        def result = jsonService.toObject(invoiceInJson, Invoice.class)
        then:
        def exception = thrown(RuntimeException)
        exception.message == "Cannot parse json"
    }

    def "Should map json to object"() {
        given:
        def invoiceInJson = TestHelpers.company(0)
        when:
        def result1 = jsonService.toJson(invoiceInJson)
        then:
        result1 == "{\"taxIdentificationNumber\":\"0000000000\",\"address\":\"ul. Bukowińska 24d/0 02-703 Warszawa, Polska\",\"name\":\"iCode Trust 0 Sp. z o.o\"}"

    }
}
