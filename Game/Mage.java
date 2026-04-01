package Game;

import javax.swing.JTextArea;

class Mage extends Personnage {
	private JTextArea textArea;
	
    public Mage(String nom,JTextArea textArea) {
        super(nom, 80, 15,textArea);
        this.textArea = textArea;
    }
    public Mage(String nom) {
        super(nom, 80, 15);
    }
   
   
    @Override
    public void attaquer(Personnage cible) {
    	textArea.append(nom + " lance un sort sur " + cible.getNom() + " ! \n");
        cible.recevoirDegats(degats);
    }

    @Override
    public void utiliserCompetence(Personnage cible) {
    	textArea.append(nom + " invoque une tempête magique ! \n");
        cible.recevoirDegats(degats + 10);
    }
}

