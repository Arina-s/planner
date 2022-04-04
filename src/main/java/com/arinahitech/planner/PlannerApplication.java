package com.arinahitech.planner;

import org.springframework.context.ApplicationContext;
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
        System.out.println(book.getItemName());
        PlannerControl plannerControlXml = xmlContext.getBean("plannerControl", PlannerControl.class);
        plannerControlXml.printPlannedSubject();

        System.out.println("----- Annotations -----");
        Goal goal = xmlContext.getBean("goal", Goal.class);
        goal.printGoal();
        AmbitionStore ambitionStore = (AmbitionStore) xmlContext.getBean("ambitionStore");
        ambitionStore.printAllAmbitions();

    }

}
