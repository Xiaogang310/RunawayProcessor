package com.cyou.runaway.Processor.DocBuilder;

import com.cyou.runaway.Processor.internal.FunctionInfo;

import java.util.List;

/**
 * Created by Gang on 2016/10/11.
 */
public class BindingSet extends IBindingSet
{
    private String mCommand;
    private List<FunctionInfo> mFunctionInfos;
    private String mTable = "\t";
    private String mReturn = "\n";
    private String mQuote = " : ";
    private String mDot = " , ";

    public BindingSet(String cmd, List<FunctionInfo> funcInfos)
    {
        mCommand = cmd;
        mFunctionInfos = funcInfos;
    }

    @Override
    public String buildBinding()
    {
        String result = buildCommand();

        for (FunctionInfo info : mFunctionInfos)
        {
            result += buildFunction(info);
        }

        return result;
    }

    private String buildCommand()
    {
        return mCommand + mQuote + mReturn;
    }

    private String buildFunction(FunctionInfo info)
    {
        String result = "";
        result += buildPair("Function", info.getFunctionName());
        result += buildPair("Description", info.getDescription());
        result += buildPair("Type", info.getMethodType().toString());
        result += buildPair("Params", info.getParams());
        result += buildPair("Return", info.getResult());

        return result;
    }

    private String buildPair(String key, String value)
    {
        return  mTable + key + mQuote + value + mReturn;
    }

    private String buildPair(String key, String[] values)
    {
        String line = mTable + key + mQuote;

        for (int i = 0; i < values.length; ++i)
        {
            line += values[i];
            if (values.length - 1 != i)
                line += mDot;
        }

        line += mReturn;

        return line;
    }
}
