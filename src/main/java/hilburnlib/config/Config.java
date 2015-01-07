package hilburnlib.config;

import net.minecraftforge.common.config.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Config
{
    public String name();
    public String category() default Configuration.CATEGORY_GENERAL;
    public String comment() default "";
}
