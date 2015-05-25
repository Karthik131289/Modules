package net.utills;

import net.SerialPortComm.CRC16;

import java.util.StringTokenizer;
import java.util.Vector;


public class Convert
{
	public static String chartoHexString( String s )
	{
		
		String hex="";
		String ichar;
		byte b[]=s.getBytes();
		
		for(int i=0;i<b.length;i++)
		{
			ichar="";
			ichar = Integer.toHexString(b[i]);

			if(ichar.length()>2)
			{	ichar=ichar.substring(ichar.length()-2);	}
			
			if( (Integer.parseInt(ichar,16)) <=9)
			{	ichar="0"+ichar;	}
 
			hex=hex+ichar;
		}
		
		hex=hex.toUpperCase();
		
		return hex;
	}
	
	@SuppressWarnings("unchecked")
	public static byte[] stringtoHexString(String s)
	{
		String c;
		Vector<String> v = new Vector<String>();
		
		StringTokenizer str = new StringTokenizer(s,",");
		int count = str.countTokens();
		byte b[]=new byte[count];
		
		count=0;
		while(str.hasMoreTokens())
		{
			c = str.nextToken().toString();
			v.add(count++,c);
		}
		
		for(int i=0;i<count;i++)
		{
			b[i]=(byte)( Integer.parseInt(v.elementAt(i).toString(),16) & 0xFF );
		}
		
		return b;
	}
	
	public static byte[] stringtohex_withCRC(String s)
	{
		byte b[]=stringtoHexString(s);
		byte b1[] = new byte[b.length+2];
		
		int crc=0x0000;
		int c1=0,c2=0,count=0;
		
		crc = CRC16.Modbuscrc16(b, b.length);
		crc=crc & 0xFFFF;
		count = b.length;
		c2 = crc & 0xFF;
		c1 = crc & 0xFF00;
		c1 = c1>>8;
		
		for(int i=0;i<b.length;i++)
		{	b1[i] = b[i];	}
		
		b1[count++]=(byte)c1;
		b1[count]=(byte)c2;
		
		return b1;
	}
	
	public static String hextoString(byte[] b)
	{
		String str="",val="";
		int i=0;
		char c;
		
		for(i=0; i<b.length;i++)
		{
			c= (char)b[i];
			val="0x"+c;
			str += val+",";
		}
		
		return str;
	}
	
	public static String  hextoChar(String hexa)
	{
		String hexastring="0123456789abcdef";
		String hexastring1="0123456789ABCDEF";
		String character="";
		
		for(int i=0;i<hexa.length();i=i+2)
		{
			String s=hexa.substring(i,i+2);
			
			int pos1=hexastring.indexOf(s.charAt(0));
			if(pos1==-1)
				pos1=hexastring1.indexOf(s.charAt(0));
			
			int pos2=hexastring.indexOf(s.charAt(1));
			if(pos2==-1)
				pos2=hexastring1.indexOf(s.charAt(1));
			
			int result=(pos2*1)+(pos1*16);

			character=character+(char)result;
		}
		
		return character;
	}
	
	public static String get_HexString(int val)
	{
		String str ="";
		str = Integer.toHexString(val);
		
		if (str.length()<=1)
		{	str = "000"+str;	}
		else if (str.length()==2)
		{	str = "00"+str;		}
		else if (str.length()==3)
		{	str = "0"+str;		}
		
		str = str.substring(0,2)+","+str.substring(2,4);
		
		return str;
	}
	
	public static String toHexString( int data )
	{
		String res = null;
		
		res = String.format("%2s", Integer.toHexString( data ) ).replaceAll(" ", "0");
		
		return res;
	}
	
	public static final Float toLiters( Float kiloLiters )
	{
		return ( kiloLiters*1000.0f );
	}
	public static final Float toKiloLiters( Float liters )
	{
		return ( liters/1000.0f );
	}
}
