package by.step.time.extension;

import org.junit.jupiter.api.extension.*;
import java.time.Duration;
import java.time.Instant;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        context.getStore(ExtensionContext.Namespace.GLOBAL)
               .put("start", Instant.now());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Instant start = context.getStore(ExtensionContext.Namespace.GLOBAL)
                               .get("start", Instant.class);
        Duration duration = Duration.between(start, Instant.now());
        System.out.printf("Тест %s выполнился за %d мс%n",
                context.getDisplayName(), duration.toMillis());
    }
}