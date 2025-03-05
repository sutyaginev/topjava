package ru.javawebinar.topjava.util;

import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestDurationLogUtil {
    private static final Logger logger = LoggerFactory.getLogger(TestDurationLogUtil.class);
    private static final Map<String, Long> testDurations = new HashMap<>();

    public static class StopwatchTest extends Stopwatch {
        @Override
        protected void finished(long nanos, Description description) {
            String testName = description.getMethodName();
            long millis = TimeUnit.NANOSECONDS.toMillis(nanos);
            logger.info("Test \"{}\" finished - {} ms", testName, millis);
            testDurations.put(testName, millis);
        }
    }

    public static class SummaryRule implements TestRule {
        @Override
        public Statement apply(Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        base.evaluate();
                    } finally {
                        printResults();
                    }
                }
            };
        }
    }

    public static void printResults() {
        StringBuilder results = new StringBuilder("\n\n*****  Time execution results  *****\n" +
                "------------------------------------\n" +
                "TEST                        DURATION\n" +
                "------------------------------------\n");
        testDurations.forEach((testName, duration) ->
                results.append(String.format("%-25s   %5d ms%n", testName, duration))
        );
        results.append("------------------------------------\n" +
                "*****      Tests finished      *****\n");
        logger.info(results.toString());
    }
}
