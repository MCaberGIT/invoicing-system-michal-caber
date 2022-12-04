package pl.futurecollars.invoicing.utils

import spock.lang.Ignore
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class FileServiceTest extends Specification {

    private FileService fileService = new FileService();
    private Path path = Path.of("C:\\Users\\mcabe\\Desktop\\Projects\\invoicing-system-michal-caber\\app\\src\\test\\resources\\lineTest.txt")

    def cleanup() {
        Files.write(path, [])
    }

    @Ignore
    def "should line is correctly appended to file"() {
        given:
        def testLine = "Test line to write"
        fileService.appendLineToFile(path, testLine)

        when:
        def result = Files.readAllLines(path)

        then:
        [testLine] == result

    }

    @Ignore
    def "should line is correctly written to file"() {

        given:
        fileService.writeToFile(path, "1")

        when:
        def result = Files.readAllLines(path)

        then:
        ["1"] == result
    }

    @Ignore
    def "should list of lines is correctly written to file"() {
        given:
        def letters = ['a', 'b', 'c']
        fileService.writeLinesToFile(path, letters)

        when:
        def result = Files.readAllLines(path)

        then:
        letters == result

    }

    @Ignore
    def "line is correctly read from file"() {
        given:
        def lines = List.of("line 1", "line 2", "line 3")
        Files.write(path, lines)

        when:
        def result = fileService.readAllLines(path)

        then:
        lines == result
    }

}
