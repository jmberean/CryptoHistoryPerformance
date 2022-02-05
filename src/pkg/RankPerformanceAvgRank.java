package pkg;

//class name
public class RankPerformanceAvgRank {
	// class attributes
	double performance;
	double averageRanking;

	// default class construcor
	public RankPerformanceAvgRank() {

	}

	// class constructor
	public RankPerformanceAvgRank(double performance, double averageRanking) {
		this.performance = performance;
		this.averageRanking = averageRanking;
	}

	// class setters
	public void setPerformance(double performance) {
		this.performance = performance;
	}

	public void setAverageRanking(double averageRanking) {
		this.averageRanking = averageRanking;
	}

	// class getters
	public double getPerformance() {
		return this.performance;
	}

	public double getAverageRanking() {
		return this.averageRanking;
	}

	// toString()
	public String toString() {
		return ("performance = " + this.performance + " averageRanking = " + this.averageRanking);
	}

	// equals()
	public boolean equals(RankPerformanceAvgRank obj) {
		return (this.performance == obj.performance && this.averageRanking == obj.averageRanking);
	}
}
