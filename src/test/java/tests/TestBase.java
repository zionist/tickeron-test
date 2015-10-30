package tests;

import com.tickeron.test.config.AppConfig;
import com.tickeron.test.web.functional.FuncEmbedder;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import com.tickeron.test.web.functional.steps.service.ServiceStepsBasic;
import junit.framework.TestCase;
import org.jbehave.core.io.StoryFinder;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by slaviann on 26.10.15.
 */
public class TestBase extends TestCase {
    protected static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    @Override
    protected void setUp() throws Exception {
        System.out.println("Setting it up!");
        context.register(AppConfig.class);
        context.refresh();
    }



    @Test
    public void testJsRuns() throws InterruptedException {
        SeleniumSteps seleniumSteps = context.getBean(SeleniumSteps.class);
        seleniumSteps.setUpWebDriver();
        System.out.println(seleniumSteps.executeJs("return window.angular.version;"));

    }

    @Test
    public void testStoryFinder() throws IOException {
        FuncEmbedder funcEmbedder = context.getBean(FuncEmbedder.class);
        String testStoriesPath = "stories";
        String codeLocation = new ClassPathResource(testStoriesPath).getFile().getAbsolutePath() + File.separator;
        //String codeLocation = "stories/";
        //List<String> searchIn = Arrays.asList(String.format("*%s*.story", File.separator));
        List<String> searchIn = Arrays.asList(String.format("iproducts/*.story", File.separator));
        List<String> exlude = Arrays.asList(String.format("*%s_*.story", File.separator));
        System.out.println(new StoryFinder().findPaths(codeLocation, searchIn, exlude, "stories/"));
        //funcEmbedder.runStoriesAsPaths((new StoryFinder().findPaths(codeLocation, searchIn, exlude, "stories/")));

    }

    @Test
    public void testgetStories() throws IOException {
        FuncEmbedder funcEmbedder = context.getBean(FuncEmbedder.class);
        System.out.println(funcEmbedder.getStories("*"));
    }



}
