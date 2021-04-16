package comp3350.team7.scheduleapp.application;
/*
 * Created By Thai Tran on 10 March,2021
 *
 */


public class Config {
    private static final String mode = "production";

    public static String getDeployMode() {
        return mode;
    }
}
