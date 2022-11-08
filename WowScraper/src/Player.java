public class Player {

    private int rank;
    private int rating;
    private String name;
    private int level;
    private String spec;
    private String wowClass;
    private String realm;

    // player object constructor
    public Player(int rank, int rating, String name, int level, String spec, String wowClass, String realm) {
        this.rank = rank;
        this.rating = rating;
        this.name = name;
        this.level = level;
        this.spec = spec;
        this.wowClass = wowClass;
        this.realm = realm;
    }



    int getRank() {
        return this.rank;
    }

    int getRating() {
        return this.rating;
    }

    String getName() {
        return this.name;
    }

    int getLevel() {
        return this.level;
    }

    String getSpec() {
        return this.spec;
    }

    String getWowClass() {
        return this.wowClass;
    }

    String getRealm() {
        return this.realm;
    }


}
