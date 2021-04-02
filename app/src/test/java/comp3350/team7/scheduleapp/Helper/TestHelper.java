package comp3350.team7.scheduleapp.Helper;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import comp3350.team7.scheduleapp.application.DbClient;

public class TestHelper {
    private static final File dbScript = new File("src/main/assets/db/schedule.script");

    public static File cloneDb() throws IOException {
        final File tempDbScript = File.createTempFile("tempDbScript", ".script");
        Files.copy(dbScript, tempDbScript);
        DbClient.setDBPathName(tempDbScript.getAbsolutePath().replace(".script", ""));
        return tempDbScript;
    }
}
