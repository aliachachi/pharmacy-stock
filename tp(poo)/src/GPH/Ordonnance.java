package GPH;
import java.util.Scanner;

public class Ordonnance {
    String nom_Patient,Prenom_Patiant,prenom_Medcin,nom_Medcin,adresse;
	int age;
	static class Medicament_Prescrit{
		Medicament Med;
		int quant_dem;
		int dure_traitmt;
		}
	Medicament_Prescrit [] med_pres = new Medicament_Prescrit[10];
	Scanner input = new Scanner(System.in);
	public Ordonnance(String nom_Patient,String Prenom_Patiant,int age,String nom_Medcin,String prenom_Medcin,String adresse,Medicament_Prescrit t[]) {
		// TODO Auto-generated constructor stub
		this.nom_Patient = nom_Patient;
		this.Prenom_Patiant = Prenom_Patiant;
		this.age = age;
		this.nom_Medcin = nom_Medcin;
		this.prenom_Medcin = prenom_Medcin;
		this.adresse = adresse;
		this.med_pres = t;
	}
	
	float Achat_ordenance(int nbr_med) {
		
		int i=0 ;
		Medicament_Prescrit p []= new Medicament_Prescrit[10]; 
		int j=0;
		float total=0;
		float So=0;
		
		for (i=0 ;i< nbr_med;i++ ) {			
			So=this.med_pres[i].Med.achat(this.med_pres[i].quant_dem);
			total += So;
			if ((So == 0) && (med_pres[i].Med instanceof Medicament_Interne) == false )
				p[j++]= this.med_pres[i];
			}
		if (j != 0) {
			if (pharmacie2.Existe_Client_Per(nom_Patient,Prenom_Patiant)) {
				
				System.out.println("il existe des medicament manquantes , taper sur 1 si vous vollez contenuer la vente ;");
				i = input.nextInt();
				if (i==1) {
					//pharmacie.passecommande();
					for (int k=pharmacie2.nbr_commande;k<pharmacie2.liste_commande.length;k++) {
						pharmacie2.liste_commande[k] = p[k-pharmacie2.nbr_commande].Med;
					}
					pharmacie2.nbr_commande += j;
				}
				else {
					System.out.println("la vente n'est pas fait ");
					return 0;
				}
			}
			else {
				System.out.println("taper sur 1 si vous vollez devenir un client permenant ;");
				i = input.nextInt();
				if (i==1) {
					pharmacie2.ajout_Client_Per(this.nom_Patient, this.Prenom_Patiant,this.age);
					System.out.println("il existe des medicament manquantes , taper sur 1 si vous vollez contenuer la vente ;");
					i = input.nextInt();
					if (i==1) {
						//pharmacie.passecommande();
						for (int k=pharmacie2.nbr_commande;k<pharmacie2.liste_commande.length;k++) {
							pharmacie2.liste_commande[k] = p[k-pharmacie2.nbr_commande].Med;
						}
						pharmacie2.nbr_commande += j;
					}
				}
				else {
					System.out.println("la vente n'est pas fait ");
					return 0;
				}
				
			}
		}
		
		return total;
		
	}
}
