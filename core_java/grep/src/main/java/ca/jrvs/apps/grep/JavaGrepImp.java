package ca.jrvs.apps.grep;

import java.io.BufferedWriter;
import java.io.FileWriter;
import org.apache.log4j.BasicConfigurator;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep{
  static final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<String>();
    for (File file : listFiles(rootPath)){
      for(String line : readLines(file)) {
        if(containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException{
    List<File> fileList = new ArrayList<File>();
    File [] files = new File(rootDir).listFiles();
    for (File file : files) {
      if(file.isFile()) {
        fileList.add(file);
      }
    }
    return fileList;
  }

  @Override
  public List<String> readLines(File inputFile) throws IOException {
    FileReader fileReader = new FileReader(inputFile);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    List<String> linesList = new ArrayList<>();
    String line = bufferedReader.readLine();

    while (line != null){
      linesList.add(line);
      linesList.add("\n");
    }
    bufferedReader.close();

    return linesList;
  }

  @Override
  public boolean containsPattern(String line) {
    if(line.matches(regex)){
      return true;
    }
    return false;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    FileWriter fileWriter = new FileWriter(rootPath + "/" + outFile);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    for (String write : lines) {
      bufferedWriter.write(write);
      bufferedWriter.write("\n");

    }
    bufferedWriter.close();
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void getOutFile(String outFile) {
    this.outFile = outFile;
  }

  public static void main(String[] args) {
  if(args.length != 3) {
    throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
  }

    BasicConfigurator.configure();
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.getOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      JavaGrepImp.logger.error("Error Unable to process: ", ex);
    }
  }

}
