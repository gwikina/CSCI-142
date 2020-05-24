package jukebox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

    public class Jukebox {
        // declares private instance variables
        private int song_count;
        private HashMap<Song, Integer> list_of_songs;
        private int run;
        private int average_run;
        private int time;
        private Song top_song;
        private Song[] song_keys = null;

        public Jukebox(){
            /**
             * constructs the object with all instance variables given a default variables
             *
             * @param none
             *
             * @return none
             *
             */

            this.list_of_songs = new HashMap<>();
            this.song_count = 0;
            this.run = 0;
            this.average_run = 0;
            this.time = 0;
            this.top_song = null;

        }
        public ArrayList <String> read_file(String filename, ArrayList songs) throws FileNotFoundException {
            /**
             * reads text file and adds each line in the file to an arraylist
             *
             * @param filename filename of the file we are using as a parameter
             *
             * @param songs list of songs
             *
             * @return none
             *
             */
            Scanner in = new Scanner(new File(filename));
            while (in.hasNextLine()){
                songs.add(in.nextLine());
            }
            return songs;
        }

        public void build_map(ArrayList<String> songs) {
            /**
             * builds hashmap with the elements with elements of the file
             *
             * @param none
             *
             * @return none
             *
             */
            for (String line: songs){
                String[] data = line.split("<SEP>", 4);
                Song song = new Song(data[2], data[3]);
                this.list_of_songs.putIfAbsent(song, 0);
            }
            Set<Song> keys = (list_of_songs.keySet());
            song_keys = keys.toArray(new Song[keys.size()]);
        }

        public Song randomize_song(Random rand){
            /**
             * returns a random key value, representing a random song
             *
             * @param rand random number
             *
             * @return a song given by a random index
             *
             */
            return this.song_keys[rand.nextInt(this.song_count)];
        }


        public void play_song(Random rand){
            /**
             * plays the jukebox one time, also deletes duplicates that are found
             *
             * @param rand random index val
             *
             * @return none
             *
             */
            int song_count = -1;
            HashMap<Song, Integer> new_list = new HashMap<>();

            while (true) {
                song_count ++;
                Song song = this.randomize_song(rand);
                list_of_songs.put(song,  list_of_songs.get(song) + 1);
                if (new_list.containsKey(song))
                    break;
                else
                    new_list.put(song, 1);

            }
            this.run += song_count;
        }

        public int get_average_run(){
            /**
             *  Sets the average run to the number of runs to the total amount of times ran divided by how many
             times the jukebox was reset
             *
             * @param none
             *
             * @return none
             *
             */

            this.average_run = this.run/50000;
            return this.average_run;
        }


        public int get_time(long start, long end){
            /**
             *  Finds the elapsed time
             *
             * @param start the start time
             *
             * @param end the end time
             *
             * @return none
             *
             */
            this.time = (int) ((end - start) / 1000);

            return this.time;
        }

        @Override

        public String toString() {
            /**
             *  formats the details of the jukebox
             *
             * @param none
             *
             * @return pretty list of all the important info
             *
             */
            String facts = "Duration " + this.time + " second/s\n" +
                    "Number of simulations: 50000\n" +
                    "Total number of songs played: " + this.run + "\n" +
                    "Average number of songs played: " + this.average_run + "\n" +
                    "Most played song: " + "\"" + this.top_song.getSong() + "\" by \"" + this.top_song.getArtist() + "\"\n" +
                    "Songs sorted alphabetically by \"" + this.top_song.getArtist() + ":\"\n";
            String sorted = " ";
            ArrayList<Song> songs = new ArrayList<>();
            for (Song key: song_keys) {
                if (key.getArtist().equals(this.top_song.getArtist())) {
                    songs.add(key);
                }
            }
            Collections.sort(songs);
            for (Song key: songs) {
                sorted += "\t\"" + key.getSong() + "\" with " + list_of_songs.get(key) + " plays\n";
            }

            return (facts + sorted);
        }

        public Song get_most_played(){
            /**
             *  finds most played song
             *
             * @param none
             *
             * @return none
             *
             */
            int most = 0;
            Song top = null;
            for (Song key: song_keys){
                int temp = list_of_songs.get(key);
                if (temp > most) {
                    most = temp;
                    top = key;
                }
            }
            this.top_song = top;
            return this.top_song;
        }


        public int get_song_count(){
            /**
             *  sets the song count without the duplicates
             *
             * @param none
             *
             * @return none
             *
             */
            this.song_count = list_of_songs.size();
            return song_count;
        }


        public static void main(String[] args) throws FileNotFoundException {
            /**
             * runs the program
             *
             * @param args
             *
             * @throws FileNotFoundException
             *
             * @return none
             *
             */
            if (args.length != 2)
                throw new FileNotFoundException(" Usage: java Jukebox filename seed");
            ArrayList<String> songs = new ArrayList<>();
            Jukebox jbox = new Jukebox();
            long start = System.currentTimeMillis();
            songs = jbox.read_file(args[0], songs);
            Random rand = new Random(Long.parseLong(args[1]));
            jbox.build_map(songs);
            System.out.println("$ java Jukebox " + args[0] + " " + args[1]);
            System.out.println("Jukebox of " + jbox.get_song_count() + " songs starts rockin'...");
            for(int i = 0; i < 50000; i++){
                jbox.play_song(rand);
            }
            jbox.get_average_run();
            jbox.get_most_played();
            long end = System.currentTimeMillis();
            jbox.get_time(start, end);
            System.out.println(jbox.toString());

        }
    }
