package Game;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class SaveLoad {

  // Implement save, if you close the game, a json file will show out "Game1", "Game2", "Game3"
  public static void save(Serializable data, String fileName){
    try{
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      ObjectMapper mapper = new ObjectMapper();
      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
      fileOutputStream.write(jsonStr.getBytes());
      System.out.println("saved");
      fileOutputStream.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // not yet implement, feel free to change it
  public static <T> T load(String fileName, Class<T> clazz) {
    try {
      FileInputStream fileInputStream = new FileInputStream(fileName);
      ObjectMapper mapper = new ObjectMapper();
      T data = mapper.readValue(fileInputStream, clazz);
      fileInputStream.close();
      return data;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
