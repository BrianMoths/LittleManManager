/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package littlemangame.littleman.littlemanutilities.location;

/**
 * this class represents a location in the office.
 *
 * @author brian
 */
public enum OfficeLocation {

    /**
     * the location of the page number sheet
     */
    PAGE_NUMBER_SHEET(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getPageNumberSheetPosition());
        }

    }),
    /**
     * the location of the worksheet
     */
    WORKSHEET(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getWorksheetPosition());

        }

    }),
    /**
     * the location of the remembered notebook page
     */
    REMEMBERED_NOTEBOOK_PAGE(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getRememberedPagePosition());
        }

    }),
    /**
     * the location of the output panel
     */
    OUTPUT_PANEL(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getOutputPanelPosition());

        }

    }),
    /**
     * the location of the input panel
     */
    INPUT_PANEL(new PositionGetter() {

        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return littleManPosition.goToPoint(littleManPosition.getPositionGetterAdapter().getInputPanelPosition());
        }

    }),
    /**
     * the current little man's location
     */
    CURRENT_LOCATION(new PositionGetter() {
        @Override
        public boolean goTo(LittleManPosition littleManPosition) {
            return true;
        }

    });
    private final PositionGetter positionGetter;

    private OfficeLocation(PositionGetter positionGetter) {
        this.positionGetter = positionGetter;
    }

    boolean goTo(LittleManPosition littleManPosition) {
        return positionGetter.goTo(littleManPosition);

    }

    private static interface PositionGetter {

        public boolean goTo(LittleManPosition littleManPosition);

    }

}
