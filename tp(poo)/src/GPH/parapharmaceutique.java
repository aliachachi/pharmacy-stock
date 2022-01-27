package GPH;

public class parapharmaceutique extends Medicament_en_stocke{
	String nom_produit;
	String type_produit;
	Fournisseur f;
	
	public parapharmaceutique(int quant ,int lot,float prix,int seuil_min,String nom_produit,String type_produit,Fournisseur f ,date d) {
		// TODO Auto-generated constructor stub
		this.quant = quant;
		this.lot =lot;
		this.prix=prix;
		this.seuil_min=seuil_min;
		this.type_produit=type_produit;
		this.nom_produit = nom_produit;
		this.f=f;
		this.date_exp=d;
	}
	@Override
	float achat(int quant) {
		// TODO Auto-generated method stub
		int j = pharmacie2.Existe_Med_stocke(this);
		if (j != -1) {
			if (pharmacie2.med_stock[j].Med.seuil_min >= (pharmacie2.med_stock[j].quantete_totale - quant)) {
				//paseer une commande au fournisseur
				pharmacie2.liste_commande[pharmacie2.nbr_commande]=pharmacie2.med_stock[j].Med;
				pharmacie2.nbr_commande++ ;
				System.out.println("le nombre de ce medicament"+this.nom_produit+ " est inferieur a la seuil minimale !");
			}
			if (pharmacie2.med_stock[j].quantete_totale >= quant  && pharmacie2.valide_Med(pharmacie2.med_stock[j].Med)) {
			pharmacie2.med_stock[j].quantete_totale -= quant ;
	        return (float) (pharmacie2.med_stock[j].Med.prix*quant);
			}
		}
		System.out.println("la vente de  "+this.nom_produit+ " est invalide !");
	    return -1;
	}
	@Override
	boolean equals(Medicament_en_stocke m) {
		// TODO Auto-generated method stub
			parapharmaceutique para = (parapharmaceutique) m;
			return this.nom_produit.equalsIgnoreCase(para.nom_produit) && this.type_produit.equalsIgnoreCase(para.type_produit) ;
		
	}
	
}
