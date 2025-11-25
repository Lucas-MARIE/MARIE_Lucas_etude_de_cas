import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.einfolearning.tp2.metiers.TextBuffer;

public class TextBufferTest {

    @Test
    public void should_succed_when_toString_return_the_correct_string() {

        // Arrange

        TextBuffer tb = new TextBuffer("This is not a String");

        String expectedString = "This is not a String";


        // Act
        String actualString = tb.toString();


        // Assert
        Assertions.assertEquals(expectedString, actualString);

    }
}
