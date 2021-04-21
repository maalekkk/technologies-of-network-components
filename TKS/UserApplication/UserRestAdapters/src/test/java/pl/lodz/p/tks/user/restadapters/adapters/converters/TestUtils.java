package pl.lodz.p.tks.user.restadapters.adapters.converters;

import pl.lodz.p.tks.user.core.domainmodel.user.User;
import pl.lodz.p.tks.user.restadapters.data.user.UserRest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class TestUtils {
    static <T> boolean compareObjectsPropertiesByName(Object obj1, Object obj2, Class<T> type, String propertyName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getNameM1 = obj1.getClass().getMethod(propertyName);
        Method getNameM2 = obj2.getClass().getMethod(propertyName);

        T nameM1 = type.cast(getNameM1.invoke(obj1));
        T nameM2 = type.cast(getNameM2.invoke(obj2));
        return nameM1.equals(nameM2);
    }

    static <T> T getObjectProperty(Object obj, Class<T> type, String propertyName)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = obj.getClass().getMethod(propertyName);
        return type.cast(method.invoke(obj));
    }

    static boolean compareUsers(Object user1, Object user2) {
        if (!user1.getClass().equals(user2.getClass())) {
            return false;
        }

        if (!(user1 instanceof User || user2 instanceof UserRest)) {
            return false;
        }

        try {
            boolean notEqual = !compareObjectsPropertiesByName(user1, user2, String.class, "getUsername");
            notEqual |= !compareObjectsPropertiesByName(user1, user2, UUID.class, "getId");
            notEqual |= !compareObjectsPropertiesByName(user1, user2, String.class, "getFullname");

            if (notEqual) {
                return false;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

}
