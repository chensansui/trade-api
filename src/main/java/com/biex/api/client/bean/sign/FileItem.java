package com.biex.api.client.bean.sign;

import com.biex.api.client.tool.BiexUtils;

import java.io.*;

/**
 * File metadata.
 * 
 * @since 1.0, Sep 12, 2009
 */
public class FileItem
{
    private String fileName;
    
    private String mimeType;
    
    private byte[] content;
    
    private File   file;
    
    /**
     * A local file based constructor.
     * 
     * @param file local files
     */
    public FileItem(File file)
    {
        this.file = file;
    }
    
    /**
     * A constructor based on the absolute path of a file.
     * 
     * @param filePath Absolute path of file
     */
    public FileItem(String filePath)
    {
        this(new File(filePath));
    }
    
    /**
     * A constructor based on file name and byte stream.
     * 
     * @param fileName file name
     * @param content File byte stream
     */
    public FileItem(String fileName, byte[] content)
    {
        this.fileName = fileName;
        this.content = content;
    }
    
    /**
     * A constructor based on file name, byte stream, and media type.
     * 
     * @param fileName file name
     * @param content File byte stream
     * @param mimeType media type
     */
    public FileItem(String fileName, byte[] content, String mimeType)
    {
        this(fileName, content);
        this.mimeType = mimeType;
    }
    
    public String getFileName()
    {
        if (this.fileName == null && this.file != null && this.file.exists())
        {
            this.fileName = file.getName();
        }
        return this.fileName;
    }
    
    public String getMimeType() throws IOException
    {
        if (this.mimeType == null)
        {
            this.mimeType = BiexUtils.getMimeType(getContent());
        }
        return this.mimeType;
    }
    
    public byte[] getContent() throws IOException
    {
        if (this.content == null && this.file != null && this.file.exists())
        {
            InputStream in = null;
            ByteArrayOutputStream out = null;
            try
            {
                in = new FileInputStream(this.file);
                out = new ByteArrayOutputStream();
                int ch;
                while ((ch = in.read()) != -1)
                {
                    out.write(ch);
                }
                this.content = out.toByteArray();
            }
            finally
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
        }
        return this.content;
    }
}
