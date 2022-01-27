package GPH;

public abstract class Medicament_en_stocke {
	
	int quant;
	int lot;
	float prix;
	int seuil_min ;
	date date_exp;
	abstract float achat(int quant);
	abstract boolean equals(Medicament_en_stocke m);

}
