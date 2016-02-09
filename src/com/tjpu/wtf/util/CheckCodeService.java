/**
 *  ClassName: CheckCodeService.java
 *  Created on 2011-12-11 下午03:28:01
 *  Copyrights 2010 csdn-soft.com All rights reserved.
 *  site: http://www.csdn-soft.com
 *  email: qjyong@gmail.com
 */
package com.tjpu.wtf.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 生成图片验证码的业务逻辑类
 * @author qiujy
 */
public class CheckCodeService {
	private static String[] src = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private Random random = new Random();
	
	public String randomString(int num){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < num; i++){
			int index = random.nextInt(src.length); // [0,36)
			sb.append(src[index]);
		}
		return sb.toString();
	}
	
	/**
	 * 用指定字符串生成指定宽度和高度的图片，并输出到指定的输出流中
	 * @param src 源字符串
	 * @param out 目标输出流
	 * @param width 宽度(px)
	 * @param height 高度(px)
	 * @throws IOException IO异常
	 */
	public void renderImage(String src, OutputStream out, int width, int height) throws IOException{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //内存图片
		
		Graphics g = img.getGraphics(); //获取画笔
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);//画白色背景

		//画边框
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		
		//画干扰圈
	    for(int i=0;i<20;i++){
	    	g.setColor(randColor(150, 250));
	    	g.drawOval(random.nextInt(width-10), random.nextInt(height-10), 5+random.nextInt(10), 5+random.nextInt(10));
	    }
		
		//画字符串
		g.setColor(randColor(10, 255));
		g.setFont(new Font("Arial", Font.ITALIC, 22));
		g.drawString(src, 8, height - 3);
		
		ImageIO.write(img, "jpg", out);
	}
	
	/**
	 * 产生一个随机颜色
	 * @param min
	 * @param max
	 * @return
	 */
	private Color randColor(int min, int max){
		if(min > 255){
			min = 255;
		}
		if(max > 255){
			max = 255;
		}
		int r = min + random.nextInt(max - min);
		int g = min + random.nextInt(max - min);
		int b = min + random.nextInt(max - min);
		
		return new Color(r, g, b);
	}
	
	
	public static void main(String[] args) {
		CheckCodeService ccService =new CheckCodeService();
		
		String str=  ccService.randomString(4);
		System.out.println(str);
	}
}
