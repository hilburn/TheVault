package hilburnlib.junit.test;

import org.junit.Test;

import static org.junit.Assert.*;
import static hilburnlib.java.science.SIUnits.*;

public class SIUnitsTest
{
    @Test
    public void testUnits()
    {
        assertEquals("1kN", newton.getEngineeringFormat(1000));
        assertEquals("100µV", volt.getEngineeringFormat(0.0001));
        assertEquals("1Mhz", hertz.getEngineeringFormat(1000000));
        assertEquals("1rad", radian.getEngineeringFormat(1));
        assertEquals("3kΩ", ohm.getEngineeringFormat(3000));
        assertEquals("3.56Plm", lumen.getEngineeringFormat(3560000000000000D));
        assertEquals("3.123kK", kelvin.getEngineeringFormat(3123));
        assertEquals("146J", energy.getEngineeringFormat(146));
    }
}
