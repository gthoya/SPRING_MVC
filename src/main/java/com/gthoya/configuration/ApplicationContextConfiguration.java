package com.gthoya.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = "com.gthoya", excludeFilters = @Filter(Controller.class))
public class ApplicationContextConfiguration {

}
