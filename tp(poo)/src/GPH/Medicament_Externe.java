package GPH;

public class Medicament_Externe extends Medicament {

	Fournisseur f;
	
	public Medicament_Externe (String nom,String type,String mode,boolean vendu_sans_ord,int quant,int lot,int sueil_min,float taux,Fournisseur f,float prix,date d) {
		// TODO Auto-generated constructor stub
		nom_Med = nom;
		type_Med= type;
		mode_Med = mode;
		this.quant=quant;
		this.lot = lot;
		this.f = f;
		this.vendu_sans_ord = vendu_sans_ord;
		this.prix = prix;
		this.seuil_min=sueil_min;
		this.taux_remboursement = taux;
		this.date_exp=d;
	}
	@Override
	 float remboursement(int quant) {
		// TODO Auto-generated method stub
		return (float) (this.prix*((1.0-this.taux_remboursement/100))*quant);
		}
	@Override
	float achat(int quant) {
		// TODO Auto-generated method stub
		int j = pharmacie2.Existe_Med_stocke(this);
		if (j != -1) {
			//if (((Medicament_Externe)(pharmacie2.med_stock[j].Med)).vendu_sans_ord == true) {
				if (pharmacie2.med_stock[j].Med.seuil_min >= (pharmacie2.med_stock[j].quantete_totale - quant)) {
					System.out.println("le nombre de ce medicament"+this.nom_Med+ " est inferieur a la seuil minimale !");
					pharmacie2.liste_commande[pharmacie2.nbr_commande]=pharmacie2.med_stock[j].Med;
					pharmacie2.nbr_commande ++;
				}
				if (pharmacie2.med_stock[j].quantete_totale >= quant  && pharmacie2.valide_Med(pharmacie2.med_stock[j].Med)) {
					pharmacie2.med_stock[j].quantete_totale -= quant ;
					return (float) (((Medicament_Externe)(pharmacie2.med_stock[j].Med)).remboursement(quant));
				}
			//}
		}
		System.out.println("la vente de  "+this.nom_Med+ " est invalide !");
	    return 0;
		
	}
	@Override
	boolean equals(Medicament_en_stocke m) {
		// TODO Auto-generated method stub
		Medicament Med = (Medicament) m;
		return this.nom_Med.equalsIgnoreCase(Med.nom_Med) && this.type_Med.equalsIgnoreCase(Med.type_Med) && this.mode_Med.equalsIgnoreCase(Med.mode_Med) ;
	}
	
}
