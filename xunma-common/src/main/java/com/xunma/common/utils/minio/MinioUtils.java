package com.xunma.common.utils.minio;

import com.xunma.common.constant.MinioConstants;

/**
 * minio工具包
 */
public class MinioUtils {
    //常见图片后缀字符串
    public static final String IMAGE_SUFFIX = "jpg,jpeg,png,gif,bmp";
    //常见视频后缀字符串
    public static final String VIDEO_SUFFIX = "mp4,avi,rmvb,rm,asf,divx,mpg,mpeg,mpe,wmv,mkv,vob";
    //常见压缩文件后缀字符串
    public static final String PACKAGE_SUFFIX = "zip,rar,7z";

    //根据对饮的后缀字符串获取对应的minio桶名称
    public static String getMinioBucketName(String suffix) {
        if (IMAGE_SUFFIX.contains(suffix)) {
            return MinioConstants.MINIO_BUCKET_IMAGE;
        } else if (VIDEO_SUFFIX.contains(suffix)) {
            return MinioConstants.MINIO_BUCKET_VIDEO;
        } else if (PACKAGE_SUFFIX.contains(suffix)) {
            return MinioConstants.MINIO_BUCKET_PACKAGE;
        } else {
            return MinioConstants.MINIO_BUCKET_GENERAL;
        }
    }
    //获取资源类型
    public static String getResourceType(String suffix) {
        if (IMAGE_SUFFIX.contains(suffix)) {
            return "0";
        } else if (VIDEO_SUFFIX.contains(suffix)) {
            return "2";
        }
        return "1";
    }

}
