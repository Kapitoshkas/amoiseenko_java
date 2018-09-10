package ru.stqa.pft.rest.appmanager;


import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
  private Properties properties;

  private String browser;
  ;


  public ApplicationManager() {
    properties = new Properties();
    }


  public void init() throws IOException {

    //String target = System.getProperty("target", "local");
    //properties.load(new FileReader(new File(String.format("mantistests/src/test/resources/%s.properties", target))));


  }



  public void stop() {

  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

}
