import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Dictionary 
{
	public static void main(String[] args) 
	{
		if(args.length > 6 || args.length < 2)
		{
			System.out.println("Usage:");
			System.out.println("java Dictionary [-bst] <dictionary.txt> <locate.txt> [-t <TimeFileInsert.txt> <TimeFileSearch.txt>]");
			System.exit(1);
		}
		DictionaryADT obj;
		String inputFileName;
		String locateFileName;
		String insertTimeFileName = null;
		String searchTimeFileName = null;
		if(args[0].equals("-bst"))				
		{
			if(args.length == 2)
			{
				System.out.println("Usage:");
				System.out.println("java Dictionary [-bst] <dictionary.txt> <locate.txt> [-t <TimeFileInsert.txt> <TimeFileSearch.txt>]");
				System.exit(1);
			}
			obj=new BST();
			inputFileName = args[1];
			locateFileName = args[2];
			if(args.length > 3)
			{
				if(!args[3].equals("-t") || args.length != 6)				
				{
					System.out.println("Usage:");
					System.out.println("java Dictionary [-bst] <dictionary.txt> <locate.txt> [-t <TimeFileInsert.txt> <TimeFileSearch.txt>]");
					System.exit(1);
				}
				insertTimeFileName = args[4];
				searchTimeFileName = args[5];
			}
		}
		else
		{
			obj=new RBT();
			inputFileName = args[0];
			locateFileName = args[1];
			if(args.length > 2)
			{
				if(!args[2].equals("-t") || args.length != 5)				
				{
					System.out.println("Usage:");
					System.out.println("java Dictionary [-bst] <dictionary.txt> <locate.txt> [-t <TimeFileInsert.txt> <TimeFileSearch.txt>]");
					System.exit(1);
				}
				insertTimeFileName = args[3];
				searchTimeFileName = args[4];
			}
		}
		System.out.println("Populating the Dictionary...");
		obj.PopulateDictionary(inputFileName, insertTimeFileName);
		System.out.println("Seaching items in the Dictionary...");
		obj.LocateInDictionary(locateFileName, searchTimeFileName);
		Menu(obj);
	}

	private static void Menu(DictionaryADT obj) 
	{
		System.out.println("Choose one of the following options:");
	    try 
	    {
			while(true)
			{
				System.out.println("1. Insert");
				System.out.println("2. Delete");
				System.out.println("3. Search");
				System.out.println("4. Display");
				System.out.println("5. Clear");
				System.out.println("6. Exit");
				
				//Taking the input from the Input Buffer for the option			
				BufferedReader bufferReadOption = new BufferedReader(new InputStreamReader(System.in));
			    String sOption = bufferReadOption.readLine();
				switch(sOption)
				{
					case "1":
					{	
						System.out.println("Enter the element to be inserted");
						bufferReadOption = new BufferedReader(new InputStreamReader(System.in));
					    String sElement = bufferReadOption.readLine();
					    obj.Insert(Integer.parseInt(sElement));
						break;
					}
					case "2":
					{	
						System.out.println("Enter the element to be deleted");
						bufferReadOption = new BufferedReader(new InputStreamReader(System.in));
					    String sElement = bufferReadOption.readLine();
					    if(obj.Delete(Integer.parseInt(sElement)))
					    {
					    	System.out.println(sElement + " deleted from the Dictionary");
					    }
					    else
					    {
					    	System.out.println(sElement + " does not exist in the Dictionary");
					    }
						break;
					}
					case "3":
					{	
						System.out.println("Enter the element to be searched");
						bufferReadOption = new BufferedReader(new InputStreamReader(System.in));
					    String sElement = bufferReadOption.readLine();
					    if(obj.Search(Integer.parseInt(sElement)))
					    {
					    	System.out.println(sElement + " exists in the Dictionary");
					    }
					    else
					    {
					    	System.out.println(sElement + " does not exist in the Dictionary");
					    }
						break;
					}
					case "4":
					{	
						System.out.println("Displaying elements in sorted order");
						obj.DisplayADT();
						System.out.println();
						break;
					}
					case "5":
					{	
						System.out.println("Clearing the Dictionary");
						obj.ClearADT();
						break;
					}
					case "6":
					{	
						obj.ClearADT();
						System.out.println("Exiting program");
						System.exit(0);
						break;
					}
					default:
						System.out.println("Wrong Option!!!");
				}
			}
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}

}
