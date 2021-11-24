package utils;

import common.Constants;

import java.util.*;


public interface Sort {

    /**
     * Method that sorts a map using an ArrayList
     * @param map to be sorted
     * @param sortType can be ascending or descending (both by value and alphabetically)
     * @param number that limits elements in the ArrayList
     * @return ArrayList containing only the String from the sorted map
     */
    default ArrayList<String> sort(final LinkedHashMap<String, Double> map,
                                   final String sortType, final Integer number) {

        ArrayList<Map.Entry<String, Double>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort(Map.Entry.comparingByKey());
        switch (sortType) {
            case Constants.ASC ->
                sortedList.sort(Comparator.comparingDouble(Map.Entry::getValue));
            case Constants.DESC -> {
                Collections.reverse(sortedList);
                sortedList.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
            }
            default -> throw new IllegalStateException("Unexpected value: " + sortType);
        }

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            if (result.size() < number && entry.getValue() != 0) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
