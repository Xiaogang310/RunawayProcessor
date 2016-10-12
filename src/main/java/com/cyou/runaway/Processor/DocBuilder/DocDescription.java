package com.cyou.runaway.Processor.DocBuilder;

/**
 * Created by Gang on 2016/10/12.
 */
public class DocDescription
{
    public static String msDescription =
                    "文档说明\n" +
                    "/*文档由工具自动生成，有疑问请联系Xiao gang\n" +
                    "=======================\n" +
                    "1. 简介\n" +
                    "\n" +
                    "C#客户端与平台SDK采用Command通信，Command包括Command名称和参数，\n" +
                    "通过PlatformSDKHelper客户端将命令传给平台SDK，如下所示。\n" +
                    "\n" +
                    "mGPSCommand = \"locationCmd\";\n" +
                    "\n" +
                    "JsonData json = new JsonData()\n" +
                    "//public static readonly string CommandFuncName = \"CommandFunc\";\n" +
                    "json[PlatformSDKHelper.CommandFuncName] = \"start\";\n" +
                    "//public static readonly string Callback = \"Callback\";\n" +
                    "json[PlatformSDKHelper.Callback] = \"GPSLocationCallabck\";\n" +
                    "PlatformSDKHelper.getInstance().Call(mGPSCommand, json);\n" +
                    "\n" +
                    "mGPSCommand为Command的名称，json内容为Command的参数。\n" +
                    "\n" +
                    "2. Command种类\n" +
                    "\n" +
                    "Command目前包括三种，分别是Call类型，Get类型和Post类型。\n" +
                    "\n" +
                    "Call类型：客户端调用SDK并通过回调函数异步获取返回数据。\n" +
                    "\n" +
                    "\tmGPSCommand = \"locationCmd\";\n" +
                    "\n" +
                    "\tJsonData json = new JsonData()\n" +
                    "\t//public static readonly string CommandFuncName = \"CommandFunc\";\n" +
                    "\tjson[PlatformSDKHelper.CommandFuncName] = \"start\";\n" +
                    "\t//public static readonly string Callback = \"Callback\";\n" +
                    "\tjson[PlatformSDKHelper.Callback] = \"GPSLocationCallabck\";\n" +
                    "\tPlatformSDKHelper.getInstance().Call(mGPSCommand, json);\n" +
                    "\n" +
                    "\t如上面的locationCmd，需要在Command参数中指定Callback函数名称。\n" +
                    "\t/****************************注意***************************/\n" +
                    "\t此Callback函数必须在MonoBehavior中，此MonoBehavior与PlatformSDKHelper\n" +
                    "\t挂在同一个GameObject上，或者这个GameObject的子GameObject上.\n" +
                    "\t返回的数据是json格式，客户端根据需要进行解析\n" +
                    "\t/**********************************************************/\n" +
                    "\t\n" +
                    "Get类型：客户端调用SDK并同步得到返回值。\n" +
                    "\t\n" +
                    "\tjson[PlatformSDKHelper.CommandFuncName] = \"uuid\";\n" +
                    "    mInfo = PlatformSDKHelper.getInstance().Get(mUtilCmd, json);\n" +
                    "\t\n" +
                    "Post类型：客户端向SDK发送命令，SDK执行，并不需要返回值\n" +
                    "\n" +
                    "\tJsonData json = new JsonData();\n" +
                    "    json[PlatformSDKHelper.CommandFuncName] = \"openMap\";\n" +
                    "    PlatformSDKHelper.getInstance().Post(\"activityCmd\", json);\n" +
                    "\t\n" +
                    "3. Command参考手册\n";
}
