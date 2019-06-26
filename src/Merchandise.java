import java.util.HashMap;

public class Merchandise {

    private HashMap<String, Integer> merchandiseList;

    public Merchandise() {
        this.merchandiseList = new HashMap<>();

            merchandiseList.put("Plain croissant", 3);
            merchandiseList.put("Chocolate croissant", 8);
            merchandiseList.put("Almond croissant", 8);
            merchandiseList.put("Escagot", 8);
            merchandiseList.put("Cheesecake slice", 15);
            merchandiseList.put("Gateau slice", 15);
            merchandiseList.put("Chocolate eclair", 10);
            merchandiseList.put("Chocolate profiterole", 5);
            merchandiseList.put("Custard tart", 10);
            merchandiseList.put("Whole gateau", 30);

        }


    public HashMap<String, Integer> getMerchandise () {
        return merchandiseList;
    }
}
