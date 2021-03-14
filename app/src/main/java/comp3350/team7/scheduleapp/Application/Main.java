package comp3350.team7.scheduleapp.Application;

/*
 * Created By Thai Tran on 10 March,2021
 *
 */

public class Main {
    private static String userDBName = "UserDB";

    public static void Main(String[] args){
        System.out.println("Booting up Schedule Management Application.");
        //run app
    }

    public static void setDBPathName(final String pathName){
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            System.out.println(e.printStackTrace());
        } catch (IllegalAccessException e) {
            System.out.println(e.printStackTrace());
        } catch (ClassNotFoundException e) 
            System.out.println(e.printStackTrace());
        }
        userDBName = pathName;
    }

    public static String getDBPathName(){
        return userDBName;
    }
}
