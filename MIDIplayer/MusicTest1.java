import javax.sound.midi.*;

public class MusicTest1{
    GuiTest guiTest = new GuiTest();
    GuiTest.Gui2 gui2 = guiTest.new Gui2();
    GuiTest.Gui2.MyDrawPanel myDrawPanel = gui2.new MyDrawPanel();
    public static void main(String[] args) {
        MusicTest1 musicTest1 = new MusicTest1();
        musicTest1.go();
    }
    public void go(){
        guiTest.go();
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            Sequence cd = new Sequence(Sequence.PPQ,4);
            Track songTrack = cd.createTrack();

            int[] controllerEvent = {127};
            player.addControllerEventListener(guiTest.gui2.myDrawPanel,controllerEvent);

            for(int i=1;i<61;i++){
                songTrack.add(makeEvent(144,1,i,100,i));
                songTrack.add(makeEvent(176,1,127,0,i));
                songTrack.add(makeEvent(128,1,i,100,i+2));
            }
            player.setSequence(cd);
            player.setTempoInBPM(220);
            player.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tack){
        MidiEvent result =null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(command,channel,data1,data2);
            result = new MidiEvent(a,tack);
        }catch (InvalidMidiDataException ex){ex.printStackTrace();}
        return result;
    }
}
/*    public void play(int noteOnNote00,int noteOnNote01){
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
    }*/
