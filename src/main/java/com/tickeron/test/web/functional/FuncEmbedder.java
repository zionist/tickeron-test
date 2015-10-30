package com.tickeron.test.web.functional;

import com.tickeron.test.common.exceptions.PropertyNotFoundException;
import com.tickeron.test.web.functional.steps.ParamsAndVariablesSteps;
import com.tickeron.test.web.functional.steps.service.PortfolioSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import com.tickeron.test.web.functional.steps.service.ServiceStepsBasic;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.EmbedderMonitor;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.ANSIConsoleOutput;
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

import static org.jbehave.core.reporters.Format.*;

public class FuncEmbedder extends Embedder {

    public Boolean failed = false;

    @Autowired
    private Environment env;
    @Autowired
    private SeleniumSteps seleniumSteps;
    @Autowired
    private ServiceStepsBasic serviceStepsBasic;
    @Autowired
    private PortfolioSteps portfolioSteps;

    @Autowired
    private ParamsAndVariablesSteps paramsAndVariablesSteps;

    @Autowired
    private CustomAnsiStoryReporter customAnsiStoryReporter;

    public FuncEmbedder(EmbedderMonitor embedderMonitor) {
        super(embedderMonitor);
    }

    /**
     * Set global story run is failed
     */
    public void setFailed() {
        failed = true;
    }


    @Override
    public EmbedderControls embedderControls() {
        return new EmbedderControls()
                .doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true)
                .doVerboseFailures(true)
                .doVerboseFiltering(false)
                .doFailOnStoryTimeout(false)
                .useStoryTimeouts("1h")
                .doGenerateViewAfterStories(true);
    }

    @Override
    public Configuration configuration() {
        Class<? extends FuncEmbedder> embedderClass = this.getClass();

            return new MostUsefulConfiguration()
                    .useStoryLoader(new LoadFromClasspath(embedderClass.getClassLoader()))
                    .useStoryReporterBuilder(new StoryReporterBuilder()
                            .withReporters(customAnsiStoryReporter)
                            .withCodeLocation(CodeLocations.codeLocationFromClass(embedderClass))
                                    //.withDefaultFormats()
                            .withFormats(TXT, STATS, XML)
                            .withFailureTrace(false)
                            .withFailureTraceCompression(false)
                            .withCrossReference(new CrossReference()))
                    .useParameterConverters(new ParameterConverters()
                            .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")))) // use custom date pattern
                            //.useStepPatternParser(new RegexPrefixCapturingPatternParser(
                            //        "%")) // use '%' instead of '$' to identify parameters
                    .useStepMonitor(new SilentStepMonitor());
        }


    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), serviceStepsBasic, seleniumSteps, paramsAndVariablesSteps);
        //return new InstanceStepsFactory(configuration());
    }

    /**
     * Get stories via glob. Or comma separated list of globs
     * @param storiesGlob
     * @return list of stories file matching glob
     * @throws IOException
     */
    protected List<String> storyPaths(Optional<String> storiesGlob) throws IOException {
        // Specify story paths as URLs
        String testStoriesPath  = env.getProperty("test.stories.path");
        String codeLocation = new ClassPathResource(testStoriesPath).getFile().getAbsolutePath() + File.separator;

        List<String> searchIn = new LinkedList<>(Arrays.asList(
                String.format("*%s*.story", File.separator),
                String.format("*%s*%s*.story", File.separator, File.separator)
                )
        );
        if (storiesGlob.isPresent()) {
            searchIn.clear();
            for(String glob : storiesGlob.get().split(",")) {
                searchIn.add(glob + File.separator);
            }

        }
        List<String> exlude = Arrays.asList(String.format("*%s_*.story", File.separator));
        return new StoryFinder().findPaths(codeLocation, searchIn, exlude, testStoriesPath + File.separator);
    }

    /**
    * Parse and set tests properties (for example username, password etc) from command line prompt
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

    public void run(Optional<String> storiesGlob) throws IOException {
        Integer exit_code = 0;
        //try {
        this.runStoriesAsPaths(storyPaths(storiesGlob));
        //} catch (Exception e) {
            //e.printStackTrace();
            //exit_code = 1;
        //}
        if (this.failed) exit_code = 1;
        // May have client threads in background
        System.exit(exit_code);
    }

}