package hilburnlib.java.science;

import hilburnlib.java.string.StringHelper;
import hilburnlib.reference.Constants;

public enum SIUnits
{
    time("second", "s"),
    distance("meter", "m"),
    speed("meters/second", "ms" + Constants.Scripts.minus.sup + Constants.Scripts.one.sup),
    acceleration("meters/second" + Constants.Scripts.two.sup, "ms" + Constants.Scripts.minus.sup + Constants.Scripts.two.sup),
    mass("gram", "g"),
    newton("Newton", "N"),
    energy("Joules", "J"),
    kelvin("Kelvin", "K"),
    celsius("degrees Celsius", "°C"),
    radian("Radian", "rad"),
    hertz("Hertz", "hz"),
    coulomb("Coulomb", "C"),
    volt("Volt", "V"),
    farad("Farad", "F"),
    ohm("Ohm", "Ω"),
    siemens("Siemens", "S"),
    weber("Weber", "Wb"),
    tesla("Tesla", "T"),
    henry("Henry", "H"),
    lumen("Lumen", "lm"),
    lux("Lux", "lx"),
    becquerel("Becquerel", "Bq"),
    gray("Gray", "Gy"),
    sievert("Sievert", "Sv"),
    katal("Katal", "kat");

    private String fullName;
    private String symbol;

    SIUnits(String fullName, String symbol)
    {
        this.fullName = fullName;
        this.symbol = symbol;
    }

    public String getEngineeringFormat(Number number)
    {
        return StringHelper.getEngineeringUnits(number, this.symbol);
    }

    @Override
    public String toString()
    {
        return fullName;
    }
}
