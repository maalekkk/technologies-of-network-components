package pl.lodz.pl.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineGamingEnt;
import pl.lodz.p.tks.repositoriesadapters.data.machine.MachineWorkstationEnt;
import pl.lodz.p.tks.repositoriesadapters.data.rent.PeriodEnt;
import pl.lodz.p.tks.repositoriesadapters.data.rent.RentEnt;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.view.domainmodel.model.machine.Machine;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineGaming;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;
import pl.lodz.p.tks.view.domainmodel.model.rent.Rent;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.UUID;

public class TestUtils {
    static <T> boolean compareObjectsPropertiesByName(Object obj1, Object obj2, Class<T> type, String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getNameM1 = obj1.getClass().getMethod(propertyName);
        Method getNameM2 = obj2.getClass().getMethod(propertyName);

        T nameM1 = type.cast(getNameM1.invoke(obj1));
        T nameM2 = type.cast(getNameM2.invoke(obj2));
        return nameM1.equals(nameM2);
    }

    static <T> T getObjectProperty(Object obj, Class<T> type, String propertyName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = obj.getClass().getMethod(propertyName);
        return type.cast(method.invoke(obj));
    }

    static boolean compareMachines(Object machine1, Object machine2) {
        if (!machine1.getClass().equals(machine2.getClass())) {
            return false;
        }
        if (!(machine1 instanceof Machine || machine1 instanceof MachineEnt)) {
            return false;
        }
        try {
            boolean notEqual = !compareObjectsPropertiesByName(machine1, machine2, UUID.class, "getId");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, String.class, "getName");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getCores");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getRamSize");
            notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getHddSize");

            if(machine1 instanceof MachineGaming || machine1 instanceof MachineGamingEnt) {
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getGpuPower");
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getGpuVram");
            } else if(machine1 instanceof MachineWorkstation || machine1 instanceof MachineWorkstationEnt) {
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getSsdSize");
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Integer.class, "getNetCards");
                notEqual |= !compareObjectsPropertiesByName(machine1, machine2, Boolean.class, "getRaidSupport");
            }

            if(notEqual) {
                return false;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;
    }

    static boolean comparePeriods(Object period1, Object period2) {
        if (!period1.getClass().equals(period2.getClass())) {
            return false;
        }

        if (!(period1 instanceof Period || period2 instanceof PeriodEnt)) {
            return false;
        }

        try {
            boolean notEqual = !compareObjectsPropertiesByName(period1, period2, LocalDateTime.class, "getStartDate");
            notEqual |= !compareObjectsPropertiesByName(period1, period2, LocalDateTime.class, "getEndDate");

            if(notEqual) {
                return false;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    static boolean compareUsers(Object user1, Object user2) {
        if (!user1.getClass().equals(user2.getClass())) {
            return false;
        }

        if (!(user1 instanceof User || user2 instanceof UserEnt)) {
            return false;
        }

        try {
            boolean notEqual = !compareObjectsPropertiesByName(user1, user2, String.class, "getUsername");
            notEqual |= !compareObjectsPropertiesByName(user1, user2, UUID.class, "getId");
            notEqual |= !compareObjectsPropertiesByName(user1, user2, String.class, "getFullname");

            if(notEqual) {
                return false;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    static boolean compareRents(Object rent1, Object rent2) {
        if (!rent1.getClass().equals(rent2.getClass())) {
            return false;
        }

        if (!(rent1 instanceof Rent || rent2 instanceof RentEnt)) {
            return false;
        }

        try {
            boolean notEqual = !compareObjectsPropertiesByName(rent1, rent2, UUID.class, "getId");
            if(rent1 instanceof Rent) {
                Period p1 = getObjectProperty(rent1, Period.class, "getPeriod");
                Period p2 = getObjectProperty(rent2, Period.class, "getPeriod");
                notEqual |= !comparePeriods(p1, p2);

                User u1 = getObjectProperty(rent1, User.class, "getUser");
                User u2 = getObjectProperty(rent2, User.class, "getUser");
                notEqual |= !compareUsers(u1, u2);

                Machine m1 = getObjectProperty(rent1, Machine.class, "getMachine");
                Machine m2 = getObjectProperty(rent2, Machine.class, "getMachine");
                notEqual |= !compareMachines(m1, m2);

            } else {
                PeriodEnt p1 = getObjectProperty(rent1, PeriodEnt.class, "getPeriod");
                PeriodEnt p2 = getObjectProperty(rent2, PeriodEnt.class, "getPeriod");
                notEqual |= !comparePeriods(p1, p2);

                UserEnt u1 = getObjectProperty(rent1, UserEnt.class, "getUser");
                UserEnt u2 = getObjectProperty(rent2, UserEnt.class, "getUser");
                notEqual |= !compareUsers(u1, u2);

                MachineEnt m1 = getObjectProperty(rent1, MachineEnt.class, "getMachine");
                MachineEnt m2 = getObjectProperty(rent2, MachineEnt.class, "getMachine");
                notEqual |= !compareMachines(m1, m2);
            }

            if(notEqual) {
                return false;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }
}
