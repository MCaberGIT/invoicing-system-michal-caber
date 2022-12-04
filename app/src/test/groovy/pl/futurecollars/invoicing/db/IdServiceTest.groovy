package pl.futurecollars.invoicing.db


import pl.futurecollars.invoicing.utils.FileService
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path


class IdServiceTest  extends Specification {
    Path path = Path.of("C:\\Users\\mcabe\\Desktop\\Projects\\invoicing-system-michal-caber\\app\\src\\test\\resources\\idTestFile")
    Path wrongPath = Path.of("C:\\Users\\mcabe\\Desktop\\Projects\\invoicing-michal-caber\\app\\src\\test\\resources\\idTestFile")
    FileService fileService = new FileService()

    def cleanup() {
        Files.write(path, [])

    }

    def "next id starts from 1 if file was empty"() {
        given:
        IdService idService = new IdService(path, fileService)

        when:
        ["1"] == Files.readAllLines(path)
        def result = idService.getIdAndIncrement()

        then:
        result == 1

    }

    def "next id starts from last number if file was not empty"() {
        given:
        Files.write(path, ["17"])
        IdService idService = new IdService(path, fileService)

        when:
        ["17"] == Files.readAllLines(path)
        def result = idService.getIdAndIncrement()

        then:
        result == 17

    }

    def "next id starts from last number if file was not empty"() {
        when:
        IdService idService = new IdService(wrongPath, fileService)
        def result = idService.idAndIncrement

        then:
        def ex = thrown(RuntimeException)
        ex.message == "Filed"
        ex.cause.class == NoSuchFileException
    }
}
