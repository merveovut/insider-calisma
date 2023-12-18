package selenium.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import selenium.report.ExtentReporter;

public class Watcher extends TestWatcher {
  private static final Log logger = LogFactory.getLog(Watcher.class);

  @Override
  protected void starting(Description description) {
    logger.info("Test başladı: " + description.getMethodName());
    ExtentReporter.createTest(description.getMethodName());
    ExtentReporter.logInfo("Test başladı: " + description.getMethodName());
  }

  @Override
  protected void succeeded(Description description) {
    logger.info("Test başarılı: " + description.getMethodName());
    ExtentReporter.logPass("Test başarılı: " + description.getMethodName());
  }

  @Override
  protected void failed(Throwable e, Description description) {
    logger.error("Test başarısız oldu: " + description.getMethodName());
    ExtentReporter.logFail("Test başarısız oldu: " + description.getMethodName());
    ExtentReporter.logFail(e.getLocalizedMessage());
  }

  @Override
  protected void skipped(AssumptionViolatedException e, Description description) {
    logger.warn("Test atlandı: " + description.getMethodName());
    ExtentReporter.logSkip("Test atlandı: " + description.getMethodName());
  }

  @Override
  protected void finished(Description description) {
    logger.info("Test tamamlandı: " + description.getMethodName());
    ExtentReporter.logInfo("Test tamamlandı: " + description.getMethodName());
  }

}
