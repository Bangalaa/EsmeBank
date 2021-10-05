package fr.karamouche.esmebank;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	final private String name;
	final private ArrayList<Client> clients = new ArrayList<Client>();
	private int IDclient = 1;
	private int IDaccount = 1;
	final private Scanner sc = new Scanner(System.in);
	
	public Bank(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void menu() {
		System.out.println("Bienvenue à "+this.getName());
		System.out.println("Que voulez vous faire ?");
		System.out.println("1 : consulter mes comptes");
		System.out.println("2 : créer un compte bancaire");
		System.out.println("3 : créer un compte client");
		System.out.println("4 : quitter");
		int value = sc.nextInt();
		while (value != 4) {
			while (value != 1 && value != 2 && value != 3 && value != 4) {
				System.out.println("Que voulez vous faire ?");
				System.out.println("1 : consulter mes comptes");
				System.out.println("2 : créer un compte bancaire");
				System.out.println("3 : créer un compte client");
				System.out.println("4 : quitter");
				value = sc.nextInt();
			}
			if (value == 1 || value == 2 || value == 3 || value == 4) {
				switch(value) {
					case 1:
						this.viewAccount();
						break;
					case 2:
						this.createAccount();
						break;
					case 3:
						this.createClient();
						break;
					case 4:
						break;
					default:
						break;
				}
			}
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : consulter mes comptes");
			System.out.println("2 : créer un compte bancaire");
			System.out.println("3 : créer un compte client");
			System.out.println("4 : quitter");
			value = sc.nextInt();
		}
	}
	
	private void viewAccount() {
		System.out.print("Donnez votre ID client : ");
		int IDclient = sc.nextInt();
		Client client = null;
		for (Client element : this.getClients()) {
			if (element.getID() == IDclient) {
				client = element;
			}
		}
		if(client == null) {
			System.out.println("Votre id client n'a pas été trouvé : veuillez réessayer !");
		}else {
			System.out.print("NOM : "+client.getName()+"\n"+
					"PRENOM : "+client.getSurname()+"\n\n"+
					"COMPTES : \n");
			client.getAccountsFormat();
			//rajouter tout les choix de gestion de compte
			//(retirer, déposer et virement)
			System.out.print("Voulez-vous gérer vos comptes ? : 0/non 1/oui : ");
			int isGest = sc.nextInt();
			if(isGest == 1) {
				client.manage(sc);
			}
		}
	}

	public void createClient() {
		System.out.print("Donnez votre nom : ");
		final String name = sc.next();
		
		System.out.print("Donnez votre prénom : ");
		final String surname = sc.next();
		
		Client client = new Client(this.IDclient, name, surname);
		this.getClients().add(client);
		this.IDclient++;
		System.out.println("Votre compte client a bien été crée");
		System.out.println("Au nom de "+client.toString());
		System.out.println("Avec pour ID : "+client.getID()+" (notez le bien !)");
	}
	
	public void createAccount() {
		System.out.print("Donnez votre ID client : ");
		int IDclient = sc.nextInt();
		Client client = null;
		for (Client element : this.getClients()) {
			if (element.getID() == IDclient) {
				client = element;
			}
		}
		if(client == null) {
			System.out.println("Votre id client n'a pas été trouvé : veuillez réessayer !");
		}else {
			System.out.print("Donnez la valeur maximal de retrait : ");
			final long MaxGet = sc.nextLong();
			System.out.print("Donnez la valeur maximal de découvert : ");
			final long overdraft = sc.nextLong();
			
			Account account = new Account(IDaccount, client, MaxGet, overdraft);
			System.out.println(client.toString()+" : votre compte bancaire a bien été créé avec l'ID : "+account.getID());
			this.IDaccount++;
		}
	}
}
