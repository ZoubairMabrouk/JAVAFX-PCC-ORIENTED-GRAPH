package factory_shape;

import javafx.scene.input.MouseEvent;

public interface LineBuilder {

    void handlePressed(MouseEvent event);
    void handleReleased(MouseEvent event);
    boolean isReady();
    Shape build();
}
