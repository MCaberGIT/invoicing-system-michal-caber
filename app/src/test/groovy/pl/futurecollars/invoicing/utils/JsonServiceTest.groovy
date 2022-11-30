//package pl.futurecollars.invoicing.utils
//
//import pl.futurecollars.invoicing.TestHelpers
//import pl.futurecollars.invoicing.model.Invoice
//import spock.lang.Specification
//
//
//class JsonServiceTest extends Specification {
//
//    JsonService jsonService = new JsonService();
//
//    def "convert object to json "() {
//        given:
//        def invoiceInJson = "{\"id\":0,\"date\":{\"year\":2022,\"month\":\"NOVEMBER\",\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"},\"era\":\"CE\",\"monthValue\":11,\"dayOfMonth\":30,\"dayOfYear\":334,\"dayOfWeek\":\"WEDNESDAY\",\"leapYear\":false},\"buyer\":{\"taxIdentificationNumber\":\"5213861303\",\"address\":\"ul. BukowiĹ„ska 24d/7 02-703 Warszawa, Polska\",\"name\":\"iCode Trust Sp. z o.o\"},\"seller\":{\"taxIdentificationNumber\":\"552-168-66-00\",\"address\":\"32-005 Niepolomice, Nagietkowa 19\",\"name\":\"Piotr Kolacz Development\"},\"entries\":[{\"description\":\"Programming course\",\"price\":10000,\"vatValue\":2300,\"vatRate\":\"VAT_23\"}]}"
//        when:
//        def result = jsonService.toObject(invoiceInJson, Invoice.class)
//        then:
//        result.id == 0
//        result.buyer.name == "RW INVEST Sp. z o.o"
//    }
//
//    def "should throw exception whe json is invalid"() {
//        given:
//        def invoiceInJson = ""
//        when:
//        def result = jsonService.toObject(invoiceInJson, Invoice.class)
//        then:
//        def exception = thrown(RuntimeException)
//        exception.message == "Cannot parse json"
//    }
//
//    def "Should map json to object"() {
//        given:
//        def invoiceInJson = TestHelpers.company(0)
//        when:
//        def result1 = jsonService.toJson(invoiceInJson)
//        then:
//        result1 == "{\"taxIdentyficationNumber\":\"0\",\"address\":\"ul. Lączna 43 03-156 Lipinki, Polska\",\"name\":\"RW INVEST Sp. z o.o\"}"
//
//    }
//}
