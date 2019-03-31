/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author Test
 * @param <E>
 * @param <K>
 * @param <V>
 */
public class Genertics<E,K,V,T> implements Serializable{
     
       public boolean SaveArrayObject(E l,String filename) throws FileNotFoundException, IOException{
        //T --Type
        //K -- key
        //E--elements
        //N--Number
        //V--Value
        File toWrite  = new File(filename);
        if(!toWrite.exists()) {
                try {
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();
                }catch(Exception e){
                    System.out.println("directory :" + toWrite.getAbsolutePath() + " Created");
                }
        }

        FileOutputStream fos =null;
        ObjectOutputStream out = null;
      
  try{     
        fos = new FileOutputStream(filename);
        out = new ObjectOutputStream(fos);
        out.writeObject(l);
          JOptionPane.showMessageDialog(null, "Saving....","saved",0);
       return true;
        
  }catch(FileNotFoundException x){
//  JOptionPane.showMessageDialog(null, "erroe occured ");
      System.out.println("file was not found");
  }catch(IOException e){
      
    }finally{
               if( fos!=null){
               try{
               fos.close();
               }catch(IOException ex){
               
                   System.out.println("Failed to close");
               }
               }
               }
        
  
//  JOptionPane.showMessageDialog(null, "erroe occured  could not save ","Error",8);
System.out.println("Null Returned");  
return false;
    
       
       
      
       }  
    public E getArrayObject(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fin =null;
        ObjectInputStream in = null;
      
         File toWrite  = new File(filename);
        if(!toWrite.exists()) {
                try {
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();
                }catch(Exception e){
                    System.out.println("directory :" + toWrite.getAbsolutePath() + " Created");
                }
        }
       try{
            fin = new  FileInputStream(filename); 
            in = new ObjectInputStream(fin);
            E obj =(E)in.readObject();
         //    JOptionPane.showMessageDialog(null, "Found....");
            return obj;
       }catch(   FileNotFoundException e){
           System.out.println("Not file not found\n");
       }catch(IOException r){
         
           
       }finally {
           if(fin != null){
               try{
                   fin.close();
               }catch(IOException r1){
                   
               }
           }
       }
        return null;
    }
    
    /**
     *
     * @param l
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public  boolean SaveMapObject(Map<K,V> l,String filename) throws FileNotFoundException, IOException{
        //T --Type
        //K -- key
        //E--elements
        //N--Number
        //V--Value
        File toWrite  = new File(filename);
        if(!toWrite.exists()) {
                try {
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();
                }catch(Exception e){
                    System.out.println("directory :" + toWrite.getAbsolutePath() + " Created");
                }
        }
        FileOutputStream fos =null;
        ObjectOutputStream out = null;
    
  try{     
        fos = new FileOutputStream(filename);
        out = new ObjectOutputStream(fos);
              
          out.writeObject(l);
          out.flush();
          out.close();
         System.out.println("Saved ");
          return true;
        
  }catch(FileNotFoundException x){
      System.out.println(" file not found occured ");
  }catch(IOException e){
      
    }finally{
               if( fos!=null){
               try{
               fos.close();
               }catch(IOException ex){
               
               
               }
               }
               }
        
  
        System.out.println( "erroe occured  could not save ");
  return false;
       
       
       
    }
    
    
    public Object getMapObject(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
       
        
          File toWrite  = new File(filename);
        if(!toWrite.exists()) {
                try {
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();
                }catch(Exception e){
                    System.out.println("directory :" + toWrite.getAbsolutePath() + " Created");
                }
        }
        FileInputStream fin =null;
        ObjectInputStream in = null;
      
        
       try{
            fin = new  FileInputStream(filename); 
            in = new ObjectInputStream(fin);
             Object obj =(Map<K,V>) in.readObject();
            
           System.out.println("Found....");
            return obj;
       }catch(   FileNotFoundException e){
           System.out.println("exceptiion not found" + e);
       }catch(IOException r){
           
           
       }finally {
           if(fin != null){
               try{
                   fin.close();
               }catch(IOException r1){
                   System.out.println("failed to close");
               }
           }
       }
        System.out.println("null Returned");
        return null;
    }
    
    
    
    
    
    
    
       public boolean saveAnyobject(T obj, String filename) {
             File toWrite  = new File(filename);
        if(!toWrite.exists()) {
                try {
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();
                }catch(Exception e){
                    System.out.println("directory :" + toWrite.getAbsolutePath() + " Created");
                }
        }
        ObjectOutputStream out = null;
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(obj);
            out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
            System.out.println("Failed to Write " + ex);
                  } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    
                }
            }
        }

        return false;

    }
      public Object getAnyObject(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
     
            File toWrite  = new File(filename);
        if(!toWrite.exists()) {
                try {
                    toWrite.getAbsoluteFile().getParentFile().mkdir();
                    toWrite.createNewFile();
                }catch(Exception e){
                    System.out.println("directory :" + toWrite.getAbsolutePath() + " Created");
                }
        }
          FileInputStream fin =null;
        ObjectInputStream in = null;
      
        
       try{
            fin = new  FileInputStream(filename); 
            in = new ObjectInputStream(fin);
             Object obj =(T) in.readObject();
            
           System.out.println("Found....");
            return obj;
       }catch(   FileNotFoundException e){
           System.out.println("exceptiion not found" + e);
       }catch(IOException r){
           
           
       }finally {
           if(fin != null){
               try{
                   fin.close();
               }catch(IOException r1){
                   System.out.println("failed to close");
               }
           }
       }
        System.out.println("null Returned");
        return null;
    }
    
    
    
    
    
}


    
    
    
    


