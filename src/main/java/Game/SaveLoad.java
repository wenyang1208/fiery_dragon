package Game;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.Serializable;

public class SaveLoad {

  public static void save(Serializable data, String fileName){
    try{
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      ObjectMapper mapper = new ObjectMapper();
      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
      fileOutputStream.write(jsonStr.getBytes());
      System.out.println("saved");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
