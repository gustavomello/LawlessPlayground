package org.example6.example6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SerializableData {
	public String savePath;
	public HashMap<Integer,HashMap<Integer,Long>> ChunkLastRefreshTime
		= new HashMap<Integer,HashMap<Integer,Long>>();
	public HashMap<Integer,HashMap<Integer,Long>> ChunkLastInteractTime
		= new HashMap<Integer,HashMap<Integer,Long>>();
	public HashMap<Integer,HashMap<Integer,Integer>> ChunkCurrentInteractCount
		= new HashMap<Integer,HashMap<Integer,Integer>>();

	public SerializableData(String path) {
		this.savePath = path;
	}
	
	public void save()
	{
		saveHashMap(ChunkLastRefreshTime, 
				this.savePath + File.separator + "chunks_refresh.dat");
		saveHashMap(ChunkLastInteractTime, 
				this.savePath + File.separator + "chunks_last_interact.dat");
		saveHashMap(ChunkCurrentInteractCount, 
				this.savePath + File.separator + "chunks_count_interact.dat");
		
	}

	@SuppressWarnings("unchecked")
	public void reload()
	{
		ChunkLastRefreshTime = (HashMap<Integer, HashMap<Integer, Long>>) 
			loadHashMap(this.savePath + File.separator + "chunks_refresh.dat");
		ChunkLastInteractTime = (HashMap<Integer, HashMap<Integer, Long>>)
			loadHashMap(this.savePath + File.separator + "chunks_last_interact.dat");
		ChunkCurrentInteractCount = (HashMap<Integer, HashMap<Integer, Integer>>)
			loadHashMap(this.savePath + File.separator + "chunks_count_interact.dat");
	}
	
	public static void saveHashMap(Object obj, String path)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Object loadHashMap(String path)
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			Object result = ois.readObject();
			ois.close();
			//you can feel free to cast result to HashMap<String, Integer> if you know there's that HashMap in the file
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
