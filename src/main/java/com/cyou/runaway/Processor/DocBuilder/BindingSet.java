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

    public BindingSet(String cmd, List<FunctionInfo> funcInfos)
    {
        mCommand = cmd;
        mFunctionInfos = funcInfos;
    }

    @Override
    public String buildBinding()
    {
        String result = BuildTool.buildSeperator();
        result += BuildTool.buildPair("命令", mCommand, false);

        for (FunctionInfo info : mFunctionInfos)
        {
            result += BuildTool.buildTabSeperator();
            result += buildFunction(info);
        }
        result += BuildTool.buildTabSeperator();
        return result;
    }

    private String buildFunction(FunctionInfo info)
    {
        String result = "";
        result += BuildTool.buildPair("函数", info.getFunctionName(), true);
        result += BuildTool.buildPair("描述", info.getDescription(), true);
        result += BuildTool.buildPair("类型", info.getMethodType().toString(), true);
        result += BuildTool.buildPair("参数", info.getParams(), true);
        result += BuildTool.buildPair("返回值", info.getResult(), true);

        return result;
    }
}
