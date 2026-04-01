package Game;

import java.util.Random;

import javax.swing.JTextArea;

class Voleur extends Personnage {
	private JTextArea textArea;
	
	
   
    public Voleur(String nom,JTextArea textArea) {
        super(nom, 75, 12,textArea);
        this.textArea = textArea;
    }

    
   
    @Override
    public void attaquer(Personnage cible) {
    	textArea.append(nom + " attaque furtivement " + cible.getNom() + " ! \n");
        cible.recevoirDegats(degats);
        // Chance d'une attaque critique
        if (Math.random() < 0.2) {
        	textArea.append(nom + " inflige un coup critique ! \n");
            cible.recevoirDegats(degats);
        }
    }

    @Override
    public void utiliserCompetence(Personnage cible) {
    	textArea.append(nom + " utilise sa compétence spéciale : Attaque rapide ! \n");
        cible.recevoirDegats(degats * 3);
    }
}
