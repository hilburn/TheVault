package hilburnlib.junit;

import com.google.common.io.Files;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFMLSidedHandler;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MCTestRunner extends Runner
{
    protected Class<?> clazz;
    protected LaunchClassLoader loader;
    protected Object testInstance;
    protected Class<?> testClass;
    protected Map<Method, Description> testMethods = new HashMap<>();

    public MCTestRunner(Class<?> clazz) throws InitializationError, ClassNotFoundException, InstantiationException
    {
        this.clazz = clazz;
        setupMCAndFML();
        loadTests();
    }

    /**
     * Sets up some classes used for Minecraft Testing 
     */
    private void setupMCAndFML() throws InstantiationException
    {
        try
        {
            // Grab Loader that will work with FML
            URL[] urLs = ((URLClassLoader) Launch.class.getClassLoader()).getURLs();
            loader = new LaunchClassLoader(urLs);

            Class<?> relaunchLogClass = loader.loadClass("cpw.mods.fml.relauncher.FMLRelaunchLog");
            Field sideField = relaunchLogClass.getDeclaredField("side");
            sideField.setAccessible(true);
            sideField.set(relaunchLogClass, Enum.valueOf((Class<Enum>) sideField.getType(), "CLIENT"));

            Class<?> FMLLoader = loader.loadClass("cpw.mods.fml.common.Loader");
            Method m1 = FMLLoader.getMethod("injectData", Object[].class);
            
            // Empty(defaulting) data for the FMLLoader
            Object[] data = new Object[]{"", "", "", "", "1.7.10", "", Files.createTempDir(), Collections.EMPTY_LIST};
            m1.invoke(null, new Object[]{data});

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new InstantiationException("Something went wrong during the initialization of MC and/or FML");
        }
    }

    /**
     * Loads the tests in the class
     */
    private void loadTests() throws InstantiationException
    {
        try
        {
            // Load the class with proper Loader so MC and FML work
            testClass = loader.loadClass(clazz.getName());
            testInstance = testClass.newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e)
        {
            e.printStackTrace();
            throw new InstantiationException("Something went wrong during the initialization of the tests");
        }

        for (Method method : testClass.getMethods())
        {
            Annotation annotation = method.getAnnotation(Test.class); // TODO find out why this doesn't work
            if (annotation != null || method.getName().startsWith("test"))// workaround for issue found above
                testMethods.put(method, Description.createTestDescription(testClass, method.getName()));
        }
    }

    @Override
    public Description getDescription()
    {
        Description description = Description.createSuiteDescription(clazz.getName() + " | MCTest");
        for (Description value : testMethods.values())
            description.addChild(value);
        return description;
    }

    @Override
    public void run(RunNotifier notifier)
    {
        for (Map.Entry<Method, Description> entry : testMethods.entrySet())
        {
            Method method = entry.getKey();
            String name = entry.getKey().getName();
            notifier.fireTestStarted(entry.getValue());
            try
            {
                method.invoke(testInstance);
            } catch (Exception e)
            {
                Throwable cause = e;
                if (e instanceof InvocationTargetException)
                {
                    cause = e.getCause();
                }
                cause = new RuntimeException("Test " + name + " has failed", cause);
                Failure failure = new Failure(entry.getValue(), cause);
                notifier.fireTestFailure(failure);
            }
            notifier.fireTestFinished(entry.getValue());
        }
        notifier.fireTestRunFinished(new Result());
    }
}
