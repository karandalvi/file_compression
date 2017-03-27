import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class BinaryFileReader {

  String inputfilename;
  File input;
  InputStream inputStream;

  public BinaryFileReader(String filename) {
    this.inputfilename = "../output/" + filename;
    input = new File(inputfilename);
  }

  public StringBuilder read() {
    StringBuilder binaryString = new StringBuilder("");
    try {
      inputStream = new FileInputStream(input);
      byte[] b;

      while (inputStream.available() > 0) {
        b = new byte[1];
        inputStream.read(b);
        binaryString.append(toBinaryString(b));
      }
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
