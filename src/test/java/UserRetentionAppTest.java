import com.embrace.challenge.Main;
import com.embrace.challenge.configuration.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(classes = Main.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserRetentionAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void first_test() throws IOException {
        //setup
        System.setOut(new PrintStream(outContent));

        //when
        System.out.println("hola");

        //then
        Assertions.assertEquals(getExpectedResponse(), outContent.toString());
    }

    private String getExpectedResponse() throws IOException {
        return Files.readString(Path.of("src/test/resources/expectedResponse.txt"));
    }

}
