package selenium.helper;

public class MethodCall {
  public static String getCallingMethodName() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

    StackTraceElement callingMethod = stackTrace[2];

    return callingMethod.getMethodName();
  }
}
