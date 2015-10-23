package com.tickeron.test.web.functional;

import com.tickeron.test.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Created by slaviann on 24.02.15.
 */
public class Runner {

    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    public static void main(String[] args) {
        context.register(AppConfig.class);
        context.refresh();
        FuncEmbedder funcEmbedder = context.getBean(FuncEmbedder.class);

        String glob = System.getProperty("story.glob");
        String meta = System.getProperty("meta.filter");
        String properties = System.getProperty("tests.properties");

        funcEmbedder.useMetaFilters(asList(meta.split(" ")));
        funcEmbedder.setTestsProperties(Optional.ofNullable(properties));
        funcEmbedder.run(glob);
    }
}
