import view_controler.GUI.GUIView;
import view_controler.text.TextView;

/**
 * The class that started it all...
 * @author Mark Nash
 */
public class Main {

    /**
     * The method starts the respective view. If there is an argument of any
     * kind, it starts a GUI program. if not, it starts a text based program.
     *
     * @param args A string argument or lack thereof.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            TextView view = new TextView();
            view.begin();
        }
        else {
            GUIView.main(new String[0]);
        }
    }
}
