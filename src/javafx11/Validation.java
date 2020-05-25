

package javafx11;

import javafx.scene.control.TextField;

public class Validation {

    public boolean emptyTextField(TextField t) {
        return t.getText().trim().equals("");
    }
}
