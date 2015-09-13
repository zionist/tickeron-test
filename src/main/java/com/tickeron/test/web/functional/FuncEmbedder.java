package com.tickeron.test.web.functional;

import com.tickeron.test.web.functional.steps.CommonSteps;
import com.tickeron.test.web.functional.steps.SeleniumSteps;
import com.tickeron.test.web.functional.steps.ServiceSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class FuncEmbedder extends Embedder {

    @Autowired
    private Environment env;
    @Autowired
    private SeleniumSteps seleniumSteps;
    @Autowired
    private ServiceSteps serviceSteps;
    @Autowired
    private CommonSteps commonSteps;

    @Override
    public EmbedderControls embedderControls() {
        return new EmbedderControls()
                .doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true)
                .doVerboseFailures(true)
                .doGenerateViewAfterStories(true)
                .doVerboseFiltering(true);
    }

    @Override
    public Configuration configuration() {
        Class<? extends FuncEmbedder> embedderClass = this.getClass();

        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(embedderClass.getClassLoader()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(embedderClass))
                        .withDefaultFormats()
                        .withFormats(ANSI_CONSOLE)
                        .withCrossReference(new CrossReference()))
                .useParameterConverters(new ParameterConverters()
                        .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")))) // use custom date pattern
                        //.useStepPatternParser(new RegexPrefixCapturingPatternParser(
                        //        "%")) // use '%' instead of '$' to identify parameters
                .useStepMonitor(new SilentStepMonitor());

    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), serviceSteps, seleniumSteps, commonSteps);
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