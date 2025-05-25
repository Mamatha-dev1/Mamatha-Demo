package Practice;

public class PS1 extends PS2{
	int a;
	public PS1(int a)
	{    super(a);
		this.a=a;
	}
	public int increment()
	{
		a=a+1;
		return a;
		
	}
	
	public int decrement()
	{
		a=a-2;
		return a;
		
	}
}
