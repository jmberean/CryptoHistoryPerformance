package pkg;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Start {

	public static void main(String[] args) {
		System.out.println("START ------------------------\n");
		try {
			runIt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
		}

		System.out.println("\nEND ------------------------");

	}

	public static void runIt() throws InterruptedException, ParseException {

		ArrayList<ArrayList<Crypto>> al1 = new ArrayList<>();
		ArrayList<String> al2 = getDates();
		ArrayList<String> al3 = new ArrayList<>();
		ArrayList<Crypto> tempAl = null;
		int totalDates = 0;
		
		System.out.println("Dates gathered : \n");
		for (int i = 0; i < al2.size(); i++) {
			if (i == 0) {
				totalDates++;
				System.out.println(al2.get(i));
				al3.add(al2.get(i));
			} else if (i % 18 == 0) {
				totalDates++;
				System.out.println(al2.get(i));
				al3.add(al2.get(i));
			} else if (i == al2.size() - 1) {
				totalDates++;
				System.out.println(al2.get(i));
				al3.add(al2.get(i));
				break;
			}
		}
		
		System.out.println("Total Dates = " + totalDates);
		
		System.out.println("\nCrawling Data : \n");

		for (int i = 0; i < al3.size(); i++) {
			tempAl = mainMethod(al3.get(i));
			for (Crypto s : tempAl) {
				System.out.println(s.toString());
			}
			System.out.println("------------------------");
			Thread.sleep(10000);
			al1.add(tempAl);
		}

		for (int i = 0; i < al1.size(); i++) {
			for (int x = 0; al1.get(i) != null && x < al1.get(i).size(); x++) {
				System.out.println(al1.get(i).get(x).toString());
			}
			System.out.println();
		}

		getTopOneHundred(al1);
	}

	public static void getTopOneHundred(ArrayList<ArrayList<Crypto>> al1) {
		HashMap<String, Integer> hm = new HashMap<>();

		for (int i = 0; i < al1.size(); i++) {
			for (int x = 0; al1.get(i) != null && x < al1.get(i).size(); x++) {
				if (hm.containsKey(al1.get(i).get(x).getTicker())) {
					hm.put(al1.get(i).get(x).getTicker(), hm.get(al1.get(i).get(x).getTicker()) + 1);
				} else {
					hm.put(al1.get(i).get(x).getTicker(), 1);
				}
			}
		}

		Map<String, Integer> hm1 = sortByValue(hm);
		for (Map.Entry<String, Integer> en : hm1.entrySet()) {
			System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue());
		}

	}

	// function to sort hashmap by values
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public static String addOneDayCalendar(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		c.add(Calendar.DATE, 1);
		return sdf.format(c.getTime());
	}

	public static ArrayList<String> getDates() throws ParseException {
		ArrayList<String> al = new ArrayList<>();
		String startDate = "2013-04-28";
		String terminalDate = "2018-01-01";
		String tempDate = "";
		String tempDateWithoutSlash = "";

		for (int i = 0; !tempDate.equalsIgnoreCase(terminalDate); i++) {
			if (i == 0) {
				tempDateWithoutSlash = startDate.replaceAll("-", "");
				tempDate = startDate;
				al.add(tempDateWithoutSlash);
			} else {
				tempDate = addOneDayCalendar(tempDate);
				tempDateWithoutSlash = tempDate.replaceAll("-", "");
				al.add(tempDateWithoutSlash);
			}
		}

		return al;

	}

	public static ArrayList<Crypto> mainMethod(String date) {

		ArrayList<Crypto> al = new ArrayList<>();

		try {
			URL url = new URL("https://coinmarketcap.com/historical/" + date + "/");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			String temp = "";
			String temp2 = "";
			String ticker = "<td class=\"cmc-table__cell cmc-table__cell--sortable cmc-table__cell--left cmc-table__cell--sort-by__symbol\"><div class=\"\">";
			String marketCap = "<td class=\"cmc-table__cell cmc-table__cell--sortable cmc-table__cell--right cmc-table__cell--sort-by__market-cap\"><div>";
			String price = "<td class=\"cmc-table__cell cmc-table__cell--sortable cmc-table__cell--right cmc-table__cell--sort-by__price\"><div>";
			String div = "</div>";

			while ((line = in.readLine()) != null) {
				temp += line;
			}

			temp2 = new String(temp);

			in.close();

			// number of cryptos
			for (int i = 0; StringUtils.substringBetween(temp, ticker, div) != null; i++) {
				if (i == 0) {
					al.add(new Crypto());
				} else {
					temp = temp.replaceFirst(ticker, "");
					if (StringUtils.substringBetween(temp, ticker, div) == null) {
						break;
					} else {
						al.add(new Crypto());
					}
				}
			}

			for (int a = 0; a < al.size(); a++) {
				al.get(a).setRank(a + 1);
			}
			// ticker
			for (int i = 0; i < al.size() && StringUtils.substringBetween(temp2, ticker, div) != null; i++) {
				if (i == 0) {
					al.get(i).setTicker(StringUtils.substringBetween(temp2, ticker, div));
					//if(GlobalVars.alRankPerformanceAvgRank.contains(o))
				} else {
					temp2 = temp2.replaceFirst(ticker, "");
					if (StringUtils.substringBetween(temp2, ticker, div) == null) {
						break;
					} else {
						al.get(i).setTicker(StringUtils.substringBetween(temp2, ticker, div));
					}
				}
			}

			// market cap
			for (int i = 0; i < al.size() && StringUtils.substringBetween(temp2, marketCap, div) != null; i++) {
				if (i == 0) {
					al.get(i).setMarketCap(Double.parseDouble(
							StringUtils.substringBetween(temp2, marketCap, div).replace("$", "").replace(",", "")));
				} else {
					temp2 = temp2.replaceFirst(marketCap, "");
					if (StringUtils.substringBetween(temp2, marketCap, div) == null) {
						break;
					} else {
						al.get(i).setMarketCap(Double.parseDouble(
								StringUtils.substringBetween(temp2, marketCap, div).replace("$", "").replace(",", "")));
					}
				}
			}

			// price
			for (int i = 0; i < al.size() && StringUtils.substringBetween(temp2, price, div) != null; i++) {
				if (i == 0) {
					al.get(i).setPrice(Double.parseDouble(
							StringUtils.substringBetween(temp2, price, div).replace("$", "").replace(",", "")));
				} else {
					temp2 = temp2.replaceFirst(price, "");
					if (StringUtils.substringBetween(temp2, price, div) == null) {
						break;
					} else {
						al.get(i).setPrice(Double.parseDouble(
								StringUtils.substringBetween(temp2, price, div).replace("$", "").replace(",", "")));
					}
				}
			}

		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return al;
	}
}