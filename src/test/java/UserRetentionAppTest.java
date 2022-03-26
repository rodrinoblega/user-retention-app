import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.configuration.ApplicationConfiguration;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(args = {"src/test/resources/entireAppInput.txt", "25101991", "25101991"})
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserRetentionAppTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Autowired
    Instrumentation instrumentation;

    @Autowired
    UserRetentionController userRetentionController;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void test_entire_application() throws IOException {
        Assertions.assertEquals(getExpectedResponse(), outContent.toString());
    }

    private String getExpectedResponse() throws IOException {
        return Files.readString(Path.of("src/test/resources/entireAppResponse.txt"));
    }

}
