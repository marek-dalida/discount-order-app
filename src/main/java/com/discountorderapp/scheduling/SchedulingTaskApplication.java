package com.discountorderapp.scheduling;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Profile({"random-order", "report"})
public class SchedulingTaskApplication {
}

