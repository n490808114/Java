import javax.sound.midi.*;

public class MusicTest1{
    public void play(int noteOnNote00,int noteOnNote01){
        try{
            Sequencer player = MidiSystem.getSequencer();//got a Player
            player.open();//open it
            Sequence cd = new Sequence(Sequence.PPQ,4);//got a CD
            Track song =cd.createTrack();//create a song track(音轨) on CD

            ShortMessage message00 = new ShortMessage();//创建信息
            message00.setMessage(144, 1, 44, 100);//设置信息
            MidiEvent noteOn = new MidiEvent(message00, 1);//创建MIDI 事件

            ShortMessage message01 = new ShortMessage();//Message描述做什么
            message01.setMessage(128, 1, 44, 100);
            //128类型,1频率,44音符,100音道
            //类型：144开始，128结束
            //频道：1吉他，2Bass
            //音符：0-127表示音高
            //音道：用多大的声音按下，0几乎听不到，100差不多
            MidiEvent noteOff = new MidiEvent(message01,16);//MidiEvent指定何时做

            song.add(noteOn);//把MIDI加进歌曲中
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