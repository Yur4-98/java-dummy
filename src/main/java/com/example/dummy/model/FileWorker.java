package com.example.dummy.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;

public class FileWorker<T> {
    @Value("${file.write}")
    private String writeFile;


    public void write(T content) {

        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer= new StringWriter();
        try(FileWriter wr = new FileWriter(writeFile, true)) {
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


    public User readUsersFromFile(String login) {//считать в начале + выбор нужного

        String file = "Users.txt";//файл в ресурсах
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

        for (int i = 0; i < arr.toArray().length; i++) {
            if(arr.get(i).getLogin().equals(login))
                return arr.get(i);
        }

        return null;
    }
}
