package test_.serial; 
public class StringToHex {
  public StringToHex() {
  }

  /**
   * ��ָ��byte������16���Ƶ���ʽ��ӡ������̨
   * @param hint String
   * @param b byte[]
   * @return void
   */
  public static void printHexString( byte[] b) {
    //System.out.print(hint);
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      System.out.print(hex.toUpperCase() + " ");
    }
    System.out.println("");
  }
  public static void main(String args[]){
	  byte[] a=HexString2Bytes("efacbedeefacbede");
	  printHexString( a);
  }

  /**
   *
   * @param b byte[]
   * @return String
   */
  public static String Bytes2HexString(byte[] b) {
    String ret = "";
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      ret += hex.toUpperCase();
    }
    return ret;
  }

  /**
   * ������ASCII�ַ��ϳ�һ���ֽڣ�
   * �磺"EF"--> 0xEF
   * @param src0 byte
   * @param src1 byte
   * @return byte
   */
  public static byte uniteBytes(byte src0, byte src1) {
    byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
    _b0 = (byte)(_b0 << 4);
    byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
    byte ret = (byte)(_b0 ^ _b1);
    return ret;
  }

  /**
   * ��ָ���ַ���src����ÿ�����ַ��ָ�ת��Ϊ16������ʽ
   * �磺"2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
   * @param src String
   * @return byte[]
   */
  public static byte[] HexString2Bytes(String src){
    byte[] ret = new byte[6];
    byte[] tmp = src.getBytes();
    for(int i=0; i<6; i++){
      ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]);
    }
    return ret;
  }

}
