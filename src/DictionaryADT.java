import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Math;


public abstract class DictionaryADT 
{
	public void PopulateDictionary(String InputFileName, String TimeFileInsert) //Populate dictionary using given input text file
	{
		try
		{
			FileReader fileReader = new FileReader(InputFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String sInputList = bufferedReader.readLine();
			bufferedReader.close();
			String[] splitString = new String[sInputList.length()];
			int len = 0;
			for(int i=0; i < sInputList.length(); i++)
			{
				//Checking whether the character at the current position is a space or not
				if(sInputList.charAt(i) != ' ')
				{
					//Extracting each element from the string and storing them in a string array
					splitString[len] = sInputList.substring(i, sInputList.indexOf(' ', i) == -1 ? sInputList.length() : sInputList.indexOf(' ', i));
					//Updating the value of i
					i = sInputList.indexOf(' ', i) == -1 ? sInputList.length() : sInputList.indexOf(' ', i);
					//Incrementing len
					len++;
				}
			}
			int [] inputArray=new int[len];
			for(int i = 0; i < len; i++)
			{
				inputArray[i] = Integer.parseInt(splitString[i]);
			}
			long totalInsertTime = 0;
			for(int i=0;i<len;i++)
			{
				long startTime = System.nanoTime();
				this.Insert(inputArray[i]);
				long endtTime = System.nanoTime();
				totalInsertTime += (endtTime - startTime);
			}
			if(TimeFileInsert != null)
			{
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(TimeFileInsert, true)));
			    out.println(len + " " + totalInsertTime/Math.pow(10, 9));
			    out.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void LocateInDictionary(String  SearchFileName, String TimeFileSearch) 
	{
		try
		{
			FileReader fileReader = new FileReader(SearchFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String sInputList = bufferedReader.readLine();
			bufferedReader.close();
			String[] splitString = new String[sInputList.length()];
			int len = 0;
			for(int i=0; i < sInputList.length(); i++)
			{
				//Checking whether the character at the current position is a space or not
				if(sInputList.charAt(i) != ' ')
				{
					//Extracting each element from the string and storing them in a string array
					splitString[len] = sInputList.substring(i, sInputList.indexOf(' ', i) == -1 ? sInputList.length() : sInputList.indexOf(' ', i));
					//Updating the value of i
					i = sInputList.indexOf(' ', i) == -1 ? sInputList.length() : sInputList.indexOf(' ', i);
					//Incrementing len
					len++;
				}
			}
			int [] inputArray=new int[len];
			for(int i = 0; i < len; i++)
			{
				inputArray[i] = Integer.parseInt(splitString[i]);
			}
			long totalInsertTime = 0;
			int itemsFound = 0;
			for(int i=0;i<len;i++)
			{
				long startTime = System.nanoTime();
				boolean found = this.Search(inputArray[i]);
				long endtTime = System.nanoTime();
				if(found)
				{
					totalInsertTime += (endtTime - startTime);
					itemsFound++;
				}
			}
			if(TimeFileSearch != null)
			{
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(TimeFileSearch, true)));
			    out.println(itemsFound + " " + totalInsertTime/Math.pow(10, 9));
			    out.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	abstract public void Insert(int val); 
	abstract public boolean Delete(int val);
	abstract public boolean Search(int val);
	abstract public void ClearADT();
	abstract public void DisplayADT();
}
