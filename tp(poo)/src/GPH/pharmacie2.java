package GPH;
import java.util.Scanner;

import GPH.Ordonnance.Medicament_Prescrit;

public class pharmacie2 {
	
	static date la_date = new date();
	static Scanner input = new Scanner(System.in);
	
	public static class Client_permanant{
		String nom_per,prenom_per;
		int age,numero_SC;
		boolean chronique;
	}
	
	public static class Medicament_stocke{
		Medicament_en_stocke Med;
		int tab_lot [];
		int indice=0;
		int quantete_totale=0;
	}
	
	static Medicament_stocke med_stock [] = new Medicament_stocke[10];
	static Medicament_stocke med_inter [] = new Medicament_stocke[10];
	static int nbr_Med_stocke =0;
	static int nbr_Med_inter =0;
	
	static Client_permanant Client []= new Client_permanant[10];
	static int nbr_Client = 0;
	static Medicament_en_stocke liste_commande [] = new Medicament_en_stocke[10];
	static int nbr_commande =0;
	
	public static int Existe_Med_stocke(Medicament_en_stocke m) {
		
		for (int i=0 ;i < pharmacie2.nbr_Med_stocke;i++ ) {
			try {
				if( pharmacie2.med_stock[i] != null && pharmacie2.med_stock[i].Med.equals(m) == true ) 
					return i ;	
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
		return -1 ;
	}
	public static int Existe_Med_inter(Medicament_en_stocke m) {
	
		for (int i=0 ;i < pharmacie2.nbr_Med_inter;i++ ) {
			try {
				if( pharmacie2.med_inter[i] != null && (pharmacie2.med_inter[i].Med).equals(m) == true)  
					return i ;	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return -1 ;
	}
	public static boolean valide_Med(Medicament_en_stocke med) {
		if (pharmacie2.Existe_Med_stocke(med) != -1 || pharmacie2.Existe_Med_inter(med) != -1) {
			boolean bool = (med.date_exp.getAnnee() >= la_date.getAnnee() && (med.date_exp.getAnnee() > la_date.getAnnee() || med.date_exp.getMois() >= la_date.getMois() ) && (med.date_exp.getAnnee() > la_date.getAnnee() || med.date_exp.getMois() > la_date.getMois() || med.date_exp.getJours() > la_date.getJours() ) );
			if (bool == false)
				System.out.println("\n attention , ce Medicament est perime");
			return bool  ;
		}
		return false;
	}
	
	public static void ajout_Med_stocke (Medicament_en_stocke m) {
		int j= Existe_Med_stocke(m);
		if (j != -1) {
			pharmacie2.med_stock[j].tab_lot[pharmacie2.med_stock[j].indice++]=m.lot; 
			pharmacie2.med_stock[j].quantete_totale += m.quant;
		}else {
			pharmacie2.med_stock[nbr_Med_stocke] = new pharmacie2.Medicament_stocke();			 
			med_stock[nbr_Med_stocke].Med = m ;
			med_stock[nbr_Med_stocke].quantete_totale += m.quant;
			med_stock[nbr_Med_stocke].tab_lot = new int[10];
			med_stock[nbr_Med_stocke].indice=0;
			med_stock[nbr_Med_stocke].tab_lot[med_stock[nbr_Med_stocke].indice] = m.lot;
			med_stock[nbr_Med_stocke++].indice++;
		}
		System.out.println("\n bonne entree !");
	}
	
	public static void ajout_Med_inter (Medicament_Interne m, int quant) {
		int j=Existe_Med_inter(m);
		if (j != -1) {
			pharmacie2.med_inter[j].quantete_totale += quant;
		}else {
			med_inter[nbr_Med_inter]=new Medicament_stocke();
			med_inter[nbr_Med_inter].Med = new Medicament_Interne(m.nom_Med, m.type_Med, m.mode_Med, m.vendu_sans_ord, m.seuil_min, m.composition, m.prix, m.taux_remboursement,m.date_exp);
			med_inter[nbr_Med_inter].Med.prix = m.prix;
			med_inter[nbr_Med_inter].Med.seuil_min = m.seuil_min;
			med_inter[nbr_Med_inter].Med.date_exp = m.date_exp;
			med_inter[nbr_Med_inter].quantete_totale = quant;
			med_inter[nbr_Med_inter].tab_lot=null ;
			pharmacie2.nbr_Med_inter ++;
		}
		System.out.println("\n bonne entree !");
	}
	
	public static boolean Existe_Client_Per(String nom,String prenom) {
		
		for (int i=0 ;i < pharmacie2.nbr_Client;i++ ) {
			try {
			if( pharmacie2.Client[i] != null && pharmacie2.Client[i].nom_per.equalsIgnoreCase(nom) && pharmacie2.Client[i].prenom_per.equalsIgnoreCase(prenom)); 
				return true ;	
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
		return false ;
	}
	public static void ajout_Client_Per(String nom,String Prenom,int age) {
		if(! Existe_Client_Per(nom, Prenom)) {
			pharmacie2.Client[nbr_Client]=new Client_permanant();
			pharmacie2.Client[nbr_Client].nom_per= nom.toString();
			pharmacie2.Client[nbr_Client].prenom_per = Prenom.toString() ;
			pharmacie2.Client[nbr_Client].age = age;
			System.out.println(" est ce que vous etes affilie ? 1:Oui,0:Non");
			
			int i = input.nextInt();
			if (i==1) {
				System.out.println("le numero de sucurite social = ");
				pharmacie2.Client[nbr_Client].numero_SC = input.nextInt();
			}
			System.out.println("est ce que vous etes un maladie chronique ? 1:Oui,0:Non");
			i = input.nextInt();
			if (i==1) pharmacie2.Client[nbr_Client].chronique = true;
			else pharmacie2.Client[nbr_Client].chronique = false;
			
			pharmacie2.nbr_Client ++;
			System.out.println("\n-----bonne entre----- \n");
		}else System.out.println("\n-----le client est existe deja !");
	}
	public static void achat_sans_ord(Medicament_en_stocke m,int quant) {
		if ((m instanceof Medicament_Interne && (pharmacie2.Existe_Med_inter(m) != -1) && ((Medicament_Interne)(pharmacie2.med_inter[pharmacie2.Existe_Med_inter(m)].Med)).vendu_sans_ord == false)  || (m instanceof Medicament_Externe && (pharmacie2.Existe_Med_stocke(m) != -1) && ((Medicament_Externe)(pharmacie2.med_stock[pharmacie2.Existe_Med_stocke(m)].Med)).vendu_sans_ord == false)) {
			System.out.println("\n ce medicament "+((Medicament)m).nom_Med+" ne peut pas etre vendu sans ordonnance ");
		}else {
			System.out.println("\n------le montatnt total = "+ m.achat(quant)+" DA");
			if (pharmacie2.nbr_commande > 0) {
				System.out.println("\n******le nombre total de ce medicament manque : "+pharmacie2.nbr_commande+ " , veuillez passer la commande au fournisseur correspond ");
				pharmacie2.nbr_commande =0;
			}
		}
	}
	public static void achat_avec_ord(Ordonnance ord ,int nbr_med ) {
		
		System.out.println("\n ------le montatnt total = "+ ord.Achat_ordenance(nbr_med)+" DA");
		if (pharmacie2.nbr_commande != 0) {
			System.out.println("\n ******voici la liste des medicamment a commandes :");
			for (int i=0;i<pharmacie2.nbr_commande;i++) {
				if (liste_commande[i] instanceof Medicament_Externe)
					System.out.println(((Medicament_Externe)pharmacie2.liste_commande[i]).nom_Med);
				else System.out.println(((parapharmaceutique)pharmacie2.liste_commande[i]).nom_produit);
			}
			System.out.println("veuillez passer la listes au fournisseur correspondant");
			pharmacie2.nbr_commande =0;
		}
	}
	
	
	static Medicament_en_stocke saisir_Med(int parem){
		date date_ex= new date();
		System.out.println("\t ce prouduit est un : 1 : Medicament intern / 2: Medicament externe / 3: produit parapharmaceutique ");
		int choix2 ; 
		do { choix2 = input.nextInt(); }while(choix2 < 1 || choix2 > 3);
		if (choix2==1 || choix2 == 2) {
			System.out.println("le nom du medicament :");
			String nom = input.next();
			System.out.println("le type du medicament :");
			String type = input.next();
			System.out.println("le mode du prise  du medicament :");
			String mode = input.next();
			if (parem==1) {
				if(choix2 == 1)
					return new Medicament_Interne(nom, type, mode, true, 0, "",0,0,null);
				else return new Medicament_Externe(nom, type, mode, true, 0, 0, 0,0, null, 0, null);
			}
			System.out.println("la seuil minimale de ce medicament ");
			int sueil_min= input.nextInt();
			System.out.println("ce medicament peut etre vendu sans ordanance ? 1: true | 0: false :");
			int i= input.nextInt();
			boolean vendu_sans_ord;
			if (i==1) vendu_sans_ord = true;
			else vendu_sans_ord = false;
			if (choix2==1) {
				System.out.println("la composition  du medicament :");
				String composition = input.next();
				System.out.println("le prix  du medicament :");
				float prix = input.nextFloat();
				System.out.println("le taux de remboursement % :");
				float taux = input.nextFloat();
				System.out.println("veullez saisir la date d'expiration ");
				System.out.println("jours :");	
				do { date_ex.setJours(input.nextInt()); }while(date_ex.getJours() <= 0 || date_ex.getJours() > 31);
				System.out.println("mois :");
				do { date_ex.setMois(input.nextInt()); }while(date_ex.getMois() <= 0 || date_ex.getMois() > 12);
				System.out.println("annee :");
				date_ex.setAnnee(input.nextInt());
				return new Medicament_Interne(nom, type, mode, vendu_sans_ord, sueil_min, composition, prix ,taux, date_ex);
				
			}else {
				System.out.println("numero de lot:");
				int lot= input.nextInt();
				System.out.println("quantete : ");
				int quant= input.nextInt();
				System.out.println("le prix :");
				int prix= input.nextInt();
				System.out.println("le taux de remboursement % :");
				float taux = input.nextFloat();
				System.out.println("le nom du fournisseur :");
				String nom_four = input.next();
				System.out.println("le prenom du fournisseur :");
				String prenom_four = input.next();
				Fournisseur f=new Fournisseur();
				f.nom_four= nom_four;
				f.prenom_four=prenom_four;
				System.out.println("veullez saisir la date d'expiration ");
				
				System.out.println("jours :");
				do { date_ex.setJours(input.nextInt()); }while(date_ex.getJours() <= 0 || date_ex.getJours() > 31);
				System.out.println("mois :");
				do { date_ex.setMois(input.nextInt()); }while(date_ex.getMois() <= 0 || date_ex.getMois() > 12);
				System.out.println("annee :");
				date_ex.setAnnee(input.nextInt());
				return new Medicament_Externe(nom, type, mode, vendu_sans_ord, quant, lot, sueil_min,taux, f, prix,date_ex);
			}
		}else {
			System.out.println("le nom du produit :");
			String nom = input.next();
			System.out.println("le type du produit :");
			String type = input.next();
			if(parem == 1)
				return new parapharmaceutique(10,10,10, 10, nom, type, null,null);
			System.out.println("numero de lot:");
			int lot= input.nextInt();
			System.out.println("quantete : ");
			int quant= input.nextInt();
			System.out.println("le prix :");
			int prix= input.nextInt();
			System.out.println("la seuil minimale de ce medicament ");
			int sueil_min= input.nextInt();
			System.out.println("le nom du fournisseur :");
			String nom_four = input.next();
			System.out.println("le prenom du fournisseur :");
			String prenom_four = input.next();
			Fournisseur f=new Fournisseur();
			f.nom_four= nom_four;
			f.prenom_four=prenom_four;
			System.out.println("veullez saisir la date d'expiration ");
			
			System.out.println("jours :");
			do { date_ex.setJours(input.nextInt()); }while(date_ex.getJours() <= 0 || date_ex.getJours() > 31);
			System.out.println("mois :");
			do { date_ex.setMois(input.nextInt()); }while(date_ex.getMois() <= 0 || date_ex.getMois() > 12);
			System.out.println("annee :");
			date_ex.setAnnee(input.nextInt());
			return new parapharmaceutique(quant, lot, prix, sueil_min, nom, type, f,date_ex);
			}	
	}
	
	public static void passer_commande(String nom,String prenom,Medicament_en_stocke t[],int nbr_Med) {
		
		System.out.println("\n veillez passer la commande au fournisseur correspond \n la liste des medicament a commandes : \n");
		for(int i=0;i<nbr_Med;i++) {
			if (t[i] instanceof Medicament_Externe)
				System.out.println(((Medicament_Externe)t[i]).nom_Med);
			else System.out.println(((parapharmaceutique)t[i]).nom_produit);
		}
		System.out.println("\n");
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("veullez saisir la date d'aujourd'hui ");
		System.out.println("jours :");
		pharmacie2.la_date.setJours(input.nextInt());
		System.out.println("mois :");
		pharmacie2.la_date.setMois(input.nextInt());
		System.out.println("annee :");
		pharmacie2.la_date.setAnnee(input.nextInt());
		int choix = 1;
		while(choix != 0) {
			System.out.println("Menu : veullez choisir l'un des action suivants \n 1: Ajouter un medicament ou bien un proiduit parapharmaceutique au stocke \n 2:Vendre un Medicament sans ordonances \n 3:Vendre des medicament avec ordonances \n 4:ajouter un client permanant \n 5:passer une commande par le medecin ou bien par le client  \n 0 : Exit \n ");
	
			 choix = input.nextInt();
			if (choix == 1 ) {
				System.out.println("\n Ajouter un medicament ou bien un proiduit parapharmaceutique au stocke : -----------------");
				Medicament_en_stocke med =saisir_Med(0);
				if (med instanceof Medicament_Interne) {
					System.out.println("la quantete a saisir");
					pharmacie2.ajout_Med_inter((Medicament_Interne)med,input.nextInt());
				}
			else {
					pharmacie2.ajout_Med_stocke(med);
				}
				
			}else {
				if (choix == 2) {
					System.out.println("\n Achat sans ordonnance : -----------------");
					Medicament_en_stocke med = saisir_Med(1);
					System.out.println("la quantete a acheter : ");
					pharmacie2.achat_sans_ord(med,input.nextInt());
				}else {
					if (choix == 3) {
						System.out.println("\n Achat avec ordonnance : --------------\n \t veillez saisir l'ordonnance a acheter ");
						System.out.println("nom du patient :");
						String nom_pat = input.next();
						System.out.println("prenom du medicament :");
						String prenom_pat = input.next();
						System.out.println("Age :");
						int age = input.nextInt();
						System.out.println("nom_Medcin  :");
						String nom_Medcin = input.next();
						System.out.println("prenom_Medcin :");
						String prenom_Medcin = input.next();
						System.out.println("adresse du medecin :");
						String adresse = input.next();
						System.out.println("le nombre des medicaments dans l'ordonnance :");
						int nbr = input.nextInt();
						Medicament_Prescrit t[] = new Medicament_Prescrit[10];
						for(int i=0;i<nbr;i++) {
							System.out.println("\t medicament "+ (i+1)+" :");
							t[i]= new Ordonnance.Medicament_Prescrit();
							Medicament_en_stocke med=saisir_Med(1);
							if (med instanceof Medicament_Interne) {
								t[i].Med=(Medicament_Interne)med;
							}else {
								if (med instanceof Medicament_Externe) {
									t[i].Med= (Medicament_Externe)med;
									}else {
										System.out.println("le prouduit parapharmaceutique ne peut pas etre vendu avec ordonnance");
										i--;
										continue ;
									}
								}
							
							System.out.println("\t quantete demande :");
							t[i].quant_dem = input.nextInt();
							System.out.println("\t la duree du traitement en jours :");
							t[i].dure_traitmt = input.nextInt();
						}
						pharmacie2.achat_avec_ord(new Ordonnance(nom_pat, prenom_pat, age, nom_Medcin, prenom_Medcin, adresse, t),nbr);
					}else {
						if (choix == 4) {
							System.out.println("Ajout d'un client permanant ");
							System.out.println("nom :");
							String nom = input.next();
							System.out.println("prenom :");
							String prenom = input.next();
							System.out.println("Age :");
							int age = input.nextInt();
							pharmacie2.ajout_Client_Per(nom, prenom, age);
						}else {
							if (choix ==5 ) {
								System.out.println("\n passer une commande par le medecin ou bien par le client------------");
								System.out.println("nom :");
								String nom = input.next();
								System.out.println("prenom :");
								String prenom = input.next();						
								System.out.println("prendre a l'esprit que les medicament commande sont que des medicament externe et parapharmaceutique \n quelle est le nbr des medicament : ");
								int nbr= input.nextInt();
								Medicament_en_stocke t[] = new Medicament_en_stocke[nbr];
								Medicament_en_stocke c;
								for(int i=0;i<nbr;i++) {
									c = pharmacie2.saisir_Med(1);
									if (c instanceof Medicament_Externe || c instanceof parapharmaceutique)
										t[i]=c;
									else i--;
								}
								pharmacie2.passer_commande(nom, prenom, t, nbr);
							}
						}					
					}
				}
			}
			System.out.println("\n");
		}
		System.out.println("fin du programme");
	}
	
	
	
}