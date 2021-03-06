package com.cyou.runaway.Processor.DocBuilder;

import java.io.*;
import java.util.List;

/**
 * Created by Gang on 2016/10/11.
 */
public class Builder
{
    public static void build(List<? extends IBindingSet> bindings)
    {
        if (bindings.isEmpty())
            return;

        String docContent = DocDescription.msDescription;

        for (IBindingSet set : bindings)
        {
            docContent += set.buildBinding();
        }

        docContent += BuildTool.buildSeperator();

        generateDoc("runaway.txt", docContent);
    }

    private static void generateDoc(String path, String content)
    {
        if (!createFile(path))
            return;

        try
        {
            FileOutputStream stream = new FileOutputStream(path);

            try
            {
                stream.write(content.getBytes());
                stream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private static boolean createFile(String path)
    {
        File file = new File(path);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
                return true;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
}
