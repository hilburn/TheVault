package hilburnlib.reflection;

import java.util.HashSet;
import java.util.Set;

public class ClassScraper
{
    public static Set<Class> getGeneralizations(Class classObject)
    {
        Set<Class> generalizations = new HashSet<Class>();
        generalizations.add(classObject);
        Class superClass = classObject.getSuperclass();
        if (superClass != null)
        {
            generalizations.addAll(getGeneralizations(superClass));
        }
        Class[] superInterfaces = classObject.getInterfaces();
        for (int i = 0; i < superInterfaces.length; i++)
        {
            Class superInterface = superInterfaces[i];
            generalizations.addAll(getGeneralizations(superInterface));
        }
        return generalizations;
    }


    public static boolean classInstanceOf(Class clazz, Class... instances)
    {
        Set<Class> generalizations =  getGeneralizations(clazz);
        for (Class instance : instances)
            if (!generalizations.contains(instance)) return false;
        return true;
    }
}
