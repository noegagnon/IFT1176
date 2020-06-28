import java.util.*;
public class App {
    
    public static void main(String[] args) throws Exception {
   /*
            // create arraylist       
            ArrayList<String> arlst = new ArrayList<String>();

      
            // populate the list
            arlst.add("TP");
            arlst.add("PROVIDES");
            arlst.add("QUALITY");
            arlst.add("TUTORIALS");
      
            // search for key 'TUTORIALS' with natural ordering
            int index = Collections.binarySearch(arlst, "TUTORIALS", null);     
      
            System.out.println("'TUTORIALS' is available at index: "+index);
*/
            Set<String> set1 = new TreeSet<String> ();
            set1.add("Bonjour");
            
            Collections.binarySearch(set1, "Bonjour");
         
    }
}
