package bigIntPackage;

import java.util.Arrays;
import exceptionPackage.BigIntException;

public class BigInt {
	
	public byte[] num;
	boolean neg;
	
	public BigInt(String numString) throws BigIntException {
		
		int len=numString.length();
		int startIndex=0;
		
		if(numString.matches("[0]+"))
		{
			num=new byte[1];
			num[0]=0;
			neg=false;
		}
		else if(!numString.matches("[+-]?[0-9]+")) 
		{
			throw new BigIntException("Invalid Input");
		}
		else
		{
			if(numString.charAt(0)=='+')
			{
				startIndex = 1;	
				len-=1;	
			}	
			else if (numString.charAt(0)=='-')
			{
				startIndex = 1;	
				len-=1;
				neg=true;
			}
			while(numString.charAt(startIndex)=='0')
			{
				startIndex++;
				len-=1;
			}
			
			num = new byte[len];

			for(int i=0; startIndex < numString.length(); i++)
			{
				
				num[i]=(byte) (numString.charAt(startIndex)-'0');
				startIndex++;
			}	
		}
	}

	private BigInt(byte[] res, boolean signed) {
		neg=signed;
		int startIndex=0;
		int len=res.length;
		while(res[startIndex]==0 && startIndex < res.length)
		{
			startIndex++;	//1	2	3	stop					0001 
			len--;	//3 2 1 0
		}
		if(len==0)
		{
			num=new byte[] {0};
			neg=false;
		}
		else
		{
			num = new byte[len];
			System.arraycopy(res, startIndex, num, 0, len);
		}
	}
	
	public int compareTo(BigInt obj2)
	{
		if(!neg && obj2.neg)
		{
			return 1;
		}
		else if(neg && !obj2.neg)
		{
			return -1;
		}
		else if(!neg && !obj2.neg)
		{
			
			if(num.length > obj2.num.length)
			{
				return 1;
			}
			else if(num.length < obj2.num.length)
			{
				return -1;
			}
			else
			{
				for(int i=0; i<num.length; i++)
				{
					if(num[i] > obj2.num[i])
					{					
						return 1;
					}
					else if (num[i] < obj2.num[i])
					{
						return -1;
					}
				}
				return 0;	
			}
				
		}
		else 
		{
			if(num.length < obj2.num.length)
			{
				return 1;
			}
			else if(num.length > obj2.num.length)
			{
				return -1;
			}
			else
			{
				for(int i=0; i<num.length; i++)
				{
					if(num[i] < obj2.num[i])
					{					
						return 1;
					}
					else if (num[i] > obj2.num[i])
					{
						return -1;
					}		
				}
				return 0;			
			}	
		}
	}

	public boolean equals(BigInt obj2) 
	{
		if(neg != obj2.neg)
		{
			return false;
		}
		else if(num.length != obj2.num.length)
		{
			return false;
		}
		return Arrays.equals(num, obj2.num);
	}
	
 	public String toString() {
		StringBuilder str = new StringBuilder();
		if(neg)
		{
			str.append("-");
		}
		for(byte n: num)
		{
			str.append(n);
		}
		return str.toString();
	}
	
 	public BigInt clone() {
 		String numStr=this.toString();
 		boolean invalidInput=false;
 		BigInt cloned;
 		do
 		{
 			try {
 				cloned = new BigInt(numStr);
 				return cloned;
 			} 
 			catch (BigIntException e)
 			{	
 				invalidInput=true;
 				System.out.println("Please use the valid object i.e. number");
 			}	
 		} while(invalidInput);
 		
		return null;
 	}
 	
	private byte[] add(byte[] a, byte[] b)
	{
		int maxLength=Math.max(a.length, b.length);
		byte[] result=new byte[maxLength + 1] ;
		byte carry=0;
		for(int i=0; i<maxLength; i++)
		{
			if(i >= a.length) 
			{
				result[maxLength-i] = (byte) ((byte) (b[b.length-i-1]+carry)%10);
				carry = (byte) ((byte) (b[b.length-i-1]+carry)/10);					
			}
			else if(i >= b.length)
			{
				result[maxLength-i] = (byte) ((byte) (a[a.length-i-1]+carry)%10);
				carry = (byte) ((byte) (a[a.length-i-1]+carry)/10);
			}
			else 
			{ 
				result[maxLength-i]= (byte) ((byte) (a[a.length- i-1] + b[b.length - i -1] + carry)%10);
				carry= (byte) ((byte) (a[a.length- i-1] + b[b.length - i -1] + carry)/10);
			}
		}
		
		if(carry!=0)
		{
			result[0]=carry;
		}
		return result;
			
	}
	
	private byte[] subtract(byte[] a, byte[] b)
	{
		int maxLength=Math.max(a.length, b.length);
		byte[] result=new byte[maxLength] ;
		
		for(int i=0; i<maxLength; i++)
		{
			if(i >= b.length)
			{
				if(a[a.length-i-1]<0)
				{
					a[a.length-i-1]= (byte) (a[a.length-i-1]+10);	
					if(a[a.length-i-1]==9)
					{
						a[a.length-i-2]=(byte) (a[a.length-i-2]-1);
					}
				}
				result[maxLength-i-1] = (byte) (a[a.length-i-1]);
			}
			
			else
			{
				byte adigit= a[a.length-i-1];
				byte bdigit= b[b.length-i-1];
				result[maxLength-i-1]= (byte) (adigit - bdigit);					
			}
			
			if(result[maxLength-i-1]<0 )
			{					
				result[maxLength-i-1]= (byte) (result[maxLength-i-1]+10);				
				a[a.length-i-2]=(byte) (a[a.length-i-2]-1);
			}	
		}
		return result;
	}

	public BigInt add(BigInt obj2) {
		boolean negSum=false;
		if(!neg && !(obj2.neg)) 
		{
			negSum=false;
			BigInt sum= new BigInt(add(this.num, obj2.num), negSum);
			return sum;
		}
		else if(neg && obj2.neg)
		{
			negSum=true;
			BigInt sum= new BigInt(add(this.num, obj2.num), negSum);
			return sum;
		}
		else if(num.length > obj2.num.length)
		{
			negSum=neg;
		}
		else if(num.length < obj2.num.length)
		{
			negSum=obj2.neg;
			BigInt result =new BigInt(subtract(obj2.num, num), negSum);
			return result;
		}
		else if(num.length == obj2.num.length)
		{
			for(int i=0; i<num.length; i++)
			{
				if(num[i] > obj2.num[i])
				{					
					negSum=neg;
				}
				else if (num[i] < obj2.num[i])
				{
					negSum=obj2.neg;
					BigInt result =new BigInt(subtract(obj2.num, num), negSum);
					return result;
				}
			}	
		}
		else
		{
			negSum=false;
		}
		
		BigInt sum= new BigInt(subtract(this.num, obj2.num), negSum);
		return sum;
		
	}

	public BigInt subtract(BigInt obj2)
	{
		boolean negResult=false;
		
		if(neg != obj2.neg)
		{
			negResult=neg;
			BigInt result =new BigInt(add(num, obj2.num), negResult);
			return result;
		}
		else if(num.length > obj2.num.length)
		{
			negResult=neg;
		}
		else if(num.length < obj2.num.length)
		{
			negResult=!obj2.neg;
			BigInt result =new BigInt(subtract(obj2.num, num), negResult);
			return result;
		}
		else if(num.length == obj2.num.length)
		{
			for(int i=0; i<num.length; i++)
			{
				if(num[i] > obj2.num[i])
				{					
					negResult=neg;
				}
				else if (num[i] < obj2.num[i])
				{
					negResult=!obj2.neg;	
					BigInt result =new BigInt(subtract(obj2.num, num), negResult);
					return result;
				}
			}	
		}
		else 
		{
			negResult=false;
		}
		BigInt result =new BigInt(subtract(num, obj2.num), negResult);
		return result;
	}
}


