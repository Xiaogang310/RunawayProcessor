package com.cyou.runaway.Processor.DocBuilder;

/**
 * Created by Gang on 2016/10/11.
 */
public class BuildTool
{
    private static String msTable = "\t";
    private static String msReturn = "\n";
    private static String msQuote = " : ";
    private static String msDot = " , ";
    private static String msLine = "\t========================\n";
    private static String msStar = "****************************************\n";

    public static String buildPair(String key, String value, boolean beginWithTab)
    {
        String pair = key + msQuote + value + msReturn;
        if (beginWithTab)
            return msTable + pair;
        else
            return pair;
    }

    public static String buildPair(String key, String[] values, boolean beginWithTab)
    {
        String line = key + msQuote;

        for (int i = 0; i < values.length; ++i)
        {
            line += values[i];
            if (values.length - 1 != i)
                line += msDot;
        }

        line += msReturn;

        if (beginWithTab)
            return msTable + line;
        else
            return line;
    }

    public static String buildSeperator()
    {
        return msStar;
    }

    public static String buildTabSeperator()
    {
        return msLine;
    }
}
