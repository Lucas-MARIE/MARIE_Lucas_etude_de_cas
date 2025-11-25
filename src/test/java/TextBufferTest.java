import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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
        assertThat(actualString, is(equalTo(expectedString)));
    }

    @Test
    public void should_return_correct_maxP() {
        TextBuffer tb = new TextBuffer("12345");
        assertThat(tb.maxP(), is(5));
    }

    @Test
    public void should_insert_string_at_valid_position() {
        TextBuffer tb = new TextBuffer("Hello World");
        tb.ins("Beautiful ", 6);
        assertThat(tb.toString(), is("Hello Beautiful World"));
    }

    @Test
    public void should_not_insert_string_at_negative_position() {
        TextBuffer tb = new TextBuffer("Hello");
        tb.ins("X", -1);
        assertThat(tb.toString(), is("Hello"));
    }

    @Test
    public void should_not_insert_string_at_position_out_of_bounds() {
        TextBuffer tb = new TextBuffer("Hello");
        // position 5 is equal to length, condition is < length, so it should fail to insert (or append)
        tb.ins("X", 5); 
        assertThat(tb.toString(), is("Hello"));
        
        tb.ins("X", 10);
        assertThat(tb.toString(), is("Hello"));
    }

    @Test
    public void should_delete_substring_nominal() {
        TextBuffer tb = new TextBuffer("Hello World");
        tb.del(0, 6); // Delete "Hello "
        assertThat(tb.toString(), is("World"));
    }

    @Test
    public void should_handle_delete_out_of_bounds() {
        TextBuffer tb = new TextBuffer("Hello");
        
        // from < 0 -> 0, to > max -> max. Should delete everything.
        tb.del(-5, 100); 
        assertThat(tb.toString(), is(""));

        // Test spécifique pour from > max (doit être ramené à max)
        tb = new TextBuffer("Hello");
        tb.del(10, 12); // from=10 -> 5, to=12 -> 5. delete(5, 5) -> rien
        assertThat(tb.toString(), is("Hello"));

        // Test spécifique pour to < 0 (doit être ramené à 0)
        tb = new TextBuffer("Hello");
        tb.del(0, -5); // from=0, to=-5 -> 0. delete(0, 0) -> rien
        assertThat(tb.toString(), is("Hello"));
    }

    @Test
    public void should_handle_delete_inverted_bounds_or_empty_range() {
        TextBuffer tb = new TextBuffer("Hello");
        // from=3, to=3 -> delete nothing
        tb.del(3, 3);
        assertThat(tb.toString(), is("Hello"));
    }

    @Test
    public void should_extract_substring_nominal() {
        TextBuffer tb = new TextBuffer("Hello World");
        String sub = tb.substr(6, 11);
        assertThat(sub, is("World"));
    }

    @Test
    public void should_handle_substr_out_of_bounds() {
        TextBuffer tb = new TextBuffer("Hello");
        // from < 0 -> 0, to > max -> max. Should return everything.
        String sub = tb.substr(-1, 10);
        assertThat(sub, is("Hello"));

        // Test spécifique pour from > max (doit être ramené à max)
        sub = tb.substr(10, 12); // from=10 -> 5, to=12 -> 5. substring(5, 5) -> ""
        assertThat(sub, is(""));

        // Test spécifique pour to < 0 (doit être ramené à 0)
        sub = tb.substr(0, -5); // from=0, to=-5 -> 0. substring(0, 0) -> ""
        assertThat(sub, is(""));
    }
}
