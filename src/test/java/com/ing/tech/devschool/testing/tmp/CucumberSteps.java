package com.ing.tech.devschool.testing.tmp;

import com.ing.tech.devschool.testing.TestingApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestingApplication.class})
public @interface CucumberSteps {
}
