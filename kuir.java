package Q1;

public class kuir {

	public static void main(String[] args) {
		//String makeKey="C:\\Users\\ee\\SimpleR\\q1\\collection.xml";
		//String makeCollect="C:/Users/ee/Desktop/-c";

		String command = args[0];   
		String path = args[1];	
		if(command.equals("-c")) {
			makeCollection collection = new makeCollection(path);
			collection.makeXml();
		}
		else if(command.equals("-k")) {
			makeKeyword keyword = new makeKeyword(path);
			keyword.convertXml();
		}
		else if(command.equals("-i")) {
			indexer keyword = new indexer(path);
			keyword.makeIndexer();
		}
		
	}
}