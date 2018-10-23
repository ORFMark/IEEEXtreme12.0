
// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
	public static void main(String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		ArrayList<Commodity> com = new ArrayList<Commodity>(48);
		int numberOfCommodities = 0;
		int exchanges = in.nextInt();
		String firstCom = null;
		String secondCom = null;
		String start = null;
		String end = null;
		String inter = null;
		boolean found = false;
		long rate = 0;
		for (int i = 0; i < exchanges; i++) {
			firstCom = in.next();
			secondCom = in.next();
			rate = (long) in.nextInt();
			for (int j = 0; j < numberOfCommodities; j++) {
				//System.out.println("Checking for exisitng exchange rate from " + com[j].getName() + " to " + secondCom);
				if (com.get(j).isCommodity(firstCom)) {
					if(com.get(j).getRate(secondCom) == -1) {
						com.get(j).addExchange(secondCom, rate);
						found = true;
					}
					else {
						found = true;
					}
				} else if (com.get(j).getRate(firstCom) != -1) {
					com.get(j).addExchange(secondCom, (long) rate * com.get(j).getRate(firstCom));
				}
			}
			if (!found) {
				//System.out.println("adding a new commodity");
				com.add(new Commodity(firstCom));
				com.get(com.size()-1).addExchange(secondCom, rate);
				numberOfCommodities++;
			}

			found = false;
		}
		exchanges = in.nextInt();
		for (int i = 0; i < exchanges; i++) {
			firstCom = in.next();
			secondCom = in.next();
			found = false;
			if (firstCom.equals(secondCom)) {
				System.out.println("1");
				found = true;
			}
			for (int j = 0; j < numberOfCommodities && !found; j++) {
				if (com.get(j).getName().equals(firstCom)) {
					//System.out.println("Found rate for " + firstCom + " to " + secondCom);
					System.out.println(com.get(j).getRate(secondCom));
					found = true;
				}

			}
			if(!found) {
				System.out.println("-1");
			}
		}
		System.out.flush();
	}
}
class Commodity {
	private String name;
	private ArrayList<String> exchange;
	public String hasExchanges;
	private int numberOfExchanges;

	Commodity(String name) {
		this.name = name;
		exchange = new ArrayList<String>(32);
		numberOfExchanges = 0;
		hasExchanges = "name";
	}

	public void addExchange(String name, Long rate) {
			//	System.out.println("Adding exchange" + this.name + name);
			exchange.add(name);
			exchange.add(Long.toString(rate));
			numberOfExchanges += 2;
			hasExchanges += name;
		}
	

	public Long getRate(String key) {

		for (int i = 0; i < numberOfExchanges; i += 2) {
			if (exchange.get(i).equals(key)) {
				return ((Long.parseLong(exchange.get(i+1))) % 998244353);
			}
		}
		return (long) -1;
	}

	public boolean isCommodity(String key) {
		return name.equals(key);
	}

	public String getName() {
		return name;
	}

}

