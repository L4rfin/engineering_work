package ekg.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import ekg.entity.ResultsEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class DataFolderService {

    private final String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
    File dataFolder = new File((System.getProperty("user.home") + File.separator + "Desktop"),"data");

    public void createFileStructure(Long id) {
        if (!dataFolder.exists()) {
            if (dataFolder.mkdir()) {
                System.out.println("Folder 'data' został utworzony.");
            } else {
                System.out.println("Nie udało się utworzyć folderu 'data'.");
                return;
            }
        }
        File jsonFile = new File(dataFolder, "user" + id + ".json");
        try {
            if (jsonFile.createNewFile()) {
                System.out.println("Plik 'user.json' został utworzony.");
                eraseData(id);
            } else {
                System.out.println("Plik 'user.json' już istnieje.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public DataReader readDataFileOf(Long id){

        String fileName =  "user" + id + ".json";
        File file = new File(dataFolder,fileName);
        if (!file.exists()){
            createFileStructure(id);
        }
        DataReader data;
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(file, DataReader.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
    public void eraseData(Long id){
        String filePath = dataFolder.getPath()+"/user"+id+".json";
        try {
            System.out.println(filePath);
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.append("{\"x\":[]," + "\"y\":[]}");
            fileWriter.close();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    public void RemoveUserDataFile(long id){
        String filePath = dataFolder.getPath()+"/user"+id+".json";
        try{
            File file = new File(filePath);
            System.out.println(file.exists());
            if(file.delete()) System.out.println("user data file was successful deleted");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}