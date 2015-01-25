package hilburnlib.parsing.parsers;

import hilburnlib.parsing.IParsing;

public abstract class ParserBase implements IParsing
{
    public static final String VAL = "V";
    public static final String TYPE = "T";
    protected byte key;

    protected ParserBase(int key)
    {
        this.key = (byte)key;
    }
}
