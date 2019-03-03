package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EventsListener extends AbstractWebDriverEventListener {

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println("[Something Happened:] "+throwable.getMessage().split(":")[0]);

        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile.toPath(),new File(System.currentTimeMillis() + "screen.png").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[Screenshot Captured]");
    }
}
