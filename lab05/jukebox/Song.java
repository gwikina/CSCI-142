package jukebox;

import java.util.Objects;

public class Song implements Comparable <Song> {
    // declares variable states
    private String artist;
    private String song;
    public Song(String artist, String song){
        /**
         * constructs the object with a new artist and song
         *
         * @param artist the artist name as a string
         *
         * @param song the song name as a string
         *
         * @return none
         *
         */
        this.artist = artist;
        this.song = song;
    }
    public String getArtist(){
        /**
         * returns artist string
         *
         * @param none
         *
         * @return String artist
         *
         */
        return artist;
    }

    public String getSong() {
        /**
         * returns song string
         *
         * @param none
         *
         * @return String song
         *
         */
        return song;
    }

    @Override
    public boolean equals(Object o) {
        /**
         * decides whether the objects are equal
         *
         * @param o another object of the same type
         *
         * @return true if objects have the same artist and same song,
         *
         * @return false, otherwisew
         *
         */
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song1 = (Song) o;
        return Objects.equals(artist, song1.artist) &&
                Objects.equals(song, song1.song);
    }

    @Override
    public int hashCode() {
        /**
         * calculates and returns the hashcode for both artist and song
         *
         * @param none
         *
         * @return artist.hashCode() + song.hashCode()
         *
         */
        int result = artist.hashCode() + song.hashCode();
        return result;
    }

    @Override
    public int compareTo(Song other) {
        /**
         * decide which object of type song is larger
         *
         * @param other
         *
         * @return return 1, 0, -1 depending on if the current object is greater, equal, or less
         than the second object
         *
         */

        if (this.artist.compareTo(other.artist) > 0)
            return 1;
        else if (this.artist.compareTo(other.artist) < 0)
            return - 1;
        else{
            if (this.song.compareTo(other.song) > 0)
                return 1;
            else if (this.artist.compareTo(other.artist) < 0)
                return -1;
            else
                return 0;
        }
    }
}
