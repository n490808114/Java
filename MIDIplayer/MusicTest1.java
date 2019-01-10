import javax.sound.midi.*;

public class MusicTest1{
    public void play(int noteOnNote00,int noteOnNote01){
        try{
            Sequencer player = MidiSystem.getSequencer();//got a Player
            player.open();//open it
            Sequence cd = new Sequence(Sequence.PPQ,4);//got a CD
            Track song =cd.createTrack();//create a song track on CD

            ShortMessage message00 = new ShortMessage();
            message00.setMessage(144, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(message00, 1);

            ShortMessage message01 = new ShortMessage();
            message01.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(message01,16);

            song.add(noteOn);
            song.add(noteOff);

            player.setSequence(cd);//put CD on Player
            player.start();//start player
        }catch (MidiUnavailableException mue){
            System.out.println("Line 9:This have a MidiUnavailableException");
        }catch(InvalidMidiDataException imde){
            System.out.println("Line 13:This have a InvalidMidiDataException");
        }
    }
    public static void main(String[] args) {
        MusicTest1 mt = new MusicTest1();
        if (args.length<2 || args.length >2){
            System.out.println("not enough length");
        }else{
            int noteOnNote00 = Integer.parseInt(args[0]);
            int noteOnNote01 = Integer.parseInt(args[1]);
            mt.play(noteOnNote00,noteOnNote01);
        }

    }
}