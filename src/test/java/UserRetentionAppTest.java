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
    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void resetStreams() {
        outContent.reset();
    }

    @Test
    void test_entire_application() throws IOException {
        String[] ars = {"src/test/resources/entireAppInput.txt"};
        UserRetentionController userRetentionController = applicationConfiguration.obtainUserRetentionController();

        userRetentionController.process(ars);
        Assertions.assertEquals(getExpectedResponse("src/test/resources/entireAppResponse.txt"), outContent.toString());
    }

    @Test
    void test_entire_application_with_custom_range_date() throws IOException {
        String[] ars = {"src/test/resources/entireAppInputRecordsExtended.txt", "01", "03"};
        UserRetentionController userRetentionController = applicationConfiguration.obtainUserRetentionController();

        userRetentionController.process(ars);
        Assertions.assertEquals(getExpectedResponse("src/test/resources/entireAppResponseRecordsExtended.txt"), outContent.toString());
    }

    private String getExpectedResponse(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

}
