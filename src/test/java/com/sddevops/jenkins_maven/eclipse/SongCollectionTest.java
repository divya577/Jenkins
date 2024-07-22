package com.sddevops.jenkins_maven.eclipse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongCollectionTest {
    private SongCollection sc;
    private Song s1;
    private Song s2;
    private Song s3;
    private Song s4;
    private final int SONG_COLLECTION_SIZE = 4;
    private SongCollection sc_with_size;
    private SongCollection sc_with_size_1;

    @BeforeEach
    void setUp() throws Exception {
        sc = new SongCollection();
        s1 = new Song("001", "good 4 u", "Olivia Rodrigo", 3.59);
        s2 = new Song("002", "Peaches", "Justin Bieber", 3.18);
        s3 = new Song("003", "MONTERO", "Lil Nas", 2.3);
        s4 = new Song("004", "bad guy", "billie eilish", 3.14);

        sc.addSong(s1);
        sc.addSong(s2);
        sc.addSong(s3);
        sc.addSong(s4);

        sc_with_size = new SongCollection(5);
        sc_with_size_1 = new SongCollection(1);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetSongs() {
        List<Song> testSc = sc.getSongs();
        assertEquals(SONG_COLLECTION_SIZE, testSc.size());
        assertTrue(testSc.contains(s1));
        assertTrue(testSc.contains(s2));
        assertTrue(testSc.contains(s3));
        assertTrue(testSc.contains(s4));
    }

    @Test
    void testAddSong() {
        List<Song> testSc = sc.getSongs();
        assertEquals(SONG_COLLECTION_SIZE, testSc.size());

        sc.addSong(new Song("005", "drivers license", "Olivia Rodrigo", 4.02));
        assertEquals(SONG_COLLECTION_SIZE + 1, testSc.size());

        sc_with_size_1.addSong(s1);
        assertEquals(1, sc_with_size_1.getSongs().size());

        sc_with_size_1.addSong(s2);
        assertEquals(1, sc_with_size_1.getSongs().size());
    }

    @Test
    void testSortSongsByTitle() {
        sc.sortSongsByTitle();
        List<Song> sortedSongs = sc.getSongs();
        assertEquals(s4, sortedSongs.get(0)); // bad guy
        assertEquals(s1, sortedSongs.get(1)); // good 4 u
        assertEquals(s3, sortedSongs.get(2)); // MONTERO
        assertEquals(s2, sortedSongs.get(3)); // Peaches
    }

    @Test
    void testSortSongsBySongLength() {
        sc.sortSongsBySongLength();
        List<Song> sortedSongs = sc.getSongs();
        assertEquals(s3, sortedSongs.get(0)); // MONTERO - 2.3
        assertEquals(s4, sortedSongs.get(1)); // bad guy - 3.14
        assertEquals(s2, sortedSongs.get(2)); // Peaches - 3.18
        assertEquals(s1, sortedSongs.get(3)); // good 4 u - 3.59
    }

    @Test
    void testFindSongsById() {
        assertEquals(s1, sc.findSongsById("001"));
        assertEquals(s2, sc.findSongsById("002"));
        assertEquals(s3, sc.findSongsById("003"));
        assertEquals(s4, sc.findSongsById("004"));
        assertNull(sc.findSongsById("999")); // Non-existent ID
    }

    @Test
    void testFindSongByTitle() {
        assertEquals(s1, sc.findSongByTitle("good 4 u"));
        assertEquals(s2, sc.findSongByTitle("Peaches"));
        assertEquals(s3, sc.findSongByTitle("MONTERO"));
        assertEquals(s4, sc.findSongByTitle("bad guy"));
        assertNull(sc.findSongByTitle("non-existent title")); // Non-existent title
    }
    
    @Test
    void testSongConstructorAndGetters() {
        Song song = new Song("006", "New Song", "New Artist", 3.5);
        assertEquals("006", song.getId());
        assertEquals("New Song", song.getTitle());
        assertEquals("New Artist", song.getArtiste());
        assertEquals(3.5, song.getSongLength());
    }

    @Test
    void testSongSetters() {
        Song song = new Song("007", "Another Song", "Another Artist", 4.0);
        song.setId("008");
        song.setTitle("Updated Song");
        song.setArtiste("Updated Artist");
        song.setSongLength(3.8);

        assertEquals("008", song.getId());
        assertEquals("Updated Song", song.getTitle());
        assertEquals("Updated Artist", song.getArtiste());
        assertEquals(3.8, song.getSongLength());
    }

    @Test
    void testSongEqualsAndHashCode() {
        Song song1 = new Song("009", "Test Song", "Test Artist", 3.7);
        Song song2 = new Song("009", "Test Song", "Test Artist", 3.7);
        Song song3 = new Song("010", "Different Song", "Different Artist", 4.2);

        assertEquals(song1, song2);
        assertNotEquals(song1, song3);
        assertEquals(song1.hashCode(), song2.hashCode());
        assertNotEquals(song1.hashCode(), song3.hashCode());
    }

    @Test
    void testSongTitleComparator() {
        Song songA = new Song("011", "AAA", "Artist", 3.0);
        Song songB = new Song("012", "BBB", "Artist", 3.0);

        assertTrue(Song.titleComparator.compare(songA, songB) < 0);
        assertTrue(Song.titleComparator.compare(songB, songA) > 0);
        assertTrue(Song.titleComparator.compare(songA, songA) == 0);
    }

    @Test
    void testSongLengthComparator() {
        Song songA = new Song("011", "AAA", "Artist", 2.0);
        Song songB = new Song("012", "BBB", "Artist", 3.0);

        assertTrue(Song.songLengthComparator.compare(songA, songB) < 0);
        assertTrue(Song.songLengthComparator.compare(songB, songA) > 0);
        assertTrue(Song.songLengthComparator.compare(songA, songA) == 0);
    }
}