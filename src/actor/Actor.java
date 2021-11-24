package actor;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

@Getter
public class Actor {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;

    public Actor(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
    }

    /**
     * a
     * @return b
     */
    public int getAwardsNumber() {
        int count = 0;
        for (Map.Entry<ActorsAwards, Integer> award : awards.entrySet()) {
            count += award.getValue();
        }
        return count;
    }
}
