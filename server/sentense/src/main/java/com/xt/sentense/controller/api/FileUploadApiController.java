package com.xt.sentense.controller.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.nutz.lang.random.R;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xt.sentense.constant.Const;
import com.xt.sentense.constant.Res;
/**
 * 文件上传api接口类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/file")
public class FileUploadApiController {
	/**
	 * 文件上传保存路径
	 */
	private String path = Const.APP_FILE + "/file";
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload.api")
	@ResponseBody
	public Res upload(
			@RequestParam("file")MultipartFile file){
		if(file.isEmpty()){
			return Res.NEW().code(Res.ERROR).msg("文件为空");
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName = R.sg(3).next() + sf.format(new Date()).replaceAll(":", "") + R.captchaChar(6);
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}
		try {
			file.transferTo(new File(folder, fileName));
			return Res.NEW().code(Res.SUCCESS).msg("上传成功").data("/file/getImage.jpg?name=" + fileName);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Res.NEW().code(Res.ERROR).msg("上传失败: " + e.getMessage());
		}
	}
	/**
	 * 获取图片文件
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="getImage.jpg", produces={MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE})
	@ResponseBody
	public byte[] getImage(String name) throws IOException{
		File file = new File(path + "/" + name);
		FileInputStream fis = new FileInputStream(file);
		byte[] bs = new byte[fis.available()];
		fis.read(bs, 0, fis.available());
		return bs;
	}
}
