package com.biex.api.client.bean.sign;

/**
 * Request parameter header file
 */
public class RequestHolder
{
    /**
     * Public required parameter
     */
    private BiexMap protocalMustParams;
    
    /**
     * Public optional parameter
     */
    private BiexMap protocalOptParams;
    
    /**
     * Business request parameter
     */
    private BiexMap applicationParams;
    
    public BiexMap getProtocalMustParams()
    {
        return protocalMustParams;
    }
    
    public void setProtocalMustParams(BiexMap protocalMustParams)
    {
        this.protocalMustParams = protocalMustParams;
    }
    
    public BiexMap getProtocalOptParams()
    {
        return protocalOptParams;
    }
    
    public void setProtocalOptParams(BiexMap protocalOptParams)
    {
        this.protocalOptParams = protocalOptParams;
    }
    
    public BiexMap getApplicationParams()
    {
        return applicationParams;
    }
    
    public void setApplicationParams(BiexMap applicationParams)
    {
        this.applicationParams = applicationParams;
    }
}
