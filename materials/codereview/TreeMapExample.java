import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, Double> map = new TreeMap<>();
        map.put(0, 0.015);
        map.put(1000, 0.01);
        map.put(5000, 0.005);

        int amountInRecipientCurrency = 2000;
        Double discount = map.lowerEntry(amountInRecipientCurrency).getValue();

//        if (amountInRecipientCurrency < 1000) {
//            Commission commission = new Commission(amountInRecipientCurrency * 0.015, user);
//            commissionRepository.save(commission);
//        }
//        if (amountInRecipientCurrency > 1000) {
//            Commission commission = new Commission(amountInRecipientCurrency * 0.01, user);
//            commissionRepository.save(commission);
//        }
//        if (amountInRecipientCurrency > 5000) {
//            Commission commission = new Commission(amountInRecipientCurrency * 0.005, user);
//            commissionRepository.save(commission);
//        }


    }
}
