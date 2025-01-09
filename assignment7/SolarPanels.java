/**
 * Class represents solar panels, street map, and
 * an array of parking lot projects.
 * 
 * @author Jessica De Brito
 * @author Kal Pandit
 */
public class SolarPanels {
    
    private Panel[][] panels;
    private String[][] streetMap;
    private ParkingLot[] lots;

    /**
     * Default constructor: initializes empty panels and objects.
     */
    public SolarPanels() {
        panels = null;
        streetMap = null;
        lots = null;
        StdRandom.setSeed(2023);
    }

    /**
     * Updates the instance variable streetMap to be an l x w
     * array of Strings. Reads each label from input file in parameters.
     * 
     * @param streetMapFile the input file to read from
     */
    public void setupStreetMap(String streetMapFile) {
        // WRITE YOUR CODE HERE
        StdIn.setFile(streetMapFile);
        int length = StdIn.readInt();
        int width = StdIn.readInt();
        streetMap = new String[length][width];
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < width; j++) {
                streetMap[i][j] = StdIn.readString();
            }
        }
    }

    /**
     * Adds parking lot information to an array of parking lots.
     * Updates the instance variable lots to store these parking lots.
     * 
     * @param parkingLotFile the lot input file to read
     */
    public void setupParkingLots(String parkingLotFile) {
        // WRITE YOUR CODE HERE
        StdIn.setFile(parkingLotFile);
        int items = StdIn.readInt();
        lots = new ParkingLot[items];
        for(int i = 0; i < items; i++) {
            String lotName = StdIn.readString();
            int maxPanels = StdIn.readInt();
            double budget = StdIn.readDouble();
            int energyCapacity = StdIn.readInt();
            double panelEfficiency = StdIn.readDouble();

            lots[i] = new ParkingLot(lotName, maxPanels, budget, energyCapacity, panelEfficiency);
        }

    }

    /**
     * Insert panels on each lot as much as space and budget allows.
     * Updates the instance variable panels to be a 2D array parallel to
     * streetMap, storing panels placed.
     * 
     * Panels have a 95% chance of working. Use StdRandom.uniform(); if
     * the resulting value is < 0.95 the panel works.
     * 
     * @param costPerPanel the fixed cost per panel, as a double
     */
    public void insertPanels(double costPerPanel) {
        // WRITE YOUR CODE HERE
        panels = new Panel[streetMap.length][streetMap[0].length];
        
        for(int p = 0; p < lots.length; p++) {
            double curBudget = lots[p].getBudget();
            String curName = lots[p].getLotName();
            int numPanels = 0;
            for(int i = 0; i < panels.length; i++) {
                for(int j = 0; j < panels[i].length; j++) {
                    if(curName.equals(streetMap[i][j]) && curBudget-costPerPanel >= 0 && numPanels < lots[p].getMaxPanels()) {
                        double ratedEfficiency = lots[p].getPanelEfficiency();
                        int maxOutput = lots[p].getEnergyCapacity();
                        Boolean works = StdRandom.uniform() < .95;
                        panels[i][j] = new Panel(ratedEfficiency, maxOutput, works);
                        curBudget -= costPerPanel;
                        numPanels++;
                    }
                }
            }
        }
    }

    /**
     * Given a temperature and coefficient, update panels' actual efficiency
     * values. Panels are most optimal at 77 degrees F.
     * 
     * Panels perform worse in hotter environments and better in colder ones.
     * worse = efficiency loss, better = efficiency gain.
     * 
     * Coefficients are usually negative to represent energy loss.
     * 
     * @param temperature the current temperature, in degrees F
     * @param coefficient the coefficient to use
     */
    public void updateActualEfficiency(int temperature, double coefficient) {
        // WRITE YOUR CODE HERE
        double efficiency = coefficient * (temperature - 77);
        for(int i = 0; i < panels.length; i++) {
            for(int j = 0; j < panels[i].length; j++) {
                if(panels[i][j] != null) 
                    panels[i][j].setActualEfficiency(panels[i][j].getRatedEfficiency()-efficiency);
            }
        }
    }

    /**
     * For each WORKING panel, update the electricity generated for 4 hours 
     * of sunlight as follows:
     * 
     * (actual efficiency / 100) * 1500 * 4
     * 
     * RUN updateActualEfficiency BEFORE running this method.
     */
    public void updateElectricityGenerated() {
        // WRITE YOUR CODE HERE
        for(int i = 0; i < panels.length; i++) {
            for(int j = 0; j < panels[i].length; j++) {
                if(panels[i][j] != null) {
                    panels[i][j].setElectricityGenerated((int)((panels[i][j].getActualEfficiency()/100 * 1500)*4)); 
                    if(!panels[i][j].isWorking()) {
                        panels[i][j].setElectricityGenerated(0);
                    }
                }   
            }
        }
    }

    /**
     * Count the number of working panels in a parking lot.
     * 
     * @param parkingLot the parking lot name
     * @return the number of working panels
     */
    public int countWorkingPanels(String parkingLot) {
        // WRITE YOUR CODE HERE
        int count = 0;
        for(int i = 0; i < panels.length; i++) {
            for(int j = 0; j < panels[i].length; j++) {
                if(streetMap[i][j].equals(parkingLot))  {
                    if(panels[i][j].isWorking()) {
                        count++;
                    }
                }
            }
        }
        return count; // PLACEHOLDER TO AVOID COMPILATION ERROR - REPLACE WITH YOUR CODE
    }

    /**
     * Find the broken panels in the map and repair them.
     * @return the count of working panels in total, after repair
     */
    public int updateWorkingPanels() {
        // WRITE YOUR CODE HERE
        int count = 0;
        for(int i = 0; i < panels.length; i++) {
            for(int j = 0; j < panels[i].length; j++) {
                if(panels[i][j] != null) {
                    if(!panels[i][j].isWorking()) {
                        panels[i][j].setIsWorking(true);
                        count++;
                    }
                }
                
            }
        }
        return count;
        // PLACEHOLDER TO AVOID COMPILATION ERROR - REPLACE WITH YOUR CODE
    }

    /**
     * Calculate Rutgers' savings on energy by using
     * these solar panels.
     * 
     * ASSUME:
     * - Multiply total electricity generated by 0.001 to convert to KwH.
     * - There are 365 days in a year.
     * 
     * RUN electricityGenerated before running this method.
     */
    public double calculateSavings() {
        // WRITE YOUR CODE HERE
        double sum = 0;
        for(int i = 0; i < panels.length; i++) {
            for(int j = 0; j < panels[i].length; j++) {
                if(panels[i][j] != null) {
                    sum += panels[i][j].getElectricityGenerated();
                }
            }
        }
        double kwH = sum * .001 * 365;
        return (kwH/4270000) * 60000000; // PLACEHOLDER TO AVOID COMPILATION ERROR - REPLACE WITH YOUR CODE
    }

    /*
     * Getter and Setter methods
     */
    public Panel[][] getPanels() {
        // DO NOT TOUCH THIS METHOD
        return this.panels;
    }

    public void setPanels(Panel[][] panels) {
        // DO NOT TOUCH THIS METHOD
        this.panels = panels;
    }

    public String[][] getStreetMap() {
        // DO NOT TOUCH THIS METHOD
        return this.streetMap;
    }

    public void setStreetMap(String[][] streetMap) {
        // DO NOT TOUCH THIS METHOD
        this.streetMap = streetMap;
    }

    public ParkingLot[] getLots() {
        // DO NOT TOUCH THIS METHOD
        return this.lots;
    }

    public void setLots(ParkingLot[] lots) {
        // DO NOT TOUCH THIS METHOD
        this.lots = lots;
    }
}
