package nl.mikee.twitterstat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mike on 14/01/16.
 */
public class Runner {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("spring-cfg.xml");

        TwitterController search1 = (TwitterController) context.getBean("search1");
        search1.go();

    }
}
