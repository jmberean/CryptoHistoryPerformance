package pkg;

//class name
public class Crypto {
	// class attributes
	String ticker;
	double marketCap;
	double price;
	int rank;
	double performance;
	double averageRanking;

	// default class construcor
	public Crypto() {
	}

	// class constructor
	public Crypto(String ticker, double marketCap, double price, int rank, double performance, double averageRanking) {
		this.ticker = ticker;
		this.marketCap = marketCap;
		this.price = price;
		this.rank = rank;
		this.performance = performance;
		this.averageRanking = averageRanking;
	}

	// class setters
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	public void setAverageRanking(double averageRanking) {
		this.averageRanking = averageRanking;
	}

	// class getters
	public String getTicker() {
		return this.ticker;
	}

	public double getMarketCap() {
		return this.marketCap;
	}

	public double getPrice() {
		return this.price;
	}

	public int getRank() {
		return this.rank;
	}

	public double getPerformance() {
		return this.performance;
	}

	public double getAverageRanking() {
		return this.averageRanking;
	}

	// toString()
	public String toString() {
		return ("ticker = " + this.ticker + " marketCap = " + this.marketCap + " price = " + this.price + " rank = "
				+ this.rank + " performance = " + this.performance + " averageRanking = " + this.averageRanking);
	}

	// equals()
	public boolean equals(Crypto obj) {
		return (this.ticker == obj.ticker && this.marketCap == obj.marketCap && this.price == obj.price
				&& this.rank == obj.rank && this.performance == obj.performance);
	}
}
