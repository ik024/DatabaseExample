package com.example.databaseexample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

//test branch comment1 - lol
//this comment is for merging purpose added in test branch
//cherry-pick this comment
//rebase commit

	public final static String TEXT_FILE_NAME_INTERNAL_STORAGE = "internalStorageFile1.txt";
	public final static String TEXT_FILE_NAME_CACHE_PRIVATE = "cachePrivateStorageFile1.txt";
	public final static String TEXT_FILE_NAME_CACHE_PUBLIC = "cachePublicStorageFile1.txt";
	public final static String TEXT_FILE_NAME_EXT_STORAGE_PRIVATE = "privateExternalStorageFile1.txt";
	public final static String TEXT_FILE_NAME_EXT_STORAGE_PUBLIC = "publicExternalStorageFile1Ismail Khan.txt";

	private FileOutputStream fos = null;// to write data to file
	File folder;
	EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edittext = (EditText) findViewById(R.id.editText1);

	}

	public void storeInInternalFile(View view) {
		folder = getFilesDir();
		writeData(folder, TEXT_FILE_NAME_INTERNAL_STORAGE);
	}

	public void storePrivateDataInCache(View view) {
		folder = getCacheDir();
		writeData(folder, TEXT_FILE_NAME_CACHE_PRIVATE);
	}

	public void storePublicDataInCache(View view) {
		folder = getExternalCacheDir();
		writeData(folder, TEXT_FILE_NAME_CACHE_PUBLIC);
	}

	public void storePrivateDataInExternalStorage(View view) {
		folder = getExternalFilesDir("com.example.databaseexample");
		//get the state of the external storage
		String state = Environment.getExternalStorageState();
		//make sure the external storage in mounted and writeble
		if(Environment.MEDIA_MOUNTED.equals(state) ){
			writeData(folder, TEXT_FILE_NAME_EXT_STORAGE_PRIVATE);
		}else{
			Toast.makeText(this, "Problem accessing external storage in your device", Toast.LENGTH_LONG).show();
		}

	}

	public void storePublicDataInExternalStorage(View view) {
		folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		//get the state of the external storage
		String state = Environment.getExternalStorageState();
		//make sure the external storage in mounted and writeble
		if(Environment.MEDIA_MOUNTED.equals(state) ){
			writeData(folder, TEXT_FILE_NAME_EXT_STORAGE_PUBLIC);
		}else{
			Toast.makeText(this, "Problem accessing external storage in your device", Toast.LENGTH_LONG).show();
		}

	}

	public void gotoNextActivity(View view) {
		startActivity(new Intent(getApplicationContext(), SecondActivity.class));
	}

	private void serverTestFunction(){

	}

	private void writeData(File folder, String fileName){
		String data = edittext.getText().toString();
		if (!data.trim().isEmpty()) {
			File myFile = new File(folder, fileName);
			try {
				fos = new FileOutputStream(myFile);
				fos.write(data.getBytes());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.e(fileName, "File not found");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(fileName, "IO Exception");
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.e(fileName, "Closing IO Exception");
						e.printStackTrace();
					}
				}
				Toast.makeText(this, "Data Written Sccessfully  To: " + folder,
						Toast.LENGTH_LONG).show();
				Log.d(fileName, ""+folder);
			}
		} else {
			Toast.makeText(this, "Enter Some data", Toast.LENGTH_SHORT).show();
		}
	}

	private void testFunction(){
	    //Removed something wierd
	    //This is a test function
	    doSomeWierdStuff();

    }
}
