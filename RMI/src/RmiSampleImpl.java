import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
  
  
  
public class RmiSampleImpl extends UnicastRemoteObject implements RmiSample {
	
	
	private static Map<String,String> maps = new HashMap<String, String>();
	
	private Worker w = new Worker();
	
	static{
		maps.put("name", "gaoll");
		maps.put("age","20");
	}
	
	
    /**  
     *   
     */  
    private static final long serialVersionUID = 2742977636753958461L;   
  
    public RmiSampleImpl() throws RemoteException {   
        super();   
    }   
  
    public String sum(String key)  {
        return w.work();   
    }

	@Override
	public Map<String, String> getMaps() throws RemoteException {
		return maps;
	}

	@Override
	public String work(Worker w)  {
		return w.work();
	}   
  
}   