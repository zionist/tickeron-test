package com.tickeron.web.test.functional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.Arrays.asList;

/**
 * Created by slaviann on 24.02.15.
 */
public class Runner {
    private static ApplicationContext springContext = new ClassPathXmlApplicationContext("beans.xml");
    private static FuncEmbedder funcEmbedder = springContext.getBean("funcEmbedder", FuncEmbedder.class);
    private static String path = springContext.getBean("testStoriesPath", String.class);

    public static void main(String[] args) {
        String glob = System.getProperty("story.glob");
        String meta = System.getProperty("meta.filter");

        funcEmbedder.useMetaFilters(asList(meta.split(" ")));
        funcEmbedder.run(glob);
    }
}
