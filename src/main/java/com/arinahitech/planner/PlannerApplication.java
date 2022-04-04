package com.arinahitech.planner;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlannerApplication {

    public static void main(String[] args) {
        PlannerControl plannerControl = new PlannerControl(new Book("Want Can Should"));
        plannerControl.printPlannedSubject();
        plannerControl.setPlannedSubject(new Rollers());
        plannerControl.printPlannedSubject();

        System.out.println("----------XML----------");
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Book book = xmlContext.getBean("book", Book.class);
        book.setName("Harry");
        System.out.println(book.getItemName());
        Book book2 = xmlContext.getBean("book", Book.class);
        System.out.println(book2.getItemName());
        PlannerControl plannerControlXml = xmlContext.getBean("plannerControl", PlannerControl.class);
        plannerControlXml.printPlannedSubject();
        Rollers rollers = xmlContext.getBean("rollers", Rollers.class);
        System.out.println(rollers.getHours());
        rollers.setHours(30);
        Rollers rollers2 = xmlContext.getBean("rollers", Rollers.class);
        System.out.println(rollers2.getHours());

        System.out.println("----- Annotations -----");
        Goal goal = xmlContext.getBean("goal", Goal.class);
        goal.printGoal();
        AmbitionStore ambitionStore = (AmbitionStore) xmlContext.getBean("ambitionStore");
        ambitionStore.printAllAmbitions();

        System.out.println("----- All beans -----");
        String[] beansNames = xmlContext.getBeanDefinitionNames();
        for (String name : beansNames) {
            System.out.println(name);
        }
        Map<String, Object> beans = xmlContext.getBeansOfType(Object.class);

        System.out.println("--------Java Config-------");
        ApplicationContext javaContext = new AnnotationConfigApplicationContext(AppConfig.class);
        BookReview bookReview = (BookReview) javaContext.getBean("bookReview");
        System.out.println(bookReview.getBook().getItemName());

    }

}
