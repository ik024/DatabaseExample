package com.example.databaseexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {

	TextView tv;
	FileInputStream fis = null;
	File folder = null;

//hello i am second activity of branch

//i am from master -- branch
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		tv = (TextView) findViewById(R.id.textview);
	}

	public void loadFromInternalStorage(View view) {
		folder = getFilesDir();
		String data = readData(folder, MainActivity.TEXT_FILE_NAME_INTERNAL_STORAGE);
		tv.setText(data);
	}

	public void loadFromPrivateCache(View view) {
		folder = getCacheDir();
		String data = readData(folder, MainActivity.TEXT_FILE_NAME_CACHE_PRIVATE);
		tv.setText(data);
	}

	public void loadFromPublicCache(View view) {
		folder = getExternalCacheDir();
		String data = readData(folder, MainActivity.TEXT_FILE_NAME_CACHE_PUBLIC);
		tv.setText(data);
	}

	public void loadFromPrivateExternalStorage(View view) {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			folder = getExternalFilesDir("com.example.databaseexample");
			String data = readData(folder, MainActivity.TEXT_FILE_NAME_EXT_STORAGE_PRIVATE);
			tv.setText(data);
		}
	}

	public void loadFromPublicExternalStorage(View view) {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			String data = readData(folder, MainActivity.TEXT_FILE_NAME_EXT_STORAGE_PUBLIC);
			tv.setText(data);
		}
	}

	public void goPrevious(View view) {
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}

	private String readData(File folder, String fileName){
		File myFile = new File(folder, fileName);
		StringBuffer buffer = new StringBuffer();
		try {
			fis = new FileInputStream(myFile);
			int read = -1;
			while( (read = fis.read()) != -1){
				buffer.append((char)read);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

}
