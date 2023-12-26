
package com.mini_games;

import javafx.scene.Node;

public interface SubController {
    
    void unfold();
    
    void restore();
    
    default Node checkedLookup(Node container, String fxid) {
        Node node = container.lookup(fxid);
        if (node != null) {
            return node;
        }
        throw new NullPointerException("FXID: \""+fxid+"\"-FXML element not fount at the "+container.getId());
    }
    
}
