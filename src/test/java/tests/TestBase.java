package tests;

import com.tickeron.test.config.AppConfig;
import com.tickeron.test.web.functional.FuncEmbedder;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by slaviann on 26.10.15.
 */
public class TestBase extends TestCase {
    protected static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    public TestBase() {
        context.register(AppConfig.class);
        context.refresh();
    }

    @Test
    public void testJsRuns() throws InterruptedException {
        SeleniumSteps seleniumSteps = context.getBean(SeleniumSteps.class);
        seleniumSteps.setUpWebDriver();
        System.out.println(seleniumSteps.executeJs("return window.angular.version;"));

    }
}
