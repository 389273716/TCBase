package com.tc.tcbase.base.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.tc.tcbase.base.utils.log.LogUtil;
import com.tc.tcbase.constants.BaseConstants;

import java.io.File;
import java.io.IOException;

/**
 * author：   zp
 * date：     2015/9/10 0010 18:00
 * version    1.0
 * description 文件操作的工具类
 * modify by
 */
public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * SD卡是否能用
     *
     * @return true 可用,false不可用
     */
    public static boolean isSDCardAvailable() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            Log.e(TAG, "isSDCardAvailable : SD卡不可用!", e);
            return false;
        }
    }

    /**
     * 创建一个文件目录, 存在则返回, 不存在则新建
     *
     * @param catalog     目录
     * @param catalogName 目录catalog下的新目录名
     * @return 目录
     */
    public static File generateFileCatalog(File catalog, String catalogName) {
        if (catalog == null || TextUtils.isEmpty(catalogName)) {
            Log.e(TAG, "generateFileCatalog : 路径或目录名为空, 请检查!");
            return null;
        }
        File file = new File(catalog, catalogName);
        boolean flag;
        if (!file.exists()) {
            flag = file.mkdir();
        } else {
            flag = true;
        }
        if (!flag) {
            Log.e(TAG, "generateFileCatalog : 创建文件目录+" + file.getName() + "失败,请检查!");
        }
        return flag ? file : null;
    }


    /**
     * 创建一个文件目录, 存在则返回, 不存在则新建
     *
     * @param filePath    文件路径
     * @param catalogName 目录名
     * @return 目录
     */
    public static File generateFileCatalog(String filePath, String catalogName) {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(catalogName)) {
            Log.e(TAG, "generateFileCatalog : 路径或目录名为空, 请检查!");
            return null;
        }
//        filePath = filePath.concat(File.separator);
        File catalogFile = new File(filePath);
        boolean flag;
        if (!catalogFile.exists()) {
            flag = catalogFile.mkdir();
        } else {
            flag = true;
        }
        if (!flag) {
            Log.e(TAG, "generateFileCatalog : 文件路径+" + filePath + "创建失败!");
        }
        return flag ? generateFileCatalog(catalogFile, catalogName) : null;
    }

    /**
     * 创建一个文件, 存在则返回, 不存在则新建
     *
     * @param catalogPath 路径
     * @param name        文件名
     * @return 文件
     */
    public static File generateFile(String catalogPath, String name) {
        if (TextUtils.isEmpty(catalogPath) || TextUtils.isEmpty(name)) {
            Log.e(TAG, "generateFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        boolean flag = false;
        File file = new File(catalogPath, name);
        if (!file.exists()) {
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "generateFile : 创建" + catalogPath + "目录下的文件" + name + "文件失败!", e);
                flag = false;
            }
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /**
     * 创建一个文件, 存在则返回, 不存在则新建
     *
     * @param catalog 文件目录
     * @param name    文件名
     * @return 文件
     */
    public static File generateFile(File catalog, String name) {
        if (catalog == null || TextUtils.isEmpty(name)) {
            Log.e(TAG, "generateFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        boolean flag;
        File file = new File(catalog, name);
        if (!file.exists()) {
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "generateFile : 创建" + catalog + "目录下的文件" + name + "文件失败!", e);
                flag = false;
            }
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /**
     * 根据全路径创建一个文件
     *
     * @param filePath 文件全路径
     * @return 文件
     */
    public static File generateFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            Log.e(TAG, "generateFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        boolean flag;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "generateFile : 创建" + file.getName() + "文件失败!", e);
                flag = false;
            }
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /**
     * 获取文件, 不存在返回空
     *
     * @param catalogPath 文件目录路径
     * @param name        文件名
     * @return 获取到的文件, 不存在返回空
     */
    public static File getFile(String catalogPath, String name) {
        if (catalogPath == null || TextUtils.isEmpty(name)) {
            Log.e(TAG, "getFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        File file = new File(catalogPath, name);
        return file.exists() ? file : null;
    }

    /**
     * 获取文件, 不存在返回空
     *
     * @param catalog 文件目录
     * @param name    文件名
     * @return 获取到的文件, 不存在返回空
     */
    public static File getFile(File catalog, String name) {
        if (catalog == null || TextUtils.isEmpty(name)) {
            Log.e(TAG, "getFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        File file = new File(catalog, name);
        return file.exists() ? file : null;
    }

    /**
     * 根据全路径获取获取文件, 不存在返回空
     *
     * @param filePath 文件全路径
     * @return 文件, 不存在返回空
     */
    public static File getFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            Log.e(TAG, "getFile : 创建失败, 文件路径为空, 请检查!");
            return null;
        }
        File file = new File(filePath);
        return file.exists() ? file : null;
    }

    /**
     * 获取应用缓存目录
     *
     * @param context context
     * @return path
     */
    public static String getAppCachePath(Context context) {
        if (context == null) {
            return null;
        }
        File cacheDir = context.getExternalCacheDir();
        return cacheDir != null ? cacheDir.getAbsolutePath() : null;
    }

    /**
     * 创建应用缓存目录下的图片目录, 存在则返回
     *
     * @param context context
     * @return 图片目录
     */
    public static File generateCacheImageCatalog(Context context) {
        String appCachePath = getAppCachePath(context);
        return generateFileCatalog(appCachePath, BaseConstants.APP_IMAGE);
    }

    /**
     * 创建缓存目录下的图片文件, 存在就返回, 不存在则新建
     *
     * @param context  context
     * @param fileName fileName
     * @return 图片文件, 不存在则新建
     */
    public static File generateCacheImageFile(Context context, String fileName) {
        File imageCatalog = generateCacheImageCatalog(context);
        return generateFile(imageCatalog, fileName);
    }

    /**
     * 获取缓存目录下的图片文件, 不存在返回空
     *
     * @param context  context
     * @param fileName fileName
     * @return 图片文件, 不存在则返回空
     */
    public static File getCacheImageFile(Context context, String fileName) {
        File imageCatalog = generateCacheImageCatalog(context);
        return getFile(imageCatalog, fileName);
    }

    /**
     * 创建应用缓存目录下的缩略图目录, 存在则返回, 不存在新建
     *
     * @param context context
     * @return 缩略图目录
     */
    public static File generateCacheThumbnailCatalog(Context context) {
        String appCachePath = getAppCachePath(context);
        return generateFileCatalog(appCachePath, BaseConstants.APP_THUMBNAIL);
    }

    /**
     * 创建应用缓存目录下的缩略图文件, 不存在则新建
     *
     * @param context  context
     * @param fileName fileName
     * @return 缩略图文件, 不存在则新建
     */
    public static File generateCacheThumbnailFile(Context context, String fileName) {
        File thumbnailCatalog = generateCacheThumbnailCatalog(context);
        return generateFile(thumbnailCatalog, fileName);
    }

    /**
     * 获取应用缓存目录下的缩略图文件, 不存在返回空
     *
     * @param context  context
     * @param fileName fileName
     * @return 缩略图文件, 不存在返回空
     */
    public static File getCacheThumbnailFile(Context context, String fileName) {
        File thumbnailCatalog = generateCacheThumbnailCatalog(context);
        return getFile(thumbnailCatalog, fileName);
    }

    /**
     * 创建应用缓存目录下的临时文件目录, 存在则返回
     *
     * @param context context
     * @return 临时文件目录
     */
    public static File generateCacheTemporaryCatalog(Context context) {
        String appCachePath = getAppCachePath(context);
        return generateFileCatalog(appCachePath, BaseConstants.APP_TMP);
    }

    /**
     * 创建应用缓存目录下的临时文件目录文件, 存在则返回
     *
     * @param context  context
     * @param fileName fileName
     * @return 临时文件目录文件, 不存在新建
     */
    public static File generateCacheTemporaryFile(Context context, String fileName) {
        File temporaryCatalog = generateCacheTemporaryCatalog(context);
        return generateFile(temporaryCatalog, fileName);
    }

    /**
     * 获取应用缓存目录下的临时文件目录文件, 不存在返回空
     *
     * @param context  context
     * @param fileName fileName
     * @return 临时文件目录文件, 不存在返回空
     */
    public static File getCacheTemporaryFile(Context context, String fileName) {
        File temporaryCatalog = generateCacheTemporaryCatalog(context);
        return getFile(temporaryCatalog, fileName);
    }

    /**
     * 创建应用缓存目录下的下载目录, 存在则返回
     *
     * @param context context
     * @return 下载目录
     */
    public static File generateCacheDownloadCatalog(Context context) {
        String appCachePath = getAppCachePath(context);
        return generateFileCatalog(appCachePath, BaseConstants.APP_DOWNLOAD);
    }

    /**
     * 创建缓存目录下的下载目录文件, 存在则返回
     *
     * @param context  context
     * @param fileName fileName
     * @return 下载目录文件, 不存在则新建
     */
    public static File generateCacheDownloadFile(Context context, String fileName) {
        File downloadCatalog = generateCacheDownloadCatalog(context);
        return generateFile(downloadCatalog, fileName);
    }

    /**
     * 获取缓存目录下的下载目录文件, 不存在返回空
     *
     * @param context  context
     * @param fileName fileName
     * @return 下载目录文件, 不存在返回空
     */
    public static File getCacheDownloadFile(Context context, String fileName) {
        File downloadCatalog = generateCacheDownloadCatalog(context);
        return getFile(downloadCatalog, fileName);
    }

    /**
     * 获取应用存储目录
     *
     * @return
     */
    public static File getAppStorageCatalog() {
        if (!isSDCardAvailable()) {
            return null;
        }
        File storageRoot = Environment.getExternalStorageDirectory();
        String storagePath = storageRoot.getAbsolutePath();
        return generateFileCatalog(storagePath, BaseConstants.APP_DOWNLOAD);
    }

    /**
     * 创建应用缓存目录下的图片目录, 存在则返回
     *
     * @return 图片目录
     */
    public static File generateStorageImageCatalog() {
        File storageCatalog = getAppStorageCatalog();
        return generateFileCatalog(storageCatalog, BaseConstants.APP_IMAGE);
    }

    /**
     * 创建缓存目录下的图片文件,存在则返回
     *
     * @param fileName fileName
     * @return 图片文件
     */
    public static File generateStorageImageFile(String fileName) {
        File imageCatalog = generateStorageImageCatalog();
        return generateFile(imageCatalog, fileName);
    }

    /**
     * 获取缓存目录下的图片文件,不存在返回空
     *
     * @param fileName fileName
     * @return 图片文件
     */
    public static File getStorageImageFile(String fileName) {
        File imageCatalog = generateStorageImageCatalog();
        return getFile(imageCatalog, fileName);
    }

    /**
     * 创建应用缓存目录下的缩略图目录, 存在则返回
     *
     * @return 缩略图目录
     */
    public static File generateStorageThumbnailCatalog() {
        File storageCatalog = getAppStorageCatalog();
        return generateFileCatalog(storageCatalog, BaseConstants.APP_THUMBNAIL);
    }

    /**
     * 创建应用缓存目录下的缩略图文件, 存在则返回
     *
     * @param fileName fileName
     * @return 缩略图文件
     */
    public static File generateStorageThumbnailFile(String fileName) {
        File thumbnailCatalog = generateStorageThumbnailCatalog();
        return generateFile(thumbnailCatalog, fileName);
    }

    /**
     * 获取应用缓存目录下的缩略图文件, 不存在返回空
     *
     * @param fileName fileName
     * @return 缩略图文件
     */
    public static File getStorageThumbnailFile(String fileName) {
        File thumbnailCatalog = generateStorageThumbnailCatalog();
        return getFile(thumbnailCatalog, fileName);
    }

    /**
     * 创建应用缓存目录下的临时文件目录, 存在则返回
     *
     * @return 临时文件目录
     */
    public static File generateStorageTemporaryCatalog() {
        File storageCatalog = getAppStorageCatalog();
        return generateFileCatalog(storageCatalog, BaseConstants.APP_TMP);
    }

    /**
     * 创建应用缓存目录下的临时文件目录文件, 存在则返回
     *
     * @param fileName fileName
     * @return 临时文件目录文件
     */
    public static File generateStorageTemporaryFile(String fileName) {
        File temporaryCatalog = generateStorageTemporaryCatalog();
        return generateFile(temporaryCatalog, fileName);
    }

    /**
     * 获取应用缓存目录下的临时文件目录文件, 不存在返回空
     *
     * @param fileName fileName
     * @return 临时文件目录文件
     */
    public static File getStorageTemporaryFile(String fileName) {
        File temporaryCatalog = generateStorageTemporaryCatalog();
        return getFile(temporaryCatalog, fileName);
    }

    /**
     * 创建应用缓存目录下的下载目录, 存在则返回
     *
     * @return 下载目录
     */
    public static File generateStorageDownloadCatalog() {
        File storageCatalog = getAppStorageCatalog();
        return generateFileCatalog(storageCatalog, BaseConstants.APP_DOWNLOAD);
    }

    /**
     * 创建缓存目录下的下载目录文件, 存在则返回
     *
     * @param fileName fileName
     * @return 下载目录文件
     */
    public static File generateStorageDownloadFile(String fileName) {
        File downloadCatalog = generateStorageDownloadCatalog();
        return generateFile(downloadCatalog, fileName);
    }

    /**
     * 获取缓存目录下的下载目录文件, 不存在返回空
     *
     * @param fileName fileName
     * @return 下载目录文件
     */
    public static File getStorageDownloadFile(String fileName) {
        File downloadCatalog = generateStorageDownloadCatalog();
        return getFile(downloadCatalog, fileName);
    }

    /**
     * 根据名字检查该文件是否存在于应用缓存中
     * 缓存目录作为根目录
     *
     * @param context  context
     * @param catalog  该文件到根目录下的相对路径
     * @param fileName 文件名
     * @return true存在
     */
    public static boolean checkFileExistsFromCache(Context context, String catalog, String fileName) {
        if (context == null) {
            Log.e(TAG, "checkImageExistsFromCache : context为空,请检查!");
            return false;
        }
        String path = getAppCachePath(context);
        return checkFileExists(path, catalog, fileName);
    }

    /**
     * 根据名字检查该文件是否存在于应用缓存中
     * SD卡目录作为根目录
     *
     * @param catalog  该文件到根目录下的相对路径
     * @param fileName 文件名
     * @return true存在
     */
    public static boolean checkFileExistsFromStorage(String catalog, String fileName) {
        File file = getAppStorageCatalog();
        if (file == null) {
            return false;
        }
        String path = file.getAbsolutePath();
        return checkFileExists(path, catalog, fileName);
    }

    /**
     * 根据文件名检查文件是否存在
     *
     * @param rootPath 根路径, 可为空
     * @param catalog  该文件到根目录下的相对路径, 若根路径为空, 则这个位全路径
     * @param fileName 文件名不可为空,根路径或目录其中一个可为空
     * @return true存在
     */
    public static boolean checkFileExists(@Nullable String rootPath, String catalog, String fileName) {
        if ((TextUtils.isEmpty(rootPath) && TextUtils.isEmpty(catalog)) || TextUtils.isEmpty(fileName)) {
            return false;
        }
        StringBuilder file = new StringBuilder();
        if (!TextUtils.isEmpty(rootPath)) {
            file.append(rootPath).append(File.separator);
        }
        file.append(catalog).append(File.separator).append(fileName);
        return new File(file.toString()).exists();
    }

    /**
     * 删除文件，如果是文件夹会递归删除子文件，
     * 传入null值或者文件不存在
     *
     * @param file file
     * @return true-成功删除文件
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        if (!file.exists()) {
            return true;
        }
        boolean result = true;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        if (!deleteFile(subFile)) {
                            result = false;
                        }
                    } else {
                        LogUtil.d("xiaode", "delete file " + file);
                        if (!subFile.delete()) {
                            result = false;
                        }
                    }
                }
            }
        }
        LogUtil.d("xiaode", "delete file " + file);
        if (!file.delete()) {
            result = false;
        }

        return result;
    }

    public static long calculateFileSize(File file) {
        if (file == null) {
            return 0;
        }

        if (!file.exists()) {
            return 0;
        }

        long result = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        result += calculateFileSize(subFile);
                    } else {
                        result += subFile.length();
                    }
                }
            }
        }
        result += file.length();
        return result;
    }

    /**
     * 删除空目录
     *
     * @param dir 将要删除的目录路径
     */
    private static boolean deleteFile(@NonNull String dir) {
        return new File(dir).delete();
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return 删除成功
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
