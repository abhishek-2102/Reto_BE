package com.niit.reto.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Imageupload {
	
	String path = "E:\\DT porjects\\Project 2\\Reto_FE\\WebContent\\resources\\Images\\";
	
	public void upload(String userid,MultipartFile photo){
		String photoname=userid+".jpg";
		String filename=path+photoname;
		File file=new File(filename);
		try {
			byte[] byt= photo.getBytes();
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
			bos.write(byt);
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
