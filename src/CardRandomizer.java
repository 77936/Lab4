import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.EmptyBorder;


/**
 * Requirements:
 * - Card image files must be stored in a "cards" folder.
 * - Images should be named as: "A_of_spades.png", "10_of_hearts.png", etc.
 */
public class CardRandomizer extends JFrame{
    // === GUI Components ===
    private JPanel cardPanel;            // Panel to hold all card images

    // === Data ===
    private ArrayList<ImageIcon> cards;  // Stores the deck of card images

    // === Card details ===
    private final String[] ranks = {
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    };
    private final String[] suits = {
            "spades", "hearts", "clubs", "diamonds"
    };

    public CardRandomizer() {
        // ----- Window setup -----
        setTitle("Card Randomizer");                  // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app when closed
        setLayout(new BorderLayout());                // Layout manager

        // ----- Panel to display cards -----
        // GridLayout: 4 rows, 13 columns, with gaps between cards
        cardPanel = new JPanel(new GridLayout(4, 13, 15, 5));
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        cardPanel.setBackground(new Color(0, 100, 0)); // Green like a poker table
        add(cardPanel, BorderLayout.CENTER);           // Add to center

        // ----- Shuffle button -----
        // Button to shuffle the deck
        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.setPreferredSize(new Dimension(100, 30)); // Make button small

        // Put button inside its own panel to control alignment
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(shuffleButton);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel at bottom

        // ----- Initialize deck -----
        cards = new ArrayList<>();   // Create list for card images
        loadOrderedDeck();           // Load cards in order (Ace→King by suit)
        displayCards();              // Show them in the grid

        // ----- Shuffle button action -----
        shuffleButton.addActionListener(e -> actionPerformed());

        // ----- Final window settings -----
        setSize(1300, 700); // Width x Height
        setVisible(true);   // Make window visible
    }

    // ----- Shuffle button action -----
    public void actionPerformed() {
        Collections.shuffle(cards); // Randomize order of cards
        displayCards();             // Refresh display
    }

    /**
     * Loads the deck of cards in a fixed order:
     * - Row 1: Spades (Ace → King)
     * - Row 2: Hearts (Ace → King)
     * - Row 3: Clubs  (Ace → King)
     * - Row 4: Diamonds (Ace → King)
     *
     * Each card image is scaled to 80x120 pixels.
     */
    private void loadOrderedDeck() {
        cards.clear(); // Remove any existing cards

        for (String suit : suits) {          // Loop through each suit
            for (String rank : ranks) {      // Loop through each rank
                // Build filename, e.g., "cards/A_of_spades.png"
                String filename = "cards/" + rank + "_of_" + suit + ".png";

                // Load the image
                ImageIcon card = new ImageIcon(filename);

                // Scale the image so all cards have the same size
                Image img = card.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);

                // Wrap scaled image back in ImageIcon and add to deck
                cards.add(new ImageIcon(img));
            }
        }
    }

    /**
     * Displays all cards currently in the "cards" list.
     * The order in the ArrayList determines the order in the grid.
     */
    private void displayCards() {
        cardPanel.removeAll(); // Clear the old cards

        // Add each card image as a JLabel (so it can be displayed)
        for (ImageIcon card : cards) {
            cardPanel.add(new JLabel(card));
        }

        // Refresh the panel to show the updated card layout
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // Main method: program entry point.
    public static void main(String[] args) { new CardRandomizer();}
}
