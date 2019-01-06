import javax.sound.midi.*;

public class MusicTest1{
    public void play(){
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("we got a Sequencer");
        }catch (MidiUnavailableException ex){
            System.out.println("Line 9:This have a MidiUnavailableException");
        }

    }
    public static void main(String[] args) {
        MusicTest1 mt = new MusicTest1();
        mt.play();
    }
}