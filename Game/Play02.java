package Game;

import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ListFormat.Style;
import java.util.List;


public class Play02 {
	
	private static JFrame frame;
	private static JTextArea textArea ;
	static JButton attackButton, skillButton, passButton, selectPlayerButton;
    static JComboBox<String> playerSelectComboBox, targetSelectComboBox;
    static String currentAction = "";
	
    static Personnage guerrier;
    static Personnage mage;
    static Personnage voleur;
    static Personnage joueur;

  
   
    
    public static Personnage getPlayerByName(String name) {
        switch (name) {
            case "Thor": return guerrier;
            case "Loki": return voleur;
            case "Gandalf": return mage;
            default: return null;
        }
    }
    
    public static String[] getAlivePlayers() {
        List<String> alivePlayers = new ArrayList<>();
        
        if (guerrier.estVivant() && !guerrier.equals(joueur)) alivePlayers.add(guerrier.getNom());
        if (mage.estVivant() && !mage.equals(joueur)) alivePlayers.add(mage.getNom());
        if (voleur.estVivant() && !voleur.equals(joueur)) alivePlayers.add(voleur.getNom());
        
        // Convert the list to an array and return
        return alivePlayers.toArray(new String[0]);
    }
    
    public static Personnage showTargetSelection() {
    	String[] alivePlayers = getAlivePlayers();

        // If no players are alive, show a message and return null
        if (alivePlayers.length == 0) {
            textArea.append("Aucun joueur vivant disponible à cibler.\n");
            return null;
        }

        // Create the combo box with the alive players
        targetSelectComboBox = new JComboBox<>(alivePlayers);

        // Show the target selection dialog
        int option = JOptionPane.showConfirmDialog(frame, targetSelectComboBox, "Sélectionnez la cible", JOptionPane.OK_CANCEL_OPTION);
        
        // Handle the selection
        if (option == JOptionPane.OK_OPTION) {
        	 String selectedTarget = (String) targetSelectComboBox.getSelectedItem();

             // Ensure that the selected target is not null (in case nothing is selected)
             if (selectedTarget != null && !selectedTarget.isEmpty()) {
                 // Find the corresponding Personnage object by name
                 Personnage target = getPlayerByName(selectedTarget);
                 if (target != null) {
                     return target;
                 } else {
                     textArea.append("La cible sélectionnée est introuvable.\n");
                 }
             }
        }
        return null;
        }
    
    
    public static void checkWinLoss() {
        if (!joueur.estVivant()) {
            // If the player (joueur) is dead, the player loses.
            textArea.append(joueur.getNom() + " a été vaincu ! Vous avez perdu.\n");
            endGame();
        } else if (!mage.estVivant() && !voleur.estVivant()) {
            // If both enemies (mage and voleur) are defeated, the player wins.
            textArea.append(joueur.getNom() + " a gagné ! Vous avez triomphé des ennemis !\n");
            endGame();
        }
    }

    // Method to end the game
    public static void endGame() {
        attackButton.setEnabled(false);
        skillButton.setEnabled(false);
        passButton.setEnabled(false);
        // Optionally, display a restart button or exit the game
    }

    
    public static void main(String[] args) {
    	
    	
    	frame = new JFrame("Shadow Adventures");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create text area for game updates
 
		textArea = new JTextArea(10,30);
        textArea.setEditable(false);
        
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        

        textArea.setText("                              Welcome to Shadow Adventures! Choose your character to begin.\n");
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        guerrier = new Guerrier("Thor", textArea);  // Pass initialized textArea
        mage = new Mage("Gandalf", textArea);  // Pass initialized textArea
         voleur = new Voleur("Loki", textArea);
         joueur = guerrier;
         
        // Panel for selecting the character and actions
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // ComboBox to select player
        playerSelectComboBox = new JComboBox<>(new String[]{"Warrior", "Thief", "Wizard"});
        panel.add(playerSelectComboBox);

        // Action buttons (Attack, Skill, Pass)
        attackButton = new JButton("attaquer");
        skillButton = new JButton("utiliserCompetence");
        passButton = new JButton("pass le tour");
        selectPlayerButton = new JButton("Sélectionner un joueur");

        panel.add(attackButton);
        panel.add(skillButton);
        panel.add(passButton);
        panel.add(selectPlayerButton);
        
        attackButton.setVisible(false);
        skillButton.setVisible(false);
        passButton.setVisible(false);
        
      
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = "Attack";
                Personnage target = showTargetSelection();
                if ( target != null && target.estVivant() ){
                	
                	textArea.setText("");
                joueur.attaquer(target);
                   joueur.gagnerExperience(20);  
                   textArea.append("Tu a recu 20 xp \n");
           if(!target.estVivant()) {
        	   textArea.append(target.getNom() +" est vaincu ! \n" );
           }
           int randomNumber = (int) (Math.random() * 2) + 1;
           	if(randomNumber == 1) {
                if (mage.estVivant()) {
                   mage.attaquer(joueur);
                
                    if (!joueur.estVivant()) {
                      textArea.append(joueur.getNom() + " est vaincu ! \n");
                     
                    }
                }
           	if(voleur.estVivant() && mage.estVivant()) {
           		voleur.attaquer(mage);
           		if(!mage.estVivant()) {
             	   textArea.append(mage.getNom() +" est vaincu ! \n" );
                }
           		}}
           	else {

                if (voleur.estVivant()) {
                    voleur.attaquer(joueur);
                    if (!joueur.estVivant()) {
                    	textArea.append(joueur.getNom() + " est vaincu ! \n");
                     
                    }
                }
            	if(voleur.estVivant() && mage.estVivant()) {
               		mage.attaquer(voleur);
               		if(!voleur.estVivant()) {
                  	   textArea.append(voleur.getNom() +" est vaincu ! \n" );
                     }}}
                
                checkWinLoss();
            }}
        });

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAction = "Special Skill";
                Personnage target = showTargetSelection();
                if ( target != null && target.estVivant()) {
                	textArea.setText("");
                joueur.utiliserCompetence(target); 
                joueur.gagnerExperience(25);
                textArea.append("Tu a recu 25 xp \n");
                if(!target.estVivant()) {
             	   textArea.append(target.getNom() +" est vaincu ! \n" );
                }
                
                int randomNumber = (int) (Math.random() * 2) + 1;
               	if(randomNumber == 1) {
                    if (mage.estVivant()) {
                       mage.utiliserCompetence(joueur);
                    
                        if (!joueur.estVivant()) {
                          textArea.append(joueur.getNom() + " est vaincu ! \n");
                         
                        }
                    }
               	if(voleur.estVivant() && mage.estVivant()) {
               		voleur.utiliserCompetence(mage);
               		if(!mage.estVivant()) {
                 	   textArea.append(mage.getNom() +" est vaincu ! \n" );
                    }
               		}}
               	else {

                    if (voleur.estVivant()) {
                        voleur.utiliserCompetence(joueur);
                        if (!joueur.estVivant()) {
                        	textArea.append(joueur.getNom() + " est vaincu ! \n");
                         
                        }
                    }
                	if(voleur.estVivant() && mage.estVivant()) {
                   		mage.utiliserCompetence(voleur);
                   		if(!voleur.estVivant()) {
                      	   textArea.append(voleur.getNom() +" est vaincu ! \n" );
                         }}
                    }
                checkWinLoss();
            }}
        });

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 textArea.append(joueur.getNom() + " passe son tour. \n");
            	 checkWinLoss();
            }
        });
       
        selectPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlayer = (String) playerSelectComboBox.getSelectedItem();
                switch (selectedPlayer) {
                    case "Warrior":
                    	joueur = guerrier;
                        break;
                    case "Thief":
                    	joueur = voleur;
                        break;
                    case "Wizard":
                    	joueur = mage;
                        break;
                }
                textArea.setText("Vous avez sélectionné: " + joueur.nom + "\n");
                selectPlayerButton.setVisible(false);
                playerSelectComboBox.setVisible(false);
                attackButton.setVisible(true);
                skillButton.setVisible(true);
                passButton.setVisible(true);

            }
        });

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
        
       
    }
    
}
      
 