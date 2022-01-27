package GPH;

public class Medicament_Interne extends Medicament {
	String composition;
	final int quant=0;
	final int lot=-1;
	final float prix;
	
	public Medicament_Interne(String nom,String type,String mode,boolean vendu_sans_ord,int sueil_min,String composition,float prix,float taux_remboursement,date d) {
		// TODO Auto-generated constructor stub
		nom_Med = nom;
		type_Med= type;
		mode_Med = mode;
		this.vendu_sans_ord = vendu_sans_ord;
		this.seuil_min=sueil_min;
		this.prix = prix;
		this.taux_remboursement = taux_remboursement;
		this.composition = composition;
		this.date_exp =d;
	}
	
	@Override
	 float remboursement(int quant) {
		// TODO Auto-generated method stub
		return (float) (this.prix*((1.0-this.taux_remboursement/100))*quant);
		}
	@Override
	float achat(int quant) {
		// TODO Auto-generated method stub
		int j = pharmacie2.Existe_Med_inter(this);
		if (j != -1) {
				if (pharmacie2.med_inter[j].Med.seuil_min >= (pharmacie2.med_inter[j].quantete_totale - quant)) {
					System.out.println("le nombre de ce medicament"+this.nom_Med+ " est inferieur a la seuil minimale !");
					
				}
				if (pharmacie2.med_inter[j].quantete_totale >= quant  && pharmacie2.valide_Med(pharmacie2.med_inter[j].Med)) {
					pharmacie2.med_inter[j].quantete_totale -= quant ;
					return (float) (((Medicament_Interne)pharmacie2.med_inter[j].Med).remboursement(quant));
				}
			
		}
		System.out.println("la vente de  "+this.nom_Med+ " est invalide !");
	    return 0;
	}
	@Override
	boolean equals(Medicament_en_stocke m) {
		// TODO Auto-generated method stub
		Medicament_Interne Med = (Medicament_Interne) m;
		return this.nom_Med.equalsIgnoreCase(Med.nom_Med) && this.type_Med.equalsIgnoreCase(Med.type_Med) && this.mode_Med.equalsIgnoreCase(Med.mode_Med)  ;
	}
	
}