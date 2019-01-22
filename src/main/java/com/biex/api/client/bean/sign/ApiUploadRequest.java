package com.biex.api.client.bean.sign;

import java.util.Map;

/**
 * Upload request interface, support uploading multiple files at the same time.
 * 
 * @since 1.0, Sep 12, 2009
 */
public interface ApiUploadRequest<T extends ApiResponse> extends ApiBasicRequest<T>
{
    /**
      * Get all the file request parameter sets in the form of Key-Value. among them:
      * <ul>
      * <li>Key: Request parameter name</li>
      * <li>Value: Request parameter file metadata</li>
      * </ul>
      *
      * @return file request parameter set
      */
    Map<String, FileItem> getFileParams();
}
