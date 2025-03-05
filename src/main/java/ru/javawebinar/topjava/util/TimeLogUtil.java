package ru.javawebinar.topjava.util;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimeLogUtil {
    private static final Logger logger = LoggerFactory.getLogger(TimeLogUtil.class);
    private static final Map<String, Long> testDurations = new HashMap<>();

    public static class StopwatchTest extends Stopwatch {
        @Override
        protected void succeeded(long nanos, Description description) {
            logInfo(description, "succeeded", nanos);
        }

        @Override
        protected void failed(long nanos, Throwable e, Description description) {
            logInfo(description, "failed", nanos);
        }

        @Override
        protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
            logInfo(description, "skipped", nanos);
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, "finished", nanos);
        }

        private void logInfo(Description description, String status, long nanos) {
            String testName = description.getMethodName();
            long millis = TimeUnit.NANOSECONDS.toMillis(nanos);
            logger.info("Test {} {}, spent {} millis", testName, status, millis);
            testDurations.put(testName, millis);
        }
    }

    public static void printResults() {
        logger.info("\n\n***** Time execution results *****");
        testDurations.forEach((testName, duration) ->
                logger.info("{} - {} ms", testName, duration)
        );
        logger.info("\n***** Test finished *****\n");
    }
}
