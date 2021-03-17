package nurseschedules.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getFirstWord() {

        assertEquals("test",
                nurseschedules.parser.Parser.getFirstWord("test get first word"));
    }
}