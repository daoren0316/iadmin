package cc.kokoko.server.common.util;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import magick.CompositeOperator;
import magick.CompressionType;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cc.kokoko.server.commons.exception.ServiceException;
import cc.kokoko.server.commons.util.MMGlobals;


/**
 * @author robin 图片处理工具类
 * 
 */
public class ImageUtil
{

	private static final int	BUFFER_SIZE	= 16 * 1024;

	public static String path(long id)
	{
		return File.separator + (id % 100) + File.separator
			+ (id % 10000 / 100) + File.separator + (id / 10000)
			+ File.separator;
	}
	


	/**
	 * @author robin
	 * @param src,dst,width,height
	 * @param 源文件，目标文件，需缩略图宽度，需缩略图高度
	 * 
	 */
	public static void makeThumbnail(File src, File dst, int width, int height,
			boolean watermark) throws Exception
	{
		try
		{
			ImageInfo info = new ImageInfo(src.getPath());
			MagickImage image = new MagickImage(info);
			Dimension dim = resize(image, width, height);
			image = image.scaleImage((int) dim.getWidth(), (int) dim
					.getHeight());
			image.setFileName(dst.getPath());
			image.writeImage(info);
			image.destroyImages();
			// 产生带水印的图
			if (watermark)
			{
				File waterFile = new File(StdLib.getBaseServletRealPath()
					+ MMGlobals.getProperty("WATERMARK_FILE"));
				makeWatermark(dst, waterFile);
			}

		} catch (Exception e)
		{

			throw new Exception(e);
		}

	}

	/**
	 * process 计算缩略图的合理大小比例
	 * 
	 * @author robin
	 * @param iamge,toWidth,toHeight
	 * @param 源文件,需缩略图宽度，需缩略图高度
	 * 
	 */
	public static Dimension resize(MagickImage image, int toWidth, int toHeight)
	{

		try
		{
			Dimension imageDim = image.getDimension();
			double imageWidth = imageDim.getWidth(); // 原图实际宽度
			double imageHeight = imageDim.getHeight(); // 原图实际高度
			double zoomout;
			// 确定缩放比
			if (imageWidth >= imageHeight)// 宽类型图片
			{
				zoomout = toWidth / imageWidth;

			} else
			// 高类型图片
			{
				zoomout = toHeight / imageHeight;
			}
			double realWidth = zoomout * imageWidth;
			double realHeight = zoomout * imageHeight;
			return new Dimension((int) realWidth, (int) realHeight);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}
	


	/**
	 * process 生成水印图
	 * 
	 * @author robin
	 * @param original,watemark
	 * @param 源文件,水印文件
	 * 
	 */
	public static void makeWatermark(File original, File watemark)
	{
		String original_filename = original.getPath();
		String watermark_filename = watemark.getPath();
		String result_filename = original.getPath();
		try
		{
			ImageInfo ii_orig = new ImageInfo();
			ii_orig.setFileName(original_filename);
			MagickImage mi_orig = new MagickImage(ii_orig);
			Dimension d_orig = mi_orig.getDimension();
			double orig_width = d_orig.getWidth();
			double orig_height = d_orig.getHeight();

			ImageInfo ii_wm = new ImageInfo();
			ii_wm.setFileName(watermark_filename);
			MagickImage mi_wm = new MagickImage(ii_wm);
			Dimension d_wm = mi_wm.getDimension();
			double wm_width = d_wm.getWidth();
			double wm_height = d_wm.getHeight();

			mi_orig.compositeImage(CompositeOperator.DissolveCompositeOp,
				mi_wm, (int) (orig_width - wm_width) / 2,
				(int) (orig_height - wm_height) / 2);

			mi_orig.setCompression(CompressionType.JPEGCompression);

			final ImageInfo ii_result = new ImageInfo();
			ii_result.setQuality(60);
			mi_orig.setFileName(result_filename);
			mi_orig.writeImage(ii_result);
			mi_orig.destroyImages();
			mi_wm.destroyImages();
		} catch (MagickException me)
		{
			me.printStackTrace();
		}

	}

	public static void copy(File src, File dst) throws ServiceException 
	{
		try
		{
			InputStream in = null;
			OutputStream out = null;
			int count=0;

			try
			{
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);

				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((count=in.read(buffer)) > 0)
				{
					out.write(buffer,0,count);
				}
			} finally
			{
				if (null != in)
				{
					in.close();
				}
				if (null != out)
				{
					out.close();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new ServiceException("拷贝文件失败："+e.getMessage(),e);
		}
	}
	
	
	/**
	 * 创建目录
	 * @param baseDir web发布目录
	 * @param childDirs 子目录，多个目录之间用"/"分开
	 */
	public static void makeDirectory(String baseDir, String childDirs){
		String[] dirs = childDirs.split("/");
		try {
			File dir = null;
			
			for (int i = 0; i < dirs.length; i++) {
				String endDir = baseDir + dirs[i];
				dir = new File(endDir);
				baseDir = baseDir+dirs[i]+"/";
				// 目录不存在就创建
				if(!dir.exists()){
					dir.mkdir();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param toDel
	 * @return
	 */
	public synchronized static boolean delDir(File toDel){
		if(!toDel.exists()) return true;
//		System.out.println("to del :"+toDel.getPath()+" "+toDel.isFile());
		if(toDel.isFile()){
			return toDel.delete();
		}
		else {
			File[] subList=toDel.listFiles();
			
			for(File temp:subList){
//				System.out.println("sub del: "+temp.getPath());
				delDir(temp);
			}
			return toDel.delete();
		}
	}
	
	public static void main(String[] args)
	{
	//	ImageUtil.copy(new File("c:/cmdTestHttp.txt"), new File("c:/cmdTestHttp_copy.txt"));
		boolean ret=ImageUtil.delDir(new File("D:/新建文件夹"));
		System.out.println("ret: "+ret);
		try {
			makeThumbnail(new File("D:/Koala.jpg"), new File("D:/Koala_small.jpg"), 200, 200,
					false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path(485142563));
	}	
}
