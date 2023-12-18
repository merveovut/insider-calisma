package selenium.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExtentReporter {
  private static final Log logger = LogFactory.getLog(ExtentReporter.class);
  private static final String reportDirectory = "./extent-report";
  private static final String reportName = "/report.html";
  private static final String reportTimeFormat = "dd-MM-yyyy HH:mm:ss";
  private static ExtentReports extent;
  private static ExtentTest test;
  private static ExtentTest subTest;
  private static String date;

  public static void initialize() {
    Date nowDate = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(reportTimeFormat);
    date = simpleDateFormat.format(nowDate);
    String reportPath = reportDirectory + " " + date + reportName;
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    logger.debug("Initialize Reporter");
  }

  public static void createTest(String testName) {
    test = extent.createTest(testName);
    logger.info("Create New Test");
  }

  public static ExtentTest getTest(){
    return test;
  }

  public static void createNode(String nodeName){
    logger.info("Create New Node");
    subTest = test.createNode(nodeName);
  }

  public static void setNodeLogInfo(String details){
    logger.info("Node Set Log Info");
    subTest.log(Status.INFO, details);
  }

  public static void setNodeInfo(Status status, String details){
    logger.info("Node Set Log Info");
    subTest.log(status, details);
  }

  public static void logInfo(String log) {
    test.log(Status.INFO, log);
  }

  public static void logPass(String log) {
    test.log(Status.PASS, log);
  }

  public static void logFail(String log) {
    test.log(Status.FAIL, log);
  }


  public static void logSkip(String log) {
    test.log(Status.SKIP, log);
  }

  public static void flush() {
    extent.flush();
  }

}
