import view_controler.GUI.GUIView;
import view_controler.text.TextView;

/**
 * Created by abacef on 6/2/17.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            TextView view = new TextView();
            view.begin();
        }
        else {
            GUIView view = new GUIView();
            view.begin();
        }
    }

}
