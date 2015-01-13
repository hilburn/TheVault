package hilburnlib.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegisterKey
{
    public String unlocalizedName();
    public int keyCode();
    public String unlocalizedCategory() default "";//not filling in the category will default to key.categories.MOD_ID
}
