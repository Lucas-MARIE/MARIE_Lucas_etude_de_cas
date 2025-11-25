//
// Utilisation nominale des classes td3.Buffer, Killring et td3.Editor
//
////////////////////////////////////////////////////////////////

import fr.einfolearning.tp2.metiers.TextEditor;
import fr.einfolearning.tp2.metiers.exceptions.EmacsKillRingOverflowException;

public class App {

    public static void main(String[] args) {
        // Implémentation des user stories
        // Texte initial : "je suis un editeur de texte"
        TextEditor editor = new TextEditor("je suis un editeur de texte");

        System.out.println("Initial buffer: " + editor.getBuffer());

        try {
            // 1) Sauvegarder la sous-chaîne "suis" dans le kill-ring
            // indices pour "suis" : 3..7 (end exclusive)
            editor.setCursor(3);
            editor.setMark(7);
            editor.killRingBackup();
            System.out.println("After saving 'suis' to kill-ring: " + editor.getBuffer());

            // 2) Couper la sous-chaîne "edite"
            // "edite" se trouve aux indices 11..16 (end exclusive)
            editor.setCursor(11);
            editor.setMark(16);
            editor.killSection();
            System.out.println("After cutting 'edite': " + editor.getBuffer());

            // 3) Coller la sous-chaîne coupée (yank) avant le mot "un" (position 8)
            editor.setCursor(8);
            editor.yank();
            System.out.println("After yank (paste 'edite' before 'un'): " + editor.getBuffer());

            // 4) Écraser la sous-chaîne collée par le mot "suis" présent dans le kill-ring
            // (yankPop remplace la dernière insertion yank par l'élément suivant du kill-ring)
            editor.yankPop();
            System.out.println("After yankPop (replace pasted text by 'suis'): " + editor.getBuffer());

        } catch (EmacsKillRingOverflowException e) {
            System.err.println("KillRing overflow: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Illegal operation: " + e.getMessage());
        }
    }
}
