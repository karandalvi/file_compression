/*
Created by: Karan Dalvi
BinaryFileReader is a helper class that reads an encoded binary file and returns
the file data in StringBuilder format.
*/
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class BinaryFileReader {

  String inputfilename;

  public BinaryFileReader(String filename) {
    this.inputfilename = filename;
  }

  public StringBuilder read() {
    StringBuilder binaryString = new StringBuilder("");
    try {
      InputStream inputStream = new FileInputStream(new File(inputfilename));
      byte[] b;

      while (inputStream.available() > 1) {
        int n = inputStream.available();
        b = new byte[n];
        inputStream.read(b);
        binaryString.append(toBinaryString(b));
      }
      inputStream.close();
    }

    catch(IOException e) {
      e.printStackTrace();
    }
    finally {
        return binaryString;
    }
  }

  String toBinaryString(byte[] bytes)
  {
    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
  }

}
