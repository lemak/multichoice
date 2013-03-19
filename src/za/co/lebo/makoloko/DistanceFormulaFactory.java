package za.co.lebo.makoloko;


public class DistanceFormulaFactory {
	/**
	 * This is a factory method that is used to produce the different types of distance formulas as may
	 * be required by the A* algorithm. At the moment only one type of a distance formula is implemented (Manhattan). 
	 * In future many other types of formulas can be added by adding a new enum as well as changing the switch statement.  
	 *
	 */
    public static AstarSearch getDistanceFormula(DistanceFormula distanceFormula) {
        switch(distanceFormula) {
            case Manhattan:
                return new Manhattan();
            default:
                return null;
        }
    }
}
