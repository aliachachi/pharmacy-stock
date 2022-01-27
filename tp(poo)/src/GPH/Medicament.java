package GPH;

public abstract class Medicament extends Medicament_en_stocke {
	String nom_Med;
	String type_Med;
	String mode_Med;
	boolean vendu_sans_ord;
	float taux_remboursement;
	abstract float remboursement(int quant);
	

}