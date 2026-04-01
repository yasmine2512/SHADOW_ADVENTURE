package Game;

import javax.swing.JTextArea;

abstract class Personnage implements Attaquable {
	protected String nom;
	protected int pointsDeVie;
	protected int degats; // Dégâts de base
	protected int niveau;
	protected int experience;
	private JTextArea textArea;

	
	public Personnage(String nom, int pointsDeVie, int degats,JTextArea textArea) {
		this.nom = nom;
		this.pointsDeVie = pointsDeVie;
		this.degats = degats;
		this.niveau = 1;
		this.experience = 0;
		this.textArea = textArea;
	}
	public Personnage(String nom, int pointsDeVie, int degats) {
		this.nom = nom;
		this.pointsDeVie = pointsDeVie;
		this.degats = degats;
		this.niveau = 1;
		this.experience = 0;
	}

	public void recevoirDegats(int degats) {
		pointsDeVie -= degats;
		textArea.append(nom + " a reçu " + degats + " points de dégâts. Points de vie restants : " + pointsDeVie +"\n" );
	}

	public boolean estVivant() {
		return pointsDeVie > 0;
	}

	public String getNom() {
		return nom;
	}

	public void gagnerExperience(int xp) {
		experience += xp;
		if (experience >= 100) {
			niveau++;
			experience = 0;
			textArea.append(nom + " a atteint le niveau " + niveau + " ! \n");
		}
	}

}
