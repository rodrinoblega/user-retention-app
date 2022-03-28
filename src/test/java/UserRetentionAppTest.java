import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.configuration.ApplicationConfiguration;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserRetentionAppTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void test_entire_application() throws IOException {
        String[] ars = {"src/test/resources/entireAppInput.txt"};
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        UserRetentionController userRetentionController = applicationConfiguration.obtainUserRetentionController();

        userRetentionController.process(ars);
        Assertions.assertEquals(getExpectedResponse(), outContent.toString());
    }

    private String getExpectedResponse() throws IOException {
        return Files.readString(Path.of("src/test/resources/entireAppResponse.txt"));
    }

}
