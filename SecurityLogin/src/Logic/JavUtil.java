package Logic;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class JavUtil {

    public static ByteArrayInputStream getStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    @SuppressWarnings("rawtypes")
    public static ArrayList<String> getFields(Class objClass, Class retClass, int mod)
            throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> matches = new ArrayList<>();
        Field[] fields = objClass.getFields();
        for (Field field : fields) {
            if (field.getModifiers() == mod && field.getType().equals(retClass)) {
                matches.add(field.getName());
            }
            System.out.println(field.get(null));
        }
        return matches;
    }

    @SuppressWarnings("rawtypes")
    public static ArrayList<String> getFieldsValues(Class objClass, Class retClass, int mod)
            throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> matches = new ArrayList<>();
        Field[] fields = objClass.getFields();
        for (Field field : fields) {
            if (field.getModifiers() == mod && field.getType().equals(retClass)) {
                matches.add((String) field.get(null));
            }
        }
        return matches;
    }

}
