import java.util.HashMap;

public class Merchandise {

    private HashMap<String, Double> merchandiseList;

    public Merchandise() {
        this.merchandiseList = new HashMap<>();

            merchandiseList.put("Plain croissant", 3.00);
            merchandiseList.put("Chocolate croissant", 8.00);
            merchandiseList.put("Almond croissant", 8.00);
            merchandiseList.put("Escagot", 8.00);
            merchandiseList.put("Cheesecake slice", 15.00);
            merchandiseList.put("Gateau slice", 15.00);
            merchandiseList.put("Chocolate eclair", 10.00);
            merchandiseList.put("Chocolate profiterole", 5.00);
            merchandiseList.put("Custard tart", 10.00);
            merchandiseList.put("Whole gateau", 30.00);

        }


    public HashMap<String, Double> getMerchandise () {
        return merchandiseList;
    }
}
