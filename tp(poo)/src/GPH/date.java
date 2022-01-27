package GPH;

public class date {
	private int jours;
	private int mois;
	private int annee;
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public void setJours(int jj) {
		if (jj>0 && jj<31)
			this.jours = jj;
		else System.out.println("invalide jour");
	}
	public void setMois(int mm) {
		if (mm>0 && mm<13)
			this.mois = mm;
		else System.out.println("invalide mois");
	}
	public int getMois() {
		return mois;
	}
	public int getJours() {
		return jours;
	}
	public int getAnnee() {
		return annee;
	}
	
	
}