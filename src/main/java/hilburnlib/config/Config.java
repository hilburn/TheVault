package hilburnlib.config;

import javax.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Config
{
    public String name() default "";

    public String category() default net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

    public String comment() default "";

    public boolean needsRestart() default false;

    @Nullable
    public String[] validValues();

    public double max() default Integer.MAX_VALUE;

    public double min() default Integer.MIN_VALUE;

    public String pattern();
}
