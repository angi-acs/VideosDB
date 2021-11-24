package actions;

import common.Constants;
import entertainment.Video;

import java.util.*;

public interface Sort {

    /**
     * a
     * @param map b
     * @param sortType c
     * @param number e
     * @return f
     */
    default ArrayList<String> sort(final LinkedHashMap<String, Double> map,
                                   final String sortType, final Integer number) {

        ArrayList<Map.Entry<String, Double>> sortedList = new ArrayList<>(map.entrySet());

        if (sortType.equals("mydesc")) {
            sortedList.sort((item1, item2) -> Double.compare(item2.getValue(), item1.getValue()));
        } else {
            sortedList.sort(Map.Entry.comparingByKey());
            switch (sortType) {
                case Constants.ASC -> {
                    sortedList.sort(Comparator.comparingDouble(Map.Entry::getValue));
                }
                case Constants.DESC -> {
                    Collections.reverse(sortedList);
                    sortedList.sort((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
                }

                default -> throw new IllegalStateException("Unexpected value: " + sortType);
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            if (result.size() < number && entry.getValue() != 0) {
                System.out.println(entry.getKey() + entry.getValue());
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * a
     * @param videos b
     * @param filters c
     * @return d
     */
    default List<Video> filter(List<Video> videos, final List<List<String>> filters) {
        List<Video> filteredList = new ArrayList<>();
        String genre = filters.get(Constants.GENRE_FILTER).get(0);

        if (filters.get(Constants.YEAR_FILTER).get(0) == null && genre == null) {
            filteredList.addAll(videos);
        }

        if (filters.get(Constants.YEAR_FILTER).get(0) != null) {
            int year = Integer.parseInt(filters.get(Constants.YEAR_FILTER).get(0));
            for (Video video : videos) {
                if (video.getYear() == year && video.findGenre(genre) != null) {
                    filteredList.add(video);
                } else if (video.getYear() == year && genre == null) {
                    filteredList.add(video);
                }
            }
        } else if (genre != null) {
            for (Video video : videos) {
                if (video.findGenre(genre) != null) {
                    filteredList.add(video);
                }
            }
        }
        return filteredList;
    }
}
