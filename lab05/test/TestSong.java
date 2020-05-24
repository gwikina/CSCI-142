package test;

import jukebox.Song;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test framework for the jukebox.Song class.
 *
 * @author RIT CS
 */
public class TestSong {
    @Test
    public void testSongAccessors() {
        Song song1 = new Song("Lil Nas X", "Old Town Road");
        assertEquals("Lil Nas X", song1.getArtist());
        assertEquals("Old Town Road", song1.getSong());

        Song song2 = new Song("Tool", "Pneuma");
        assertEquals("Tool", song2.getArtist());
        assertEquals("Pneuma", song2.getSong());
    }

    @Test
    public void testSongEquals() {
        Song song1 = new Song("Adele", "Rolling in the Deep");
        Song song2 = new Song("Adele", "Rolling in the Deep");
        Song song3 = new Song("Linkin Park", "Rolling in the Deep");
        Song song4 = new Song("Adele", "Hello");

        assertEquals(song1, song2);
        assertNotEquals(song1, song3);
        assertNotEquals(song1, song4);
    }

    @Test
    public void testSongHashCode() {
        Song song1 = new Song("Phish", "Chalk Dust Torture");
        assertEquals(-201310392, song1.hashCode());
        Song song2 = new Song("Phish", "Chalk Dust Torture");
        assertEquals(-201310392, song2.hashCode());
        Song song3 = new Song("Massive Attack", "Angel");
        assertEquals(1115209893, song3.hashCode());
    }

    @Test
    public void testComparable() {
        Song song1 = new Song("Pinkfong", "Baby Shark");
        Song song2 = new Song("Pinkfong", "Baby Shark");
        Song song3 = new Song("Pink Floyd", "Money");
        Song song4 = new Song("Cardi B", "Money");

        assertTrue(song1.compareTo(song2) == 0);
        assertTrue(song1.compareTo(song3) > 0);
        assertTrue(song4.compareTo(song3) < 0);
    }
}