package utilities;

public class Util {

    public static String errorDescription(Exception e, Object o) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        String errorPlace = o.getClass().getSimpleName() + "." + methodName + "()";
        return "Exception in " + errorPlace + " : " + e.getMessage();
    }

}
