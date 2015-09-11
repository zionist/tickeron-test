package com.tickeron.test.web.functional;

import com.tickeron.test.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.Arrays.asList;

/**
 * Created by slaviann on 24.02.15.
 */
public class Runner {

    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    //private static ApplicationContext springContext = new ClassPathXmlApplicationContext("beans.xml");
    //private static String path = springContext.getBean("testStoriesPath", String.class);

    public static void main(String[] args) {
        context.register(AppConfig.class);
        context.refresh();
        FuncEmbedder funcEmbedder = context.getBean(FuncEmbedder.class);

        String testBean = context.getBean("testBean").toString();
        System.out.println("#");
        System.out.println(testBean);
        System.out.println("#");

        String glob = System.getProperty("story.glob");
        String meta = System.getProperty("meta.filter");


        funcEmbedder.useMetaFilters(asList(meta.split(" ")));
        funcEmbedder.run(glob);
    }
}
