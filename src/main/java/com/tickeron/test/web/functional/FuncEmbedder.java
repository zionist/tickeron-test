package com.tickeron.test.web.functional;

import com.tickeron.test.common.exceptions.PropertyNotFoundException;
import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.service.PortfolioSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.EmbedderMonitor;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;

public class FuncEmbedder extends Embedder {

    @Autowired
    private Environment env;
    @Autowired
    private SeleniumSteps seleniumSteps;
    //@Autowired
    //private ServiceStepsBasic serviceSteps;
    @Autowired
    private PortfolioSteps portfolioSteps;

    @Autowired
    private ParamsAndVariablesSteps paramsAndVariablesSteps;

    @Autowired
    private CustomStoryReporter customStoryReporter;

    public FuncEmbedder(EmbedderMonitor embedderMonitor) {
        super(embedderMonitor);
    }

    @Autowired

    @Override
    public EmbedderControls embedderControls() {
        return new EmbedderControls()
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true)
                .doVerboseFailures(true)
                .doVerboseFiltering(false)
                .doGenerateViewAfterStories(true);
    }

    @Override
    public Configuration configuration() {
        Class<? extends FuncEmbedder> embedderClass = this.getClass();

            return new MostUsefulConfiguration()
                    .useStoryLoader(new LoadFromClasspath(embedderClass.getClassLoader()))
                    .useStoryReporterBuilder(new StoryReporterBuilder()
                            .withReporters(customStoryReporter)
                            .withCodeLocation(CodeLocations.codeLocationFromClass(embedderClass))
                                    //.withDefaultFormats()
                            .withFormats(ANSI_CONSOLE)
                            .withFailureTrace(false)
                            .withFailureTraceCompression(true)
                            .withCrossReference(new CrossReference()))
                    .useParameterConverters(new ParameterConverters()
                            .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")))) // use custom date pattern
                            //.useStepPatternParser(new RegexPrefixCapturingPatternParser(
                            //        "%")) // use '%' instead of '$' to identify parameters
                    .useStepMonitor(new SilentStepMonitor());
        }


    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), portfolioSteps, seleniumSteps, paramsAndVariablesSteps);
        //return new InstanceStepsFactory(configuration());
    }

    // list of stories files from ClassPathResource
    public List<String> getStories(String storiesGlob) {
        File storiesDir = null;
        String testStoriesPath  = env.getProperty("test.stories.path");
        try {
            storiesDir = new ClassPathResource(testStoriesPath).getFile();
        } catch (IOException e) {
            System.err.println("Wrong path to stories");
            e.printStackTrace();
        }
        if (!storiesDir.isDirectory()) {
            System.err.println("Stories path is not a directory");
            System.exit(1);
        }
        if (storiesDir.listFiles().length == 0 ) {
            System.err.println("Stories path has no any story file");
            System.exit(1);
        }
        List<String> paths = new LinkedList<String>();

        PathMatcher matcher =
                FileSystems.getDefault().getPathMatcher("glob:" + storiesGlob);
        //File[] listFiles = storiesDir.listFiles();
        //Arrays.sort(listFiles);
        for (File file : storiesDir.listFiles()) {
            String filename = file.getName();
            Path p =  Paths.get(testStoriesPath, filename);
            // glob match
            if(matcher.matches(p.getFileName())) {
                // exclude all scenarios with _<filename>. Do not skip exact matching for any story name
                if (!filename.startsWith("_") || filename.equals(storiesGlob)) paths.add(p.toString());
            }
        }

        // sort
        Collections.sort(paths);

        return paths;
    }
    /*
    Parse and set tests properties (for example username, password etc) from command line prompt
     */
    public void setTestsProperties(Optional<String> properties) {
        properties.ifPresent((p) -> {
            Iterator<String> propertiesIterator = Arrays.asList(p.split(",")).iterator();
            while (propertiesIterator.hasNext()) {
                String property = propertiesIterator.next();
                String[]pairs = property.split("=");
                String name = pairs[0];
                String filePropertyName = pairs[1];
                String propertyValue = env.getProperty(filePropertyName, String.class, "");
                if (property.isEmpty()) throw new PropertyNotFoundException(filePropertyName);
                paramsAndVariablesSteps.getTestParamsStorage().put(name, propertyValue);
            }
        } );
    }

    public void run(String storiesGlob) {
        Integer exit_code = 0;
        try {
            this.runStoriesAsPaths(getStories(storiesGlob));
        } catch (Exception e) {
            exit_code = 1;
        }
        // May have client threads in background
        System.exit(exit_code);
    }

}