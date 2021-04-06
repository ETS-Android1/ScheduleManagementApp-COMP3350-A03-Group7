package comp3350.team7.scheduleapp.Helper;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

import comp3350.team7.scheduleapp.application.DbClient;

public class TestHelper {
    private static final File dbScript = new File("src/main/assets/db/schedule.script");

    public static File cloneDb() throws IOException {
        final File tempDbScript = File.createTempFile("tempDbScript", ".script");
        Files.copy(dbScript, tempDbScript);
        DbClient.setDBPathName(tempDbScript.getAbsolutePath().replace(".script", ""));
        return tempDbScript;
    }

    public static boolean isSorted(Object[] array, Comparator<Object> comparator, int length) {
        if (array == null || length < 2)
            return true;
        if (comparator.compare(array[length - 2], array[length - 1]) > 0)
            return false;
        return isSorted(array, comparator, length - 1);
    }
}
