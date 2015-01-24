package hilburnlib.compatibility.lua.conversion;

public interface ILuaConversion
{
    Object toLua(Object o);

    <T> T fromLua(Object o);
}
