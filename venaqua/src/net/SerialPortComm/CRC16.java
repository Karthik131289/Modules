package net.SerialPortComm;

import net.utills.Convert;

public class CRC16
{
	public static int Modbuscrc16(byte[] modbusframe,int Length)
    {
            int crc_register=0xFFFF,crc_temp;//new
            int ival=0;
            char right_shift_count=0;//new
            ival=0;
            while(ival<Length)
            {
                crc_register=(crc_register^modbusframe[ival]);
                do
                {
                    crc_temp=crc_register;
                    crc_register=crc_register>>1;
                    if((crc_temp&0x0001)==0x0001)
                    {    crc_register=(crc_register^0xA001);   }
                         right_shift_count++;
                }while(right_shift_count<8);
                right_shift_count=0;
                ival++;
            }
            crc_temp=crc_register;
            crc_register=crc_register>>8;
            crc_temp=crc_temp<<8;
            crc_register=(crc_register|crc_temp);
            return crc_register;
    }
	public static void main(String[] args)
	{ 
		byte[] bytes =Convert.stringtohex_withCRC("01,03,00,1F,00,02");
		
		System.out.println("CRC :"+Integer.toHexString( Integer.parseInt( Integer.toHexString((char)bytes[bytes.length-2]),16 )& 0xFF) );
		System.out.println("CRC :"+Integer.toHexString( Integer.parseInt( Integer.toHexString((char)bytes[bytes.length-1]),16 )& 0xFF) );
		
		
		/*crc = Modbuscrc16(bytes,bytes.length);
		crc = crc & 0xFFFF;
		System.out.println("CRC 16: "+Integer.toHexString(crc));
		int c1,c2;
		c2 = crc & 0xFF;
		c1 = crc & 0xFF00;
		c1 = c1>>8;
		System.out.println("cH: "+c1+"  cL: "+c2);
		byte cc1,cc2;
		cc1 = (byte)c1;
		cc2 = (byte)c2;
		System.out.println("H: "+c1+"  L: "+c2);
		//System.out.println("H: "+(byte)c1+"  L: "+(byte)c2);*/
    }
}
