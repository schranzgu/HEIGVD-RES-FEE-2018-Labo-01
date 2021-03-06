package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  int previousChar = ' ';
  int lineNb = 1;
  boolean firstLine = true;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i < off + len; ++i){
        this.write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i < off + len; ++i){
        this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String lineNumber = lineNb + "\t";
    if(firstLine){
        firstLine = false;
        out.write(lineNumber);
    }
    
    if(c == '\n'){
        lineNumber = ++lineNb + "\t";
        out.write(c);
        out.write(lineNumber);
    }
    else if(previousChar == '\r'){
        lineNumber = ++lineNb + "\t";
        out.write(lineNumber);
        out.write(c);
    }
    else{
        out.write(c);
    }
    previousChar = c; 
  }

  
}
