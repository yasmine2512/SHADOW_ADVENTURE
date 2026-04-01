package Game;

import javax.swing.JTextArea;

class Guerrier extends Personnage {
	private JTextArea textArea;
	
    public Guerrier(String nom,JTextArea textArea) {
        super(nom, 100, 10,textArea);
        this.textArea = textArea;
    }
    public Guerrier(String nom) {
        super(nom, 100, 10);
    }
   
    @Override
    public void attaquer(Personnage cible) {
        cible.recevoirDegats(degats);
        textArea.append(nom + " attaque " + cible.getNom() + " ! \n");
    }

    @Override
    public void utiliserCompetence(Personnage cible) {
    	textArea.append(nom + " utilise sa compétence spéciale : Coup de rage ! \n");
        cible.recevoirDegats(degats * 2);
    }
}
