/**
 * @author rshirts
 * @createdOn 2/27/2023 at 4:45 PM
 * @projectName LibraryV4
 * @packageName csc150.library.models;
 */
package csc150.library.controllers;

import java.io.*;

public class FileController {
    //TODO make sure this works
    public static final String ROOT_FOLDER = "BookShelf";
    public static final String Favorites = "Favorites";
    public static final String hasRead = "HasRed";
    public static final String planToRead = "PlaneToRead";


    /**
     * write to a file
     * @param fileName the name of the file you would like to write to d
     * @param contents what will be written to the file
     * @param append are you going to append to an existing file
     */
    public void writeFile(String fileName, String contents, boolean append) {
        //checks if root folder exits
        createRootFolder();
        BufferedWriter write = null;
        try {
            //writes to the file
            write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getFileName(fileName), append)));
            write.write(contents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // closes the file
            try {
                if(write == null){
                    return;
                }
                write.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * Delete something from a file
     * @param fileName the name of the file you wish to delete something from
     * @param contentToDelete the content that you want to delete from the file
     */
    public void deleteFromFile(String fileName, String contentToDelete){
        BufferedReader read = null;
        String content = "";
        try{
            //reads the file per line and appends to a bufferedReader
            read = new BufferedReader(new InputStreamReader(new FileInputStream(getFileName(fileName))));
            String currentLine = "";
            //removes the line
            while ((currentLine = read.readLine()) != null) {
                if(currentLine.equals(contentToDelete)){
                    continue;
                }
                content += read.readLine() + "\r\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //closes the file
                read.close();
            }catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        //rewrite the file without the removed line
        writeFile(fileName, content, false);
    }

    /**
     * Reads the content of a file
     * @param fileName the name of the file you want to read
     * @return the contents of a file
     */
    public String readFile(String fileName){
        BufferedReader read = null;
        String content = "";
        try{
            //reads the file per line and appends to a bufferedReader
            read = new BufferedReader(new InputStreamReader(new FileInputStream(getFileName(fileName))));
            while (read.ready()) {
                content += read.readLine() + "\r\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //closes the file
                read.close();
            }catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        // returns content of file
        return content;
    }

    /**
     * Gets the path of a file you are trying to use
     * @param fileName the name of the file you are trying to use
     * @return the path of the file you are trying to use
     */
    private String getFileName(String fileName){
        // file path
        return ROOT_FOLDER + "\\" + fileName + ".txt";
    }

    /**
     * will make a root folder if does not exist
     */
    private void createRootFolder(){
        File rootFolder = new File(ROOT_FOLDER);
        if(!rootFolder.exists()){
            //makes directory
            try {
                rootFolder.mkdir();
            }catch (SecurityException e){
                System.out.println(e.getMessage());
            }
        }
    }
}