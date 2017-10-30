
public class KreisAgg implements Geom {
	
	/**
	 * Attribut zur Speicherung der Kreisposition
	 */
	Point p = new Point();
	
	/**
	 * Attribut zur Speicherung des Radius
	 */
	double r;
	
	KreisAgg(Point p, double r) {
		this.p.x = p.x;
		this.p.y = p.y;
		this.r = r;
	}
	
	void setRadius(double r) {
		this.r = r;
	}
	
	double getRadius() {
		return r;
	}
	
	public double getSurface() {
		return Math.PI * r * r;
	}
	
	public KreisAgg clone() {
		return new KreisAgg(this.p,r);
	}
	
	boolean equals(KreisAgg k) {
		return (p == k.p && r == k.r);
	}
	
	public String toString() {
		return "[p=" + p + ", r=" + r + "]";
	}
	
	Point getLocation() {
		return p;
	}
	
	void setLocation(Point p) {
		this.p.x = p.x;
		this.p.y = p.y;
	}

	void setLocation(int x, int y) {
		this.p.x = x;
		this.p.y = y;
	}
}
