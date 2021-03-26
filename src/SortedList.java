import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedList {
	private List<Integer> l = new ArrayList<Integer>();
	
    public synchronized void add(Integer n) {
		this.l.add(n);
		Collections.sort(this.l);
    }
    
    public synchronized List<Integer> getListaIndices() {
    	return this.l;
    }
}
