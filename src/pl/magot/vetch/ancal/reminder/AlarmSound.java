
package pl.magot.vetch.ancal.reminder;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.net.Uri;
import android.content.res.AssetManager;


public class AlarmSound
{
	//fields
	private Context context = null;
	
	//fields
	private final String strAlarmFileName = "ancalalarm.mp3";
	private String strFilesPath = ""; 
	private File alarmFile = null;
    
	//methods
	public AlarmSound(Context context)
	{
		this.context = context;
		strFilesPath = String.format("//data//data//%s//files//", this.context.getPackageName());		
		alarmFile = new File(strFilesPath + strAlarmFileName);
		if (!alarmFile.exists())
			copyAlarmSoundToFileSystem();
	}
		
	private int getStreamFileLength(InputStream stream)
	{
		if (stream != null)
		{
			int iSize = 0;
			try
			{
				while (stream.read() != -1)
					iSize ++;
				return iSize;
			} catch (IOException e) {
				return -1;
			}														
		}				
		return -1;
	}
	
	private boolean copyBufferToFile(byte[] buffer, int iSize, String sTargetFileName)
	{
		if (buffer != null)
		{
			try
			{
				FileOutputStream fileOut = context.openFileOutput(sTargetFileName, Context.MODE_WORLD_READABLE);					
				if (fileOut != null)
				{
					fileOut.write(buffer, 0, iSize);
					fileOut.flush();								
					fileOut.close();
					return true;
				}									
			} catch (FileNotFoundException e1) {
			} catch (IOException e) {					
			}							
		}
		return false;
	}

	private boolean copyAlarmSoundToFileSystem()
	{
		boolean bResult = false;
		AssetManager assets = context.getAssets();
		if (assets != null)
		{				
			try
			{
				InputStream stream = assets.open(strAlarmFileName);
				if (stream != null)
				{
					final int iStreamFileSize = getStreamFileLength(stream);
					if (iStreamFileSize != -1)
					{
						//create buffer
						byte[] buffer = new byte[iStreamFileSize];
						//read file to buffer
						stream.read(buffer, 0, iStreamFileSize);
						//save data to file
						bResult = copyBufferToFile(buffer, iStreamFileSize, strAlarmFileName);
					}
					stream.close();
				}					
			} catch (IOException e) {
			}
			assets.close();
		}			
		return bResult;
	}
	
	public Uri getUri()
	{
		if (alarmFile.exists())
		{
			return Uri.fromFile(alarmFile);
		} else {
			return null;
		}
	}

}
