package com.example.dummy.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class FileWorker<T> {
    public void write(String file,T content) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer= new StringWriter();
        try(FileWriter wr = new FileWriter(file, true)) {
            mapper.writeValue(writer, content);
            wr.write(String.valueOf(writer));
            writer.append('\n');
            writer.flush();
        }
            catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void clear(String file) {
        File f = new File(file);
        f.delete();
    }

    public ArrayList<User> readUsersFromFile(String file) {

        ArrayList<User> arr = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                arr.add(mapper.readValue(line, User.class));
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
    public ArrayList<User> readUsersFromFile() {

        String file = "Users.txt";
        ArrayList<User> arr = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){

                arr.add(mapper.readValue(line, User.class));
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
}
