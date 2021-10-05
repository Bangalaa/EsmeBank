package fr.karamouche.esmebank;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	final private int ID;
	final private String name;
	final private String surname;
	final private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Client(int ID, String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.surname;
	}
	
	public void addAcount(Account account) {
		this.getAccounts().add(account);
	}
	
	public void getAccountsFormat() {
		for (Account element : this.getAccounts()) {
			System.out.println("ID : "+element.getID());
			System.out.println("SOLDE : "+element.getSolde()+"\n");
		}
	}

	public void manage(Scanner sc) {
		// TODO Retirer, déposer, virer
		System.out.println("Que voulez vous faire ?");
		System.out.println("1 : retirer de l'argent");
		System.out.println("2 : déposer de l'argent");
		System.out.println("3 : virement");
		System.out.println("4 : quitter");
		int value = sc.nextInt();
		while (value != 4) {
			while (value != 1 && value != 2 && value != 3 && value != 4) {
				System.out.println("Que voulez vous faire ?");
				System.out.println("1 : retirer de l'argent");
				System.out.println("2 : déposer de l'argent");
				System.out.println("3 : virement");
				System.out.println("4 : quitter");
				value = sc.nextInt();
			}
			System.out.print("Donnez l'id du compte que vous voulez gérer : ");
			final int idAccount = sc.nextInt();
			Account account = null;
			for(Account element : this.getAccounts()) {
				if(element.getID() == idAccount) {
					account = element;
				}
			}
			
			if ((value == 1 || value == 2 || value == 3 || value == 4) && account != null) {
				switch(value) {
					case 1:
						System.out.print("Combien voulez vous retirer : ");
						long amountWithdraw = sc.nextLong();
						account.withdraw(amountWithdraw);
						break;
					case 2:
						System.out.print("Combien voulez vous déposer : ");
						long amountDeposit = sc.nextLong();
						account.deposit(amountDeposit);
						break;
					case 3:
						System.out.println("Virement");
						break;
					case 4:
						break;
					default:
						break;
				}
			}else if(account == null) {
				System.out.println("Votre compte n'a pas été trouvé : veuillez réessayer !");
			}
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : retirer de l'argent");
			System.out.println("2 : déposer de l'argent");
			System.out.println("3 : virement");
			System.out.println("4 : quitter");
			value = sc.nextInt();
		}
		
	}
	
}
