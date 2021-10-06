package fr.karamouche.esmebank;

public class Account {
	private final int ID;
	private Client client;
	private long solde = 0;
	private long maxGet; //maximum de retrait
	private long overdraft; //d�couvert bancaire
	private int nbOperations = 0;
	
	public Account(int ID, Client client, long maxGet, long overdraft) {
		this.setClient(client);
		this.ID = ID;
		this.setMaxGet(maxGet);
		this.overdraft = -overdraft;
		this.client.addAcount(this);
	}

	public int getID() {
		return ID;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public long getSolde() {
		return solde;
	}

	public void setSolde(long solde) {
		this.solde = solde;
	}

	public long getMaxGet() {
		return maxGet;
	}

	public void setMaxGet(long maxGet) {
		this.maxGet = maxGet;
	}
	
	public void withdraw(long amount) {
		if(amount > 0) {
			if (amount <= this.maxGet) {
				if (this.solde - amount > this.overdraft) {
					this.solde -= amount;
					System.out.println("Vous avez retir� "+amount+"� avec succ�s");
					System.out.println("Le solde du compte est maintenant de "+this.getSolde()+"�");
					this.nbOperations++;
				}else {
					System.out.println("Erreur : vous allez d�passer votre d�couvert maximal autoris�");
				}

			}else {
				System.out.println("Erreur : vous avez d�pass� la somme maximal que vous pouvez retirer ("+this.maxGet+"�)");
			}
		}else {
			System.out.println("Erreur : veuillez indiquer une somme positive");
		}
	}
	
	public void deposit(long amount) {
		if(amount > 0) {
			this.solde += amount;
			System.out.println("Vous avez d�pos� "+amount+"� avec succ�s");
			System.out.println("Le solde du compte est maintenant de "+this.getSolde()+"�");
			nbOperations++;
		}else {
			System.out.println("Erreur : veuillez indiquer une somme positive");
		}
	}
	
	public void transfert(long amount, Account account) {
		if(amount > 0) {
			if (amount <= this.maxGet) {
				if (this.solde - amount > this.overdraft) {
					this.solde -= amount;
					System.out.println("Vous avez vir� "+amount+"� avec succ�s � "+account.getClient().toString());
					System.out.println("Le solde de votre compte est maintenant de "+this.getSolde()+"�");
					account.setSolde(account.getSolde()+amount);
				}else {
					System.out.println("Erreur : vous allez d�passer votre d�couvert maximal autoris�");
				}

			}else {
				System.out.println("Erreur : vous avez d�pass� la somme maximal que vous pouvez virer ("+this.maxGet+"�)");
			}
		}else {
			System.out.println("Erreur : veuillez indiquer une somme positive");
		}
	}
	
}
