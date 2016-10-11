package com.cyou.runaway.Processor.internal;

import com.cyou.runaway.Processor.annotation.MethodAnnotation;

/**
 * Created by Gang on 2016/10/11.
 */
public class FunctionInfo
{
    protected String mFunctionName;
    protected String mDescription;
    protected MethodAnnotation.Method_Type mMethodType;
    protected String mResult;
    protected String[] mParams;

    public FunctionInfo(String functionName)
    {
        mFunctionName = functionName;
    }

    public String getFunctionName()
    {
        return mFunctionName;
    }

    public void setFunctionName(String functionName)
    {
        mFunctionName = functionName;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    public MethodAnnotation.Method_Type getMethodType()
    {
        return mMethodType;
    }

    public void setMethodType(MethodAnnotation.Method_Type methodType)
    {
        mMethodType = methodType;
    }

    public String getResult()
    {
        return mResult;
    }

    public void setResult(String result)
    {
        mResult = result;
    }

    public String[] getParams()
    {
        return mParams;
    }

    public void setParams(String[] params)
    {
        mParams = params;
    }
}
