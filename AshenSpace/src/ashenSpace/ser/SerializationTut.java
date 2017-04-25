package ashenSpace.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ashenSpace.game.Game;

public class SerializationTut {	
	
	public static void save(Serializable objectToSerialize){
		
		FileOutputStream fos = null;
		
		try{
			fos = new FileOutputStream(Game.createDataFolder() + Game.fileTut);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objectToSerialize);
			System.out.println(Game.createDataFolder() + Game.fileTut);
			System.out.println(objectToSerialize);
			oos.flush();
			oos.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static fileInfoTut load(){
		if(checkFileExists()){
			FileInputStream fis = null;
			fileInfoTut file = null;
			try{
				
				fis = new FileInputStream(Game.createDataFolder() + Game.fileTut);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				file = (fileInfoTut) ois.readObject();
				
				ois.close();
				
			}catch(ClassNotFoundException | IOException e){
				e.printStackTrace();
			}
			return file;
			
		}
		return null;
	}
	
	public static boolean checkFileExists(){
		return new File(Game.createDataFolder() + Game.fileTut).isFile();
	}
	
	public static boolean deleteSaveFile(){
		if(!checkFileExists()){
			System.err.println("File: " + Game.createDataFolder() + Game.fileTut + " does not exist");
			return true;
		}
		
		File toDelete = new File(Game.createDataFolder() + Game.fileTut);
		
		if(toDelete.canWrite()){
			return toDelete.delete();
		}
		
		System.err.println("File: " + Game.createDataFolder() + Game.fileTut + " is write-protected");
		return false;
	}
	
}
