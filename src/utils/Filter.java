package utils;

import common.Constants;
import entertainment.Video;

import java.util.ArrayList;
import java.util.List;

public interface Filter {

    /**
     * Method that selects videos from the Database
     * @param videos to be filtered (movies or shows)
     * @param filters can be by year or by genre (or both simultaneously)
     * @return List of videos that comply the filters
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
                if (video.getYear() == year && video.hasGenre(genre)
                        || video.getYear() == year && genre == null) {
                    filteredList.add(video);
                }
            }
        } else if (genre != null) {
            for (Video video : videos) {
                if (video.hasGenre(genre)) {
                    filteredList.add(video);
                }
            }
        }
        return filteredList;
    }
}
